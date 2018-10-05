package com.atgeretg.util.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.atgeretg.util.date.DateUtil;
import com.atgeretg.util.string.StrUtil;

public class FileUtil {
	static Logger log = Logger.getLogger(FileUtil.class);
	public static final String ASCII = "ASCII";
	public static final String ISO_8859_1 = "ISO-8859-1";
	public static final String GB2312 = "GB2312";
	public static final String GBK = "GBK";
	public static final String GB18030 = "GB18030";
	public static final String UTF8 = "UTF-8";
	public static final String UTF16 = "UTF-16";
	public static final String DOT = ".";
	public static final String SLASH = "/";
	
//	public static void main(String[] args) {
//		System.out.println(getSystemUserHome());
//	}
	
	/**
	 * 获取系统当前用户的路径
	 * @return
	 */
	public static String getSystemUserHome() {
		return System.getProperties().getProperty("user.home");
	}

	/**
	 * 创建文件夹，存在则将存在文件夹重命名，存在时又不是文件夹，则将文件重命名
	 * 
	 * @param path
	 *            文件夹路径
	 * @return boolean
	 */
	public static boolean creatDirection(String path) {
		File file = new File(path);
		if (file.exists()) {
			if (file.isDirectory())
				return true;
			/* 不是文件夹，将文件改名 */
			File tempFlie = new File(file.getAbsoluteFile() + DateUtil.formatDateStr(new Date()));
			if (!file.renameTo(tempFlie)) {
				/* 改名失败 */
				System.out.println("创建文件夹失败");
			} else {
				if (file.mkdirs()) {
					return true;
				}
				System.out.println("创建文件夹失败");
			}
		} else {
			/* 文件夹不存在时 */
			if (file.mkdirs())
				return true;
			System.out.println("创建文件夹失败");
		}
		return false;
	}

	/**
	 * 通过文件路径来创建文件夹，当不知道文件保路径是否存在时用。此方法不会对文件任何改变，只是替文件创建目录。<br>
	 * 如：/home/atgertg/document/file/atgeretg.txt，当不存在“file”文件夹时，将会创建“file”文件夹，不会创建文件
	 * 
	 * @param filePath
	 * @return
	 */
	public static boolean createDir4file(String filePath) {
		String[] path = FileUtil.getDir2file(filePath);
		if (path[0] == null)
			return false;
		return FileUtil.creatDirection(path[0]);
	}

	/**
	 * 通过文件路径进行获取文件名<br>
	 * 
	 * @param filePath
	 * @return
	 */
	public static String getDir2FileName(String filePath) {
		return getDir2file(filePath)[1];
	}

	/**
	 * 通过文件路径进行获取文件夹名<br>
	 * 
	 * @param filePath
	 * @return
	 */
	public static String getDir2FileDir(String filePath) {
		return getDir2file(filePath)[0];
	}

	/**
	 * 通过文件路径进行分离文件夹和文件,数组第一个是文件夹路径，数组第二个是文件名；<br>
	 * 如果直接是文件名（没有“\\”或“/”），文件路径（第一个）为null,第二个为文件名<br>
	 * 如：/home/atgertg/document/file/atgeretg.txt，<br>
	 * 返回的是arr[0] = "/home/atgertg/document/file" 和 arr[1] = "atgeretg.txt"
	 * 
	 * @param filePath
	 * @return 数组不会null，文件夹路径和文件名，文件夹路径可能为null
	 */
	public static String[] getDir2file(String filePath) {
		if (filePath.contains("\\"))
			filePath = filePath.replaceAll("\\\\", "/");// 方法参数是正则表达式
		// System.out.println(filePath);
		int index = filePath.lastIndexOf("/");// filePath.lastIndexOf(File.separator);
		String[] arr = new String[2];
		if (index < 0) {
			arr[1] = filePath;
			return arr;
		}
		arr[0] = filePath.substring(0, index);
		arr[1] = filePath.substring(index + 1);
		return arr;
	}

	/**
	 * 获取文件类型（获取文件后缀名）
	 * @param fileName
	 * @return
	 */
	public static String getFileSuffix(String fileName) {
		if (!fileName.contains(FileUtil.DOT))
			return null;
		String nameSuffix = fileName.substring(fileName.lastIndexOf(FileUtil.DOT)+1);
		return nameSuffix;
	}

