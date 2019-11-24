package com.lzb.rock.test.ms.quartz;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.lzb.rock.base.holder.SpringContextHolder;
import com.lzb.rock.test.ms.mapper.JdGoodsMapper;
import com.lzb.rock.test.ms.mapper.MemberMapper;
import com.lzb.rock.test.ms.myThread.OrderThread;
import com.lzb.rock.test.ms.service.IGoodsOrderService;
import com.lzb.rock.test.open.model.GoodsOrderList;
import com.lzb.rock.test.open.model.JdGoods;
import com.lzb.rock.test.open.model.Member;

import lombok.extern.slf4j.Slf4j;

/**
 * 订单生成
 *
 * @author Administrator
 *
 * @date 2019年11月12日 下午9:00:03
 */
@Component
@Async
@Slf4j
public class OrderQuartz implements ApplicationRunner {
	@Value("${OrderThread.count}")
	private Integer count;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		if (count < 1) {
			return;
		}
		log.warn("订单生成线程数:{}", count);
		refIndex();
		for (int i = 0; i < count; i++) {
			OrderThread command = new OrderThread();
			TestCommon.execute(command);
		}
//		OrderThread t=new OrderThread();
//		IGoodsOrderService goodsOrderService = SpringContextHolder.getBean(IGoodsOrderService.class);
//		Member member = t.getMember();
//		List<GoodsOrderList> goodsList = t.getGoodsOrderList();
//		goodsOrderService.downloadOrder(member.getMemberId(), goodsList);
	}

	/**
	 * 刷新坐标
	 */
	@Scheduled(cron = "${OrderQuartz.refIndex.cron:0 */10 * * * ?}")
	@Async
	public void refIndex() {
		// 获取商品最大坐标
		JdGoodsMapper goodsMapper = SpringContextHolder.getBean(JdGoodsMapper.class);
		Wrapper<JdGoods> wrapper = new Condition();
		wrapper.setSqlSelect("MAX(jd_goods_id) as jdGoodsId");
		wrapper.gt("jd_goods_price", 0);
		wrapper.last("limit 1");
		List<JdGoods> list = goodsMapper.selectList(wrapper);

		if (list != null && list.size() > 0 && list.get(0).getJdGoodsId() > 0) {
			TestCommon.GOODS_MAX_INDEX = list.get(0).getJdGoodsId();
			// 获取会员最大坐标
			MemberMapper memberMapper = SpringContextHolder.getBean(MemberMapper.class);
			Wrapper<Member> wrapper2 = new Condition();
			wrapper2.setSqlSelect("MAX(member_id) as memberId");
			wrapper2.last("limit 1");
			List<Member> list2 = memberMapper.selectList(wrapper2);
			if (list2.get(0).getMemberId() > 0) {
				TestCommon.MEMBER_MAX_INDEX = list2.get(0).getMemberId();
			}
		}

	}
}
