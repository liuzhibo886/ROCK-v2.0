package com.lzb.rock.system.open.facade;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.baomidou.mybatisplus.plugins.Page;
import com.lzb.rock.base.Result;
import com.lzb.rock.system.open.common.SystemCommon;
import com.lzb.rock.system.open.dto.user.UserListReq;
import com.lzb.rock.system.open.model.User;

/**
 * 管理员表
 * Facade
 * @author lzb
 * @Date 2019-09-15 17:19:56
 */
public interface UserFacade{

    static String SERVICE_NAME=SystemCommon.SERVICE_NAME;
    static String context="/user";
    /**
   	  * 获取管理员表列表
     */
    @PostMapping(value = context+"/list")
    @ResponseBody
    public Result<Page<User>> list(@RequestBody UserListReq userListReq);
     /**
   	  * 获取管理员表列表(无分页)
     */
    @PostMapping(value = context+"/records")
    @ResponseBody
    public Result<List<User>> records(@RequestBody UserListReq userListReq);
 
    /**
	* 新增管理员表
     */
    @PostMapping(value = context+"/add")
    @ResponseBody
    public Result<User> add(@RequestBody User user);

    /**
      * 删除管理员表
     */
    @PostMapping(value = context+"/delete")
    @ResponseBody
    public Result<Void> delete(@RequestParam(name="userId") Long userId,@RequestParam(name="lastUser",required=false)String lastUser);
    /**
     * 修改管理员表
     */
    @PostMapping(value = context+"/update")
    @ResponseBody
    public Result<Void> update(@RequestBody User user);

    /**
     * 管理员表详情
     */
    @PostMapping(value = context+"/detail")
    @ResponseBody
    public Result<User> detail(@RequestParam(name="userId") Long userId);
}
