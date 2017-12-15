package com.cmos.ha.cmms.manage.filter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @author lixinjie
 * @since 2017-09-12
 */
public class RepeatableHttpServletRequest extends HttpServletRequestWrapper {

	private RepeatableServletInputStream inputStream;
	private BufferedReader reader;
	
	public RepeatableHttpServletRequest(HttpServletRequest request) throws IOException {
		super(request);
		inputStream = new RepeatableServletInputStream(request.getInputStream());
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		return inputStream;
	}
	
	@Override
	public BufferedReader getReader() throws IOException {
		if (reader == null) {
			reader = getNewReader();
		}
		return reader;
	}
	
	public String getBody() throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = getNewReader();
		char[] buf = new char[1024];
		int count;
		while ((count = reader.read(buf, 0, buf.length)) > 0) {
			sb.append(buf, 0, count);
		}
		reader.close();
		return sb.toString();
	}
	
	private BufferedReader getNewReader() throws UnsupportedEncodingException {
		String charset = getCharacterEncoding();
		return new BufferedReader(new InputStreamReader(new ByteArrayInputStream(inputStream.getDatas()), charset != null ? charset : "UTF-8"));
	}
}
