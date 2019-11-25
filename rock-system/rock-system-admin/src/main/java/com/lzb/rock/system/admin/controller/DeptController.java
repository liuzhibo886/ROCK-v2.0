package com.lzb.rock.system.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.plugins.Page;
import com.lzb.rock.base.Result;
import com.lzb.rock.base.facade.IShiro;
import com.lzb.rock.base.model.SelectNode;
import com.lzb.rock.base.model.TreeNode;
import com.lzb.rock.base.common.ResultEnum;
import com.lzb.rock.base.exception.RockException;
import com.lzb.rock.base.util.UtilJson;
import com.lzb.rock.base.util.UtilObject;
import com.lzb.rock.base.util.UtilTree;
import com.lzb.rock.system.open.client.DeptClient;
import com.lzb.rock.system.open.dto.dept.DeptListReq;
import com.lzb.rock.system.open.dto.dept.DeptListResp;
import com.lzb.rock.system.open.model.Dept;

/**
 * 部门表控制器
 *
 * @author lzb
 * @Date 2019-10-26 21:00:39
 */
@Controller
@RequestMapping("/rock/sys/dept")
public class DeptController {

	private String PREFIX = "/rock/sys/dept/";

	@Autowired
	private DeptClient deptClient;

	@Autowired
	IShiro shiro;

	/**
	 * 跳转到部门表首页
	 */
	@RequestMapping("")
	public String index() {
		return PREFIX + "dept.html";
	}

	/**
	 * 跳转到添加部门表
	 */
	@RequestMapping("/dept_add")
	public String deptAdd() {
		return PREFIX + "dept_add.html";
	}

	/**
	 * 跳转到修改部门表
	 */
	@RequestMapping("/dept_update")
	public String deptUpdate(@RequestParam(name = "deptId") Long deptId, Model model) {
		Result<Dept> rs = deptClient.detail(deptId);
		if (rs.check()) {
			model.addAttribute("item", rs.getData());
		} else {
			throw new RockException(ResultEnum.SELECT_ERR);
		}
		return PREFIX + "dept_edit.html";
	}

	/**
	 * 获取部门表列表
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public Result<List<Dept>> list() {
		DeptListReq req=new DeptListReq();
		req.setLimit(9999);
		req.setPage(1);
		Result<List<Dept>> rs = deptClient.records(req);
		return rs;
	}

	/**
	 * 获取部门下拉列表
	 */
	@RequestMapping(value = "/treeNode")
	@ResponseBody
	public Result<List<TreeNode>> treeNode() {
		List<TreeNode> nodes = new ArrayList<TreeNode>();
		Integer page = 1;
		Integer limit = 1000;
		while (true) {
			DeptListReq deptListReq = new DeptListReq();
			Result<List<Dept>> rs = deptClient.records(deptListReq);
			if (rs.checkAndNotNull()) {
				for (Dept dept : rs.getData()) {
					TreeNode node = new TreeNode();
					node.setId(dept.getDeptCode() + "");
					node.setTitle(dept.getDeptFullName());
					node.setPId(dept.getDeptPcode());
					nodes.add(node);
				}
				if (rs.getData().size() < limit) {
					break;
				}
			} else {
				break;
			}
		}
		nodes=UtilTree.assembleTree(nodes);
		return new Result<List<TreeNode>>(nodes);
	}

	/**
	 * 新增部门表
	 */
	@RequestMapping(value = "/add")
	@ResponseBody
	public Result<Dept> add(@RequestBody Dept dept) {
		UtilObject.setNull(dept);
		dept.setLastUser(UtilJson.getStr(shiro.getUser()));
		Result<Dept> rs = deptClient.add(dept);
		return rs;
	}

	/**
	 * 删除部门表
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Result<Void> delete(@RequestParam(name = "deptId") Long deptId) {
		Result<Void> rs = deptClient.delete(deptId, UtilJson.getStr(shiro.getUser()));
		return rs;
	}

	/**
	 * 修改部门表
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public Result<Void> update(@RequestBody Dept dept) {
		UtilObject.setNull(dept);
		dept.setLastUser(UtilJson.getStr(shiro.getUser()));
		Result<Void> rs = deptClient.update(dept);
		return rs;
	}

	/**
	 * 部门表详情
	 */
	@RequestMapping(value = "/detail")
	@ResponseBody
	public Result<Dept> detail(@RequestParam(name = "deptId") Long deptId) {
		Result<Dept> rs = deptClient.detail(deptId);
		return rs;
	}
}
