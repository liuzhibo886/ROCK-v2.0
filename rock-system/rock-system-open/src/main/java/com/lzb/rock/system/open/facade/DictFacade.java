package com.lzb.rock.system.open.facade;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.baomidou.mybatisplus.plugins.Page;
import com.lzb.rock.base.Result;
import com.lzb.rock.system.open.common.SystemCommon;
import com.lzb.rock.system.open.dto.dict.DictListReq;
import com.lzb.rock.system.open.model.Dict;

/**
 * 系统字典表(全局表) Facade
 * 
 * @author lzb
 * @Date 2019-09-27 14:15:30
 */
public interface DictFacade {

	static String SERVICE_NAME = SystemCommon.SERVICE_NAME;
	static String context = "/dict";

	/**
	 * 获取系统字典表(全局表)列表
	 */
	@PostMapping(value = context + "/list")
	@ResponseBody
	public Result<Page<Dict>> list(@RequestBody DictListReq dictListReq);

	/**
	 * 获取系统字典表(全局表)列表(无分页)
	 */
	@PostMapping(value = context + "/records")
	@ResponseBody
	public Result<List<Dict>> records(@RequestBody DictListReq dictListReq);

	/**
	 * 新增系统字典表(全局表)
	 */
	@PostMapping(value = context + "/add")
	@ResponseBody
	public Result<Dict> add(@RequestBody Dict dict);

	/**
	 * 删除系统字典表(全局表)
	 */
	@PostMapping(value = context + "/delete")
	@ResponseBody
	public Result<Void> delete(@RequestParam(name = "dictId") Long dictId,
			@RequestParam(name = "lastUser", required = false) String lastUser);

	/**
	 * 修改系统字典表(全局表)
	 */
	@PostMapping(value = context + "/update")
	@ResponseBody
	public Result<Void> update(@RequestBody Dict dict);

	/**
	 * 系统字典表(全局表)详情
	 */
	@PostMapping(value = context + "/detail")
	@ResponseBody
	public Result<Dict> detail(@RequestParam(name = "dictId") Long dictId);

	/**
	 * 获取全部字典
	 * 
	 * @param dictCode
	 * @return
	 */
	@PostMapping(value = context + "/selectAll")
	@ResponseBody
	public Result<List<Dict>> selectAll();

	/**
	 * 根据code和key查询字典对象
	 * 
	 * @param dictCode
	 * @param dictKey
	 * @return
	 */
	@PostMapping(value = context + "/selectDictByCodeByKey")
	@ResponseBody
	public Result<Dict> selectDictByCodeByKey(@RequestParam(name = "dictCode") String dictCode,
			@RequestParam(name = "dictKey") Long dictKey);

	/**
	 * 根据dictCode查询,有缓存
	 * 
	 * @param dictCode
	 * @return
	 */
	@PostMapping(value = context + "/selectCacheDictByCode")
	@ResponseBody
	public Result<List<Dict>> selectCacheDictByCode(@RequestParam(name = "dictCode") String dictCode);

}
