package com.lzb.rock.system.ms.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.lzb.rock.base.Result;
import com.lzb.rock.base.aop.annotation.Log;
import com.lzb.rock.base.common.ResultEnum;
import com.lzb.rock.base.exception.RockException;
import com.lzb.rock.base.factory.PageFactory;
import com.lzb.rock.base.util.UtilJson;
import com.lzb.rock.base.util.UtilString;
import com.lzb.rock.system.ms.service.IDictService;
import com.lzb.rock.system.open.dto.dict.DictListReq;
import com.lzb.rock.system.open.facade.DictFacade;
import com.lzb.rock.system.open.model.Dict;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 系统字典表(全局表)控制器
 *
 * @author lzb
 * @Date 2019-09-27 14:21:23
 */
@RestController
@Api(tags = { "系统字典表(全局表)控制器" })
public class DictMsController implements DictFacade {

	@Autowired
	IDictService dictService;

	/**
	 * 获取系统字典表(全局表)列表
	 */
	@PostMapping(value = context + "/list")
	@ResponseBody
	@Log(before = true, end = false, name = "集合查询")
	@ApiOperation(value = "获取系统字典表(全局表)列表")
	public Result<Page<Dict>> list(@RequestBody DictListReq dictListReq) {
		Page<Dict> page = new PageFactory<Dict>().defaultPage(dictListReq);
		Wrapper<Dict> wrapper = Condition.create();
		if (UtilString.isNotBlank(dictListReq.getDictCode())) {
			wrapper.eq("dict_code", dictListReq.getDictCode());
		}
		if (dictListReq.getDictPkey() != null) {
			wrapper.eq("dict_pkey", dictListReq.getDictPkey());
		}
		// wrapper.eq("is_del", 0);
		wrapper.orderBy("sort", true);
		Page<Dict> rs = dictService.selectPage(page, wrapper);
		return new Result<Page<Dict>>(rs);
	}

	/**
	 * 获取系统字典表(全局表)列表(无分页)
	 */
	@PostMapping(value = context + "/records")
	@ResponseBody
	@Log(before = true, end = false, name = "集合查询")
	@ApiOperation(value = "获取系统字典表(全局表)列表")
	public Result<List<Dict>> records(@RequestBody DictListReq dictListReq) {
		Wrapper<Dict> wrapper = Condition.create();
		wrapper.orderBy("sort", true);
		wrapper.last("limit " + dictListReq.getOffset() + "," + dictListReq.getLimit());
		List<Dict> rows = dictService.selectList(wrapper);
		return new Result<List<Dict>>(rows);
	}

	/**
	 * 新增系统字典表(全局表)
	 */
	@PostMapping(value = context + "/add")
	@ResponseBody
	@Log(before = true, end = true, name = "新增")
	@ApiOperation(value = "新增系统字典表(全局表)")
	public Result<Dict> add(@RequestBody @Valid Dict dict) {
		/**
		 * 顶级节点
		 */
		if (dict.getDictPkey() == -2) {
			dict.setDictLevel(0);
			dict.setDictKey(-1L);
			if (dict.getDictMaxLevel() == null || dict.getDictMaxLevel() < 1) {
				throw new RockException(ResultEnum.PAEAM_ERR, "最大等级异常");
			}
			// 判断字典编码是否重复
			Wrapper<Dict> wrapper = Condition.create();
			wrapper.eq("dict_code", dict.getDictCode());
			Integer count = dictService.selectCount(wrapper);
			if (count > 0) {
				throw new RockException(ResultEnum.DATA_ERR, "字典编码已经存在;code:" + dict.getDictCode());
			}
		} else {
			// key 没有 默认获取最大key加一
			if (dict.getDictKey() == null) {
				Wrapper<Dict> wrapper2 = Condition.create();
				wrapper2.eq("dict_code", dict.getDictCode());
				wrapper2.orderBy("dict_key", false);
				wrapper2.last(" limit 1");
				Dict dictKeyMaxObj = dictService.selectOne(wrapper2);
				if (dictKeyMaxObj.getDictKey() == null) {
					throw new RockException(ResultEnum.DATA_ERR, "字典数据异常：" + UtilJson.getStr(dictKeyMaxObj));
				}
				Long KeyMax = dictKeyMaxObj.getDictKey() + 1;
				dict.setDictKey(KeyMax);
			} else {
				// 判断key是否重复
				Wrapper<Dict> wrapper = Condition.create();
				wrapper.eq("dict_code", dict.getDictCode());
				wrapper.eq("dict_key", dict.getDictKey());
				Integer count = dictService.selectCount(wrapper);
				if (count > 0) {
					throw new RockException(ResultEnum.DATA_ERR,
							"字典key存在;code:" + dict.getDictCode() + ";key:" + dict.getDictKey());
				}
			}
			// 查询父节点会员级别
			Wrapper<Dict> wrapper = Condition.create();
			wrapper.eq("dict_key", dict.getDictPkey());
			wrapper.eq("dict_code", dict.getDictCode());
			Dict pDict = dictService.selectOne(wrapper);
			// 最大等级跟父节点保持一致
			dict.setDictMaxLevel(pDict.getDictMaxLevel());
			// 当前等级为父节点等级+1，需判断是否达到最大等级
			if (pDict.getDictLevel() >= pDict.getDictMaxLevel()) {
				throw new RockException(ResultEnum.DATA_ERR,
						"字典等级已经达到最大值key=" + dict.getDictKey() + ";code=" + dict.getDictCode());
			}
			dict.setDictLevel(pDict.getDictLevel() + 1);
		}
		dictService.insert(dict);
		return new Result<Dict>(dict);
	}

