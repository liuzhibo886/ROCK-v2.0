package com.lzb.rock.test.ms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.lzb.rock.base.common.ResultEnum;
import com.lzb.rock.base.exception.RockException;
import com.lzb.rock.base.facade.impl.ServiceImpl;
import com.lzb.rock.test.ms.mapper.GoodsOrderListMapper;
import com.lzb.rock.test.ms.mapper.GoodsOrderMapper;
import com.lzb.rock.test.ms.mapper.GoodsSaleCacheMapper;
import com.lzb.rock.test.ms.mapper.JdGoodsMapper;
import com.lzb.rock.test.ms.mapper.MemberMapper;
import com.lzb.rock.test.ms.service.IGoodsOrderService;
import com.lzb.rock.test.ms.service.IJdGoodsService;
import com.lzb.rock.test.open.model.GoodsOrder;
import com.lzb.rock.test.open.model.GoodsOrderList;
import com.lzb.rock.test.open.model.GoodsSaleCache;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lzb123
 * @since 2019-11-12
 */
@Service
public class GoodsOrderServiceImpl extends ServiceImpl<GoodsOrderMapper, GoodsOrder> implements IGoodsOrderService {

	@Autowired
	MemberMapper memberMapper;

	@Autowired
	GoodsOrderMapper goodsOrderMapper;

	@Autowired
	GoodsOrderListMapper goodsOrderListMapper;
	@Autowired
	JdGoodsMapper goodsMapper;
	@Autowired
	IJdGoodsService goodsService;
	@Autowired
	GoodsSaleCacheMapper goodsSaleCacheMapper;

	@Override
	@Transactional
	public GoodsOrder addOrder(Long memberId, List<GoodsOrderList> goodsList) {
		if (goodsList == null || goodsList.size() < 1 || memberId == null) {
			return null;
		}
		// 订单商品数量
		Integer goodsNum = 0;
		// 订单金额
		Integer goodsMoney = 0;
		for (GoodsOrderList goodsOrderList : goodsList) {
			goodsNum = goodsNum + goodsOrderList.getGoodsNum();
			goodsMoney = goodsMoney + goodsOrderList.getGoodsNum() * goodsOrderList.getGoodsPrice();
		}
		// 增加订单
		GoodsOrder goodsOrder = new GoodsOrder();
		goodsOrder.setMemberId(memberId);
		goodsOrder.setGoodsNum(goodsNum);
		goodsOrder.setGoodsMoney(goodsMoney);
		goodsOrderMapper.insert(goodsOrder);

		// 用户增加消费金额和订单数
		Integer count = memberMapper.placin(memberId, goodsNum, goodsMoney);
		if (count != 1) {
			throw new RockException(ResultEnum.UPDATE_ERR, "用户增加消费金额和订单数失败，订单ID:" + goodsOrder.getGoodsOrderId());
		}
		for (GoodsOrderList goodsOrderList : goodsList) {
			goodsOrderList.setMemberId(memberId);
			goodsOrderList.setGoodsOrderId(goodsOrder.getGoodsOrderId());
			// 增加订单商品
			goodsOrderListMapper.insert(goodsOrderList);
		}
		return goodsOrder;
	}

	// 此处不能开事务
	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public GoodsOrder downloadOrder(Long memberId, List<GoodsOrderList> goodsList) {
		// 新增订单
		GoodsOrder goodsOrder = addOrder(memberId, goodsList);
		// 下单成功, 扣库存
		if (goodsOrder.getGoodsOrderId() != null) {
			Boolean flag = goodsService.buckleBuckle(goodsList);
			// 扣库存成功提交
			if (flag) {
				submitOrder(goodsOrder.getGoodsOrderId(), memberId);
			} else {
				rollBack(goodsOrder.getGoodsOrderId());
			}
		}
		goodsOrder = goodsOrderMapper.selectById(goodsOrder.getGoodsOrderId());
		return goodsOrder;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void submitOrder(Long goodsOrderId, Long memberId) {
		// 修改订单状态
		GoodsOrder entity = new GoodsOrder();
		entity.setGoodsOrderStatus(1);
		Wrapper<GoodsOrder> wrapper = new Condition();
		wrapper.eq("goods_order_id", goodsOrderId);
		wrapper.eq("member_id", memberId);
		wrapper.eq("goods_order_status", 0);
		Integer count = goodsOrderMapper.update(entity, wrapper);
		if (count == 1) {
			// 修改缓存订单状态
			GoodsSaleCache goodsSaleCache = new GoodsSaleCache();
			goodsSaleCache.setGoodsSaleCacheStatus(1);
			Wrapper<GoodsSaleCache> wrapper2 = new Condition();
			wrapper2.eq("goods_order_id", goodsOrderId);
			goodsSaleCacheMapper.update(goodsSaleCache, wrapper2);
		}

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Boolean rollBack(Long goodsOrderId) {
		Boolean flag = true;
		// 查询订单状态，只有预扣成功的才能回滚
		GoodsOrder order = goodsOrderMapper.selectById(goodsOrderId);
		if (order.getGoodsOrderStatus() == 0) {
			Wrapper<GoodsSaleCache> wrapper = new Condition();
			wrapper.eq("goods_order_id", goodsOrderId);
			wrapper.eq("goods_sale_cache_status", 0);
			List<GoodsSaleCache> list = goodsSaleCacheMapper.selectList(wrapper);
			for (GoodsSaleCache goodsSaleCache : list) {
				Boolean flag2 = goodsService.reduceJdGoodsSaleNum(goodsSaleCache);
				if (!flag2) {
					flag = false;
				}
			}
		}
		if (flag) {
			GoodsOrder entity = new GoodsOrder();
			entity.setGoodsOrderId(goodsOrderId);
			entity.setGoodsOrderStatus(2);
			Wrapper<GoodsOrder> wrapper = new Condition();
			wrapper.eq("goods_order_id", goodsOrderId);
			wrapper.eq("goods_order_status", 0);
			Integer count = goodsOrderMapper.update(entity, wrapper);
			if (count == 1) {
				// 用户购买数量减少
				Integer count2 = memberMapper.rollBack(order.getMemberId(), order.getGoodsNum(), order.getGoodsMoney());
				if (count2 != 1) {
					// 失败后，下一次定时器会扫描到
					throw new RockException(ResultEnum.UPDATE_ERR, "回滚订单失败，订单ID:" + order.getGoodsOrderId());
				}
			}

		} else {
			GoodsOrder entity = new GoodsOrder();
			entity.setGoodsOrderId(goodsOrderId);
			entity.setGoodsOrderStatus(3);
			Wrapper<GoodsOrder> wrapper = new Condition();
			wrapper.eq("goods_order_id", goodsOrderId);
			wrapper.eq("goods_order_status", 0);
			goodsOrderMapper.update(entity, wrapper);
		}

		return flag;
	}

}
