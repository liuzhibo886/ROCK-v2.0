package com.lzb.rock.system.open.dto.dict;

import lombok.Data;
import com.lzb.rock.base.model.PageReq;

import io.swagger.annotations.ApiModelProperty;

/**
 * 系统字典表(全局表)请求参数
 *
 * @author lzb
 * @Date 2019-09-27 14:15:30
 */
@Data
public class DictListReq extends PageReq{

	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value="字典编码")
    private String dictCode;
	
	@ApiModelProperty(value="父节key值")
    private Long dictPkey;
}
