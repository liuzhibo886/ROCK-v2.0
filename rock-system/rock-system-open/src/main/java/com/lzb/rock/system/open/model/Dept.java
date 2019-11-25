package com.lzb.rock.system.open.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableLogic;
import java.io.Serializable;

/**
 * <p>
 * 部门表
 * </p>
 * 
 * @author lzb123
 * @since 2019-10-26
 */
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_dept")
@Data
public class Dept extends Model<Dept> {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@TableId(value = "dept_id", type = IdType.AUTO)
	@ApiModelProperty(value = "主键id")
	private Long deptId;
	/**
	 * 部门编码，唯一不能重复
	 */
	@TableField("dept_code")
	@ApiModelProperty(value = "部门编码，唯一不能重复")
	@NotBlank(message = "部门编码不能为空")
	private String deptCode;
	/**
	 * 父部门编码
	 */
	@TableField("dept_pcode")
	@ApiModelProperty(value = "父部门编码")
	@NotBlank(message = "父部门不能为空")
	private String deptPcode;
	/**
	 * 部门简称
	 */
	@TableField("dept_simple_name")
	@ApiModelProperty(value = "部门简称")
	@NotBlank(message = "部门简称不能为空")
	private String deptSimpleName;
	/**
	 * 部门全称
	 */
	@TableField("dept_full_name")
	@ApiModelProperty(value = "部门全称")
	@NotBlank(message = "部门全称不能为空")
	private String deptFullName;
	/**
	 * 备注
	 */
	@ApiModelProperty(value = "备注")
	private String remarks;
	/**
	 * 是否删除0正常 1 删除
	 */
	@TableField("is_del")
	@TableLogic
	@ApiModelProperty(value = "是否删除0正常 1 删除", hidden = true)
	private Integer isDel;
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

	@Override
	protected Serializable pkVal() {
		return this.deptId;
	}

}