	/**
	 * 保存文件，路径不存在则创建
	 * 
	 * @param b
	 * @param savePath
	 * @param isOver
	 *            存在时是否覆盖（true为是）
	 */
	public static void saveFile4byteCreate(byte[] b, String savePath, boolean isOver) {
		if (!createDir4file(savePath)) {
			log.error("创建文件路径失败");
			return;
		}
		File f;
		if (!isOver)
			f = FileUtil.reNameFile(savePath);
		else
			f = new File(savePath);
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(f);
			fos.write(b, 0, b.length);
			fos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fos != null)
					fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 通过一个输入流来保存文件,路径不存在会被创建
	 * 
	 * @param in
	 *            文件输入流
	 * @param file
	 */
	public static void saveFile2in(InputStream in, File file) {
		if (in == null) {
			log.error("saveFile2in error InputStream is null");
			return;
		}
		if (!createDir4file(file.getPath())) {
			log.error("创建文件路径失败");
			return;
		}
		FileOutputStream fos = null;
		try {
			// 把每次读取的内容写入到内存中，然后从内存中获取
			fos = new FileOutputStream(file);
			byte[] b = new byte[1024];
			int len = -1;
			while ((len = in.read(b)) != -1) {
				fos.write(b, 0, len);
			}
			fos.flush();
		} catch (FileNotFoundException e) {
			log.error(e);
		} catch (IOException e) {
			log.error(e);
		} finally {
			try {
				fos.close();
				in.close();
			} catch (Exception e) {
				log.error(e);
			}
		}
	}

	/**
	 * 通过一个输入流来保存文件,路径不存在会被创建
	 * 
	 * @param in
	 *            文件输入流
	 * @param path
	 */
	public static void saveFile2in(InputStream in, String path) {
		if (StrUtil.isEmpty(path)) {
			log.error("saveFile2in error path error");
			return;
		}
		FileUtil.saveFile2in(in, new File(path));
	}

