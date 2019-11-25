package com.lzb.rock.system.ms.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.lzb.rock.base.Result;
import com.lzb.rock.base.aop.annotation.Log;
import com.lzb.rock.base.common.ResultEnum;
import com.lzb.rock.base.factory.PageFactory;
import com.lzb.rock.system.ms.service.IUserService;
import com.lzb.rock.system.open.dto.user.UserListReq;
import com.lzb.rock.system.open.facade.UserFacade;
import com.lzb.rock.system.open.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 管理员表控制器
 *
 * @author lzb
 * @Date 2019-09-15 17:39:25
 */
@RestController
@Api(tags={"管理员表控制器"})
public class UserMsController implements UserFacade{

	@Autowired
    IUserService userService;
    /**
	* 获取管理员表列表
     */
   	@PostMapping(value = context+"/list")
    @ResponseBody
    @Log(before=true,end=false,name="集合查询")
    @ApiOperation(value = "获取管理员表列表")
    public Result<Page<User>> list(@RequestBody UserListReq userListReq){
    	Page<User> page = new PageFactory<User>().defaultPage(userListReq);
    	Wrapper<User> wrapper = Condition.create();
 		wrapper.eq("is_del", 0);
		Page<User> rs = userService.selectPage(page,wrapper);
		return  new Result<Page<User>>(rs);
    }
        /**
	* 获取管理员表列表(无分页)
     */
   	@PostMapping(value = context+"/records")
    @ResponseBody
    @Log(before=true,end=false,name="集合查询")
    @ApiOperation(value = "获取管理员表列表")
    public Result<List<User>> records(@RequestBody UserListReq userListReq){
    	Wrapper<User> wrapper = Condition.create();
 		wrapper.eq("is_del", 0);
 		wrapper.last("limit "+userListReq.getOffset()+","+userListReq.getLimit());
		List<User> rows = userService.selectList(wrapper);
		return  new Result<List<User>>(rows);
    }
    /**
	* 新增管理员表
     */
    @PostMapping(value = context+"/add")
    @ResponseBody
    @Log(before=true,end=true,name="新增")
    @ApiOperation(value = "新增管理员表")
    public Result<User> add(@RequestBody User user){
   		  user.setIsDel(0);
          userService.insert(user);
        return new Result<User>(user);
    }
    /**
	 * 删除管理员表
     */
    @PostMapping(value = context+"/delete")
    @ResponseBody
    @ApiOperation(value = "删除管理员表")
    public Result<Void> delete(@RequestParam(name="userId") Long userId,@RequestParam(name="lastUser",required=false)String lastUser) {
   		 Wrapper<User> wrapper=Condition.create();
    	User entity = new User();
		entity.setIsDel(1);
		entity.setLastUser(lastUser);
		wrapper.eq("user_id", userId);
		Integer count = userService.update(entity,wrapper);
       	if(count > 0) {
			return new Result<Void>();
		}else {
			return new Result<Void>(ResultEnum.DELETE_ERR);
		}
    }
    /**
	* 修改管理员表
     */
    @PostMapping(value = context+"/update")
    @ResponseBody
    @Log(before=true,end=true,name="修改")
    @ApiOperation(value = "修改管理员表")
    public Result<Void> update(@RequestBody User user) {
   		Wrapper<User> wrapper=Condition.create();
   		wrapper.eq("user_id", user.getUserId());
   		user.setUserId(null);
   		wrapper.eq("is_del", 0);
      	Integer count=userService.update(user,wrapper);
        if(count > 0) {
			return new Result<Void>();
		}else {
			return new Result<Void>(ResultEnum.UPDATE_ERR);
		}
    }
    /**
     * 管理员表详情
     */
    @PostMapping(value = context+"/detail")
    @ResponseBody
    @Log(before=true,end=true,name="详情")
    @ApiOperation(value = "管理员表详情,根据userId查询")
    public Result<User> detail(@RequestParam(name="userId") Long userId) {
    	Wrapper<User> wrapper=Condition.create();
		wrapper.eq("user_id", userId);
   		wrapper.eq("is_del", 0);
     	User user=userService.selectOne(wrapper);
     	
        return new Result<User>(user);
    }
}
