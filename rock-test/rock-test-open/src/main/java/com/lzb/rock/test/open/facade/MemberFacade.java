package com.lzb.rock.test.open.facade;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.baomidou.mybatisplus.plugins.Page;
import com.lzb.rock.base.Result;
import com.lzb.rock.test.open.dto.member.MemberListReq;
import com.lzb.rock.test.open.model.Member;

/**
 * 
 * Facade
 * @author lzb
 * @Date 2019-11-12 20:47:11
 */
public interface MemberFacade{

    static String SERVICE_NAME="rock-test-ms";
    static String context="/member";
    /**
   	  * 获取列表
     */
    @PostMapping(value = context+"/list")
    @ResponseBody
    public Result<Page<Member>> list(@RequestBody MemberListReq memberListReq);
     /**
   	  * 获取列表(无分页)
     */
    @PostMapping(value = context+"/records")
    @ResponseBody
    public Result<List<Member>> records(@RequestBody MemberListReq memberListReq);
 
    /**
	* 新增
     */
    @PostMapping(value = context+"/add")
    @ResponseBody
    public Result<Member> add(@RequestBody Member member);

    /**
      * 删除
     */
    @PostMapping(value = context+"/delete")
    @ResponseBody
    public Result<Void> delete(@RequestParam(name="memberId") Long memberId,@RequestParam(name="lastUser",required=false)String lastUser);
    /**
     * 修改
     */
    @PostMapping(value = context+"/update")
    @ResponseBody
    public Result<Void> update(@RequestBody Member member);

    /**
     * 详情
     */
    @PostMapping(value = context+"/detail")
    @ResponseBody
    public Result<Member> detail(@RequestParam(name="memberId") Long memberId);
}