	/**
	 * 删除系统字典表(全局表)
	 */
	@PostMapping(value = context + "/delete")
	@ResponseBody
	@ApiOperation(value = "删除系统字典表(全局表)")
	@ApiImplicitParams({ @ApiImplicitParam(name = "dictId", value = "字典ID"),
			@ApiImplicitParam(name = "lastUser", value = "最后修改人") })
	public Result<Void> delete(@RequestParam(name = "dictId") Long dictId,
			@RequestParam(name = "lastUser", required = false) String lastUser) {
		Wrapper<Dict> wrapper = Condition.create();
		Dict entity = new Dict();
		entity.setIsDel(1);
		entity.setLastUser(lastUser);
		wrapper.eq("dict_id", dictId);
		Integer count = dictService.update(entity, wrapper);
		if (count > 0) {
			return new Result<Void>();
		} else {
			return new Result<Void>(ResultEnum.DELETE_ERR);
		}
	}

	/**
	 * 修改系统字典表(全局表)
	 */
	@PostMapping(value = context + "/update")
	@ResponseBody
	@Log(before = true, end = true, name = "修改")
	@ApiOperation(value = "修改系统字典表(全局表)")
	public Result<Void> update(@RequestBody Dict dict) {
		// 只允许修改value 和text，其余修改后期做特殊处理
		Dict dictNew = new Dict();
		dictNew.setDictValue(dict.getDictValue());
		dictNew.setDictText(dict.getDictText());
		dictNew.setLastUser(dict.getLastUser());

		Wrapper<Dict> wrapper = Condition.create();
		wrapper.eq("dict_id", dict.getDictId());
		dict.setDictId(null);
		Integer count = dictService.update(dictNew, wrapper);
		if (count > 0) {
			return new Result<Void>();
		} else {
			return new Result<Void>(ResultEnum.UPDATE_ERR);
		}
	}

	/**
	 * 系统字典表(全局表)详情
	 */
	@PostMapping(value = context + "/detail")
	@ResponseBody
	@Log(before = true, end = true, name = "详情")
	@ApiOperation(value = "系统字典表(全局表)详情,根据dictId查询")
	@ApiImplicitParams({ @ApiImplicitParam(name = "dictId", value = "主键ID") })
	public Result<Dict> detail(@RequestParam(name = "dictId") Long dictId) {
		Wrapper<Dict> wrapper = Condition.create();
		wrapper.eq("dict_id", dictId);
		Dict dict = dictService.selectOne(wrapper);

		return new Result<Dict>(dict);
	}

	/**
	 * 获取全部字典
	 * 
	 * @param dictCode
	 * @return
	 */
	@PostMapping(value = context + "/selectAll")
	@ResponseBody
	@Log(end = false)
	@ApiOperation(value = "获取系统字典表全部数据")
	public Result<List<Dict>> selectAll() {

		List<Dict> rows = dictService.selectAll();
		return new Result<List<Dict>>(rows);
	}

	/**
	 * 根据code和key查询字典对象
	 * 
	 * @param dictCode
	 * @param dictKey
	 * @return
	 */
	@PostMapping(value = context + "/selectDictByCodeByKey")
	@ResponseBody
	@ApiOperation(value = "根据code和key查询字典对象")
	@Log(end = false)
	@ApiImplicitParams({ @ApiImplicitParam(name = "dictCode", value = "字典编码"),
			@ApiImplicitParam(name = "dictKey", value = "字典key值") })
	public Result<Dict> selectDictByCodeByKey(@RequestParam(name = "dictCode") String dictCode,
			@RequestParam(name = "dictKey") Long dictKey) {
		Wrapper<Dict> wrapper = Condition.create();
		wrapper.eq("dict_code", dictCode);
		wrapper.eq("dict_key", dictKey);
		Dict dict = dictService.selectOne(wrapper);

		return new Result<Dict>(dict);
	}

	/**
	 * 根据dictCode查询,有缓存
	 * 
	 * @param dictCode
	 * @return
	 */
	@PostMapping(value = context + "/selectCacheDictByCode")
	@ResponseBody
	@ApiOperation(value = "根据code查询字典（有缓存）")
	@ApiImplicitParams({ @ApiImplicitParam(name = "dictCode", value = "字典编码") })
	@Override
	public Result<List<Dict>> selectCacheDictByCode(@RequestParam(name = "dictCode") String dictCode) {
		List<Dict> rows = dictService.getCacheDictsByCode(dictCode);
		return new Result<List<Dict>>(rows);
	}

}
