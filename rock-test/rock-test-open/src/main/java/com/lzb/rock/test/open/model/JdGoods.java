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
 * 京东商品信息表
 * </p>
 * @author lzb123
 * @since 2019-11-12 
 */
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("jd_goods")
@Data
public class JdGoods extends Model<JdGoods> {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    @TableId(value = "jd_goods_id", type = IdType.AUTO)
	@ApiModelProperty(value="自增主键")
    private Long jdGoodsId;
    /**
     * 商品标题
     */
    @TableField("jd_goods_title")
	@ApiModelProperty(value="商品标题")
    private String jdGoodsTitle;
    /**
     * 商品价格
     */
    @TableField("jd_goods_price")
	@ApiModelProperty(value="商品价格")
    private Integer jdGoodsPrice;
    /**
     * 图片地址
     */
    @TableField("jd_goods_img_url")
	@ApiModelProperty(value="图片地址")
    private String jdGoodsImgUrl;
    /**
     * 赠品
     */
    @TableField("jd_goods_gift")
	@ApiModelProperty(value="赠品")
    private String jdGoodsGift;
    /**
     * 优惠券
     */
    @TableField("jd_goods_coupon")
	@ApiModelProperty(value="优惠券")
    private String jdGoodsCoupon;
    /**
     * 商品地址链接
     */
    @TableField("jd_goods_url")
	@ApiModelProperty(value="商品地址链接")
    private String jdGoodsUrl;
    /**
     * 商品原始ID，分库字段
     */
    @TableField("jd_goods_qriginal_id")
	@ApiModelProperty(value="商品原始ID，分库字段")
    private String jdGoodsQriginalId;
    /**
     * 商品销售数量
     */
    @TableField("jd_goods_sale_num")
	@ApiModelProperty(value="商品销售数量")
    private Integer jdGoodsSaleNum;
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
        return this.jdGoodsId;
    }


}
