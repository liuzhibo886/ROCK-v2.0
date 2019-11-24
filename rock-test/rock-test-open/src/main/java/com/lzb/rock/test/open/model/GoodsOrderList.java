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
@TableName("test_goods_order_list")
@Data
public class GoodsOrderList extends Model<GoodsOrderList> {

	private static final long serialVersionUID = 1L;

	@TableId(value = "goods_order_list_id", type = IdType.AUTO)
	@ApiModelProperty(value = "")
	private Long goodsOrderListId;

	/**
	 * 会员订单ID
	 */
	@TableField(value = "goods_order_id")
	@ApiModelProperty(value = "会员订单ID")
	private Long goodsOrderId;
	/**
	 * 会员ID(分库字段)
	 */
	@TableField("member_id")
	@ApiModelProperty(value = "会员ID(分库字段)")
	private Long memberId;

	@TableField("goods_id")
	@ApiModelProperty(value = "商品ID")
	private Long goodsId;
	/**
	 * 商品标题
	 */
	@TableField("goods_title")
	@ApiModelProperty(value = "商品标题")
	private String goodsTitle;
	/**
	 * 商品价格
	 */
	@TableField("goods_price")
	@ApiModelProperty(value = "商品价格")
	private Integer goodsPrice;
	/**
	 * 图片地址
	 */
	@TableField("goods_img_url")
	@ApiModelProperty(value = "图片地址")
	private String goodsImgUrl;
	/**
	 * 赠品
	 */
	@TableField("goods_gift")
	@ApiModelProperty(value = "赠品")
	private String goodsGift;
	/**
	 * 优惠券
	 */
	@TableField("goods_coupon")
	@ApiModelProperty(value = "优惠券")
	private String goodsCoupon;
	/**
	 * 商品地址链接
	 */
	@TableField("goods_url")
	@ApiModelProperty(value = "商品地址链接")
	private String goodsUrl;
	/**
	 * 商品原始ID，分库字段
	 */
	@TableField("goods_qriginal_id")
	@ApiModelProperty(value = "商品原始ID，分库字段")
	private String goodsQriginalId;
	/**
	 * 商品数量
	 */
	@TableField("goods_num")
	@ApiModelProperty(value = "商品数量")
	private Integer goodsNum;
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
		return this.goodsOrderListId;
	}

}
