package com.lzb.rock.test.ms.quartz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.lzb.rock.test.ms.service.IJdGoodsService;
import com.lzb.rock.test.ms.util.UtilTest;
import com.lzb.rock.test.open.model.JdGoods;

/**
 * 订单生成
 *
 * @author Administrator
 *
 * @date 2019年11月12日 下午9:00:03
 */
//@Component
//@Async
public class GoodsQuartz implements ApplicationRunner {


	@Autowired
	IJdGoodsService jdGoodsService;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		for (int i = 0; i < 10000; i++) {
			JdGoods goods = new JdGoods();
			goods.setJdGoodsTitle("商品" + UtilTest.getName(UtilTest.getRandom(10L, 30L)));
			goods.setJdGoodsPrice(UtilTest.getRandom(100L, 30000L).intValue());
			goods.setJdGoodsQriginalId("20191121"+i);
			jdGoodsService.insert(goods);
		}

	}

}
