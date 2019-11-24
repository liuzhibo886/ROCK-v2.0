package com.lzb.rock.test.ms.quartz;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.lzb.rock.base.util.UtilFile;
import com.lzb.rock.test.ms.mapper.GoodsOrderListMapper;
import com.lzb.rock.test.ms.mapper.GoodsSaleCacheMapper;
import com.lzb.rock.test.ms.mapper.JdGoodsMapper;
import com.lzb.rock.test.open.model.GoodsSaleCache;
import com.lzb.rock.test.open.model.JdGoods;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//@Component
//@Async
public class ValidateQuartz {

	@Autowired
	JdGoodsMapper goodsMapper;

	@Autowired
	GoodsOrderListMapper goodsOrderListMapper;
	@Autowired
	GoodsSaleCacheMapper goodsSaleCacheMapper;

	/**
	 * 验证商品和销售的数量是否一致
	 */
	@PostConstruct
	@Async
	public void tetGoods() {
		Long jdGoodsIdIndex = -1L;
		Integer jdGoodsLimit = 100;
		while (true) {
			Wrapper<JdGoods> wrapper = new Condition();
			wrapper.gt("jd_goods_id", jdGoodsIdIndex);
			wrapper.orderBy("jd_goods_id", true);
			List<JdGoods> goodss = goodsMapper.selectList(wrapper);
			for (JdGoods jdGoods : goodss) {
				jdGoodsIdIndex = jdGoods.getJdGoodsId();
				Integer goodsSubmitCount = submitGoodsNun(jdGoods.getJdGoodsId(), null);
				// 查询该商品销售数量
				if (jdGoods.getJdGoodsSaleNum().equals(goodsSubmitCount)) {
					log.info("商品销售总数量和订单详情中商品数量一致，goodsId={};goodsNunCount={};jdGoodsSaleNum={}",
							jdGoods.getJdGoodsId(), goodsSubmitCount, jdGoods.getJdGoodsSaleNum());
				} else {
					Integer goodsNotSubmitCount = notSubmitGoodsNun(jdGoods.getJdGoodsId(), null);

					if (jdGoods.getJdGoodsSaleNum().equals(goodsSubmitCount + goodsNotSubmitCount)) {
						log.info("商品销售总数量加未提交和提交失败的数量和订单详情中商品数量一致，goodsId={};goodsNunCount={};jdGoodsSaleNum={}",
								jdGoods.getJdGoodsId(), goodsSubmitCount, jdGoods.getJdGoodsSaleNum());
					} else {
						log.error("商品销售总数量和订单详情中商品数量不一致，goodsId={};goodsSubmitCount={};jdGoodsSaleNum={}",
								jdGoods.getJdGoodsId(), goodsSubmitCount, jdGoods.getJdGoodsSaleNum());
						UtilFile.out("F:\\", "goods.txt",
								"goodsId:" + jdGoods.getJdGoodsId() + ";goodsSubmitCount:" + goodsSubmitCount
										+ ";jdGoodsSaleNum:" + jdGoods.getJdGoodsSaleNum() + ";goodsNotSubmitCount:"
										+ goodsNotSubmitCount);
					}

				}
			}

			if (goodss.size() < jdGoodsLimit) {
				break;
			}
		}
	}

	/**
	 * 统计提交商品销售数量
	 * 
	 * @param goodsId
	 */
	public Integer submitGoodsNun(Long goodsId, Long goodsOrderId) {
		Wrapper<GoodsSaleCache> wrapper = new Condition();
		String[] columns = new String[2];
		columns[0] = "goods_id AS goodsId";
		columns[1] = "SUM(goods_num) AS goodsNum";

		wrapper.setSqlSelect(columns);
		wrapper.eq("goods_id", goodsId);
		wrapper.eq("goods_sale_cache_status", 1);
		if (goodsOrderId != null && goodsOrderId > 0) {
			wrapper.eq("goods_order_id", goodsOrderId);
		}
		List<GoodsSaleCache> list = goodsSaleCacheMapper.selectList(wrapper);

		if (list != null && list.size() > 0 && list.get(0) != null) {
			return list.get(0).getGoodsNum();
		}
		return 0;
	}

	/*
	 * 未提交的商品数量
	 */
	public Integer notSubmitGoodsNun(Long goodsId, Long goodsOrderId) {
		Wrapper<GoodsSaleCache> wrapper = new Condition();
		String[] columns = new String[2];
		columns[0] = "goods_id AS goodsId";
		columns[1] = "SUM(goods_num) AS goodsNum";

		wrapper.setSqlSelect(columns);
		wrapper.eq("goods_id", goodsId);
		wrapper.andNew().eq("goods_sale_cache_status", 0).or().eq("goods_sale_cache_status", 3);
		if (goodsOrderId != null && goodsOrderId > 0) {
			wrapper.eq("goods_order_id", goodsOrderId);
		}
		List<GoodsSaleCache> list = goodsSaleCacheMapper.selectList(wrapper);

		if (list != null && list.size() > 0 && list.get(0) != null) {
			return list.get(0).getGoodsNum();
		}
		return 0;
	}

	public static void main(String[] args) {
		Wrapper<GoodsSaleCache> wrapper = new Condition();
		String[] columns = new String[2];
		columns[0] = "goods_id AS goodsId";
		columns[1] = "SUM(goods_num) AS goodsNum";

		wrapper.setSqlSelect(columns);
		wrapper.eq("goods_id", "11");

		System.out.println(wrapper.getSqlSegment());

	}
}
