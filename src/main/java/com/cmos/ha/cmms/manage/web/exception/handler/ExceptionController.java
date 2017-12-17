package com.cmos.ha.cmms.manage.web.exception.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.cmos.ha.cmms.manage.result.RestResult;
import com.cmos.ha.cmms.manage.web.exception.BindingResultException;

/**
 * @author lixinjie
 * @since 2017-12-16
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@RestController
public class ExceptionController {

	private static final Logger log = LoggerFactory.getLogger(ExceptionController.class);
			
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public RestResult handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
		log.error("", ex);
		return new RestResult(405, "Method Not Allowed");
	}
	
	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public RestResult handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex) {
		log.error("", ex);
		return new RestResult(415, "Unsupported Media Type");
	}
	
	@ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
	public RestResult handleHttpMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException ex) {
		log.error("", ex);
		return new RestResult(406, "Not Acceptable");
	}
	
	@ExceptionHandler(MissingPathVariableException.class)
	public RestResult handleMissingPathVariableException(MissingPathVariableException ex) {
		log.error("", ex);
		return new RestResult(500, "Internal Server Error");
	}
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public RestResult handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
		log.error("", ex);
		return new RestResult(400, "Bad Request");
	}
	
	@ExceptionHandler(ServletRequestBindingException.class)
	public RestResult handleServletRequestBindingException(ServletRequestBindingException ex) {
		log.error("", ex);
		return new RestResult(400, "Bad Request");
	}
	
	@ExceptionHandler(ConversionNotSupportedException.class)
	public RestResult handleConversionNotSupportedException(ConversionNotSupportedException ex) {
		log.error("", ex);
		return new RestResult(500, "Internal Server Error");
	}
	
	@ExceptionHandler(TypeMismatchException.class)
	public RestResult handleTypeMismatchException(TypeMismatchException ex) {
		log.error("", ex);
		return new RestResult(400, "Bad Request");
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public RestResult handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
		log.error("", ex);
		return new RestResult(400, "Bad Request");
	}
	
	@ExceptionHandler(HttpMessageNotWritableException.class)
	public RestResult handleHttpMessageNotWritableException(HttpMessageNotWritableException ex) {
		log.error("", ex);
		return new RestResult(500, "Internal Server Error");
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public RestResult handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		log.error("", ex);
		return new RestResult(400, "Bad Request");
	}
	
	@ExceptionHandler(MissingServletRequestPartException.class)
	public RestResult handleMissingServletRequestPartException(MissingServletRequestPartException ex) {
		log.error("", ex);
		return new RestResult(400, "Bad Request");
	}
	
	@ExceptionHandler(BindException.class)
	public RestResult handleBindException(BindException ex) {
		log.error("", ex);
		return new RestResult(400, "Bad Request");
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public RestResult handleNoHandlerFoundException(NoHandlerFoundException ex) {
		log.error("", ex);
		return new RestResult(404, "Not Found");
	}
	
	@ExceptionHandler(AsyncRequestTimeoutException.class)
	public RestResult handleAsyncRequestTimeoutException(AsyncRequestTimeoutException ex) {
		log.error("", ex);
		return new RestResult(503, "Service Unavailable");
	}
	
	@ExceptionHandler(BindingResultException.class)
	public RestResult handleBindingResultException(BindingResultException ex) {
		log.error("", ex);
		return new RestResult(ex.getCode(), ex.getDesc());
	}
	
	@ExceptionHandler(Exception.class)
	public RestResult handleException(Exception ex) {
		log.error("", ex);
		return new RestResult(500, "Internal Server Error");
	}
	
	@ExceptionHandler(Throwable.class)
	public RestResult handleThrowable(Throwable ex) {
		log.error("", ex);
		return new RestResult(500, "Internal Server Error");
	}
}
