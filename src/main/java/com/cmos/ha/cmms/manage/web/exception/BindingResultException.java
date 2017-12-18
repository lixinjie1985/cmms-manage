package com.cmos.ha.cmms.manage.web.exception;

import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.cmos.ha.cmms.common.exception.CmmsException;
import com.cmos.ha.cmms.common.utils.ResultCode;

/**
 * @author lixinjie
 * @since 2017-12-16
 */
@SuppressWarnings("serial")
public class BindingResultException extends CmmsException {

	private BindingResult bindingResult;
	
	public BindingResultException(BindingResult bindingResult) {
		super(getErrorMessage(bindingResult));
		this.bindingResult = bindingResult;
	}
	
	@Override
	public int getCode() {
		return getErrorCode();
	}

	@Override
	public String getDesc() {
		return getFieldErrorDesc(bindingResult);
	}
	
	private static String getErrorMessage(BindingResult bindingResult) {
		return getErrorCode() + " " + getFieldErrorDesc(bindingResult);
	}
	
	private static int getErrorCode() {
		return ResultCode.FieldBindingError.getCode();
	}

	private static String getFieldErrorDesc(BindingResult bindingResult) {
		StringBuilder sb = new StringBuilder();
		sb.append(ResultCode.FieldBindingError.getDesc()).append("：");
		List<FieldError> fieldErrors = bindingResult.getFieldErrors();
		for (FieldError fieldError : fieldErrors) {
			sb.append(fieldError.getObjectName()).append("的")
				.append(fieldError.getField()).append("值'")
				.append(fieldError.getRejectedValue()).append("'非法，")
				.append(fieldError.getDefaultMessage()).append("；");
		}
		return sb.toString();
	}
}
