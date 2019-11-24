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
import com.lzb.rock.test.ms.service.IGoodsOrderService;
import com.lzb.rock.test.open.dto.goodsOrder.GoodsOrderListReq;
import com.lzb.rock.test.open.facade.GoodsOrderFacade;
import com.lzb.rock.test.open.model.GoodsOrder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 控制器
 *
 * @author lzb
 * @Date 2019-11-12 20:52:13
 */
@RestController
@Api(tags={"控制器"})
public class GoodsOrderMsController implements GoodsOrderFacade{

	@Autowired
    IGoodsOrderService goodsOrderService;
    /**
	* 获取列表
     */
   	@PostMapping(value = context+"/list")
    @ResponseBody
    @Log(before=true,end=false,name="集合查询")
    @ApiOperation(value = "获取列表")
    public Result<Page<GoodsOrder>> list(@RequestBody GoodsOrderListReq goodsOrderListReq){
    	Page<GoodsOrder> page = new PageFactory<GoodsOrder>().defaultPage(goodsOrderListReq);
    	Wrapper<GoodsOrder> wrapper = Condition.create();
		Page<GoodsOrder> rs = goodsOrderService.selectPage(page,wrapper);
		return  new Result<Page<GoodsOrder>>(rs);
    }
        /**
	* 获取列表(无分页)
     */
   	@PostMapping(value = context+"/records")
    @ResponseBody
    @Log(before=true,end=false,name="集合查询")
    @ApiOperation(value = "获取列表")
    public Result<List<GoodsOrder>> records(@RequestBody GoodsOrderListReq goodsOrderListReq){
    	Wrapper<GoodsOrder> wrapper = Condition.create();
 		wrapper.last("limit "+goodsOrderListReq.getOffset()+","+goodsOrderListReq.getLimit());
		List<GoodsOrder> rows = goodsOrderService.selectList(wrapper);
		return  new Result<List<GoodsOrder>>(rows);
    }
    /**
	* 新增
     */
    @PostMapping(value = context+"/add")
    @ResponseBody
    @Log(before=true,end=true,name="新增")
    @ApiOperation(value = "新增")
    public Result<GoodsOrder> add(@RequestBody GoodsOrder goodsOrder){
   		  goodsOrder.setIsDel(0);
          goodsOrderService.insert(goodsOrder);
        return new Result<GoodsOrder>(goodsOrder);
    }
    /**
	 * 删除
     */
    @PostMapping(value = context+"/delete")
    @ResponseBody
    @ApiOperation(value = "删除")
	@ApiImplicitParams({ @ApiImplicitParam(name = "goodsOrderId", value = "会员订单ID"),
		@ApiImplicitParam(name = "memberId", value = "会员ID(分库字段)"),
		@ApiImplicitParam(name = "lastUser", value = "最后修改人") })
    public Result<Void> delete(@RequestParam(name="goodsOrderId") Long goodsOrderId,@RequestParam(name="memberId",required=false) Long memberId,@RequestParam(name="lastUser",required=false)String lastUser) {
   		 Wrapper<GoodsOrder> wrapper=Condition.create();
    	GoodsOrder entity = new GoodsOrder();
		entity.setIsDel(1);
		entity.setLastUser(lastUser);
		wrapper.eq("goods_order_id", goodsOrderId);
   	    if(memberId !=null){
  		 wrapper.eq("member_id", memberId);
   		}
		Integer count = goodsOrderService.update(entity,wrapper);
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
    public Result<Void> update(@RequestBody GoodsOrder goodsOrder) {
   		Wrapper<GoodsOrder> wrapper=Condition.create();
   		wrapper.eq("goods_order_id", goodsOrder.getGoodsOrderId());
   	    if(goodsOrder.getMemberId() !=null){
  		 wrapper.eq("member_id", goodsOrder.getMemberId());
   		}
   		goodsOrder.setGoodsOrderId(null);
   		goodsOrder.setMemberId(null);
      	Integer count=goodsOrderService.update(goodsOrder,wrapper);
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
    @ApiOperation(value = "详情,根据goodsOrderId查询")
   	@ApiImplicitParams({ @ApiImplicitParam(name = "goodsOrderId", value = "会员订单ID"),
	@ApiImplicitParam(name = "memberId", value = "会员ID(分库字段)")
	 })
    public Result<GoodsOrder> detail(@RequestParam(name="goodsOrderId") Long goodsOrderId,@RequestParam(name="memberId",required=false) Long memberId) {
    	Wrapper<GoodsOrder> wrapper=Condition.create();
		wrapper.eq("goods_order_id", goodsOrderId);
   	    if(memberId !=null){
  		 wrapper.eq("member_id", memberId);
   		}
     	GoodsOrder goodsOrder=goodsOrderService.selectOne(wrapper);
     	
        return new Result<GoodsOrder>(goodsOrder);
    }
}
