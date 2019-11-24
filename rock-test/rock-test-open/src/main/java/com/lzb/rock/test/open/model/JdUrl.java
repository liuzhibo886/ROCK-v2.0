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
 * 京东url列表
 * </p>
 * @author lzb123
 * @since 2019-11-12 
 */
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("jd_url")
@Data
public class JdUrl extends Model<JdUrl> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "jd_url_id", type = IdType.AUTO)
	@ApiModelProperty(value="主键ID")
    private Long jdUrlId;
    /**
     * url地址
     */
    @TableField("jd_url_text")
	@ApiModelProperty(value="url地址")
    private String jdUrlText;
    /**
     * url的MD5值，用来去重
     */
    @TableField("jd_url_md5")
	@ApiModelProperty(value="url的MD5值，用来去重")
    private String jdUrlMd5;
    /**
     * 状态，0未爬取，1爬取中，2爬取成功，3，爬取失败，4舍弃
     */
    @TableField("jd_url_status")
	@ApiModelProperty(value="状态，0未爬取，1爬取中，2爬取成功，3，爬取失败，4舍弃")
    private Integer jdUrlStatus;
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
        return this.jdUrlId;
    }


}
