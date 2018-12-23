package com.atgeretg.util.file;

import java.io.File;

import com.atgeretg.util.string.StrUtil;

/**
 * 将文本文件转换成指定的编码格式，不指定编码格式（null）默认为系统的编码格式
 * @author atgeretg
 *
 */
public class ChangeEncode4File {
	/**
	 * 
	 * @param srcEncode 文件原来的编码格式
	 * @param destEncode 什么编码格式，不设置（null）默认为系统的编码格式
	 */
	public ChangeEncode4File(String srcEncode, String destEncode) {
		this.destEncode = destEncode;
		this.srcEncode = srcEncode;
	}

	private String srcEncode;
	private String destEncode;

	public static void main(String[] args) {
		// String path1 =
		// "E:\\atgeretg\\document\\java基于swing界面的管理系统\\卜帅JAVA项目答辩\\xiangmu12-30\\src\\wo\\ai\\ni";
		String path1 = "E:\\atgeretg\\project\\java\\desk\\workspace\\snake\\src\\atgeretg\\snake\\util";
		String gbkPath = "C:\\Users\\hyt009\\Desktop\\JDY-BLE-SDK-android-V1.5\\JDY-BLE\\src\\com\\example";// "E:\\atgeretg\\document\\java基于swing界面的管理系统\\卜帅JAVA项目答辩\\xiangmu12-30\\src";
		// String path = "E:\\atgeretg\\document\\";
		// System.out.println(StrUtil.substringAfterLast(path1, path));
//		String utf8Path = "E:\\project\\android\\DoorLock\\app\\src\\main\\java\\com\\door\\hyt\\doorlock";
		String utf8Path = "E:\\project\\android\\doorlockTTT";
		// getFile(path,savePath,"GBK","UTF-8");
		// getFile(savePath,path);
		ChangeEncode4File G2U = new ChangeEncode4File(StrUtil.GBK,StrUtil.UTF8);
//		U2G.save4clear(utf8Path, gbkPath);
		G2U.save4keep(gbkPath,utf8Path);
		
		//////////////////////////////////////
//		gbkPath = "E:\\atgeretg\\test\\G2U_gbk.txt";
//		utf8Path = "E:\\atgeretg\\test\\G2U_Utf8.txt";
//		ChangeEncode4File G2U = new ChangeEncode4File(StrUtil.GBK,StrUtil.UTF8);
//		G2U.save4clear(gbkPath,utf8Path);
		
	}

	/**
	 * 保存的文件目录结构与原来的目录结构一至,不设置“savePath”为直接替换文件
	 * 
	 * @param readPath
	 * @param savePath 直接替换文件设置null
	 */
	public void save4keep(String readPath, String savePath) {
		getFile(readPath, savePath, true);
	}

	/**
	 * 全部文件保存在同一文件夹下（改变原来目录结构，去除原所有的子文件夹），文件名相同会被覆盖面，不设置“savePath”为直接替换文件
	 * 
	 * @param readPath
	 * @param savePath 直接替换文件设置null
	 */
	public void save4clear(String readPath, String savePath) {
		getFile(readPath, savePath, false);
	}

	/**
	 * 读取文件（结构）
	 * @param readPath
	 * @param savePath
	 * @param isKeep
	 */
	private void getFile(String readPath, String savePath, boolean isKeep) {
		File file = new File(readPath);

		if (file.isDirectory()) {
			String filePath = file.getAbsolutePath() + File.separator;
			String[] list = file.list();
			File f;
			for (String name : list) {
				// System.out.println(filePath + name);
				f = new File(filePath + name);
				if (isKeep)
					readFileKeep(f, readPath, savePath);
				else
					readFileClear(f, readPath, savePath);
			}
		} else {
			if (isKeep)
				readFileKeep(file, readPath, savePath);
			else
				readFileClear(file, readPath, savePath);
		}
	}

	/**
	 * 
	 * @param file
	 * @param readPath
	 * @param savePath
	 */
	private void readFileClear(File file, String readPath, String savePath) {
		if (file.isDirectory()) {
			System.out.println("readFile : " + file.getAbsolutePath());
			// getFile(file.getAbsolutePath(),savePath);
		} else {
			String string = FileUtil.readFile2str(file, srcEncode);
			String absolutePath = file.getAbsolutePath();
			if (savePath != null)
				savePath = savePath
						+ StrUtil.substringAfterLast(absolutePath, readPath);
			else
				savePath = absolutePath;
			FileUtil.saveFile4StrCreate(string, savePath, destEncode,true);
			// System.out.println("path readFile : " + file.getAbsolutePath());
			System.out.println(savePath);
		}
	}

	/**
	 * 全部文件目录结构与原来的目录结构一至，转换文件
	 * 
	 * @param file
	 * @param readPath
	 * @param savePath
	 */
	private void readFileKeep(File file, String readPath, String savePath) {
		if (file.isDirectory()) {
			System.out.println("readFile : " + file.getAbsolutePath());
			readFile(file, readPath, savePath);
		} else {
			String string = FileUtil.readFile2str(file, srcEncode);
			System.out.println(string);
			String absolutePath = file.getAbsolutePath();
			if (savePath != null)
				savePath = savePath
						+ StrUtil.substringAfterLast(absolutePath, readPath);
			else
				savePath = absolutePath;
			//String systemEncode = System.getProperty("file.encoding");
			FileUtil.saveFile4StrCreate(string, savePath, destEncode,true);
			// System.out.println("path readFile : " + file.getAbsolutePath());
			//System.out.println(savePath + "  systemEncode = " + systemEncode + "   destEncode = "+ destEncode + "  srcEncode = "+ srcEncode);
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
	private void readFile(File file, String readPath, String savePath) {
		String filePath = file.getAbsolutePath() + File.separator;
		String[] list = file.list();
		File f;
		for (String name : list) {
			// System.out.println(filePath + name);
			f = new File(filePath + name);
			readFileKeep(f, readPath, savePath);
		}
	}

}
