package com.lzb.rock.test.open.facade;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.baomidou.mybatisplus.plugins.Page;
import com.lzb.rock.base.Result;
import com.lzb.rock.test.open.dto.jdGoods.JdGoodsListReq;
import com.lzb.rock.test.open.model.JdGoods;

/**
 * 京东商品信息表
 * Facade
 * @author lzb
 * @Date 2019-11-12 20:47:10
 */
public interface JdGoodsFacade{

    static String SERVICE_NAME="rock-test-ms";
    static String context="/jdGoods";
    /**
   	  * 获取京东商品信息表列表
     */
    @PostMapping(value = context+"/list")
    @ResponseBody
    public Result<Page<JdGoods>> list(@RequestBody JdGoodsListReq jdGoodsListReq);
     /**
   	  * 获取京东商品信息表列表(无分页)
     */
    @PostMapping(value = context+"/records")
    @ResponseBody
    public Result<List<JdGoods>> records(@RequestBody JdGoodsListReq jdGoodsListReq);
 
    /**
	* 新增京东商品信息表
     */
    @PostMapping(value = context+"/add")
    @ResponseBody
    public Result<JdGoods> add(@RequestBody JdGoods jdGoods);

    /**
      * 删除京东商品信息表
     */
    @PostMapping(value = context+"/delete")
    @ResponseBody
    public Result<Void> delete(@RequestParam(name="jdGoodsId") Long jdGoodsId,@RequestParam(name="jdGoodsQriginalId",required=false) String jdGoodsQriginalId,@RequestParam(name="lastUser",required=false)String lastUser);
    /**
     * 修改京东商品信息表
     */
    @PostMapping(value = context+"/update")
    @ResponseBody
    public Result<Void> update(@RequestBody JdGoods jdGoods);

    /**
     * 京东商品信息表详情
     */
    @PostMapping(value = context+"/detail")
    @ResponseBody
    public Result<JdGoods> detail(@RequestParam(name="jdGoodsId") Long jdGoodsId,@RequestParam(name="jdGoodsQriginalId",required=false) String jdGoodsQriginalId);
}
