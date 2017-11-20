package com.atgeretg.util.file;

import java.io.File;
import java.io.InputStream;

import org.apache.log4j.Logger;

public class ResourceFileUtil {
	static Logger log = Logger.getLogger(ResourceFileUtil.class);
	public static final String ASCII = "ASCII";
	public static final String ISO_8859_1 = "ISO-8859-1";
	public static final String GB2312 = "GB2312";
	public static final String GBK = "GBK";
	public static final String GB18030 = "GB18030";
	public static final String UTF8 = "UTF-8";
	public static final String UTF16 = "UTF-16";

	public static void main(String[] args) {
		String path = getResourcePath("config.properties");
		File f = new File(path);
		System.out.println(f.getAbsolutePath());
		System.out.println(path);
		System.out.println(URL2String(getResourceURL("config.properties")));
		boolean b = getResourceIO("notPay.wav") == null;
		System.out.println("b = " + b);
		System.out.println(URL2String(getResourceURL()));
		System.out.println(URL2String(getResourceURL("alipay.jpg")));
	}

	/**
	 * 获取运行应用的src目录的文件流<br>
	 * 打包成jar文件，只能读取源目录下的文件，读包含文件夹的会报错，为null<br>
	 * 用eclipse打开，随便你怎么写喽
	 * 
	 * @param fileName
	 *            文件名（前面不要有“/”）
	 * @return string
	 */
	public static InputStream getResourceIO(String fileName) {
		return ClassLoader.getSystemResourceAsStream(fileName);
	}

	/**
	 * 获取运行应用的src目录的文件路径<br>
	 * 打包成jar文件，只能读取源目录下的文件，读包含文件夹的会报错，为null<br>
	 * 用eclipse打开，随便你怎么写喽
	 * 
	 * @param fileName
	 *            文件名（最前面绝对不要要有“/”）
	 * @return string
	 */
	public static String getResourcePath(String fileName) {
		return URL2String(Thread.currentThread().getContextClassLoader().getResource(fileName));
	}

	/**
	 * 获取运行应用的src目录的URL<br>
	 * 要获取当前ClassPath的绝对URI路径。用Xxx.class.getResource("") -->
	 * file:/D:/work_space/java/bin/com/atgeretg/
	 * 
	 * @return null | URL
	 */
	public static java.net.URL getResourceURL() {
		return Thread.currentThread().getContextClassLoader().getResource("");
	}

	/**
	 * 获取运行应用的src目录下指定文件的URL<br>
	 * 要获取当前ClassPath的绝对URI路径。用Xxx.class.getResource("") -->
	 * file:/D:/work_space/java/bin/com/atgeretg/<br>
	 * * 打包成jar文件，只能读取源目录下的文件，读包含文件夹的会报错，为null<br>
	 * 用eclipse打开，随便你怎么写喽
	 * 
	 * @param name
	 *            文件名（最前面绝对不要要有“/”）
	 * @return null | URL
	 */
	public static java.net.URL getResourceURL(String name) {
		return Thread.currentThread().getContextClassLoader().getResource(name);
	}

	/**
	 * URL换成String路径（就是去掉了file:/）
	 * 
	 * @param url
	 * @return string
	 */
	public static String URL2String(java.net.URL url) {
		String str = url.toString();
		if (str.startsWith("file"))
			return str.substring(str.indexOf("/") + 1);
		return url.getPath();
	}

}