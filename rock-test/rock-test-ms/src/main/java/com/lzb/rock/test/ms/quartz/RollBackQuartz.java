package com.lzb.rock.test.ms.quartz;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.lzb.rock.base.util.UtilDate;
import com.lzb.rock.test.ms.service.IGoodsOrderService;
import com.lzb.rock.test.open.model.GoodsOrder;

/**
 * 超时订单回滚
 *
 * @author Administrator
 *
 * @date 2019年11月18日 上午10:40:17
 */
@Component
@Async
public class RollBackQuartz implements ApplicationRunner {
	@Autowired
	IGoodsOrderService goodsOrderService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		rollBack();
	}

	@Scheduled(cron = "${RollBackQuartz.rollBack.cron:0 */5 * * * ?}")
	@Async
	public void rollBack() {
		Long index = -1L;
		Integer limit = 100;
		while (true) {
			Wrapper<GoodsOrder> wrapper = new Condition();
			Date dateTime = UtilDate.addMinuteOfDate(new Date(), -5);
			wrapper.gt("goods_order_id", index);
			wrapper.lt("last_time", dateTime);
			wrapper.andNew().eq("goods_order_status", 0).or().eq("goods_order_status", 3);
			wrapper.last("limit " + limit);
			wrapper.orderBy("goods_order_id", true);
			List<GoodsOrder> list = goodsOrderService.selectList(wrapper);
			for (GoodsOrder goodsOrder : list) {
				index = goodsOrder.getGoodsOrderId();
				goodsOrderService.rollBack(goodsOrder.getGoodsOrderId());
			}
			if (list == null || list.size() < limit) {
				break;
			}
		}
	}

}
