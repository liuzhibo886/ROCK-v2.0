package com.lzb.rock.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lzb.rock.base.properties.RockProperties;

import io.swagger.annotations.ApiOperation;

@Controller
public class JsController {

	@ApiOperation(value = "JS跳转接口")
	@RequestMapping(value = { "/view/**" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String login(HttpServletRequest request, HttpServletResponse resp) {
		String path = request.getRequestURI();
		resp.setHeader("Content-Type", "application/javascript;charset=UTF-8");
		path = path.replace("/view", "");
		return path;
	}

}
