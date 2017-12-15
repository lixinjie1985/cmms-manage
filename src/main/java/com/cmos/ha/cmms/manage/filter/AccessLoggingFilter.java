package com.cmos.ha.cmms.manage.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lixinjie
 * @since 2017-09-12
 */
public class AccessLoggingFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		RepeatableHttpServletRequest rrequest = new RepeatableHttpServletRequest((HttpServletRequest)request);
		RepeatableHttpServletResponse rresponse = new RepeatableHttpServletResponse((HttpServletResponse)response);
		System.out.println(rrequest.getMethod() + " " + rrequest.getRequestURL());
		System.out.println(rrequest.getContentType());
		System.out.println(rrequest.getBody());
		chain.doFilter(rrequest, rresponse);
		System.out.println(rresponse.getBody());
	}

	@Override
	public void destroy() {
		
	}

}
