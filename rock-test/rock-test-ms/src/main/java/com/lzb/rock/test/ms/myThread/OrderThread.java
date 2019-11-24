package com.lzb.rock.test.ms.myThread;

import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.lzb.rock.base.holder.SpringContextHolder;
import com.lzb.rock.test.ms.mapper.JdGoodsMapper;
import com.lzb.rock.test.ms.mapper.MemberMapper;
import com.lzb.rock.test.ms.quartz.TestCommon;
import com.lzb.rock.test.ms.service.IGoodsOrderService;
import com.lzb.rock.test.ms.util.UtilTest;
import com.lzb.rock.test.open.model.GoodsOrderList;
import com.lzb.rock.test.open.model.JdGoods;
import com.lzb.rock.test.open.model.Member;

/**
 * 下订单线程
 *
 * @author lzb
 *
 * @date 2019年11月14日 下午4:14:16
 */
public class OrderThread implements Runnable {
	@Override
	public void run() {
		IGoodsOrderService goodsOrderService = SpringContextHolder.getBean(IGoodsOrderService.class);

		for (int i = 0; i < 1000000; i++) {
			Member member = getMember();
			List<GoodsOrderList> goodsList = getGoodsOrderList();
			try {
				goodsOrderService.downloadOrder(member.getMemberId(), goodsList);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * 随机获取商品列表
	 * 
	 * @return
	 */
	public List<GoodsOrderList> getGoodsOrderList() {
		JdGoodsMapper goodsMapper = SpringContextHolder.getBean(JdGoodsMapper.class);
		// 随机获取商品
		Long length = UtilTest.getRandom(1L, 10L);
		List<GoodsOrderList> goodsOrderLists = new ArrayList<GoodsOrderList>();

		for (int i = 0; i < length; i++) {
			Wrapper<JdGoods> wrapper2 = new Condition();
			Long index = UtilTest.getRandom(1L, TestCommon.GOODS_MAX_INDEX);
			wrapper2.ge("jd_goods_id", index);
			wrapper2.last("limit " + length);
			wrapper2.gt("jd_goods_price", 0);
			List<JdGoods> goods = goodsMapper.selectList(wrapper2);
			for (JdGoods jdGoods : goods) {
				GoodsOrderList goodsOrderList = new GoodsOrderList();
				Long num = UtilTest.getRandom(1L, 20L);
				goodsOrderList.setGoodsNum(num.intValue());
				goodsOrderList.setGoodsTitle(jdGoods.getJdGoodsTitle());
				goodsOrderList.setGoodsPrice(jdGoods.getJdGoodsPrice());
				goodsOrderList.setGoodsImgUrl(jdGoods.getJdGoodsImgUrl());
				goodsOrderList.setGoodsGift(jdGoods.getJdGoodsGift());
				goodsOrderList.setGoodsCoupon(jdGoods.getJdGoodsCoupon());
				goodsOrderList.setGoodsUrl(jdGoods.getJdGoodsUrl());
				goodsOrderList.setGoodsQriginalId(jdGoods.getJdGoodsQriginalId());
				goodsOrderList.setGoodsId(jdGoods.getJdGoodsId());
				goodsOrderLists.add(goodsOrderList);
			}
			if (goodsOrderLists.size() >= length) {
				break;
			}
		}
		return goodsOrderLists;
	}

	/**
	 * 随机获取一个会员
	 */
	public Member getMember() {
		Member member = null;
		MemberMapper memberMapper = SpringContextHolder.getBean(MemberMapper.class);
		while (member == null) {

			if (TestCommon.MEMBER_MAX_INDEX > 0) {
				Wrapper<Member> wrapper2 = new Condition();
				Long index = UtilTest.getRandom(1L, TestCommon.MEMBER_MAX_INDEX);
				wrapper2.ge("member_id", index);
				wrapper2.last("limit 1");
				List<Member> list2 = memberMapper.selectList(wrapper2);
				if (list2 != null && list2.size() > 0 && list2.get(0).getMemberId() > 0) {
					member = list2.get(0);
				}
			}
		}
		return member;
	}

}
