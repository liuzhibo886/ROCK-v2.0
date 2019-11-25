package com.lzb.rock.system.admin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;
import com.lzb.rock.base.Result;
import com.lzb.rock.base.model.TreeNode;

@Controller
@RequestMapping("/rock/sys/testTree")
public class TestTreeController {

	/**
	 * 获取权限表列表
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public Result<List<TreeNode>> list() {

		List<TreeNode> data = new ArrayList<TreeNode>();
		TreeNode node = new TreeNode();
		node.setTitle("江西");
		node.setId(1L + "");
		List<TreeNode> data1 = new ArrayList<TreeNode>();
		node.setChildren(data1);
		data.add(node);

		TreeNode node2 = new TreeNode();
		node2.setId(2L + "");
		node2.setTitle("南昌");
		List<TreeNode> data3 = new ArrayList<TreeNode>();
		node2.setChildren(data3);
		TreeNode node3 = new TreeNode();
		node3.setId(3L + "");
		node3.setTitle("九江");
		TreeNode node4 = new TreeNode();
		node4.setId(4L + "");
		node4.setTitle("赣州");
		data1.add(node2);
		data1.add(node3);
		data1.add(node4);

		TreeNode node5 = new TreeNode();
		node5.setId(5L + "");
		node5.setTitle("青山湖区");

		TreeNode node6 = new TreeNode();
		node6.setId(6L + "");
		node6.setTitle("高新区");
		data3.add(node5);
		data3.add(node6);
		// ---------------
		TreeNode node7 = new TreeNode();
		node7.setId(7L + "");
		node7.setTitle("广西");
		List<TreeNode> data4 = new ArrayList<TreeNode>();
		node7.setChildren(data4);
		data.add(node7);

		TreeNode node8 = new TreeNode();
		node8.setId(8L + "");
		node8.setTitle("南宁");

		TreeNode node9 = new TreeNode();
		node9.setId(9L + "");
		node9.setTitle("桂林");
		data4.add(node8);
		data4.add(node9);
		// ----------------------

		TreeNode node10 = new TreeNode();
		node10.setId(7L + "");
		node10.setTitle("广西");
		List<TreeNode> data5 = new ArrayList<TreeNode>();
		node10.setChildren(data5);
		data.add(node10);
		TreeNode node11 = new TreeNode();
		node11.setId(11L + "");
		node11.setTitle("西安");
		data5.add(node11);
		return new Result<List<TreeNode>>(data);

	}

}
