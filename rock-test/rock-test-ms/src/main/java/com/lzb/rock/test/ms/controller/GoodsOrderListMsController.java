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
import com.lzb.rock.test.ms.service.IGoodsOrderListService;
import com.lzb.rock.test.open.dto.goodsOrderList.GoodsOrderListListReq;
import com.lzb.rock.test.open.facade.GoodsOrderListFacade;
import com.lzb.rock.test.open.model.GoodsOrderList;
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
public class GoodsOrderListMsController implements GoodsOrderListFacade{

	@Autowired
    IGoodsOrderListService goodsOrderListService;
    /**
	* 获取列表
     */
   	@PostMapping(value = context+"/list")
    @ResponseBody
    @Log(before=true,end=false,name="集合查询")
    @ApiOperation(value = "获取列表")
    public Result<Page<GoodsOrderList>> list(@RequestBody GoodsOrderListListReq goodsOrderListListReq){
    	Page<GoodsOrderList> page = new PageFactory<GoodsOrderList>().defaultPage(goodsOrderListListReq);
    	Wrapper<GoodsOrderList> wrapper = Condition.create();
		Page<GoodsOrderList> rs = goodsOrderListService.selectPage(page,wrapper);
		return  new Result<Page<GoodsOrderList>>(rs);
    }
        /**
	* 获取列表(无分页)
     */
   	@PostMapping(value = context+"/records")
    @ResponseBody
    @Log(before=true,end=false,name="集合查询")
    @ApiOperation(value = "获取列表")
    public Result<List<GoodsOrderList>> records(@RequestBody GoodsOrderListListReq goodsOrderListListReq){
    	Wrapper<GoodsOrderList> wrapper = Condition.create();
 		wrapper.last("limit "+goodsOrderListListReq.getOffset()+","+goodsOrderListListReq.getLimit());
		List<GoodsOrderList> rows = goodsOrderListService.selectList(wrapper);
		return  new Result<List<GoodsOrderList>>(rows);
    }
    /**
	* 新增
     */
    @PostMapping(value = context+"/add")
    @ResponseBody
    @Log(before=true,end=true,name="新增")
    @ApiOperation(value = "新增")
    public Result<GoodsOrderList> add(@RequestBody GoodsOrderList goodsOrderList){
   		  goodsOrderList.setIsDel(0);
          goodsOrderListService.insert(goodsOrderList);
        return new Result<GoodsOrderList>(goodsOrderList);
    }
    /**
	 * 删除
     */
    @PostMapping(value = context+"/delete")
    @ResponseBody
    @ApiOperation(value = "删除")
	@ApiImplicitParams({ @ApiImplicitParam(name = "goodsOrderListId", value = ""),
		@ApiImplicitParam(name = "memberId", value = "会员ID(分库字段)"),
		@ApiImplicitParam(name = "lastUser", value = "最后修改人") })
    public Result<Void> delete(@RequestParam(name="goodsOrderListId") Long goodsOrderListId,@RequestParam(name="memberId",required=false) Long memberId,@RequestParam(name="lastUser",required=false)String lastUser) {
   		 Wrapper<GoodsOrderList> wrapper=Condition.create();
    	GoodsOrderList entity = new GoodsOrderList();
		entity.setIsDel(1);
		entity.setLastUser(lastUser);
		wrapper.eq("goods_order_list_id", goodsOrderListId);
   	    if(memberId !=null){
  		 wrapper.eq("member_id", memberId);
   		}
		Integer count = goodsOrderListService.update(entity,wrapper);
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
    public Result<Void> update(@RequestBody GoodsOrderList goodsOrderList) {
   		Wrapper<GoodsOrderList> wrapper=Condition.create();
   		wrapper.eq("goods_order_list_id", goodsOrderList.getGoodsOrderListId());
   	    if(goodsOrderList.getMemberId() !=null){
  		 wrapper.eq("member_id", goodsOrderList.getMemberId());
   		}
   		goodsOrderList.setGoodsOrderListId(null);
   		goodsOrderList.setMemberId(null);
      	Integer count=goodsOrderListService.update(goodsOrderList,wrapper);
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
    @ApiOperation(value = "详情,根据goodsOrderListId查询")
   	@ApiImplicitParams({ @ApiImplicitParam(name = "goodsOrderListId", value = ""),
	@ApiImplicitParam(name = "memberId", value = "会员ID(分库字段)")
	 })
    public Result<GoodsOrderList> detail(@RequestParam(name="goodsOrderListId") Long goodsOrderListId,@RequestParam(name="memberId",required=false) Long memberId) {
    	Wrapper<GoodsOrderList> wrapper=Condition.create();
		wrapper.eq("goods_order_list_id", goodsOrderListId);
   	    if(memberId !=null){
  		 wrapper.eq("member_id", memberId);
   		}
     	GoodsOrderList goodsOrderList=goodsOrderListService.selectOne(wrapper);
     	
        return new Result<GoodsOrderList>(goodsOrderList);
    }
}
