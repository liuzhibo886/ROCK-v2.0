package com.lzb.rock.system.open.model;

import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 管理员表
 * </p>
 * 
 * @author lzb123
 * @since 2019-09-15
 */
@TableName("sys_user")
@Data
public class User extends Model<User> {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	@TableId(value = "user_id", type = IdType.AUTO)
	@ApiModelProperty(value = "用户ID")
	private Long userId;
	/**
	 * 用户头像base64格式
	 */
	@TableField("user_avatar")
	@ApiModelProperty(value = "用户头像base64格式")
	private String userAvatar;
	/**
	 * 用户账号
	 */
	@TableField("user_account")
	@ApiModelProperty(value = "用户账号")
	private String userAccount;
	/**
	 * 用户密码
	 */
	@TableField("user_password")
	@ApiModelProperty(value = "用户密码")
	private String userPassword;
	/**
	 * 用户md5密码盐
	 */
	@TableField("user_salt")
	@ApiModelProperty(value = "用户md5密码盐")
	private String userSalt;
	/**
	 * 用户名字
	 */
	@TableField("user_name")
	@ApiModelProperty(value = "用户名字")
	private String userName;
	/**
	 * 用户生日
	 */
	@TableField("user_birthday")
	@ApiModelProperty(value = "用户生日")
	private String userBirthday;
	/**
	 * 性别（1：男 2：女）
	 */
	@TableField("user_sex")
	@ApiModelProperty(value = "性别（1：男 2：女）")
	private Integer userSex;
	/**
	 * 用户电子邮件
	 */
	@TableField("user_email")
	@ApiModelProperty(value = "用户电子邮件")
	private String userEmail;
	/**
	 * 用户电话
	 */
	@TableField("user_phone")
	@ApiModelProperty(value = "用户电话")
	private String userPhone;
	/**
	 * 用户所属角色id,多个#分割,前后都要包含#
	 */
	@TableField("role_ids")
	@ApiModelProperty(value = "用户所属角色id,多个#分割,前后都要包含#")
	private String roleIds;
	/**
	 * 用户所属角色名称,json数组，包含字段roleId,roleName
	 */
	@TableField("role_names")
	@ApiModelProperty(value = "用户所属角色名称,json数组，包含字段roleId,roleName")
	private String roleNames;
	/**
	 * 用户所属部门id,多个#分割,前后都要包含#
	 */
	@TableField("dept_ids")
	@ApiModelProperty(value = "用户所属部门id,多个#分割,前后都要包含#")
	private String deptIds;
	/**
	 * 用户所属部名词,json数组包含deptid,deptName
	 */
	@TableField("dept_names")
	@ApiModelProperty(value = "用户所属部名词,json数组包含deptid,deptName")
	private String deptNames;
	/**
	 * 状态(1：启用 2：冻结 ）
	 */
	@TableField("user_status")
	@ApiModelProperty(value = "状态(1：启用  2：冻结 ）")
	private Integer userStatus;
	/**
	 * 创建时间
	 */
	@TableField("create_time")
	@ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**
	 * 最后修改人
	 */
	@TableField("last_time")
	@ApiModelProperty(value = "最后修改人")
	private Date lastTime;
	/**
	 * 最后修改人
	 */
	@TableField("last_user")
	@ApiModelProperty(value = "最后修改人")
	private String lastUser;
	/**
	 * 是否删除0正常 1 删除
	 */
	@TableField("is_del")
	@ApiModelProperty(value = "是否删除0正常 1 删除")
	private Integer isDel;

	@Override
	protected Serializable pkVal() {
		return this.userId;
	}

}
