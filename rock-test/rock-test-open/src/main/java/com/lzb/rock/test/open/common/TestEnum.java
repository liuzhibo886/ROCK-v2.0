package com.lzb.rock.test.open.common;

import com.lzb.rock.base.facade.RockEnum;

/**
 * 状态枚举类
 * 
 * @author lzb 2018年2月1日 下午3:50:38
 */
public enum TestEnum implements RockEnum {

	/**
	 * 字典key异常
	 */
	DICT_KEY_ERR("2001", "字典key异常"),
	/**
	 * 字典编码已存在
	 */
	CODE_REPEAT_ERR("2002", "字典编码已存在"),
	/**
	 * 字典编码为空
	 */
	DICT_CODE_IS_NULL_ERR("2003", "字典编码为空"),
	/**
	 * 字典达到最大值
	 */
	DICT_LEVEL_MAX_ERR("2004", "字典等级已达到最大值"),
	/**
	 * 最大枚举值
	 */
	zzz("2999", "最大枚举");

	private String code;
	private String msg;

	TestEnum(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	/**
	 * 枚举类型的判断和获取
	 * 
	 * @param code 错误码
	 * @return 返回错误码对应的枚举信息
	 */
	public static TestEnum statusOf(String code) {
		for (TestEnum error : values()) {
			if (error.getCode() == code) {
				return error;
			}
		}
		return null;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg == null ? "" : msg.trim();
	}

}
