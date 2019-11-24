package com.lzb.rock.test.open.facade;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.baomidou.mybatisplus.plugins.Page;
import com.lzb.rock.base.Result;
import com.lzb.rock.test.open.dto.goodsOrderList.GoodsOrderListListReq;
import com.lzb.rock.test.open.model.GoodsOrderList;

/**
 * 
 * Facade
 * @author lzb
 * @Date 2019-11-12 20:47:11
 */
public interface GoodsOrderListFacade{

    static String SERVICE_NAME="rock-test-ms";
    static String context="/goodsOrderList";
    /**
   	  * 获取列表
     */
    @PostMapping(value = context+"/list")
    @ResponseBody
    public Result<Page<GoodsOrderList>> list(@RequestBody GoodsOrderListListReq goodsOrderListListReq);
     /**
   	  * 获取列表(无分页)
     */
    @PostMapping(value = context+"/records")
    @ResponseBody
    public Result<List<GoodsOrderList>> records(@RequestBody GoodsOrderListListReq goodsOrderListListReq);
 
    /**
	* 新增
     */
    @PostMapping(value = context+"/add")
    @ResponseBody
    public Result<GoodsOrderList> add(@RequestBody GoodsOrderList goodsOrderList);

    /**
      * 删除
     */
    @PostMapping(value = context+"/delete")
    @ResponseBody
    public Result<Void> delete(@RequestParam(name="goodsOrderListId") Long goodsOrderListId,@RequestParam(name="memberId",required=false) Long memberId,@RequestParam(name="lastUser",required=false)String lastUser);
    /**
     * 修改
     */
    @PostMapping(value = context+"/update")
    @ResponseBody
    public Result<Void> update(@RequestBody GoodsOrderList goodsOrderList);

    /**
     * 详情
     */
    @PostMapping(value = context+"/detail")
    @ResponseBody
    public Result<GoodsOrderList> detail(@RequestParam(name="goodsOrderListId") Long goodsOrderListId,@RequestParam(name="memberId",required=false) Long memberId);
}