	/**
	 * 保存文件byte方式，路径一定要存在
	 * 
	 * @param b
	 * @param savePath
	 * @param isOver
	 *            存在时是否覆盖（true为是）
	 */
	public static void saveFile4byte(byte[] b, String savePath, boolean isOver) {
		File file = new File(savePath);
		// File f;
		FileOutputStream fos = null;
		try {
			if (!isOver)
				file = FileUtil.reNameFile(savePath);
			fos = new FileOutputStream(file);
			fos.write(b, 0, b.length);
			fos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fos != null)
					fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 保存文件String方式，路径一定要存在，不设置编码格式（null）默认为系统的编码格式
	 * 
	 * @param strContent
	 * @param savePath
	 * @param encode
	 *            什么编码格式，不设置（null）默认为系统的编码格式
	 * @param isOver
	 *            存在时是否覆盖（true为是）
	 */
	public static void saveFile4Str(String strContent, String savePath, String encode, boolean isOver) {
		File file = new File(savePath);
		OutputStreamWriter writer = null;
		BufferedWriter bw = null;
		try {
			// if (f.exists()) {
			// f.delete();
			// }
			if (!isOver)
				file = FileUtil.reNameFile(savePath);
			OutputStream os = new FileOutputStream(file);
			if (encode != null && !"".equals(encode))
				writer = new OutputStreamWriter(os, encode);
			else
				writer = new OutputStreamWriter(os);
			bw = new BufferedWriter(writer);
			bw.write(strContent);
			bw.flush();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 保存文件，路径不存在则创建，不设置编码格式（null）默认为系统的编码格式
	 * 
	 * @param strContent
	 * @param savePath
	 * @param encode
	 *            什么编码格式，不设置（null）默认为系统的编码格式
	 * @param isOver
	 *            存在时是否覆盖（true为是）
	 */
	public static void saveFile4StrCreate(String strContent, String savePath, String encode, boolean isOver) {
		if (!createDir4file(savePath)) {
			log.error("创建文件路径失败");
			return;
		}
		File f;
		if (!isOver)
			f = FileUtil.reNameFile(savePath);
		else
			f = new File(savePath);
		OutputStreamWriter writer = null;
		BufferedWriter bw = null;
		try {
			OutputStream os = new FileOutputStream(f);
			if (encode != null && !"".equals(encode))
				writer = new OutputStreamWriter(os, encode);
			else
				writer = new OutputStreamWriter(os);
			bw = new BufferedWriter(writer);
			bw.write(strContent);
			bw.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 文件名相同时，重新命名，“方式：xxx---变---xxx(i)”，i是int型自增数，并返回一个File对象，也可用于生成创建一个文件
	 * 
	 * @param path
	 * @return File
	 */
	public static File reNameFile(String path) {
		createDir4file(path);
		File f = new File(path);
		String tempStr = null, tempName = null, suffix = null;
		int i = 0, index = 0;
		// 存在则重会命名
		while (f.exists()) {
			i++;
			if (path.contains(".")) {
				index = path.lastIndexOf(".");
				suffix = path.substring(index);
				tempName = path.substring(0, index);
				tempStr = tempName + "(" + i + ")" + suffix;
			} else {
				tempStr = path + "(" + i + ")";
			}
			f = new File(tempStr);
		}
		return f;
	}

	/**
	 * 快速复制大文件、多个文件（包含文件夹）
	 * 
	 * @param file
	 *            文件
	 * @param destpath
	 *            目标路径
	 */
	public static void copyFile(File file, String destpath) {
		String pathWindows = file.getAbsolutePath() + File.separator;
		String[] list = file.list();
		for (String name : list) {
			FileUtil.copyFile_private(pathWindows + name, destpath + File.separator + name);
		}
	}

	/**
	 * 快速复制大文件、多个文件（包含文件夹） 递归复制
	 * 
	 * @param srcPath
	 * @param destpath
	 */
	public static void copyFile(String srcPath, String destpath) {
		File file = new File(srcPath);
		log.info(srcPath);
		if (!file.isDirectory()) {
			if (!createDir4file(destpath)) {
				log.error("创建文件路径失败");
				return;
			}
			copyFile_private(srcPath, destpath);
		} else
			FileUtil.copyFile(file, destpath);
	}

	/**
	 * 快速复制大文件、多个文件（包含文件夹） 递归复制
	 * 
	 * @param srcPath
	 * @param destpath
	 */
	private static void copyFile_private(String srcPath, String destpath) {
		BufferedInputStream reader = null;
		BufferedOutputStream writer = null;
		try {
			File file = new File(srcPath);
			if (file.isDirectory()) {
				File fileDir = new File(destpath);
				String tempStr = null;
				int i = 0;
				// 存在则重会命名
				while (fileDir.exists()) {
					i++;
					tempStr = destpath + "(" + i + ")";
					fileDir = new File(tempStr);
				}
				if (fileDir.mkdirs())
					FileUtil.copyFile(file, fileDir.getAbsolutePath());

			} else {
				reader = new BufferedInputStream(new FileInputStream(file));
				writer = new BufferedOutputStream(new FileOutputStream(destpath));
				byte[] buff = new byte[reader.available()];
				while ((reader.read(buff)) != -1) {
					writer.write(buff);
				}
			}
		} catch (Exception e) {
			log.error(e);
		} finally {
			try {
				if (writer != null) {
					writer.flush();
					writer.close();
				}
				if (reader != null)
					reader.close();
			} catch (IOException e) {
				log.error(e);
			}

		}
	}

	/**
	 * 文件转成byte[]
	 * 
	 * @param file
	 * @return null | byte[]
	 */
	static public byte[] readFile2byteArrary(File file) {

		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			// 把每次读取的内容写入到内存中，然后从内存中获取
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024 * 1024];
			int len = 0;
			// 只要没读完，不断的读取
			while ((len = fileInputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, len);
			}
			// 得到内存中写入的所有数据
			byte[] data = outputStream.toByteArray();
			fileInputStream.close();
			return data;
		} catch (FileNotFoundException e) {
			log.error(e);
		} catch (IOException e) {
			log.error(e);
		}
		return null;
	}

	/**
	 * 读取txt文件
	 * 
	 * @param file
	 * @return null | String
	 */
	static public String readFile2str(File file) {

		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			// 把每次读取的内容写入到内存中，然后从内存中获取
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024 * 1024];
			int len = 0;
			// 只要没读完，不断的读取
			while ((len = fileInputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, len);
			}
			// 得到内存中写入的所有数据
			byte[] data = outputStream.toByteArray();
			fileInputStream.close();
			return new String(data);
		} catch (FileNotFoundException e) {
			log.error(e);
		} catch (IOException e) {
			log.error(e);
		}
		return null;
	}

	/**
	 * 读取txt文件，指定编码格式输出（txt文件的编码格式）
	 * 
	 * @param file
	 * @param encond
	 *            以哪种编码读取返回文件字符串
	 * @return
	 */
	static public String readFile2str(File file, String encond) {

		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			// 把每次读取的内容写入到内存中，然后从内存中获取
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			// 只要没读完，不断的读取
			while ((len = fileInputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, len);
			}
			// 得到内存中写入的所有数据
			byte[] data = outputStream.toByteArray();
			fileInputStream.close();
			return new String(data, encond);// 以GBK（什么编码格式）方式转
		} catch (FileNotFoundException e) {
			log.error(e);
		} catch (IOException e) {
			log.error(e);
		}
		return null;
	}

