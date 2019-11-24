package com.lzb.rock.test.ms.service;

import java.util.List;

import com.lzb.rock.base.facade.IService;
import com.lzb.rock.test.open.model.GoodsOrder;
import com.lzb.rock.test.open.model.GoodsOrderList;
import com.lzb.rock.test.open.model.JdGoods;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lzb123
 * @since 2019-11-12
 */

public interface IGoodsOrderService extends IService<GoodsOrder> {
	/**
	 * 新增订单
	 */
	public GoodsOrder addOrder(Long memberId, List<GoodsOrderList> goodsList);

	/**
	 * 下预订单
	 * 
	 * @param memberId
	 * @param goodsList
	 * @return
	 */
	public GoodsOrder downloadOrder(Long memberId, List<GoodsOrderList> goodsList);

	/**
	 * 提交订单
	 * 
	 * @param goodsOrderId
	 * @param memberId
	 */
	public void submitOrder(Long goodsOrderId, Long memberId);

	/**
	 * 回滚订单
	 * 
	 * @return
	 */
	public Boolean rollBack(Long goodsOrderId);

}
