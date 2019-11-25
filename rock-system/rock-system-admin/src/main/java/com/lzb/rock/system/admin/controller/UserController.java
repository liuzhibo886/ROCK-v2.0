package com.lzb.rock.system.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.baomidou.mybatisplus.plugins.Page;
import com.lzb.rock.base.Result;
import com.lzb.rock.base.facade.IShiro;
import com.lzb.rock.base.common.ResultEnum;
import com.lzb.rock.base.exception.RockException;
import com.lzb.rock.base.util.UtilJson;
import com.lzb.rock.base.util.UtilObject;
import com.lzb.rock.system.open.client.UserClient;
import com.lzb.rock.system.open.dto.user.UserListReq;
import com.lzb.rock.system.open.model.User;

/**
 * 管理员表控制器
 *
 * @author lzb
 * @Date 2019-09-26 16:46:07
 */
@Controller
@RequestMapping("/rock/sys/user")
public class UserController {

	private String PREFIX = "/rock/sys/user/";

	@Autowired
	private UserClient userClient;

	@Autowired
	IShiro shiro;

	/**
	 * 跳转到管理员表首页
	 */
	@RequestMapping("")
	public String index() {
		return PREFIX + "user.html";
	}

	/**
	 * 跳转到添加管理员表
	 */
	@RequestMapping("/user_add")
	public String userAdd() {
		return PREFIX + "user_add.html";
	}

	/**
	 * 跳转到修改管理员表
	 */
	@RequestMapping("/user_update")
	public String userUpdate(@RequestParam(name = "userId") Long userId, Model model) {
		Result<User> rs = userClient.detail(userId);
		if (rs.check()) {
			model.addAttribute("item", rs.getData());
		} else {
			throw new RockException(ResultEnum.SELECT_ERR);
		}
		return PREFIX + "user_edit.html";
	}

	/**
	 * 获取管理员表列表
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public Result<Page<User>> list(@RequestBody UserListReq req) {
		Result<Page<User>> rs = userClient.list(req);
		return rs;
	}

	/**
	 * 新增管理员表
	 */
	@RequestMapping(value = "/add")
	@ResponseBody
	public Result<User> add(@RequestBody User user) {
		UtilObject.setNull(user);
		user.setLastUser(UtilJson.getStr(shiro.getUser()));
		Result<User> rs = userClient.add(user);
		return rs;
	}

	/**
	 * 删除管理员表
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Result<Void> delete(@RequestParam(name = "userId") Long userId) {
		Result<Void> rs = userClient.delete(userId, UtilJson.getStr(shiro.getUser()));
		return rs;
	}

	/**
	 * 修改管理员表
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public Result<Void> update(@RequestBody User user) {
		UtilObject.setNull(user);
		user.setLastUser(UtilJson.getStr(shiro.getUser()));
		Result<Void> rs = userClient.update(user);
		return rs;
	}

	/**
	 * 管理员表详情
	 */
	@RequestMapping(value = "/detail")
	@ResponseBody
	public Result<User> detail(@RequestParam(name = "userId") Long userId) {
		Result<User> rs = userClient.detail(userId);
		return rs;
	}
}
