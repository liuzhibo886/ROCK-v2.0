package com.lzb.rock.test.ms.controller;

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
import com.lzb.rock.base.util.UtilString;
import com.lzb.rock.test.ms.service.IJdUrlService;
import com.lzb.rock.test.open.dto.jdUrl.JdUrlListReq;
import com.lzb.rock.test.open.facade.JdUrlFacade;
import com.lzb.rock.test.open.model.JdUrl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 京东url列表控制器
 *
 * @author lzb
 * @Date 2019-11-12 20:52:13
 */
@RestController
@Api(tags={"京东url列表控制器"})
public class JdUrlMsController implements JdUrlFacade{

	@Autowired
    IJdUrlService jdUrlService;
    /**
	* 获取京东url列表列表
     */
   	@PostMapping(value = context+"/list")
    @ResponseBody
    @Log(before=true,end=false,name="集合查询")
    @ApiOperation(value = "获取京东url列表列表")
    public Result<Page<JdUrl>> list(@RequestBody JdUrlListReq jdUrlListReq){
    	Page<JdUrl> page = new PageFactory<JdUrl>().defaultPage(jdUrlListReq);
    	Wrapper<JdUrl> wrapper = Condition.create();
		Page<JdUrl> rs = jdUrlService.selectPage(page,wrapper);
		return  new Result<Page<JdUrl>>(rs);
    }
        /**
	* 获取京东url列表列表(无分页)
     */
   	@PostMapping(value = context+"/records")
    @ResponseBody
    @Log(before=true,end=false,name="集合查询")
    @ApiOperation(value = "获取京东url列表列表")
    public Result<List<JdUrl>> records(@RequestBody JdUrlListReq jdUrlListReq){
    	Wrapper<JdUrl> wrapper = Condition.create();
 		wrapper.last("limit "+jdUrlListReq.getOffset()+","+jdUrlListReq.getLimit());
		List<JdUrl> rows = jdUrlService.selectList(wrapper);
		return  new Result<List<JdUrl>>(rows);
    }
    /**
	* 新增京东url列表
     */
    @PostMapping(value = context+"/add")
    @ResponseBody
    @Log(before=true,end=true,name="新增")
    @ApiOperation(value = "新增京东url列表")
    public Result<JdUrl> add(@RequestBody JdUrl jdUrl){
   		  jdUrl.setIsDel(0);
          jdUrlService.insert(jdUrl);
        return new Result<JdUrl>(jdUrl);
    }
    /**
	 * 删除京东url列表
     */
    @PostMapping(value = context+"/delete")
    @ResponseBody
    @ApiOperation(value = "删除京东url列表")
	@ApiImplicitParams({ @ApiImplicitParam(name = "jdUrlId", value = "主键ID"),
		@ApiImplicitParam(name = "lastUser", value = "最后修改人") })
    public Result<Void> delete(@RequestParam(name="jdUrlId") Long jdUrlId,@RequestParam(name="lastUser",required=false)String lastUser) {
   		 Wrapper<JdUrl> wrapper=Condition.create();
    	JdUrl entity = new JdUrl();
		entity.setIsDel(1);
		entity.setLastUser(lastUser);
		wrapper.eq("jd_url_id", jdUrlId);
		Integer count = jdUrlService.update(entity,wrapper);
       	if(count > 0) {
			return new Result<Void>();
		}else {
			return new Result<Void>(ResultEnum.DELETE_ERR);
		}
    }
    /**
	* 修改京东url列表
     */
    @PostMapping(value = context+"/update")
    @ResponseBody
    @Log(before=true,end=true,name="修改")
    @ApiOperation(value = "修改京东url列表")
    public Result<Void> update(@RequestBody JdUrl jdUrl) {
   		Wrapper<JdUrl> wrapper=Condition.create();
   		wrapper.eq("jd_url_id", jdUrl.getJdUrlId());
   		jdUrl.setJdUrlId(null);
      	Integer count=jdUrlService.update(jdUrl,wrapper);
        if(count > 0) {
			return new Result<Void>();
		}else {
			return new Result<Void>(ResultEnum.UPDATE_ERR);
		}
    }
    /**
     * 京东url列表详情
     */
    @PostMapping(value = context+"/detail")
    @ResponseBody
    @Log(before=true,end=true,name="详情")
    @ApiOperation(value = "京东url列表详情,根据jdUrlId查询")
   	@ApiImplicitParams({ @ApiImplicitParam(name = "jdUrlId", value = "主键ID")
	 })
    public Result<JdUrl> detail(@RequestParam(name="jdUrlId") Long jdUrlId) {
    	Wrapper<JdUrl> wrapper=Condition.create();
		wrapper.eq("jd_url_id", jdUrlId);
     	JdUrl jdUrl=jdUrlService.selectOne(wrapper);
     	
        return new Result<JdUrl>(jdUrl);
    }
}
