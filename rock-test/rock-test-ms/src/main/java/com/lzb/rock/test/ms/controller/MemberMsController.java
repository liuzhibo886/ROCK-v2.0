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
import com.lzb.rock.test.ms.service.IMemberService;
import com.lzb.rock.test.open.dto.member.MemberListReq;
import com.lzb.rock.test.open.facade.MemberFacade;
import com.lzb.rock.test.open.model.Member;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 控制器
 *
 * @author lzb
 * @Date 2019-11-12 20:52:14
 */
@RestController
@Api(tags={"控制器"})
public class MemberMsController implements MemberFacade{

	@Autowired
    IMemberService memberService;
    /**
	* 获取列表
     */
   	@PostMapping(value = context+"/list")
    @ResponseBody
    @Log(before=true,end=false,name="集合查询")
    @ApiOperation(value = "获取列表")
    public Result<Page<Member>> list(@RequestBody MemberListReq memberListReq){
    	Page<Member> page = new PageFactory<Member>().defaultPage(memberListReq);
    	Wrapper<Member> wrapper = Condition.create();
		Page<Member> rs = memberService.selectPage(page,wrapper);
		return  new Result<Page<Member>>(rs);
    }
        /**
	* 获取列表(无分页)
     */
   	@PostMapping(value = context+"/records")
    @ResponseBody
    @Log(before=true,end=false,name="集合查询")
    @ApiOperation(value = "获取列表")
    public Result<List<Member>> records(@RequestBody MemberListReq memberListReq){
    	Wrapper<Member> wrapper = Condition.create();
 		wrapper.last("limit "+memberListReq.getOffset()+","+memberListReq.getLimit());
		List<Member> rows = memberService.selectList(wrapper);
		return  new Result<List<Member>>(rows);
    }
    /**
	* 新增
     */
    @PostMapping(value = context+"/add")
    @ResponseBody
    @Log(before=true,end=true,name="新增")
    @ApiOperation(value = "新增")
    public Result<Member> add(@RequestBody Member member){
   		  member.setIsDel(0);
          memberService.insert(member);
        return new Result<Member>(member);
    }
    /**
	 * 删除
     */
    @PostMapping(value = context+"/delete")
    @ResponseBody
    @ApiOperation(value = "删除")
	@ApiImplicitParams({ @ApiImplicitParam(name = "memberId", value = "会员ID"),
		@ApiImplicitParam(name = "lastUser", value = "最后修改人") })
    public Result<Void> delete(@RequestParam(name="memberId") Long memberId,@RequestParam(name="lastUser",required=false)String lastUser) {
   		 Wrapper<Member> wrapper=Condition.create();
    	Member entity = new Member();
		entity.setIsDel(1);
		entity.setLastUser(lastUser);
		wrapper.eq("member_id", memberId);
		Integer count = memberService.update(entity,wrapper);
       	if(count > 0) {
			return new Result<Void>();
		}else {
			return new Result<Void>(ResultEnum.DELETE_ERR);
		}
    }
    /**
	* 修改
     */
    @PostMapping(value = context+"/update")
    @ResponseBody
    @Log(before=true,end=true,name="修改")
    @ApiOperation(value = "修改")
    public Result<Void> update(@RequestBody Member member) {
   		Wrapper<Member> wrapper=Condition.create();
   		wrapper.eq("member_id", member.getMemberId());
   		member.setMemberId(null);
      	Integer count=memberService.update(member,wrapper);
        if(count > 0) {
			return new Result<Void>();
		}else {
			return new Result<Void>(ResultEnum.UPDATE_ERR);
		}
    }
    /**
     * 详情
     */
    @PostMapping(value = context+"/detail")
    @ResponseBody
    @Log(before=true,end=true,name="详情")
    @ApiOperation(value = "详情,根据memberId查询")
   	@ApiImplicitParams({ @ApiImplicitParam(name = "memberId", value = "会员ID")
	 })
    public Result<Member> detail(@RequestParam(name="memberId") Long memberId) {
    	Wrapper<Member> wrapper=Condition.create();
		wrapper.eq("member_id", memberId);
     	Member member=memberService.selectOne(wrapper);
     	
        return new Result<Member>(member);
    }
}
