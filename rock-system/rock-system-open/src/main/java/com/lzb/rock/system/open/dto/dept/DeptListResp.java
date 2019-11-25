package com.lzb.rock.system.open.dto.dept;

import lombok.Data;
import com.lzb.rock.system.open.model.Dept;

import io.swagger.annotations.ApiModelProperty;

/**
 * 部门表返回参数
 *
 * @author lzb
 * @Date 2019-10-24 21:51:04
 */
@Data
public class DeptListResp extends Dept {

	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "父部门全称")
	private String deptPFullName;
}