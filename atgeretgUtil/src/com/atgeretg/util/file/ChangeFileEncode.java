package com.atgeretg.util.file;

import java.io.File;

import com.atgeretg.util.string.StrUtil;

public class ChangeFileEncode {

	public static void main(String[] args) {
		// String path1 =
		// "E:\\atgeretg\\document\\java基于swing界面的管理系统\\卜帅JAVA项目答辩\\xiangmu12-30\\src\\wo\\ai\\ni";
		String path1 = "E:\\atgeretg\\project\\java\\desk\\workspace\\snake\\src\\atgeretg\\snake\\util";
		String path = "E:\\atgeretg\\test\\gbkTest.txt";// "E:\\atgeretg\\document\\java基于swing界面的管理系统\\卜帅JAVA项目答辩\\xiangmu12-30\\src";
		// String path = "E:\\atgeretg\\document\\";
		// System.out.println(StrUtil.substringAfterLast(path1, path));
		String savePath = "E:\\atgeretg\\test\\Utf8Test.txt";
		// getFile(path,savePath,"GBK","UTF-8");
		getFile(savePath, path, StrUtil.UTF8, StrUtil.GBK);
	}

	public static void getFile(String readPath, String savePath,
			String srcEncode, String destEncode) {
		File file = new File(readPath);

		if (file.isDirectory()) {
			String filePath = file.getAbsolutePath() + File.separator;
			String[] list = file.list();
			File f;
			for (String name : list) {
				// System.out.println(filePath + name);
				f = new File(filePath + name);
				readFileKeep(f, readPath, savePath, srcEncode, destEncode);
			}
		} else {
			readFileKeep(file, readPath, savePath, srcEncode, destEncode);
		}
	}

	/**
	 * 全部文件在同一文件夹下（去除原所有的文件夹）
	 * 
	 * @param file
	 * @param readPath
	 * @param savePath
	 */
	private static void readFileClear(File file, String readPath,
			String savePath) {
		if (file.isDirectory()) {
			System.out.println("readFile : " + file.getAbsolutePath());
			// getFile(file.getAbsolutePath(),savePath);
		} else {
			String string = FileUtil.readFile2str(file);
			String absolutePath = file.getAbsolutePath();
			if (savePath != null)
				savePath = savePath
						+ StrUtil.substringAfterLast(absolutePath, readPath);
			else
				savePath = absolutePath;
			// FileUtil.saveFile4StrCreate(StrUtil.changEncode(string,
			// StrUtil.GBK, StrUtil.UTF8), savePath, true);
			// System.out.println("path readFile : " + file.getAbsolutePath());
			System.out.println(savePath);
		}
	}

	/**
	 * 全部文件目录结构与原来的一至
	 * 
	 * @param file
	 * @param readPath
	 * @param savePath
	 */
	private static void readFileKeep(File file, String readPath,
			String savePath, String srcEncond, String destEncode) {
		if (file.isDirectory()) {
			System.out.println("readFile : " + file.getAbsolutePath());
			readFile(file, readPath, savePath, srcEncond, destEncode);
		} else {
			String string = FileUtil.readFile2str(file, srcEncond);
			System.out.println(string);
			String absolutePath = file.getAbsolutePath();
			if (savePath != null)
				savePath = savePath
						+ StrUtil.substringAfterLast(absolutePath, readPath);
			else
				savePath = absolutePath;
			String systemEncode = System.getProperty("file.encoding");
			// if(!systemEncode.equals(destEncode))
			// string = StrUtil.changEncode(string, systemEncode, destEncode);
			System.out.println(string);
			// string = StrUtil.changEncode(string, destEncode,
			// System.getProperty("file.encoding"));
			// System.out.println(string);
			// FileUtil.saveFile4StrCreate(string, savePath, true);
			// System.out.println("path readFile : " + file.getAbsolutePath());
			System.out.println(savePath + "  " + systemEncode);
		}
	}

	/**
	 * file
	 * 
	 * @param file
	 *            一定要是个文件夹
	 * @param readPath
	 * @param savePath
	 */
	private static void readFile(File file, String readPath, String savePath,
			String srcEncond, String destEncode) {
		String filePath = file.getAbsolutePath() + File.separator;
		String[] list = file.list();
		File f;
		for (String name : list) {
			// System.out.println(filePath + name);
			f = new File(filePath + name);
			readFileKeep(f, readPath, savePath, srcEncond, destEncode);
		}
	}

}
