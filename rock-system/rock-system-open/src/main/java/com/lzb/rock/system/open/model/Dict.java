package com.lzb.rock.system.open.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.Version;
import com.baomidou.mybatisplus.enums.IdType;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统字典表(全局表)
 * </p>
 * 
 * @author lzb123
 * @since 2019-10-16
 */
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_dict")
@Data
public class Dict extends Model<Dict> implements Comparable<Dict> {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@TableId(value = "dict_id", type = IdType.AUTO)
	@ApiModelProperty(value = "主键ID")
	private Long dictId;
	/**
	 * 字典编码
	 */
	@TableField("dict_code")
	@ApiModelProperty(value = "字典编码")
	@NotBlank(message = "字典编码不能为空")
	private String dictCode;
	/**
	 * 父节key值
	 */
	@TableField("dict_pkey")
	@ApiModelProperty(value = "父节key值")
	@NotNull(message = "父节key不能为空")
	private Long dictPkey;
	/**
	 * 字典key值
	 */
	@TableField("dict_key")
	@ApiModelProperty(value = "字典key值")
	private Long dictKey;
	/**
	 * 字典value值
	 */
	@TableField("dict_value")
	@ApiModelProperty(value = "字典value值")
	private String dictValue;
	/**
	 * 字典级别，0 开始
	 */
	@TableField("dict_level")
	@ApiModelProperty(value = "字典级别，0 开始")
	private Integer dictLevel;
	/**
	 * 最大等级
	 */
	@TableField("dict_max_level")
	@ApiModelProperty(value = "最大等级")
	private Integer dictMaxLevel;
	/**
	 * 文本内容，根据需要填写
	 */
	@TableField("dict_text")
	@ApiModelProperty(value = "文本内容，根据需要填写")
	private String dictText;
	/**
	 * 排序字段，升序
	 */
	@ApiModelProperty(value = "排序字段，升序")
	private Integer sort;
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
	 * 最后修改人
	 */
	@TableField("last_time")
	@ApiModelProperty(value = "最后修改人", hidden = true)
	private Date lastTime;
	/**
	 * 最后修改人
	 */
	@TableField("last_user")
	@ApiModelProperty(value = "最后修改人", hidden = true)
	private String lastUser;

	@Override
	protected Serializable pkVal() {
		return this.dictId;
	}

	@Override
	public int compareTo(Dict arg0) {
		if (this.sort == null) {
			this.sort = 0;
		}
		if (arg0.sort == null) {
			arg0.sort = 0;
		}
		return arg0.getSort().compareTo(this.getSort());
	}
}
