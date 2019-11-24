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
 * @author lzb123
 * @since 2019-11-12 
 */
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("test_goods_order")
@Data
public class GoodsOrder extends Model<GoodsOrder> {

    private static final long serialVersionUID = 1L;

    /**
     * 会员订单ID
     */
    @TableId(value = "goods_order_id", type = IdType.AUTO)
	@ApiModelProperty(value="会员订单ID")
    private Long goodsOrderId;
    /**
     * 会员ID(分库字段)
     */
    @TableField("member_id")
	@ApiModelProperty(value="会员ID(分库字段)")
    private Long memberId;
    /**
     * 订单商品数量
     */
    @TableField("goods_num")
	@ApiModelProperty(value="订单商品数量")
    private Integer goodsNum;
    /**
     * 订单商品金额
     */
    @TableField("goods_money")
	@ApiModelProperty(value="订单商品金额")
    private Integer goodsMoney;
    
    @TableField("goods_order_status")
	@ApiModelProperty(value="订单状态，0 预下单成功，1下单成功，2回退库存成功，3回退失败")
    private Integer goodsOrderStatus;
    /**
     * 创建时间
     */
    @TableField("create_time")
	@ApiModelProperty(value="创建时间" , hidden = true)
    private Date createTime;
    /**
     * 最后修改时间
     */
    @TableField("last_time")
	@ApiModelProperty(value="最后修改时间" , hidden = true)
    private Date lastTime;
    /**
     * 最后修改人
     */
    @TableField("last_user")
	@ApiModelProperty(value="最后修改人" , hidden = true)
    private String lastUser;
    /**
     * 是否删除0正常 1 删除
     */
    @TableField("is_del")
    @TableLogic
	@ApiModelProperty(value="是否删除0正常 1 删除" , hidden = true)
    private Integer isDel;


    @Override
    protected Serializable pkVal() {
        return this.goodsOrderId;
    }


}
