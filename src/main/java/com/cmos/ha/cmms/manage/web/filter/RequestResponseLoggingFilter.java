package com.cmos.ha.cmms.manage.web.filter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * @author lixinjie
 * @since 2017-12-15
 */
public class RequestResponseLoggingFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		String method = httpRequest.getMethod();
		String contentType = httpRequest.getContentType();
		System.out.println(method + " " + httpRequest.getRequestURL() + " " + contentType);
		if (!isFileUpload(method, contentType)) {
			RepeatableHttpServletRequest repeatableRequest = new RepeatableHttpServletRequest(httpRequest);
			System.out.println(repeatableRequest.getBody());
			httpRequest = repeatableRequest;
		}
		RepeatableHttpServletResponse repeatableResponse = new RepeatableHttpServletResponse((HttpServletResponse)response);
		chain.doFilter(httpRequest, repeatableResponse);
		String contentDisposition = repeatableResponse.getHeader("Content-Disposition");
		contentType = repeatableResponse.getContentType();
		System.out.println(contentDisposition + " " + contentType);
		if (!isFileDownload(contentDisposition, contentType)) {
			System.out.println(repeatableResponse.getBody());
		}
	}

	@Override
	public void destroy() {
		
	}

	private boolean isFileUpload(String method, String contentType) {
		if (!"post".equalsIgnoreCase(method)) {
			return false;
		}
		return (contentType != null && contentType.toLowerCase().startsWith("multipart/"));
	}
	
	private boolean isFileDownload(String contentDisposition, String contentType) {
		return (contentDisposition != null && contentDisposition.toLowerCase().contains("attachment"));
	}
	
	
	
	private static class RepeatableHttpServletRequest extends HttpServletRequestWrapper {

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
	
	private static class RepeatableHttpServletResponse extends HttpServletResponseWrapper {

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
	
	private static class RepeatableServletInputStream extends ServletInputStream {

		private ByteArrayInputStream in;
		private byte[] datas;
		
		public RepeatableServletInputStream(ServletInputStream inputStream) throws IOException {
			ByteArrayOutputStream out = new ByteArrayOutputStream(1024 * 10);
			byte[] buf = new byte[1024];
			int count;
			while ((count = inputStream.read(buf, 0, buf.length)) > 0) {
				out.write(buf, 0, count);
			}
			datas = out.toByteArray();
			in = new ByteArrayInputStream(datas);
			inputStream.close();
			out.close();
		}
		
		@Override
		public int read() throws IOException {
			return in.read();
		}

		@Override
		public long skip(long n) throws IOException {
			return in.skip(n);
		}
		
		@Override
		public int available() throws IOException {
			return in.available();
		}
		
		@Override
		public void close() throws IOException {
			in.close();
		}
		
		@Override
		public synchronized void mark(int readlimit) {
			in.mark(readlimit);
		}
		
		@Override
		public synchronized void reset() throws IOException {
			in.reset();
		}
		
		@Override
		public boolean markSupported() {
			return in.markSupported();
		}
		
		public byte[] getDatas() {
			return datas;
		}
	}

	private static class RepeatableServletOutputStream extends ServletOutputStream {

		private ServletOutputStream outputStream;
		private ByteArrayOutputStream output;
		
		public RepeatableServletOutputStream(ServletOutputStream outputStream) {
			this.outputStream = outputStream;
			this.output = new ByteArrayOutputStream(1024 * 10);
		}
		
		@Override
		public void write(int b) throws IOException {
			outputStream.write(b);
			output.write(b);
		}
		
		@Override
		public void flush() throws IOException {
			outputStream.flush();
			output.flush();
		}

		@Override
		public void close() throws IOException {
			outputStream.close();
			output.close();
		}
		
		public byte[] getDatas() {
			return output.toByteArray();
		}
	}

}
