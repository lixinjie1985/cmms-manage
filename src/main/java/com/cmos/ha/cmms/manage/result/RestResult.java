package com.cmos.ha.cmms.manage.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * @author lixinjie
 * @since 2017-12-16
 */
@JsonRootName("result")
public class RestResult {

	private int code;
	private String desc;
	@JsonInclude(Include.NON_NULL)
	private Object data;
	
	public RestResult(int code, String desc) {
		this(code, desc, null);
	}
	
	public RestResult(int code, String desc, Object data) {
		this.code = code;
		this.desc = desc;
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	public Object getData() {
		return data;
	}
	
}
