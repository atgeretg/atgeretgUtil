package com.atgeretg.util.encode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EncodeUtil {

//	public static void main(String[] args) {
//		String text = "时顺会计法“诶发";
////		text = "{\"rs_data\":[{\"value\":\"1\",\"text\":\"北京\",\"children\":[{\"value\":\"2812\",\"text\":\"顺义区\",\"children\":[{\"value\":\"51130\",\"text\":\"光明街道\"}";
//		String gbEncoding = gbEncoding(text);
//		System.out.println(gbEncoding);
//		System.out.println(decodeUnicode(gbEncoding));
//	}
	
	/**
	 * 中文转Unicode
	 * 
	 * @param gbString
	 * @return
	 */
	public static String gbEncoding(final String gbString) {
		char[] utfBytes = gbString.toCharArray();
//		String unicodeBytes = "";
		StringBuilder builder = new StringBuilder();
		for (int byteIndex = 0; byteIndex < utfBytes.length; byteIndex++) {
			String hexB = Integer.toHexString(utfBytes[byteIndex]);
			if (hexB.length() <= 2) {
				hexB = "00" + hexB;
			}
			builder.append("\\u").append(hexB);
//			unicodeBytes = unicodeBytes + "\\u" + hexB;
		}
//		System.out.println("unicodeBytes is: " + unicodeBytes);
		return builder.toString();
	}

	/**
	 * unicode 转换成 中文
	 *
	 * @author leon 2016-3-15
	 * @param theString
	 * @return
	 */
	public static String decodeUnicode(String theString) {
		char aChar;
		int len = theString.length();
		StringBuffer outBuffer = new StringBuffer(len);
		for (int x = 0; x < len;) {
			aChar = theString.charAt(x++);
			if (aChar == '\\') {
				aChar = theString.charAt(x++);
				if (aChar == 'u') {
					// Read the xxxx
					int value = 0;
					for (int i = 0; i < 4; i++) {
						aChar = theString.charAt(x++);
						switch (aChar) {
						case '0':
						case '1':
						case '2':
						case '3':
						case '4':
						case '5':
						case '6':
						case '7':
						case '8':
						case '9':
							value = (value << 4) + aChar - '0';
							break;
						case 'a':
						case 'b':
						case 'c':
						case 'd':
						case 'e':
						case 'f':
							value = (value << 4) + 10 + aChar - 'a';
							break;
						case 'A':
						case 'B':
						case 'C':
						case 'D':
						case 'E':
						case 'F':
							value = (value << 4) + 10 + aChar - 'A';
							break;
						default:
							throw new IllegalArgumentException("Malformed   \\uxxxx   encoding.");
						}
					}
					outBuffer.append((char) value);
				} else {
					if (aChar == 't')
						aChar = '\t';
					else if (aChar == 'r')
						aChar = '\r';
					else if (aChar == 'n')
						aChar = '\n';
					else if (aChar == 'f')
						aChar = '\f';
					outBuffer.append(aChar);
				}
			} else
				outBuffer.append(aChar);
		}
		return outBuffer.toString();
	}

	/**
	 * 能用decodeUnicode(String s)就不要用这个<br/>
	 * ！！！这个非常非常慢 Unicode转 汉字字符串
	 *
	 * @param str
	 *            \u6728
	 * @return
	 */
	@Deprecated
	public static String unicodeToString(String str) {

		Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
		Matcher matcher = pattern.matcher(str);
		char ch;
		while (matcher.find()) {
			// group 6728
			String group = matcher.group(2);
			// ch:'木' 26408
			ch = (char) Integer.parseInt(group, 16);
			// group1 \u6728
			String group1 = matcher.group(1);
			str = str.replace(group1, ch + "");
		}
		return str;
	}
}
