package com.lzb.rock.system.open.model;

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
 * 权限表
 * </p>
 * @author lzb123
 * @since 2019-10-31 
 */
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_authz")
@Data
public class Authz extends Model<Authz> {

    private static final long serialVersionUID = 1L;

    /**
     * 权限ID
     */
    @TableId(value = "authz_id", type = IdType.AUTO)
	@ApiModelProperty(value="权限ID")
    private Long authzId;
    /**
     * 权限编码
     */
    @TableField("authz_code")
	@ApiModelProperty(value="权限编码")
    private String authzCode;
    /**
     * 权限父编码
     */
    @TableField("authz_parent_code")
	@ApiModelProperty(value="权限父编码")
    private String authzParentCode;
    /**
     * 权限名称
     */
    @TableField("authz_name")
	@ApiModelProperty(value="权限名称")
    private String authzName;
    /**
     * 权限图片，菜单权限专用
     */
    @TableField("authz_icon")
	@ApiModelProperty(value="权限图片，菜单权限专用")
    private String authzIcon;
    /**
     * 权限url地址，菜单权限专用
     */
    @TableField("authz_url")
	@ApiModelProperty(value="权限url地址，菜单权限专用")
    private String authzUrl;
    /**
     * 权限排序，升序
     */
    @TableField("authz_sort")
	@ApiModelProperty(value="权限排序，升序")
    private Integer authzSort;
    /**
     * 权限类型，0菜单，1按钮;2字段
     */
    @TableField("authz_type")
	@ApiModelProperty(value="权限类型，0菜单，1按钮;2字段")
    private Integer authzType;
    /**
     * 备注
     */
    @TableField("authz_tips")
	@ApiModelProperty(value="备注")
    private String authzTips;
    /**
     * 菜单状态 :  0:启用   1:不启用
     */
    @TableField("authz_status")
	@ApiModelProperty(value="菜单状态 :  0:启用   1:不启用")
    private Integer authzStatus;
    /**
     * 是否打开:    1:打开   0:不打开；菜单权限专用
     */
    @TableField("authz_is_open")
	@ApiModelProperty(value="是否打开:    1:打开   0:不打开；菜单权限专用")
    private Integer authzIsOpen;
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
        return this.authzId;
    }


}
