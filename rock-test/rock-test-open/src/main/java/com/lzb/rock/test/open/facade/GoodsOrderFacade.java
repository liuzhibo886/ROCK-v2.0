package com.lzb.rock.test.open.facade;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.baomidou.mybatisplus.plugins.Page;
import com.lzb.rock.base.Result;
import com.lzb.rock.test.open.dto.goodsOrder.GoodsOrderListReq;
import com.lzb.rock.test.open.model.GoodsOrder;

/**
 * 
 * Facade
 * @author lzb
 * @Date 2019-11-12 20:47:10
 */
public interface GoodsOrderFacade{

    static String SERVICE_NAME="rock-test-ms";
    static String context="/goodsOrder";
    /**
   	  * 获取列表
     */
    @PostMapping(value = context+"/list")
    @ResponseBody
    public Result<Page<GoodsOrder>> list(@RequestBody GoodsOrderListReq goodsOrderListReq);
     /**
   	  * 获取列表(无分页)
     */
    @PostMapping(value = context+"/records")
    @ResponseBody
    public Result<List<GoodsOrder>> records(@RequestBody GoodsOrderListReq goodsOrderListReq);
 
    /**
	* 新增
     */
    @PostMapping(value = context+"/add")
    @ResponseBody
    public Result<GoodsOrder> add(@RequestBody GoodsOrder goodsOrder);

    /**
      * 删除
     */
    @PostMapping(value = context+"/delete")
    @ResponseBody
    public Result<Void> delete(@RequestParam(name="goodsOrderId") Long goodsOrderId,@RequestParam(name="memberId",required=false) Long memberId,@RequestParam(name="lastUser",required=false)String lastUser);
    /**
     * 修改
     */
    @PostMapping(value = context+"/update")
    @ResponseBody
    public Result<Void> update(@RequestBody GoodsOrder goodsOrder);

    /**
     * 详情
     */
    @PostMapping(value = context+"/detail")
    @ResponseBody
    public Result<GoodsOrder> detail(@RequestParam(name="goodsOrderId") Long goodsOrderId,@RequestParam(name="memberId",required=false) Long memberId);
}
