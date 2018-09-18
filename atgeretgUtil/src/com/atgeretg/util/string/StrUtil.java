package com.atgeretg.util.string;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StrUtil {
	private static final int INDEX_NOT_FOUND = -1;
	private static final String EMPTY = "";
	private static final String NULL = "null";
	public static final String ASCII = "ASCII";
	public static final String ISO_8859_1 = "ISO-8859-1";
	public static final String GB2312 = "GB2312";
	public static final String GBK = "GBK";
	public static final String GB18030 = "GB18030";
	public static final String UTF8 = "UTF-8";
	public static final String UTF16 = "UTF-16";
	public static final String DOT = ".";
	public static final String SLASH = "/";

	public static void main(String[] args) {
		String lastStrIsDot = lastStrIsDot("fdf.dfdafj.", true);
		System.out.println(lastStrIsDot);
		lastStrIsDot = lastStrIsSomeString("fdasfaf.df", ".df", false);
		System.out.println(lastStrIsDot);
	}

	/**
	 * 字符串是否为空
	 * 
	 * 如果这个字符串为null或者trim后为空字符串则返回true，否则返回false。
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (str == null || EMPTY.equals(str.trim()))
			return true;
		return false;
	}

	/**
	 * 字符串是否为空和“null”
	 * 
	 * 如果这个字符串为null或者trim后为空字符串或“null”字符串则返回true，否则返回false。
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmptyNull(String str) {
		if (str == null || EMPTY.equals(str.trim()) || NULL.equals(str.trim()))
			return true;
		return false;
	}

	/**
	 * string值是不是空，空返回希望值
	 * 
	 * @param string
	 *            string值
	 * @param hopeValue
	 *            希望值
	 * @return
	 */
	public static String stringNullHope(String string, String hopeValue) {
		if (StrUtil.isEmpty(string))
			return hopeValue;
		return string;
	}

	// public static void main(String[] args) {
	// String[] split =
	// "1459394,1459395,1459403,1692515,1692531,1692537,1692542,1692546,1705181,1705183,1705186,1705187,1705191,1883327".split(",");
	// System.out.println(strFirstToLow("dd",true));
	// }

	/**
	 * 最后一个字符串要不要点
	 *
	 * @param string
	 * @param need
	 *            要不要点 return 传入的字符串，加上点或去除点
	 */
	private static String lastStrIsDot(String string, boolean need) {
		return lastStrIsSomeString(string, StrUtil.DOT, need);

	}

	/**
	 * 最后一个字符串要不要斜杠
	 *
	 * @param string
	 * @param need
	 *            要不要点 return 传入的字符串，加上斜杠或去除斜杠
	 */
	public static String lastStrIsSlash(String string, boolean need) {
		return lastStrIsSomeString(string, StrUtil.SLASH, need);
	}

	/**
	 * 最后字符串是否需要某字符串
	 *
	 * @param string
	 * @param needStr
	 *            指定的字符串
	 * @param need
	 *            要不要
	 * @return null | 传入的字符串，加上字符串或去除字符串
	 */
	public static String lastStrIsSomeString(String string, String needStr, boolean need) {
		if (isEmpty(string) || isEmpty(needStr))
			return string;
		boolean have = (needStr.equals(string.substring(string.length() - needStr.length())));
		if (need) {// 需要指定的字符串
			if (have)// 有指定的字符串
				return string;
			return string + needStr;
		} else {
			if (have)// 指定的字符串
				return string.substring(0, string.length() - needStr.length());
			return string;
		}

	}

	/**
	 * 前面字符串是否需要某字符串
	 *
	 * @param string
	 * @param needStr
	 *            指定的字符串
	 * @param need
	 *            要不要
	 * @return null | 传入的字符串，加上字符串或去除字符串
	 */
	public static String firstStrIsSomeString(String string, String needStr, boolean need) {
		// TODO
		if (StrUtil.isEmpty(string) || StrUtil.isEmpty(needStr))
			return string;
		boolean have = (needStr.equals(string.substring(0, needStr.length())));
		if (need) {// 需要点
			if (have)// 有点
				return string;
			return needStr + string;
		} else {
			if (have)// 有点
				return string.substring(needStr.length(), string.length());
			return string;
		}

	}

	/**
	 * 字符串数组转成字符串
	 * 
	 * @param str
	 *            数组（集合）
	 * @return
	 */
	public static String array2String(List<String> str) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < str.size(); i++) {
			builder.append(str.get(i));
		}
		return builder.toString();
	}

	/**
	 * 字符串数组转成字符串
	 * 
	 * @param str
	 *            数组
	 * @param split
	 *            分割符
	 * @return
	 */
	public static String array2String(String str[]) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < str.length; i++) {
			builder.append(str[i]);
		}
		return builder.toString();
	}

	/**
	 * 字符串数组按指定分割符转成字符串
	 * 
	 * @param str
	 *            数组（集合）
	 * @param split
	 *            分割符
	 * @return
	 */
	public static String array2String(List<String> str, String split) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < str.size(); i++) {
			if (i != str.size() - 1)
				builder.append(str.get(i)).append(split);
			else
				builder.append(str.get(i));
		}
		return builder.toString();
	}

	/**
	 * 字符串数组按指定分割符转成字符串
	 * 
	 * @param str
	 *            数组
	 * @param split
	 *            分割符
	 * @return
	 */
	public static String array2String(String str[], String split) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < str.length; i++) {
			if (i != str.length - 1)
				builder.append(str[i]).append(split);
			else
				builder.append(str[i]);
		}
		return builder.toString();
	}

	/**
	 * 拼接传入的object，生成字符串
	 * 
	 * @param object
	 * @return object 为 null 返回null 否则返回生成的字符串
	 */
	public static String stringBuilder(Object... object) {
		if (object == null)
			return null;
		StringBuilder builder = new StringBuilder();
		for (Object obj : object) {
			builder.append(obj);
		}
		return builder.toString();
	}

	/**
	 * 下划线转驼峰法
	 * 
	 * @param line
	 *            源字符串
	 * @param smallCamel
	 *            大小驼峰,是否为小驼峰
	 * @return 转换后的字符串
	 */
	public static String underline2Camel(String line, boolean smallCamel) {
		if (line == null || "".equals(line)) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		Pattern pattern = Pattern.compile("([A-Za-z\\d]+)(_)?");
		Matcher matcher = pattern.matcher(line);
		while (matcher.find()) {
			String word = matcher.group();
			sb.append(smallCamel && matcher.start() == 0 ? Character.toLowerCase(word.charAt(0))
					: Character.toUpperCase(word.charAt(0)));
			int index = word.lastIndexOf('_');
			if (index > 0) {
				sb.append(word.substring(1, index).toLowerCase());
			} else {
				sb.append(word.substring(1).toLowerCase());
			}
		}
		return sb.toString();
	}

	/**
	 * 驼峰法转下划线
	 * 
	 * @param line
	 *            源字符串
	 * @param smallCamel
	 *            大小下划线,是否是英文小写
	 * @return 转换后的字符串
	 */
	public static String camel2Underline(String line, boolean smallCamel) {
		if (StrUtil.isEmpty(line)) {
			return "";
		}
		line = String.valueOf(line.charAt(0)).toUpperCase().concat(line.substring(1));
		StringBuilder sb = new StringBuilder();
		Pattern pattern = Pattern.compile("[A-Z]([a-z\\d]+)?");
		Matcher matcher = pattern.matcher(line);
		while (matcher.find()) {
			String word = matcher.group();
			if (smallCamel)
				sb.append(word.toLowerCase());
			else
				sb.append(word.toUpperCase());
			sb.append(matcher.end() == line.length() ? "" : "_");
		}
		return sb.toString();
	}

	/**
	 * 将带有“_"的字符串处理为：将“_”字符后个字符变成大小并去掉“_”,如果后一个是数字，“_”和数字都不变<br>
	 * 如果后一个是大写字母，“_”去掉，大写字母不变。<br>
	 * 如：a_b_cc_dd --> aBCcDd;a_b_cc_2d --> aBCc_2d;a_f_dFF_Dd --> aFDFFDd
	 * 
	 * @param str
	 * @return
	 */
	public static String changeCharPostion(String content) {
		String falg = "_";// CharSequence c= '0';
		if (isEmpty(content) || !content.contains(falg) || content.length() < 2)
			return content;
		char[] charArray = content.toCharArray();
		StringBuilder builder = new StringBuilder();

		char tempChar;
		for (int i = 0; i < charArray.length; i++) {
			if (i != charArray.length - 1) {
				tempChar = charArray[i + 1];
				if ('_' == charArray[i]) {
					if ((tempChar >= 'a' && tempChar <= 'z')) {
						charArray[i + 1] = (char) (charArray[i + 1] - 32);
						continue;
					} else if ((tempChar >= 'A' && tempChar <= 'Z')) {
						continue;
					}
				}
			}
			builder.append(charArray[i]);
		}
		return builder.toString();
	}

	/**
	 * 数组中是否包含某字符，包含返回true
	 * 
	 * @param arr
	 * @param targetValue
	 * @return true | false
	 */
	public static boolean strArrContains(String[] arr, String targetValue) {
		for (String s : arr) {
			if (s.equals(targetValue))
				return true;
		}
		return false;
	}

	/**
	 * 最后一个字符串要不要点
	 *
	 * @param string
	 *            传入字符串
	 * @param need
	 *            需不需要点 return 传入的字符串，加上点或去除点
	 */
	public static String strLastHaveDot(String string, boolean need) {
		boolean have = (".".equals(string.substring(string.length() - 1)));
		if (need) {// 需要点
			if (have)// 有点
				return string;
			return string + ".";
		} else {
			if (have)// 有点
				return string.substring(0, string.length() - 1);
			return string;
		}

	}

	/**
	 * 字符串第一个字符是不是要转成小写
	 *
	 * @param string
	 *            传入字符串
	 * @param is
	 *            是不是要转成小写（false转成大写） return 传入的字符串的转换结果
	 */
	public static String strFirstToLow(String string, boolean is) {
		String nameFirst = "";
		if (is)
			nameFirst = string.substring(0, 1).toLowerCase();
		else
			nameFirst = string.substring(0, 1).toUpperCase();
		String nameLast = string.substring(1);
		return nameFirst + nameLast;
	}

	/**
	 * 如果字符串没有超过最长显示长度返回原字符串，否则从开头截取指定长度并加...返回。
	 * 
	 * @param str
	 *            原字符串
	 * @param length
	 *            字符串最长显示的长度
	 * @return 转换后的字符串
	 */
	public static String trimString(String str, int length) {
		if (str == null) {
			return "";
		} else if (str.length() > length) {
			return str.substring(0, length - 3) + "...";
		} else {
			return str;
		}
	}

	/**
	 * <p>
	 * The maximum size to which the padding constant(s) can expand.
	 * </p>
	 */
	private static final int PAD_LIMIT = 8192;

	/**
	 * 功能：将半角的符号转换成全角符号.(即英文字符转中文字符)
	 * 
	 * @author atgeretg
	 * @param str
	 *            源字符串
	 * @return String
	 * @date 2017年04月24日
	 */
	public static String changeToFull(String str) {
		String source = "1234567890!@#$%^&*()abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_=+\\|[];:'\",<.>/?";
		String[] decode = { "１", "２", "３", "４", "５", "６", "７", "８", "９", "０", "！", "＠", "＃", "＄", "％", "︿", "＆", "＊",
				"（", "）", "ａ", "ｂ", "ｃ", "ｄ", "ｅ", "ｆ", "ｇ", "ｈ", "ｉ", "ｊ", "ｋ", "ｌ", "ｍ", "ｎ", "ｏ", "ｐ", "ｑ", "ｒ", "ｓ",
				"ｔ", "ｕ", "ｖ", "ｗ", "ｘ", "ｙ", "ｚ", "Ａ", "Ｂ", "Ｃ", "Ｄ", "Ｅ", "Ｆ", "Ｇ", "Ｈ", "Ｉ", "Ｊ", "Ｋ", "Ｌ", "Ｍ", "Ｎ",
				"Ｏ", "Ｐ", "Ｑ", "Ｒ", "Ｓ", "Ｔ", "Ｕ", "Ｖ", "Ｗ", "Ｘ", "Ｙ", "Ｚ", "－", "＿", "＝", "＋", "＼", "｜", "【", "】", "；",
				"：", "'", "\"", "，", "〈", "。", "〉", "／", "？" };
		String result = "";
		for (int i = 0; i < str.length(); i++) {
			int pos = source.indexOf(str.charAt(i));
			if (pos != -1) {
				result += decode[pos];
			} else {
				result += str.charAt(i);
			}
		}
		return result;
	}

	/**
	 * 功能：cs串中是否一个都不包含字符数组searchChars中的字符。
	 * 
	 * @author atgeretg
	 * @param cs
	 *            字符串
	 * @param searchChars
	 *            字符数组
	 * @return boolean 都不包含返回true，否则返回false。
	 * @date 2017年04月24日
	 */
	public static boolean containsNone(CharSequence cs, char... searchChars) {
		if (cs == null || searchChars == null) {
			return true;
		}
		int csLen = cs.length();
		int csLast = csLen - 1;
		int searchLen = searchChars.length;
		int searchLast = searchLen - 1;
		for (int i = 0; i < csLen; i++) {
			char ch = cs.charAt(i);
			for (int j = 0; j < searchLen; j++) {
				if (searchChars[j] == ch) {
					if (Character.isHighSurrogate(ch)) {
						if (j == searchLast) {
							// missing low surrogate, fine, like
							// String.indexOf(String)
							return false;
						}
						if (i < csLast && searchChars[j + 1] == cs.charAt(i + 1)) {
							return false;
						}
					} else {
						// ch is in the Basic Multilingual Plane
						return false;
					}
				}
			}
		}
		return true;
	}

	/**
	 * <p>
	 * 编码为Unicode，格式 '\u0020'.
	 * </p>
	 * 
	 * @author atgeretg
	 * 
	 *         <pre>
	 *   CharUtils.unicodeEscaped(' ') = "\u0020"  
	 *   CharUtils.unicodeEscaped('A') = "\u0041"
	 *         </pre>
	 * 
	 * @param ch
	 *            源字符串
	 * @return 转码后的字符串
	 * @date 2017年04月24日
	 */
	public static String unicodeEscaped(char ch) {
		if (ch < 0x10) {
			return "\\u000" + Integer.toHexString(ch);
		} else if (ch < 0x100) {
			return "\\u00" + Integer.toHexString(ch);
		} else if (ch < 0x1000) {
			return "\\u0" + Integer.toHexString(ch);
		}
		return "\\u" + Integer.toHexString(ch);
	}

	/**
	 * <p>
	 * 进行tostring操作，如果传入的是null，返回空字符串。
	 * </p>
	 * 
	 * <pre>
	 * ObjectUtils.toString(null)         = ""  
	 * ObjectUtils.toString("")           = ""  
	 * ObjectUtils.toString("bat")        = "bat"  
	 * ObjectUtils.toString(Boolean.TRUE) = "true"
	 * </pre>
	 * 
	 * @param obj
	 *            源
	 * @return String
	 */
	public static String toString(Object obj) {
		return obj == null ? "" : obj.toString();
	}

	/**
	 * <p>
	 * 进行tostring操作，如果传入的是null，返回指定的默认值。
	 * </p>
	 * 
	 * <pre>
	 * ObjectUtils.toString(null, null)           = null  
	 * ObjectUtils.toString(null, "null")         = "null"  
	 * ObjectUtils.toString("", "null")           = ""  
	 * ObjectUtils.toString("bat", "null")        = "bat"  
	 * ObjectUtils.toString(Boolean.TRUE, "null") = "true"
	 * </pre>
	 * 
	 * @param obj
	 *            源
	 * @param nullStr
	 *            如果obj为null时返回这个指定值
	 * @return String
	 */
	public static String toString(Object obj, String nullStr) {
		return obj == null ? nullStr : obj.toString();
	}

	/**
	 * <p>
	 * 只从源字符串中移除指定开头子字符串.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.removeStart(null, *)      = null  
	 * StringUtil.removeStart("", *)        = ""  
	 * StringUtil.removeStart(*, null)      = *  
	 * StringUtil.removeStart("www.domain.com", "www.")   = "domain.com"  
	 * StringUtil.removeStart("domain.com", "www.")       = "domain.com"  
	 * StringUtil.removeStart("www.domain.com", "domain") = "www.domain.com"  
	 * StringUtil.removeStart("abc", "")    = "abc"
	 * </pre>
	 * 
	 * @param str
	 *            源字符串
	 * @param remove
	 *            将要被移除的子字符串
	 * @return String
	 */
	public static String removeStart(String str, String remove) {
		if (isEmpty(str) || isEmpty(remove)) {
			return str;
		}
		if (str.startsWith(remove)) {
			return str.substring(remove.length());
		}
		return str;
	}

	/**
	 * <p>
	 * 只从源字符串中移除指定结尾的子字符串.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.removeEnd(null, *)      = null  
	 * StringUtil.removeEnd("", *)        = ""  
	 * StringUtil.removeEnd(*, null)      = *  
	 * StringUtil.removeEnd("www.domain.com", ".com.")  = "www.domain.com"  
	 * StringUtil.removeEnd("www.domain.com", ".com")   = "www.domain"  
	 * StringUtil.removeEnd("www.domain.com", "domain") = "www.domain.com"  
	 * StringUtil.removeEnd("abc", "")    = "abc"
	 * </pre>
	 * 
	 * @param str
	 *            源字符串
	 * @param remove
	 *            将要被移除的子字符串
	 * @return String
	 */
	public static String removeEnd(String str, String remove) {
		if (isEmpty(str) || isEmpty(remove)) {
			return str;
		}
		if (str.endsWith(remove)) {
			return str.substring(0, str.length() - remove.length());
		}
		return str;
	}

	/**
	 * <p>
	 * 将一个字符串重复N次
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.repeat(null, 2) = null  
	 * StringUtil.repeat("", 0)   = ""  
	 * StringUtil.repeat("", 2)   = ""  
	 * StringUtil.repeat("a", 3)  = "aaa"  
	 * StringUtil.repeat("ab", 2) = "abab"  
	 * StringUtil.repeat("a", -2) = ""
	 * </pre>
	 * 
	 * @param str
	 *            源字符串
	 * @param repeat
	 *            重复的次数
	 * @return String
	 */
	public static String repeat(String str, int repeat) {
		// Performance tuned for 2.0 (JDK1.4)

		if (str == null) {
			return null;
		}
		if (repeat <= 0) {
			return EMPTY;
		}
		int inputLength = str.length();
		if (repeat == 1 || inputLength == 0) {
			return str;
		}
		if (inputLength == 1 && repeat <= PAD_LIMIT) {
			return repeat(str.charAt(0), repeat);
		}

		int outputLength = inputLength * repeat;
		switch (inputLength) {
		case 1:
			return repeat(str.charAt(0), repeat);
		case 2:
			char ch0 = str.charAt(0);
			char ch1 = str.charAt(1);
			char[] output2 = new char[outputLength];
			for (int i = repeat * 2 - 2; i >= 0; i--, i--) {
				output2[i] = ch0;
				output2[i + 1] = ch1;
			}
			return new String(output2);
		default:
			StringBuilder buf = new StringBuilder(outputLength);
			for (int i = 0; i < repeat; i++) {
				buf.append(str);
			}
			return buf.toString();
		}
	}

	/**
	 * <p>
	 * 将一个字符串重复N次，并且中间加上指定的分隔符
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.repeat(null, null, 2) = null  
	 * StringUtil.repeat(null, "x", 2)  = null  
	 * StringUtil.repeat("", null, 0)   = ""  
	 * StringUtil.repeat("", "", 2)     = ""  
	 * StringUtil.repeat("", "x", 3)    = "xxx"  
	 * StringUtil.repeat("?", ", ", 3)  = "?, ?, ?"
	 * </pre>
	 * 
	 * @param str
	 *            源字符串
	 * @param separator
	 *            分隔符
	 * @param repeat
	 *            重复次数
	 * @return String
	 */
	public static String repeat(String str, String separator, int repeat) {
		if (str == null || separator == null) {
			return repeat(str, repeat);
		} else {
			// given that repeat(String, int) is quite optimized, better to rely
			// on it than try and splice this into it
			String result = repeat(str + separator, repeat);
			return removeEnd(result, separator);
		}
	}

	/**
	 * <p>
	 * 将某个字符重复N次.
	 * </p>
	 * 
	 * @param ch
	 *            某个字符
	 * @param repeat
	 *            重复次数
	 * @return String
	 */
	public static String repeat(char ch, int repeat) {
		char[] buf = new char[repeat];
		for (int i = repeat - 1; i >= 0; i--) {
			buf[i] = ch;
		}
		return new String(buf);
	}

	/**
	 * <p>
	 * 字符串长度达不到指定长度时，在字符串右边补指定的字符.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.rightPad(null, *, *)     = null  
	 * StringUtil.rightPad("", 3, 'z')     = "zzz"  
	 * StringUtil.rightPad("bat", 3, 'z')  = "bat"  
	 * StringUtil.rightPad("bat", 5, 'z')  = "batzz"  
	 * StringUtil.rightPad("bat", 1, 'z')  = "bat"  
	 * StringUtil.rightPad("bat", -1, 'z') = "bat"
	 * </pre>
	 * 
	 * @param str
	 *            源字符串
	 * @param size
	 *            指定的长度
	 * @param padChar
	 *            进行补充的字符
	 * @return String
	 */
	public static String rightPad(String str, int size, char padChar) {
		if (str == null) {
			return null;
		}
		int pads = size - str.length();
		if (pads <= 0) {
			return str; // returns original String when possible
		}
		if (pads > PAD_LIMIT) {
			return rightPad(str, size, String.valueOf(padChar));
		}
		return str.concat(repeat(padChar, pads));
	}

	/**
	 * <p>
	 * 扩大字符串长度，从左边补充指定字符
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.rightPad(null, *, *)      = null  
	 * StringUtil.rightPad("", 3, "z")      = "zzz"  
	 * StringUtil.rightPad("bat", 3, "yz")  = "bat"  
	 * StringUtil.rightPad("bat", 5, "yz")  = "batyz"  
	 * StringUtil.rightPad("bat", 8, "yz")  = "batyzyzy"  
	 * StringUtil.rightPad("bat", 1, "yz")  = "bat"  
	 * StringUtil.rightPad("bat", -1, "yz") = "bat"  
	 * StringUtil.rightPad("bat", 5, null)  = "bat  "  
	 * StringUtil.rightPad("bat", 5, "")    = "bat  "
	 * </pre>
	 * 
	 * @param str
	 *            源字符串
	 * @param size
	 *            扩大后的长度
	 * @param padStr
	 *            在右边补充的字符串
	 * @return String
	 */
	public static String rightPad(String str, int size, String padStr) {
		if (str == null) {
			return null;
		}
		if (isEmpty(padStr)) {
			padStr = " ";
		}
		int padLen = padStr.length();
		int strLen = str.length();
		int pads = size - strLen;
		if (pads <= 0) {
			return str; // returns original String when possible
		}
		if (padLen == 1 && pads <= PAD_LIMIT) {
			return rightPad(str, size, padStr.charAt(0));
		}

		if (pads == padLen) {
			return str.concat(padStr);
		} else if (pads < padLen) {
			return str.concat(padStr.substring(0, pads));
		} else {
			char[] padding = new char[pads];
			char[] padChars = padStr.toCharArray();
			for (int i = 0; i < pads; i++) {
				padding[i] = padChars[i % padLen];
			}
			return str.concat(new String(padding));
		}
	}

	/**
	 * <p>
	 * 扩大字符串长度，从左边补充空格
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.leftPad(null, *)   = null  
	 * StringUtil.leftPad("", 3)     = "   "  
	 * StringUtil.leftPad("bat", 3)  = "bat"  
	 * StringUtil.leftPad("bat", 5)  = "  bat"  
	 * StringUtil.leftPad("bat", 1)  = "bat"  
	 * StringUtil.leftPad("bat", -1) = "bat"
	 * </pre>
	 * 
	 * @param str
	 *            源字符串
	 * @param size
	 *            扩大后的长度
	 * @return String
	 */
	public static String leftPad(String str, int size) {
		return leftPad(str, size, ' ');
	}

	/**
	 * <p>
	 * 扩大字符串长度，从左边补充指定的字符
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.leftPad(null, *, *)     = null  
	 * StringUtil.leftPad("", 3, 'z')     = "zzz"  
	 * StringUtil.leftPad("bat", 3, 'z')  = "bat"  
	 * StringUtil.leftPad("bat", 5, 'z')  = "zzbat"  
	 * StringUtil.leftPad("bat", 1, 'z')  = "bat"  
	 * StringUtil.leftPad("bat", -1, 'z') = "bat"
	 * </pre>
	 * 
	 * @param str
	 *            源字符串
	 * @param size
	 *            扩大后的长度
	 * @param padStr
	 *            补充的字符
	 * @return String
	 */
	public static String leftPad(String str, int size, char padChar) {
		if (str == null) {
			return null;
		}
		int pads = size - str.length();
		if (pads <= 0) {
			return str; // returns original String when possible
		}
		if (pads > PAD_LIMIT) {
			return leftPad(str, size, String.valueOf(padChar));
		}
		return repeat(padChar, pads).concat(str);
	}

	/**
	 * <p>
	 * 扩大字符串长度，从左边补充指定的字符
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.leftPad(null, *, *)      = null  
	 * StringUtil.leftPad("", 3, "z")      = "zzz"  
	 * StringUtil.leftPad("bat", 3, "yz")  = "bat"  
	 * StringUtil.leftPad("bat", 5, "yz")  = "yzbat"  
	 * StringUtil.leftPad("bat", 8, "yz")  = "yzyzybat"  
	 * StringUtil.leftPad("bat", 1, "yz")  = "bat"  
	 * StringUtil.leftPad("bat", -1, "yz") = "bat"  
	 * StringUtil.leftPad("bat", 5, null)  = "  bat"  
	 * StringUtil.leftPad("bat", 5, "")    = "  bat"
	 * </pre>
	 * 
	 * @param str
	 *            源字符串
	 * @param size
	 *            扩大后的长度
	 * @param padStr
	 *            补充的字符串
	 * @return String
	 */
	public static String leftPad(String str, int size, String padStr) {
		if (str == null) {
			return null;
		}
		if (isEmpty(padStr)) {
			padStr = " ";
		}
		int padLen = padStr.length();
		int strLen = str.length();
		int pads = size - strLen;
		if (pads <= 0) {
			return str; // returns original String when possible
		}
		if (padLen == 1 && pads <= PAD_LIMIT) {
			return leftPad(str, size, padStr.charAt(0));
		}

		if (pads == padLen) {
			return padStr.concat(str);
		} else if (pads < padLen) {
			return padStr.substring(0, pads).concat(str);
		} else {
			char[] padding = new char[pads];
			char[] padChars = padStr.toCharArray();
			for (int i = 0; i < pads; i++) {
				padding[i] = padChars[i % padLen];
			}
			return new String(padding).concat(str);
		}
	}

	/**
	 * <p>
	 * 扩大字符串长度并将现在的字符串居中，被扩大部分用空格填充。
	 * <p>
	 * 
	 * <pre>
	 * StringUtil.center(null, *)   = null  
	 * StringUtil.center("", 4)     = "    "  
	 * StringUtil.center("ab", -1)  = "ab"  
	 * StringUtil.center("ab", 4)   = " ab "  
	 * StringUtil.center("abcd", 2) = "abcd"  
	 * StringUtil.center("a", 4)    = " a  "
	 * </pre>
	 * 
	 * @param str
	 *            源字符串
	 * @param size
	 *            扩大后的长度
	 * @return String
	 */
	public static String center(String str, int size) {
		return center(str, size, ' ');
	}

	/**
	 * <p>
	 * 将字符串长度修改为指定长度，并进行居中显示。
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.center(null, *, *)     = null  
	 * StringUtil.center("", 4, ' ')     = "    "  
	 * StringUtil.center("ab", -1, ' ')  = "ab"  
	 * StringUtil.center("ab", 4, ' ')   = " ab"  
	 * StringUtil.center("abcd", 2, ' ') = "abcd"  
	 * StringUtil.center("a", 4, ' ')    = " a  "  
	 * StringUtil.center("a", 4, 'y')    = "yayy"
	 * </pre>
	 * 
	 * @param str
	 *            源字符串
	 * @param size
	 *            指定的长度
	 * @param padStr
	 *            长度不够时补充的字符串
	 * @return String
	 * @throws IllegalArgumentException
	 *             如果被补充字符串为 null或者 empty
	 */
	public static String center(String str, int size, char padChar) {
		if (str == null || size <= 0) {
			return str;
		}
		int strLen = str.length();
		int pads = size - strLen;
		if (pads <= 0) {
			return str;
		}
		str = leftPad(str, strLen + pads / 2, padChar);
		str = rightPad(str, size, padChar);
		return str;
	}

	/**
	 * <p>
	 * 将字符串长度修改为指定长度，并进行居中显示。
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.center(null, *, *)     = null  
	 * StringUtil.center("", 4, " ")     = "    "  
	 * StringUtil.center("ab", -1, " ")  = "ab"  
	 * StringUtil.center("ab", 4, " ")   = " ab"  
	 * StringUtil.center("abcd", 2, " ") = "abcd"  
	 * StringUtil.center("a", 4, " ")    = " a  "  
	 * StringUtil.center("a", 4, "yz")   = "yayz"  
	 * StringUtil.center("abc", 7, null) = "  abc  "  
	 * StringUtil.center("abc", 7, "")   = "  abc  "
	 * </pre>
	 * 
	 * @param str
	 *            源字符串
	 * @param size
	 *            指定的长度
	 * @param padStr
	 *            长度不够时补充的字符串
	 * @return String
	 * @throws IllegalArgumentException
	 *             如果被补充字符串为 null或者 empty
	 */
	public static String center(String str, int size, String padStr) {
		if (str == null || size <= 0) {
			return str;
		}
		if (isEmpty(padStr)) {
			padStr = " ";
		}
		int strLen = str.length();
		int pads = size - strLen;
		if (pads <= 0) {
			return str;
		}
		str = leftPad(str, strLen + pads / 2, padStr);
		str = rightPad(str, size, padStr);
		return str;
	}

	/**
	 * <p>
	 * 检查字符串是否全部为小写.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.isAllLowerCase(null)   = false  
	 * StringUtil.isAllLowerCase("")     = false  
	 * StringUtil.isAllLowerCase("  ")   = false  
	 * StringUtil.isAllLowerCase("abc")  = true  
	 * StringUtil.isAllLowerCase("abC") = false
	 * </pre>
	 * 
	 * @param cs
	 *            源字符串
	 * @return String
	 */
	public static boolean isAllLowerCase(String cs) {
		if (cs == null || isEmpty(cs)) {
			return false;
		}
		int sz = cs.length();
		for (int i = 0; i < sz; i++) {
			if (Character.isLowerCase(cs.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * <p>
	 * 检查是否都是大写.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.isAllUpperCase(null)   = false  
	 * StringUtil.isAllUpperCase("")     = false  
	 * StringUtil.isAllUpperCase("  ")   = false  
	 * StringUtil.isAllUpperCase("ABC")  = true  
	 * StringUtil.isAllUpperCase("aBC") = false
	 * </pre>
	 * 
	 * @param cs
	 *            源字符串
	 * @return String
	 */
	public static boolean isAllUpperCase(String cs) {
		if (cs == null || StrUtil.isEmpty(cs)) {
			return false;
		}
		int sz = cs.length();
		for (int i = 0; i < sz; i++) {
			if (Character.isUpperCase(cs.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * <p>
	 * 反转字符串.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.reverse(null)  = null  
	 * StringUtil.reverse("")    = ""  
	 * StringUtil.reverse("bat") = "tab"
	 * </pre>
	 * 
	 * @param str
	 *            源字符串
	 * @return String
	 */
	public static String reverse(String str) {
		if (str == null) {
			return null;
		}
		return new StringBuilder(str).reverse().toString();
	}

	/**
	 * <p>
	 * 字符串达不到一定长度时在右边补空白.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.rightPad(null, *)   = null  
	 * StringUtil.rightPad("", 3)     = "   "  
	 * StringUtil.rightPad("bat", 3)  = "bat"  
	 * StringUtil.rightPad("bat", 5)  = "bat  "  
	 * StringUtil.rightPad("bat", 1)  = "bat"  
	 * StringUtil.rightPad("bat", -1) = "bat"
	 * </pre>
	 * 
	 * @param str
	 *            源字符串
	 * @param size
	 *            指定的长度
	 * @return String
	 */
	public static String rightPad(String str, int size) {
		return rightPad(str, size, ' ');
	}

	/**
	 * 从右边截取字符串.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.right(null, *)    = null  
	 * StringUtil.right(*, -ve)     = ""  
	 * StringUtil.right("", *)      = ""  
	 * StringUtil.right("abc", 0)   = ""  
	 * StringUtil.right("abc", 2)   = "bc"  
	 * StringUtil.right("abc", 4)   = "abc"
	 * </pre>
	 * 
	 * @param str
	 *            源字符串
	 * @param len
	 *            长度
	 * @return String
	 */
	public static String right(String str, int len) {
		if (str == null) {
			return null;
		}
		if (len < 0) {
			return EMPTY;
		}
		if (str.length() <= len) {
			return str;
		}
		return str.substring(str.length() - len);
	}

	/**
	 * <p>
	 * 截取一个字符串的前几个.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.left(null, *)    = null  
	 * StringUtil.left(*, -ve)     = ""  
	 * StringUtil.left("", *)      = ""  
	 * StringUtil.left("abc", 0)   = ""  
	 * StringUtil.left("abc", 2)   = "ab"  
	 * StringUtil.left("abc", 4)   = "abc"
	 * </pre>
	 * 
	 * @param str
	 *            源字符串
	 * @param len
	 *            截取的长度
	 * @return the String
	 */
	public static String left(String str, int len) {
		if (str == null) {
			return null;
		}
		if (len < 0) {
			return EMPTY;
		}
		if (str.length() <= len) {
			return str;
		}
		return str.substring(0, len);
	}

	/**
	 * <p>
	 * 得到tag字符串中间的子字符串，只返回第一个匹配项。
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.substringBetween(null, *)            = null  
	 * StringUtil.substringBetween("", "")             = ""  
	 * StringUtil.substringBetween("", "tag")          = null  
	 * StringUtil.substringBetween("tagabctag", null)  = null  
	 * StringUtil.substringBetween("tagabctag", "")    = ""  
	 * StringUtil.substringBetween("tagabctag", "tag") = "abc"
	 * </pre>
	 * 
	 * @param str
	 *            源字符串。
	 * @param tag
	 *            标识字符串。
	 * @return String 子字符串, 如果没有符合要求的，返回{@code null}。
	 */
	public static String substringBetween(String str, String tag) {
		return substringBetween(str, tag, tag);
	}

	/**
	 * <p>
	 * 得到两个字符串中间的子字符串，只返回第一个匹配项。
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.substringBetween("wx[b]yz", "[", "]") = "b"  
	 * StringUtil.substringBetween(null, *, *)          = null  
	 * StringUtil.substringBetween(*, null, *)          = null  
	 * StringUtil.substringBetween(*, *, null)          = null  
	 * StringUtil.substringBetween("", "", "")          = ""  
	 * StringUtil.substringBetween("", "", "]")         = null  
	 * StringUtil.substringBetween("", "[", "]")        = null  
	 * StringUtil.substringBetween("yabcz", "", "")     = ""  
	 * StringUtil.substringBetween("yabcz", "y", "z")   = "abc"  
	 * StringUtil.substringBetween("yabczyabcz", "y", "z")   = "abc"
	 * </pre>
	 * 
	 * @param str
	 *            源字符串
	 * @param open
	 *            起字符串。
	 * @param close
	 *            末字符串。
	 * @return String 子字符串, 如果没有符合要求的，返回{@code null}。
	 */
	public static String substringBetween(String str, String open, String close) {
		if (str == null || open == null || close == null) {
			return null;
		}
		int start = str.indexOf(open);
		if (start != INDEX_NOT_FOUND) {
			int end = str.indexOf(close, start + open.length());
			if (end != INDEX_NOT_FOUND) {
				return str.substring(start + open.length(), end);
			}
		}
		return null;
	}

	/**
	 * <p>
	 * 得到两个字符串中间的子字符串，所有匹配项组合为数组并返回。
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.substringsBetween("[a][b][c]", "[", "]") = ["a","b","c"]  
	 * StringUtil.substringsBetween(null, *, *)            = null  
	 * StringUtil.substringsBetween(*, null, *)            = null  
	 * StringUtil.substringsBetween(*, *, null)            = null  
	 * StringUtil.substringsBetween("", "[", "]")          = []
	 * </pre>
	 * 
	 * @param str
	 *            源字符串
	 * @param open
	 *            起字符串。
	 * @param close
	 *            末字符串。
	 * @return String 子字符串数组, 如果没有符合要求的，返回{@code null}。
	 */
	public static String[] substringsBetween(String str, String open, String close) {
		if (str == null || isEmpty(open) || isEmpty(close)) {
			return null;
		}
		int strLen = str.length();
		if (strLen == 0) {
			return new String[0];
		}
		int closeLen = close.length();
		int openLen = open.length();
		List<String> list = new ArrayList<String>();
		int pos = 0;
		while (pos < strLen - closeLen) {
			int start = str.indexOf(open, pos);
			if (start < 0) {
				break;
			}
			start += openLen;
			int end = str.indexOf(close, start);
			if (end < 0) {
				break;
			}
			list.add(str.substring(start, end));
			pos = end + closeLen;
		}
		if (list.isEmpty()) {
			return null;
		}
		return list.toArray(new String[list.size()]);
	}

	/**
	 * 功能：切换字符串中的所有字母大小写。<br/>
	 * 
	 * <pre>
	 * StringUtil.swapCase(null)                 = null  
	 * StringUtil.swapCase("")                   = ""  
	 * StringUtil.swapCase("The dog has a BONE") = "tHE DOG HAS A bone"
	 * </pre>
	 * 
	 * 
	 * @param str
	 *            源字符串
	 * @return String
	 */
	public static String swapCase(String str) {
		if (StrUtil.isEmpty(str)) {
			return str;
		}
		char[] buffer = str.toCharArray();

		boolean whitespace = true;

		for (int i = 0; i < buffer.length; i++) {
			char ch = buffer[i];
			if (Character.isUpperCase(ch)) {
				buffer[i] = Character.toLowerCase(ch);
				whitespace = false;
			} else if (Character.isTitleCase(ch)) {
				buffer[i] = Character.toLowerCase(ch);
				whitespace = false;
			} else if (Character.isLowerCase(ch)) {
				if (whitespace) {
					buffer[i] = Character.toTitleCase(ch);
					whitespace = false;
				} else {
					buffer[i] = Character.toUpperCase(ch);
				}
			} else {
				whitespace = Character.isWhitespace(ch);
			}
		}
		return new String(buffer);
	}

	/**
	 * 功能：截取出最后一个标志位之后的字符串.<br/>
	 * 如果sourceStr为empty或者expr为null，直接返回源字符串。<br/>
	 * 如果expr长度为0，直接返回sourceStr。<br/>
	 * 如果expr在sourceStr中不存在，直接返回sourceStr。<br/>
	 * 
	 * @date 2017年04月24日
	 * @param sourceStr
	 *            被截取的字符串
	 * @param expr
	 *            分隔符
	 * @return String
	 */
	public static String substringAfterLast(String sourceStr, String expr) {
		if (isEmpty(sourceStr) || expr == null) {
			return sourceStr;
		}
		if (expr.length() == 0) {
			return sourceStr;
		}

		int pos = sourceStr.lastIndexOf(expr);
		if (pos == -1) {
			return sourceStr;
		}
		return sourceStr.substring(pos + expr.length());
	}

	/**
	 * 功能：截取出最后一个标志位之前的字符串.<br/>
	 * 如果sourceStr为empty或者expr为null，直接返回源字符串。<br/>
	 * 如果expr长度为0，直接返回sourceStr。<br/>
	 * 如果expr在sourceStr中不存在，直接返回sourceStr。<br/>
	 * 
	 * @author atgeretg
	 * @date 2017年04月24日
	 * @param sourceStr
	 *            被截取的字符串
	 * @param expr
	 *            分隔符
	 * @return String
	 */
	public static String substringBeforeLast(String sourceStr, String expr) {
		if (isEmpty(sourceStr) || expr == null) {
			return sourceStr;
		}
		if (expr.length() == 0) {
			return sourceStr;
		}
		int pos = sourceStr.lastIndexOf(expr);
		if (pos == -1) {
			return sourceStr;
		}
		return sourceStr.substring(0, pos);
	}

	/**
	 * 功能：截取出第一个标志位之后的字符串.<br/>
	 * 如果sourceStr为empty或者expr为null，直接返回源字符串。<br/>
	 * 如果expr长度为0，直接返回sourceStr。<br/>
	 * 如果expr在sourceStr中不存在，直接返回sourceStr。<br/>
	 * 
	 * @author atgeretg
	 * @date 2017年04月24日
	 * @param sourceStr
	 *            被截取的字符串
	 * @param expr
	 *            分隔符
	 * @return String
	 */
	public static String substringAfter(String sourceStr, String expr) {
		if (isEmpty(sourceStr) || expr == null) {
			return sourceStr;
		}
		if (expr.length() == 0) {
			return sourceStr;
		}

		int pos = sourceStr.indexOf(expr);
		if (pos == -1) {
			return sourceStr;
		}
		return sourceStr.substring(pos + expr.length());
	}

	/**
	 * 功能：截取出第一个标志位之前的字符串.<br/>
	 * 如果sourceStr为empty或者expr为null，直接返回源字符串。<br/>
	 * 如果expr长度为0，直接返回sourceStr。<br/>
	 * 如果expr在sourceStr中不存在，直接返回sourceStr。<br/>
	 * 如果expr在sourceStr中存在不止一个，以第一个位置为准。
	 * 
	 * @author atgeretg
	 * @date 2017年04月24日
	 * @param sourceStr
	 *            被截取的字符串
	 * @param expr
	 *            分隔符
	 * @return String
	 */
	public static String substringBefore(String sourceStr, String expr) {
		if (isEmpty(sourceStr) || expr == null) {
			return sourceStr;
		}
		if (expr.length() == 0) {
			return sourceStr;
		}
		int pos = sourceStr.indexOf(expr);
		if (pos == -1) {
			return sourceStr;
		}
		return sourceStr.substring(0, pos);
	}

	/**
	 *
	 * 转换编码
	 *
	 * @param s
	 *            源字符串
	 * @param srcEncode
	 *            源编码格式
	 * @param destEncode
	 *            目标编码格式
	 * @return 目标编码
	 */
	public static String changEncode(String s, String srcEncode, String destEncode) {
		try {
			String str = new String(s.getBytes(srcEncode), destEncode);
			return str;
		} catch (UnsupportedEncodingException e) {
			return s;
		}
	}

}