	/**
	 * 一行一行读取文件，一行的内容就是一个数组中的元素，默认以UFT8的格式读取
	 * 
	 * @param path
	 *            文件路径
	 * @return 出错失败：null | 成功：String[]
	 */
	public static String[] readFile2Arrary(String path) {
		return readFile2Arrary(path, FileUtil.UTF8);
	}

	/**
	 * 一行一行读取文件，一行的内容就是一个数组中的元素,按指定的格式读取
	 * 
	 * @param path
	 *            文件路径
	 * @param encode
	 *            编码格式
	 * @return 出错失败：null | 成功：String[]
	 */
	public static String[] readFile2Arrary(String path, String encode) {
		List<String> list = readFile2List(path, encode);
		if (list == null)
			return null;
		String[] array = new String[list.size()];
		return list.toArray(array);
	}

	/**
	 * 一行一行读取文件，一行的内容就是一个list中的元素，默认以UFT8的格式读取
	 * 
	 * @param path
	 *            文件路径
	 * @return 出错失败：null | 成功：List<String>
	 */
	public static List<String> readFile2List(String path) {
		return readFile2List(path, FileUtil.UTF8);
	}

	/**
	 * 一行一行读取文件，一行的内容就是一个list中的元素，按指定的格式读取
	 * 
	 * @param path
	 *            文件路径
	 * @param encode
	 *            编码格式
	 * @return
	 */
	public static List<String> readFile2List(String path, String encode) {
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			if (StrUtil.isEmpty(path))
				return null;
			fis = new FileInputStream(path);
			isr = new InputStreamReader(fis, encode);
			br = new BufferedReader(isr);
			String line = "";
			List<String> list = new ArrayList<>();
			while ((line = br.readLine()) != null) {
				list.add(line);
			}
			return list;
		} catch (Exception e) {
			log.error(e);
		} finally {
			// 流的关闭顺序：先打开的后关，后打开的先关， 否则有可能出现java.io.IOException: Stream closed异常
			try {
				if (br != null)
					br.close();
				if (isr != null)
					isr.close();
				if (fis != null)
					fis.close();
			} catch (Exception e) {
				log.error(e);
			}
		}
		return null;

	}

	/**
	 * 一行一行写入文件，一个数组元素一行
	 */
	public static void saveFile2Array(String[] array, String savePath, String encode, boolean isOver) {
		if (array == null || array.length < 1)
			return;
		StringBuilder builder = new StringBuilder();
		for (String string : array) {
			builder.append(string).append("\r\n");
		}
		saveFile4StrCreate(builder.toString(), savePath, encode, isOver);
	}

	/**
	 * 一行一行写入文件，一个list元素一行
	 * 
	 */
	public static void saveFile2List(List<String> list, String savePath, String encode, boolean isOver) {
		if (list == null || list.size() < 1)
			return;
		StringBuilder builder = new StringBuilder();
		for (String string : list) {
			builder.append(string).append("\r\n");
		}
		saveFile4StrCreate(builder.toString(), savePath, encode, isOver);
	}

}