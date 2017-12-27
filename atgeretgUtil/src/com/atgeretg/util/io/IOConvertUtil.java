package com.atgeretg.util.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class IOConvertUtil {
	/**
	 * inputStream转outputStream
	 * 
	 * @param in
	 * @return
	 * @throws Exception
	 */
	public static ByteArrayOutputStream parse(InputStream in) throws Exception {
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
		byte[] b = new byte[1024];
		int ch = -1;
		while ((ch = in.read(b)) != -1) {
			swapStream.write(b, 0, ch);
		}
		return swapStream;
	}

	/**
	 * outputStream转inputStream
	 * 
	 * @param out
	 * @return
	 * @throws Exception
	 */
	public static ByteArrayInputStream parse(OutputStream out) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos = (ByteArrayOutputStream) out;
		ByteArrayInputStream swapStream = new ByteArrayInputStream(baos.toByteArray());
		return swapStream;
	}

	
	/**
	 * inputStream转String
	 * 
	 * @param in
	 * @return
	 * @throws Exception
	 */
	public String parse_String(InputStream in) throws Exception {
		ByteArrayOutputStream swapStream = parse(in);
		return swapStream.toString();
	}

	/**
	 * OutputStream 转String
	 * 
	 * @param out
	 * @return
	 * @throws Exception
	 */
	public String parse_String(OutputStream out) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos = (ByteArrayOutputStream) out;
		ByteArrayInputStream swapStream = new ByteArrayInputStream(baos.toByteArray());
		return swapStream.toString();
	}

	/**
	 * String转inputStream
	 * 
	 * @param in
	 * @return
	 * @throws Exception
	 */
	public ByteArrayInputStream parse_inputStream(String in) throws Exception {
		ByteArrayInputStream input = new ByteArrayInputStream(in.getBytes());
		return input;
	}

	/**
	 * String 转outputStream
	 * 
	 * @param string
	 * @return
	 * @throws Exception
	 */
	public ByteArrayOutputStream parse_outputStream(String string) throws Exception {
		return parse(parse_inputStream(string));
	}
}
