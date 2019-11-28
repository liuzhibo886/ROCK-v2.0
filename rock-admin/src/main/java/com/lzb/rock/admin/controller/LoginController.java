package com.lzb.rock.admin.controller;

import javax.servlet.http.HttpSession;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lzb.rock.base.aop.annotation.Log;
import com.lzb.rock.base.model.ShiroUser;
import com.lzb.rock.base.util.UtilHttpKit;
import com.lzb.rock.base.util.UtilString;
import com.lzb.rock.login.shiro.ShiroKit;

import io.swagger.annotations.ApiOperation;

@Controller
public class LoginController {

	@ApiOperation(value = "跳转登录页面")
	@RequestMapping(value = { "login" }, method = { RequestMethod.GET, RequestMethod.POST })
	@Log(before = false)
	public String login(@RequestParam(name = "userName", required = false) String userName,
			@RequestParam(name = "passWord", required = false) String passWord) {
		if (UtilString.isNotBlank(userName) && UtilString.isNotBlank(passWord)) {
			Subject subject = ShiroKit.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(userName, passWord.toCharArray());
			subject.login(token);

			ShiroUser shiroUser = ShiroKit.getUser();
			HttpSession session = UtilHttpKit.getRequest().getSession();
			session.setAttribute("shiroUser", shiroUser);
			return "/index.html";
		}

		return "/login.html";
	}

	@ApiOperation(value = "跳转index页面")
	@RequestMapping(value = { "index", "" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String index() {
		return "/index.html";
	}

	@ApiOperation(value = "跳转index页面")
	@RequestMapping(value = { "index2" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String index2() {
		return "/index2.html";
	}

	@ApiOperation(value = "跳转layui图标页面")
	@RequestMapping(value = { "fonts" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String fonts() {
		return "/fonts.html";
	}
	@ApiOperation(value = "跳转图标页面")
	@RequestMapping(value = { "fonts2" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String fonts2() {
		return "/fonts2.html";
	}
	
	@ApiOperation(value = "跳转IM页面")
	@RequestMapping(value = { "im" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String im() {
		return "/im.html";
	}
}
