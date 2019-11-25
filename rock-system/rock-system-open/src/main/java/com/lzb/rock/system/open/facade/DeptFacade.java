package com.lzb.rock.system.open.facade;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.baomidou.mybatisplus.plugins.Page;
import com.lzb.rock.base.Result;
import com.lzb.rock.system.open.common.SystemCommon;
import com.lzb.rock.system.open.dto.dept.DeptListReq;
import com.lzb.rock.system.open.model.Dept;

/**
 * 部门表 Facade
 * 
 * @author lzb
 * @Date 2019-10-24 21:51:04
 */
public interface DeptFacade {

	static String SERVICE_NAME = SystemCommon.SERVICE_NAME;
	static String context = "/dept";

	/**
	 * 获取部门表列表
	 */
	@PostMapping(value = context + "/list")
	@ResponseBody
	public Result<Page<Dept>> list(@RequestBody DeptListReq deptListReq);

	/**
	 * 获取部门表列表(无分页)
	 */
	@PostMapping(value = context + "/records")
	@ResponseBody
	public Result<List<Dept>> records(@RequestBody DeptListReq deptListReq);

	/**
	 * 新增部门表
	 */
	@PostMapping(value = context + "/add")
	@ResponseBody
	public Result<Dept> add(@RequestBody Dept dept);

	/**
	 * 删除部门表
	 */
	@PostMapping(value = context + "/delete")
	@ResponseBody
	public Result<Void> delete(@RequestParam(name = "deptId") Long deptId,
			@RequestParam(name = "lastUser", required = false) String lastUser);

	/**
	 * 修改部门表
	 */
	@PostMapping(value = context + "/update")
	@ResponseBody
	public Result<Void> update(@RequestBody Dept dept);

	/**
	 * 部门表详情
	 */
	@PostMapping(value = context + "/detail")
	@ResponseBody
	public Result<Dept> detail(@RequestParam(name = "deptId") Long deptId);

	/**
	 * 根据部门编码查询部门详细
	 */
	@PostMapping(value = context + "/selectByCode")
	@ResponseBody
	public Result<Dept> selectByCode(@RequestParam(name = "deptCode") String deptCode);
}
