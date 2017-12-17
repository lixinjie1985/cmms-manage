package com.cmos.ha.cmms.manage.utils;

import org.springframework.validation.BindingResult;

import com.cmos.ha.cmms.manage.web.exception.BindingResultException;

/**
 * @author lixinjie
 * @since 2017-12-17
 */
public class BindingResultUtils {

	public static void checkBindingResult(BindingResult bindingResult) {
		if (bindingResult.hasFieldErrors()) {
			throw new BindingResultException(bindingResult);
		}
	}
}
