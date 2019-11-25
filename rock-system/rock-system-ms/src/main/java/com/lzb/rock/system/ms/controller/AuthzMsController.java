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
import com.lzb.rock.base.common.ResultEnum;
import com.lzb.rock.base.aop.annotation.Log;
import com.lzb.rock.base.factory.PageFactory;
import com.lzb.rock.system.ms.service.IAuthzService;
import com.lzb.rock.system.open.dto.authz.AuthzListReq;
import com.lzb.rock.system.open.facade.AuthzFacade;
import com.lzb.rock.system.open.model.Authz;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 权限表控制器
 *
 * @author lzb
 * @Date 2019-10-31 21:25:57
 */
@RestController
@Api(tags={"权限表控制器"})
public class AuthzMsController implements AuthzFacade{

	@Autowired
    IAuthzService authzService;
    /**
	* 获取权限表列表
     */
   	@PostMapping(value = context+"/list")
    @ResponseBody
    @Log(before=true,end=false,name="集合查询")
    @ApiOperation(value = "获取权限表列表")
    public Result<Page<Authz>> list(@RequestBody AuthzListReq authzListReq){
    	Page<Authz> page = new PageFactory<Authz>().defaultPage(authzListReq);
    	Wrapper<Authz> wrapper = Condition.create();
		Page<Authz> rs = authzService.selectPage(page,wrapper);
		return  new Result<Page<Authz>>(rs);
    }
        /**
	* 获取权限表列表(无分页)
     */
   	@PostMapping(value = context+"/records")
    @ResponseBody
    @Log(before=true,end=false,name="集合查询")
    @ApiOperation(value = "获取权限表列表")
    public Result<List<Authz>> records(@RequestBody AuthzListReq authzListReq){
    	Wrapper<Authz> wrapper = Condition.create();
 		wrapper.last("limit "+authzListReq.getOffset()+","+authzListReq.getLimit());
		List<Authz> rows = authzService.selectList(wrapper);
		return  new Result<List<Authz>>(rows);
    }
    /**
	* 新增权限表
     */
    @PostMapping(value = context+"/add")
    @ResponseBody
    @Log(before=true,end=true,name="新增")
    @ApiOperation(value = "新增权限表")
    public Result<Authz> add(@RequestBody Authz authz){
   		  authz.setIsDel(0);
          authzService.insert(authz);
        return new Result<Authz>(authz);
    }
    /**
	 * 删除权限表
     */
    @PostMapping(value = context+"/delete")
    @ResponseBody
    @ApiOperation(value = "删除权限表")
	@ApiImplicitParams({ @ApiImplicitParam(name = "authzId", value = "权限ID"),
		@ApiImplicitParam(name = "lastUser", value = "最后修改人") })
    public Result<Void> delete(@RequestParam(name="authzId") Long authzId,@RequestParam(name="lastUser",required=false)String lastUser) {
   		 Wrapper<Authz> wrapper=Condition.create();
    	Authz entity = new Authz();
		entity.setIsDel(1);
		entity.setLastUser(lastUser);
		wrapper.eq("authz_id", authzId);
		Integer count = authzService.update(entity,wrapper);
       	if(count > 0) {
			return new Result<Void>();
		}else {
			return new Result<Void>(ResultEnum.DELETE_ERR);
		}
    }
    /**
	* 修改权限表
     */
    @PostMapping(value = context+"/update")
    @ResponseBody
    @Log(before=true,end=true,name="修改")
    @ApiOperation(value = "修改权限表")
    public Result<Void> update(@RequestBody Authz authz) {
   		Wrapper<Authz> wrapper=Condition.create();
   		wrapper.eq("authz_id", authz.getAuthzId());
   		authz.setAuthzId(null);
      	Integer count=authzService.update(authz,wrapper);
        if(count > 0) {
			return new Result<Void>();
		}else {
			return new Result<Void>(ResultEnum.UPDATE_ERR);
		}
    }
    /**
     * 权限表详情
     */
    @PostMapping(value = context+"/detail")
    @ResponseBody
    @Log(before=true,end=true,name="详情")
    @ApiOperation(value = "权限表详情,根据authzId查询")
   	@ApiImplicitParams({ @ApiImplicitParam(name = "authzId", value = "权限ID")
	 })
    public Result<Authz> detail(@RequestParam(name="authzId") Long authzId) {
    	Wrapper<Authz> wrapper=Condition.create();
		wrapper.eq("authz_id", authzId);
     	Authz authz=authzService.selectOne(wrapper);
     	
        return new Result<Authz>(authz);
    }
}
