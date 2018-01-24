package com.atgeretg.util.http;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.Logger;

import com.atgeretg.util.file.FileUtil;

public class DownloadThread implements Runnable {
	static Logger logger = Logger.getLogger(DownloadThread.class);
	/**
	 * 正在运行的线程的数量
	 */
	private static int runningThreadCount;
	private long startIndex;
	private long endIndex;
	private int threadId;
	private String URL_Path;
	private String filePath;
	private int threadCount;
	private static final String TEMP_SUFFIX = ".temp";
	
	/**
	 * 多线程下载
	 * @param startIndex 开始字节
	 * @param endIndex 终止字节
	 * @param threadId 第几个下载的线程
	 * @param URL_Path URL路径
	 * @param filePath 文件路径
	 */
	public DownloadThread(long startIndex, long endIndex, int threadId, String URL_Path, String filePath) {
		super();
		this.startIndex = startIndex;
		this.endIndex = endIndex;
		this.threadId = threadId;
		this.URL_Path = URL_Path;
		this.filePath = filePath;
		this.threadCount = threadCount;
		runningThreadCount = threadCount;
	}

	@Override
	public void run() {
		String folderPath = FileUtil.getDir2FileDir(filePath);
		int total = 0;
		File pointionFile = new File(folderPath, threadId + TEMP_SUFFIX);
		try {
			if (pointionFile.exists() && pointionFile.length() > 0) {
				FileInputStream fos = new FileInputStream(pointionFile);
				BufferedReader br = new BufferedReader(new InputStreamReader(fos));
				int lastToal = Integer.valueOf(br.readLine());
				br.close();
				startIndex += lastToal;
				total += lastToal;
				// Log.i(tag, "上次下载大小：" + lastToal);
				System.out.println("上次下载大小：" + lastToal);
			}
			URL url = new URL(URL_Path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5000);
			conn.setAllowUserInteraction(true);  
			conn.setRequestProperty("Range", "bytes=" + startIndex + "-" + endIndex);

			int code = conn.getResponseCode();//这个应该是206的，但一直不是
			// Log.i(tag, "code=" + code);
//			System.out.println("threadId = "+ threadId + " code = "+code);
			InputStream is = conn.getInputStream();
			File file = new File(filePath);
			RandomAccessFile raf = new RandomAccessFile(file, "rwd");
			raf.seek(startIndex);
//			System.out.println("第" + threadId + "个线程：写文件的开始位置：" + String.valueOf(startIndex) + "   "+ "bytes=" + startIndex + "-" + endIndex);
			// Log.i(tag, "第" + threadId + "个线程：写文件的开始位置：" + String.valueOf(startIndex));
			int len = 0;

			byte[] buffer = new byte[1024 * 1024 *2];
			while ((len = is.read(buffer)) != -1) {
				// Log.i(tag, 1);
				RandomAccessFile rafTemp = new RandomAccessFile(pointionFile, "rwd");
				total += len;
				raf.write(buffer, 0, len);
				rafTemp.write(String.valueOf(total).getBytes());
				rafTemp.close();
			}
			is.close();
			raf.close();
		} catch (Exception e) {
			logger.error(e);
		} finally {
//			synchronized ("000") {
//				runningThreadCount--;
//				if (runningThreadCount < 1) {
//					for (int i = 1; i <= threadCount; i++) {
//						File f = new File(folderPath, threadId + TEMP_SUFFIX);
						if (pointionFile.exists()) {// 下载完成后，删除临时存储的文件
							pointionFile.delete();
//							System.out.println("delete");
						}
//					}
//				}
//			}
		}
	}

}


