package com.lzb.rock.test.ms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.lzb.rock.base.common.ResultEnum;
import com.lzb.rock.base.exception.RockClientException;
import com.lzb.rock.base.facade.impl.ServiceImpl;
import com.lzb.rock.base.util.UtilFile;
import com.lzb.rock.test.ms.mapper.GoodsSaleCacheMapper;
import com.lzb.rock.test.ms.mapper.JdGoodsMapper;
import com.lzb.rock.test.ms.service.IGoodsOrderService;
import com.lzb.rock.test.ms.service.IJdGoodsService;
import com.lzb.rock.test.open.model.GoodsOrderList;
import com.lzb.rock.test.open.model.GoodsSaleCache;
import com.lzb.rock.test.open.model.JdGoods;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 京东商品信息表 服务实现类
 * </p>
 *
 * @author lzb123
 * @since 2019-11-12
 */
@Service
@Slf4j
public class JdGoodsServiceImpl extends ServiceImpl<JdGoodsMapper, JdGoods> implements IJdGoodsService {

	@Autowired
	JdGoodsMapper goodsMapper;
	@Autowired
	GoodsSaleCacheMapper goodsSaleCacheMapper;
	@Autowired
	IJdGoodsService goodsService;
	@Autowired
	IGoodsOrderService goodsOrderService;

	/**
	 * 商品下单扣库存，不能开启事务，否则会出现交叉事务
	 * 
	 * @param goodsOrderList
	 * @return
	 */
	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Boolean buckleBuckle(List<GoodsOrderList> goodsList) {
		Boolean falg = true;
		/**
		 * 不能开启事务，但是必须保证都成功或者都失败
		 */
		for (GoodsOrderList goodsOrderList : goodsList) {
			try {
				Boolean flag2 = goodsService.addJdGoodsSaleNum(goodsOrderList.getGoodsId(),
						goodsOrderList.getGoodsNum(), goodsOrderList.getGoodsOrderId());
				if (!flag2) {
					falg = false;
				}
			} catch (Exception e) {
				falg = false;
				e.printStackTrace();
			}

			if (!falg) {
				break;
			}
		}
		return falg;
	}

	/**
	 * 商品减销售量,修改缓存数据状态，必须在新事务中使用
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Boolean reduceJdGoodsSaleNum(GoodsSaleCache goodsSaleCache) {
		Boolean falg = false;
		if (goodsSaleCache.getGoodsSaleCacheStatus() != 0) {
			return true;
		}
		GoodsSaleCache entity = new GoodsSaleCache();
		entity.setGoodsSaleCacheStatus(2);
		Wrapper<GoodsSaleCache> wrapper = new Condition();
		wrapper.eq("goods_order_id", goodsSaleCache.getGoodsOrderId());
		wrapper.eq("goods_sale_cache_id", goodsSaleCache.getGoodsSaleCacheId());
		wrapper.eq("goods_sale_cache_status", 0);
		Integer count2 = goodsSaleCacheMapper.update(entity, wrapper);
		if (count2 == 1) {
			Integer count = goodsMapper.reduceJdGoodsSaleNum(goodsSaleCache.getGoodsId(), goodsSaleCache.getGoodsNum());
			if (count == 1) {
				falg = true;
			} else {
				log.error("回滚订单，回写商品销售量失败，goodsId:{},goodsOrderId:{},goodsNum:{},goodsSaleCacheId:{}",
						goodsSaleCache.getGoodsId(), goodsSaleCache.getGoodsOrderId(), goodsSaleCache.getGoodsNum(),
						goodsSaleCache.getGoodsSaleCacheId());
				throw new RockClientException(ResultEnum.UPDATE_ERR);
			}
		}
		return falg;
	}

	/**
	 * 商品增加销售量,写缓存数据
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Boolean addJdGoodsSaleNum(Long jdGoodsId, Integer goodsNum, Long goodsOrderId) {
		Boolean falg = false;
		Integer count = goodsMapper.addJdGoodsSaleNum(jdGoodsId, goodsNum);
		if (count == 1) {
			GoodsSaleCache goodsSaleCache = new GoodsSaleCache();
			goodsSaleCache.setGoodsId(jdGoodsId);
			goodsSaleCache.setGoodsNum(goodsNum);
			goodsSaleCache.setGoodsSaleCacheStatus(0);
			goodsSaleCache.setGoodsOrderId(goodsOrderId);
			goodsSaleCacheMapper.insert(goodsSaleCache);
			if (goodsSaleCache.getGoodsOrderId() == null) {
				throw new RockClientException(ResultEnum.INSERT_ERR);
			}
			falg = true;
		} else if (count == 0) {
			falg = false;
		} else {
			log.error("更新记录目标为一条，实际为:{}条,goodsId:{}", count, jdGoodsId);
			falg = false;
			throw new RockClientException(ResultEnum.UPDATE_ERR);
		}
		return falg;
	}

}
