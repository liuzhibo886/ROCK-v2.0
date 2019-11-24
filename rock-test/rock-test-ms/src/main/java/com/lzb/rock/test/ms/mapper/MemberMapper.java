package com.lzb.rock.test.ms.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lzb.rock.test.open.model.Member;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author lzb123
 * @since 2019-11-12
 */
public interface MemberMapper extends BaseMapper<Member> {
	@Update("update test_member set member_order_num=member_order_num + #{goodsNum},member_order_money=member_order_money + #{goodsMoney} where member_id=#{memberId}")
	public Integer placin(@Param("memberId") Long memberId, @Param("goodsNum") Integer goodsNum,
			@Param("goodsMoney") Integer goodsMoney);
	
	@Update("update test_member set member_order_num=member_order_num - #{goodsNum},member_order_money=member_order_money - #{goodsMoney} where member_id=#{memberId}")
	public Integer rollBack(@Param("memberId") Long memberId, @Param("goodsNum") Integer goodsNum,
			@Param("goodsMoney") Integer goodsMoney);
}
