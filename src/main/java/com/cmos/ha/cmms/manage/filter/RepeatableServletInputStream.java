package com.cmos.ha.cmms.manage.filter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletInputStream;

/**
 * @author lixinjie
 * @since 2017-09-12
 */
public class RepeatableServletInputStream extends ServletInputStream {

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
