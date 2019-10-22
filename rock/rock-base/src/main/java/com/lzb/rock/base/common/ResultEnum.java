package com.lzb.rock.base.common;

import com.google.common.collect.ImmutableMap;
import com.lzb.rock.base.facade.RockEnum;

/**
 * 状态枚举类
 * 
 * @author lzb 2018年2月1日 下午3:50:38
 */
public enum ResultEnum implements RockEnum {

	// 系统级别错误码,系统级别错误码小于1000
	SUCCESS("0", "操作成功"),

	NET_WORK_ERR("1001", "网络异常"),

	PAEAM_ERR("1002", "参数异常"),

	INSERT_ERR("1003", "插入失败"),

	UPDATE_ERR("1004", "更新失败"),

	SELECT_ERR("1005", "查询失败"),

	SIGN_ERR("1006", "签名异常"),

	JSON_ERR("1007", "JSON异常"),

	WECHAT_TOKEN_ERR("1008", "获取token异常"),

	TYPE_ERR("1009", "类型异常"),

	MANY_ERR("1010", "已到达最大数量"),

	DELETE_ERR("1011", "删除失败"),

	SMS_SEND_ERR("1012", "验证码发送失败"),

	SMS_SIGN_ERR("1013", "短信签名不存在"),

	SMS_TEXT_ERR("1014", "短信内容不符合规定"),

	DATA_REPEAT_ERR("1015", "数据已存在"),

	FILE_UPLOAD_ERR("1016", "文件上传失败"),

	FILE_READING_ERROR("1017", "文件读写异常"),

	FILE_NOT_FOUND("1018", "文件不存在"),
	
	DATA_ERR("1019", "数据异常"),

	
	//-------------------------------------------------------------------------------
	SYSTTEM_ERR("-1", "未知异常"),

	RUNTIME_ERR("-2", "未知的运行时异常"),

	REST_ERR("-3", "远程调用异常"),

	ENUM_ERR("-4", "枚举异常"),

	AOP_ERR("-5", "AOP异常"),

	STATUS_ERR("-6", "请求码异常");

	private String code;
	private String msg;

	ResultEnum(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	/**
	 * 枚举类型的判断和获取
	 * 
	 * @param code 错误码
	 * @return 返回错误码对应的枚举信息
	 */
	public static ResultEnum statusOf(String code) {
		for (ResultEnum resultEnum : values()) {
			if (resultEnum.getCode().equals(code)) {
				return resultEnum;
			}
		}
		return null;
	}

	@Override
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg == null ? "" : msg.trim();
	}

	/**
	 * 枚举转换为MAP
	 * 
	 * @return
	 */
	public ImmutableMap<String, String> toMap() {
		return ImmutableMap.<String, String>builder().put("msg", msg).put("code", code).build();
	}
}
