package com.lzb.rock.system.ms.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.lzb.rock.base.Result;
import com.lzb.rock.base.common.ResultEnum;
import com.lzb.rock.base.exception.RockException;
import com.lzb.rock.base.aop.annotation.Log;
import com.lzb.rock.base.factory.PageFactory;
import com.lzb.rock.base.util.UtilString;
import com.lzb.rock.system.ms.service.IDeptService;
import com.lzb.rock.system.open.dto.dept.DeptListReq;
import com.lzb.rock.system.open.facade.DeptFacade;
import com.lzb.rock.system.open.model.Dept;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 部门表控制器
 *
 * @author lzb
 * @Date 2019-10-26 21:00:39
 */
@RestController
@Api(tags = { "部门表控制器" })
public class DeptMsController implements DeptFacade {

	@Autowired
	IDeptService deptService;

	/**
	 * 获取部门表列表
	 */
	@PostMapping(value = context + "/list")
	@ResponseBody
	@Log(before = true, end = false, name = "集合查询")
	@ApiOperation(value = "获取部门表列表")
	public Result<Page<Dept>> list(@RequestBody DeptListReq deptListReq) {
		Page<Dept> page = new PageFactory<Dept>().defaultPage(deptListReq);
		Wrapper<Dept> wrapper = Condition.create();
		Page<Dept> rs = deptService.selectPage(page, wrapper);
		return new Result<Page<Dept>>(rs);
	}

	/**
	 * 获取部门表列表(无分页)
	 */
	@PostMapping(value = context + "/records")
	@ResponseBody
	@Log(before = true, end = false, name = "集合查询")
	@ApiOperation(value = "获取部门表列表")
	public Result<List<Dept>> records(@RequestBody DeptListReq deptListReq) {
		Wrapper<Dept> wrapper = Condition.create();
		wrapper.last("limit " + deptListReq.getOffset() + "," + deptListReq.getLimit());
		List<Dept> rows = deptService.selectList(wrapper);
		return new Result<List<Dept>>(rows);
	}

	/**
	 * 新增部门表
	 */
	@PostMapping(value = context + "/add")
	@ResponseBody
	@Log(before = true, end = true, name = "新增")
	@ApiOperation(value = "新增部门表")
	public Result<Dept> add(@RequestBody @Valid Dept dept) {

		// 判断编码不能重复
		Wrapper<Dept> wrapper = Condition.create();
		wrapper.eq("dept_code", dept.getDeptCode());
		Integer count = deptService.selectCount(wrapper);
		if (count > 0) {
			throw new RockException(ResultEnum.PAEAM_ERR, "字典编码已存在");
		}
		dept.setIsDel(0);
		deptService.insert(dept);
		return new Result<Dept>(dept);
	}

	/**
	 * 删除部门表
	 */
	@PostMapping(value = context + "/delete")
	@ResponseBody
	@ApiOperation(value = "删除部门表")
	@ApiImplicitParams({ @ApiImplicitParam(name = "deptId", value = "主键id"),
			@ApiImplicitParam(name = "lastUser", value = "最后修改人") })
	public Result<Void> delete(@RequestParam(name = "deptId") Long deptId,
			@RequestParam(name = "lastUser", required = false) String lastUser) {
		Wrapper<Dept> wrapper = Condition.create();
		Dept entity = new Dept();
		entity.setIsDel(1);
		entity.setLastUser(lastUser);
		wrapper.eq("dept_id", deptId);
		Integer count = deptService.update(entity, wrapper);
		if (count > 0) {
			return new Result<Void>();
		} else {
			return new Result<Void>(ResultEnum.DELETE_ERR);
		}
	}

	/**
	 * 修改部门表
	 */
	@PostMapping(value = context + "/update")
	@ResponseBody
	@Log(before = true, end = true, name = "修改")
	@ApiOperation(value = "修改部门表")
	@Transactional
	public Result<Void> update(@RequestBody Dept dept) {
		// 修改部门编码的时候，子节点编码一起修改
		if (UtilString.isNotBlank(dept.getDeptCode())) {
			// 查找编码是否重复
			Wrapper<Dept> wrapper = Condition.create();
			wrapper.eq("dept_code", dept.getDeptCode());
			Dept oldDept = deptService.selectOne(wrapper);
			if (oldDept == null) {
				// 根据code查询不到数据，说明修改了字典编码,且字典编码不重复，修改子节点字典编码
				// 查找子节点
				Wrapper<Dept> wrapper2 = Condition.create();
				wrapper2.eq("dept_pcode", dept.getDeptCode());
				List<Dept> list = deptService.selectList(wrapper2);
				for (Dept dept2 : list) {
					dept2.setDeptPcode(dept.getDeptCode());
					deptService.updateById(dept2);
				}
			} else if (oldDept.getDeptId() != dept.getDeptId()) {
				throw new RockException(ResultEnum.PAEAM_ERR, "字典编码已存在");
			}
		}
		Wrapper<Dept> wrapper = Condition.create();
		wrapper.eq("dept_id", dept.getDeptId());
		dept.setDeptId(null);
		Integer count = deptService.update(dept, wrapper);
		if (count > 0) {
			return new Result<Void>();
		} else {
			return new Result<Void>(ResultEnum.UPDATE_ERR);
		}
	}

	/**
	 * 部门表详情
	 */
	@PostMapping(value = context + "/detail")
	@ResponseBody
	@Log(before = true, end = true, name = "详情")
	@ApiOperation(value = "部门表详情,根据deptId查询")
	@ApiImplicitParams({ @ApiImplicitParam(name = "deptId", value = "主键id") })
	public Result<Dept> detail(@RequestParam(name = "deptId") Long deptId) {
		Wrapper<Dept> wrapper = Condition.create();
		wrapper.eq("dept_id", deptId);
		Dept dept = deptService.selectOne(wrapper);

		return new Result<Dept>(dept);
	}

	/**
	 * 根据部门编码查询部门详细
	 */
	@PostMapping(value = context + "/selectByCode")
	@ResponseBody
	public Result<Dept> selectByCode(@RequestParam(name = "deptCode") String deptCode) {
		Wrapper<Dept> wrapper = Condition.create();
		wrapper.eq("dept_code", deptCode);
		Dept dept = deptService.selectOne(wrapper);
		return new Result<Dept>(dept);
	}
}
