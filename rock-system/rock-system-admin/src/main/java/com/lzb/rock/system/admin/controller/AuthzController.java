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
import com.lzb.rock.system.open.client.AuthzClient;
import com.lzb.rock.system.open.dto.authz.AuthzListReq;
import com.lzb.rock.system.open.model.Authz;

/**
 * 权限表控制器
 *
 * @author lzb
 * @Date 2019-10-31 21:25:57
 */
@Controller
@RequestMapping("/rock/sys/authz")
public class AuthzController{

    private String PREFIX = "/rock/sys/authz/";

    @Autowired
    private AuthzClient authzClient;

	@Autowired
	IShiro shiro;
    /**
	 * 跳转到权限表首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "authz.html";
    }

    /**
	* 跳转到添加权限表
     */
    @RequestMapping("/authz_add")
    public String authzAdd() {
        return PREFIX + "authz_add.html";
    }

    /**
 	 * 跳转到修改权限表
     */
    @RequestMapping("/authz_update")
    public String authzUpdate(@RequestParam(name="authzId") Long authzId, Model model) {
        Result<Authz> rs = authzClient.detail(authzId);
        if (rs.check()) {
			model.addAttribute("item", rs.getData());
		}else {
			throw new RockException(ResultEnum.SELECT_ERR);
		}
        return PREFIX + "authz_edit.html";
    }

    /**
	 * 获取权限表列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Result<Page<Authz>> list(@RequestBody AuthzListReq  req) {
		Result<Page<Authz>> rs = authzClient.list(req);
		return rs;
    }

    /**
	* 新增权限表
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Result<Authz> add(@RequestBody Authz authz) {
   		UtilObject.setNull(authz);
    	authz.setLastUser(UtilJson.getStr(shiro.getUser()));    
        Result<Authz> rs = authzClient.add(authz);
		return rs;
    }

    /**
	 * 删除权限表
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Result<Void> delete(@RequestParam(name="authzId") Long authzId) {
        Result<Void> rs = authzClient.delete(authzId,UtilJson.getStr(shiro.getUser()));
		return rs;
    }

    /**
	* 修改权限表
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Result<Void> update(@RequestBody Authz authz) {
   		UtilObject.setNull(authz);
        authz.setLastUser(UtilJson.getStr(shiro.getUser()));
        Result<Void> rs=authzClient.update(authz);
		return rs;
    }

    /**
	* 权限表详情
     */
    @RequestMapping(value = "/detail")
    @ResponseBody
    public Result<Authz> detail(@RequestParam(name="authzId") Long authzId) {
        Result<Authz> rs = authzClient.detail(authzId );
		return rs;
    }
}
