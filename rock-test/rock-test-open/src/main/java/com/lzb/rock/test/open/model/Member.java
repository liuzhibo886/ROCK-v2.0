package com.lzb.rock.test.open.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.Version;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 * 
 * @author lzb123
 * @since 2019-11-12
 */
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("test_member")
@Data
public class Member extends Model<Member> {

	private static final long serialVersionUID = 1L;

	/**
	 * 会员ID
	 */
	@TableId(value = "member_id", type = IdType.AUTO)
	@ApiModelProperty(value = "会员ID")
	private Long memberId;
	/**
	 * 会员昵称
	 */
	@TableField("member_name")
	@ApiModelProperty(value = "会员昵称")
	private String memberName;
	/**
	 * 订单数
	 */
	@TableField("member_order_num")
	@ApiModelProperty(value = "订单数")
	private Integer memberOrderNum;
	/**
	 * 订单总金额
	 */
	@TableField("member_order_money")
	@ApiModelProperty(value = "订单总金额")
	private Long memberOrderMoney;
	/**
	 * 创建时间
	 */
	@TableField("create_time")
	@ApiModelProperty(value = "创建时间", hidden = true)
	private Date createTime;
	/**
	 * 最后修改时间
	 */
	@TableField("last_time")
	@ApiModelProperty(value = "最后修改时间", hidden = true)
	private Date lastTime;
	/**
	 * 最后修改人
	 */
	@TableField("last_user")
	@ApiModelProperty(value = "最后修改人", hidden = true)
	private String lastUser;
	/**
	 * 是否删除0正常 1 删除
	 */
	@TableField("is_del")
	@TableLogic
	@ApiModelProperty(value = "是否删除0正常 1 删除", hidden = true)
	private Integer isDel;

	@Override
	protected Serializable pkVal() {
		return this.memberId;
	}

}
