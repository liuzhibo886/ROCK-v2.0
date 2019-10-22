package com.lzb.rock.base.exception;

import com.lzb.rock.base.facade.RockEnum;

import lombok.Data;

/**
 * 封装rock的异常
 *
 * @author lzb
 * @Date 2017/12/28 下午10:32
 */
@Data
public class RockException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String code;

	private String message;

	String data;

	public RockException(RockEnum busEnum) {
		this.code = busEnum.getCode();
		this.message = busEnum.getMsg();
	}

	public RockException(RockEnum busEnum, String message) {
		this.code = busEnum.getCode();
		this.message = message;
	}

	public RockException(RockEnum busEnum, String message, String data) {
		this.code = busEnum.getCode();
		this.message = message;
		this.data = data;
	}

}
