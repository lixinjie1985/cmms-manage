package com.cmos.ha.cmms.manage.filter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * @author lixinjie
 * @since 2017-09-13
 */
public class RepeatableHttpServletResponse extends HttpServletResponseWrapper {

	private RepeatableServletOutputStream outputStream;
	private PrintWriter writer;
	
	public RepeatableHttpServletResponse(HttpServletResponse response) throws IOException {
		super(response);
		outputStream = new RepeatableServletOutputStream(response.getOutputStream());
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		return outputStream;
	}
	
	@Override
	public PrintWriter getWriter() throws IOException {
		if (writer == null) {
			writer = new PrintWriter(outputStream);
		}
		return writer;
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
		return new BufferedReader(new InputStreamReader(new ByteArrayInputStream(outputStream.getDatas()), charset != null ? charset : "UTF-8"));
	}
}
