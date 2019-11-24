package com.lzb.rock.test.ms.mapper;

import com.lzb.rock.test.open.model.JdGoods;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 京东商品信息表 Mapper 接口
 * </p>
 *
 * @author lzb123
 * @since 2019-11-12
 */
public interface JdGoodsMapper extends BaseMapper<JdGoods> {

	@Update("update jd_goods set jd_goods_sale_num=jd_goods_sale_num + #{goodsNum} where jd_goods_id=#{jdGoodsId}")
	public Integer addJdGoodsSaleNum(@Param("jdGoodsId") Long jdGoodsId, @Param("goodsNum") Integer goodsNum);
	
	@Update("update jd_goods set jd_goods_sale_num=jd_goods_sale_num - #{goodsNum} where jd_goods_id=#{jdGoodsId}")
	public Integer reduceJdGoodsSaleNum(@Param("jdGoodsId") Long jdGoodsId, @Param("goodsNum") Integer goodsNum);
}
