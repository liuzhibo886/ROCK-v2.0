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
 * 商品销售缓存表
 * </p>
 * @author lzb123
 * @since 2019-11-17 
 */
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("test_goods_sale_cache")
@Data
public class GoodsSaleCache extends Model<GoodsSaleCache> {

    private static final long serialVersionUID = 1L;

    /**
     * 商品销售缓存表
     */
    @TableId(value = "goods_sale_cache_id", type = IdType.AUTO)
	@ApiModelProperty(value="商品销售缓存表")
    private Long goodsSaleCacheId;
    /**
     * 商品订单ID
     */
    @TableField("goods_order_id")
	@ApiModelProperty(value="商品订单ID")
    private Long goodsOrderId;
    /**
     * 商品ID
     */
    @TableField("goods_id")
	@ApiModelProperty(value="商品ID")
    private Long goodsId;
    /**
     * 商品数量
     */
    @TableField("goods_num")
	@ApiModelProperty(value="商品数量")
    private Integer goodsNum;
    /**
     * 0预扣成功，1提交成功，2回退成功，3异常
     */
    @TableField("goods_sale_cache_status")
	@ApiModelProperty(value="0预扣成功，1提交成功，2回退成功，3异常")
    private Integer goodsSaleCacheStatus;
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
     * 是否删除0正常 1 删除
     */
    @TableField("is_del")
    @TableLogic
	@ApiModelProperty(value="是否删除0正常 1 删除" , hidden = true)
    private Integer isDel;


    @Override
    protected Serializable pkVal() {
        return this.goodsSaleCacheId;
    }


}
