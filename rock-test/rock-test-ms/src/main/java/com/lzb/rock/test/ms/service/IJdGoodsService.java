package com.lzb.rock.test.ms.service;

import java.util.List;

import com.lzb.rock.base.facade.IService;
import com.lzb.rock.test.open.model.GoodsOrderList;
import com.lzb.rock.test.open.model.GoodsSaleCache;
import com.lzb.rock.test.open.model.JdGoods;

/**
 * <p>
 * 京东商品信息表 服务类
 * </p>
 *
 * @author lzb123
 * @since 2019-11-12
 */

public interface IJdGoodsService extends IService<JdGoods> {
	/**
	 * 商品下单扣库存，不能开启事务，否则会出现交叉事务
	 * 
	 * @param goodsOrderList
	 * @return
	 */
	public Boolean buckleBuckle(List<GoodsOrderList> goodsList);

	/**
	 * 增加商品数量，同时保存到临时表
	 * 
	 * @param jdGoodsId
	 * @param goodsNum
	 * @param goodsOrderId
	 */
	public Boolean addJdGoodsSaleNum(Long jdGoodsId, Integer goodsNum, Long goodsOrderId);
	
	public Boolean reduceJdGoodsSaleNum(GoodsSaleCache goodsSaleCache);
	
}
