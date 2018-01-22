package com.atgeretg.util.url;

import java.io.UnsupportedEncodingException;

public class UrlUtil {
	private final static String ENCODE = "UTF8";

	/**
	 * URL 解码，默认编码格式UFT8
	 * 
	 * @param str
	 *            转码字符
	 * @return String
	 */
	public static String getURLDecoderString(String str) {
		return getURLDecoderString(str, ENCODE);
	}

	/**
	 * URL 解码，默认编码格式UFT8
	 * 
	 * @param str
	 *            转码字符
	 * @param timce
	 *            解码次数
	 * @return String
	 */
	public static String getURLDecoderString(String str, int timce) {
		return getURLDecoderString(str, ENCODE, timce);
	}

	/**
	 * URL 解码
	 * 
	 * @param str
	 *            转码字符
	 * @param encode
	 *            编码格式
	 * @return
	 */
	public static String getURLDecoderString(String str, String encode) {
		return getURLDecoderString(str, encode, 1);
	}

	/**
	 * URL 解码
	 * 
	 * @param str
	 *            转码字符
	 * @param encode
	 *            编码格式
	 * @param timce
	 *            解码次数
	 * @return
	 */
	public static String getURLDecoderString(String str, String encode, int timce) {
		String result = "";
		if (str == null) {// 不能去" "（空字符）
			return "";
		}
		try {
			result = java.net.URLDecoder.decode(str, encode);
			for (int i = 1; i < timce; i++) 
				result = java.net.URLDecoder.decode(result, encode);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * URL 转码,默认编码格式UFT8
	 * 
	 * @param str
	 *            转码字符
	 * @return String
	 */
	public static String getURLEncoderString(String str) {
		return getURLEncoderString(str, ENCODE);
	}

	/**
	 * URL 转码,默认编码格式UFT8
	 * 
	 * @param str
	 *            转码字符
	 * @param timce
	 *            转码次数
	 * @return String
	 */
	public static String getURLEncoderString(String str, int timce) {
		return getURLEncoderString(str, ENCODE, timce);
	}

	/**
	 * URL 转码
	 * 
	 * @param str
	 *            转码字符
	 * @param encode
	 *            编码格式
	 * @return String 没 null
	 */
	public static String getURLEncoderString(String str, String encode) {
		return getURLEncoderString(str,encode,1);
	}

	/**
	 * URL 转码
	 * 
	 * @param str
	 *            转码字符
	 * @param encode
	 *            编码格式
	 * @param timce
	 *            转码次数
	 * @return String 没null
	 */
	public static String getURLEncoderString(String str, String encode, int timce) {
		String result = "";
		if (str == null) {// 不能去" "（空字符）
			return "";
		}
		try {
			result = java.net.URLEncoder.encode(str, encode);
			for(int i = 1; i < timce; i++)
				result = java.net.URLEncoder.encode(result, encode);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) {
		String str = "测试1";
		String s = "%25E5%2595%2586%25E5%2593%2581%25E4%25B8%258A%25E6%259E%25B61-15.jar";
		System.out.println(getURLEncoderString(s));
		System.out.println(getURLDecoderString(s,2));

	}
}
