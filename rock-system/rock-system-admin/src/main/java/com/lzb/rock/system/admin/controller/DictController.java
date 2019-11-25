package com.lzb.rock.system.admin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;
import com.lzb.rock.base.Result;
import com.lzb.rock.base.common.ResultEnum;
import com.lzb.rock.base.exception.RockException;
import com.lzb.rock.base.facade.IShiro;
import com.lzb.rock.base.model.TreeNode;
import com.lzb.rock.base.util.UtilJson;
import com.lzb.rock.base.util.UtilObject;
import com.lzb.rock.base.util.UtilString;
import com.lzb.rock.base.util.UtilTree;
import com.lzb.rock.system.open.client.DictClient;
import com.lzb.rock.system.open.common.SysEnum;
import com.lzb.rock.system.open.dto.dict.DictListReq;
import com.lzb.rock.system.open.model.Dict;

/**
 * 系统字典表(全局表)控制器
 *
 * @author lzb
 * @Date 2019-09-27 14:21:23
 */
@Controller
@RequestMapping("/rock/sys/dict")
public class DictController {

	private String PREFIX = "/rock/sys/dict/";

	@Autowired
	private DictClient dictClient;

	@Autowired
	IShiro shiro;

	/**
	 * 跳转到系统字典表(全局表)首页
	 */
	@RequestMapping("")
	public String index() {
		return PREFIX + "dict.html";
	}

	/**
	 * 跳转到添加系统字典表(全局表)
	 */
	@RequestMapping("/dict_add")
	public String dictAdd(@RequestParam(name = "dictKey") Long dictKey,
			@RequestParam(name = "dictCode") String dictCode, Model model) {

		Result<Dict> rs = dictClient.selectDictByCodeByKey(dictCode, dictKey);
		if (rs.checkAndNotNull()) {
			Dict dict = rs.getData();
			// 判断是否已经达到最大等级
			if (dict.getDictLevel() >= dict.getDictMaxLevel()) {
				throw new RockException(SysEnum.DICT_LEVEL_MAX_ERR);
			}
			model.addAttribute("pDict", dict);
		} else if (UtilString.isBlank(dictCode)) {
			Dict dict = new Dict();
			dict.setDictKey(-2L);
			dict.setDictValue("顶级");
			model.addAttribute("pDict", dict);
		}

		return PREFIX + "dict_add.html";
	}

	/**
	 * 跳转到修改系统字典表(全局表)
	 */
	@RequestMapping("/dict_update")
	public String dictUpdate(@RequestParam(name = "dictId") Long dictId, Model model) {
		Result<Dict> rs = dictClient.detail(dictId);
		if (rs.check()) {
			if (rs.getData().getDictKey() == -1L) {
				Dict pdict = new Dict();
				pdict.setDictValue("顶级");
				model.addAttribute("pDict", pdict);
			} else {
				Result<Dict> pRs = dictClient.selectDictByCodeByKey(rs.getData().getDictCode(),
						rs.getData().getDictPkey());
				if (pRs.check()) {
					model.addAttribute("pDict", pRs.getData());
				}
			}

			model.addAttribute("item", rs.getData());
		} else {
			throw new RockException(ResultEnum.SELECT_ERR);
		}
		return PREFIX + "dict_edit.html";
	}

	/**
	 * 获取系统字典表(全局表)列表
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public Result<Page<Dict>> list(@RequestBody DictListReq req) {
		Result<Page<Dict>> rs = dictClient.list(req);
		return rs;
	}

	/**
	 * 新增系统字典表(全局表)
	 */
	@RequestMapping(value = "/add")
	@ResponseBody
	public Result<Dict> add(@RequestBody Dict dict) {
		UtilObject.setNull(dict);
		dict.setLastUser(UtilJson.getStr(shiro.getUser()));
		Result<Dict> rs = dictClient.add(dict);
		return rs;
	}

	/**
	 * 删除系统字典表(全局表)
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Result<Void> delete(@RequestParam(name = "dictId") Long dictId) {
		Result<Void> rs = dictClient.delete(dictId, UtilJson.getStr(shiro.getUser()));
		return rs;
	}

	/**
	 * 修改系统字典表(全局表)
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public Result<Void> update(@RequestBody Dict dict) {
		UtilObject.setNull(dict);
		dict.setLastUser(UtilJson.getStr(shiro.getUser()));
		Result<Void> rs = dictClient.update(dict);
		return rs;
	}

	/**
	 * 系统字典表(全局表)详情
	 */
	@RequestMapping(value = "/detail")
	@ResponseBody
	public Result<Dict> detail(@RequestParam(name = "dictId") Long dictId) {
		Result<Dict> rs = dictClient.detail(dictId);
		return rs;
	}

	/**
	 * 获取字典树数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getTree")
	@ResponseBody
	public Result<List<TreeNode>> getTree() {
		List<TreeNode> tree = new ArrayList<TreeNode>();
		List<TreeNode> childrens = new ArrayList<TreeNode>();

		Result<List<Dict>> rows = dictClient.selectAll();
		if (rows.checkAndNotNull()) {
			for (Dict dict : rows.getData()) {
				TreeNode treeNode = new TreeNode();
				treeNode.setId(dict.getDictKey() + "");
				treeNode.setTitle(dict.getDictValue());
				treeNode.setPId(dict.getDictPkey() + "");
				treeNode.setSort(dict.getSort());
				treeNode.setCode(dict.getDictCode());
				childrens.add(treeNode);
			}
		}
		childrens = UtilTree.assembleTree(childrens);
		TreeNode rootNode = new TreeNode();
		rootNode.setTitle("全部");
		rootNode.setId(-2L + "");
		rootNode.setChildren(childrens);
		rootNode.setSpread(true);
		tree.add(rootNode);
		return new Result<List<TreeNode>>(tree);
	}

	/**
	 * 获取字典树数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getTreeByCode")
	@ResponseBody
	public Result<List<TreeNode>> getTreeByCode(@RequestParam(name = "dictCode") String dictCode) {
		List<TreeNode> tree = new ArrayList<TreeNode>();
		Result<List<Dict>> rows = dictClient.selectCacheDictByCode(dictCode);
		if (rows.checkAndNotNull()) {
			for (Dict dict : rows.getData()) {
				TreeNode treeNode = new TreeNode();
				treeNode.setId(dict.getDictKey() + "");
				treeNode.setTitle(dict.getDictValue());
				treeNode.setPId(dict.getDictPkey() + "");
				treeNode.setSort(dict.getSort());
				treeNode.setCode(dict.getDictCode());
				tree.add(treeNode);
			}
			tree = UtilTree.assembleTree(tree);
		}
		return new Result<List<TreeNode>>(tree);
	}
}
