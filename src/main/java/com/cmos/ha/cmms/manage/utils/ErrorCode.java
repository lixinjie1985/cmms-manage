package com.cmos.ha.cmms.manage.utils;

/**
 * @author lixinjie
 * @since 2017-12-16
 */
public enum ErrorCode {

	BindingResult(40001, "绑定错误"),
	Success(20001, "成功");
	
	private int code;
	private String desc;
	
	private ErrorCode(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
	
}
