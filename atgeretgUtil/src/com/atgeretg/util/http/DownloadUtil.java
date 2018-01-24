package com.atgeretg.util.http;

import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.Logger;

import com.atgeretg.util.file.FileUtil;
import com.atgeretg.util.string.StrUtil;
import com.atgeretg.util.thread.ThreadPoolService;
import com.atgeretg.util.thread.ThreadUtil;
import com.atgeretg.util.url.UrlUtil;

public class DownloadUtil {
	static Logger logger = Logger.getLogger(DownloadUtil.class);
	/**
	 * 正在运行的线程的数量
	 */
	private static int runningThreadCount;
	
	/**
	 * 线程的数量
	 */
	private static int threadCount;

	public static boolean finish = false;
	

	/**
	 * 每个下载区块的大小
	 */
	// private static long blocksize;

	public static void main(String[] args) {
		DownloadUtil d = new DownloadUtil();
		// File f= new
		// File("D:\\home\\atgeretg\\Documents\\myDownload\\serversDownload\\tool(1).jar");
		// System.out.println(f.length());
		// String pathGet =
		// "http://localhost:8080/myDownload/DownloadServlet?download=";//
		// tool%25281%2529.jar
		String pathGet = "http://119.23.46.155:8080/myDownload/DownloadServlet?download=";//
		// %25E5%2595%2586%25E5%2593%2581%25E4%25B8%258A%25E6%259E%25B61-15.jar";
		// String name = pathGet.split("download=")[1];
		// String decoderString =
		// UrlUtil.getURLDecoderString("%25E5%2595%2586%25E5%2593%2581%25E4%25B8%258A%25E6%259E%25B61-15.jar");
		// String decoderString2 = UrlUtil.getURLDecoderString(name);
		// HttpClientUtil.getInstance().httpDownload(,
		// "E:\\tool.jar.temp");
		String download = d.download(pathGet + UrlUtil.getURLEncoderString("商品上架1-15.jar", 2), 5,
				"E:\\testDownload\\tool1df0.java");
		System.out.println(download);
	}

	/**
	 * 下载文件（多线程，目前不是只能一个线程）
	 * 
	 * @param pathUrl
	 *            网络文件路径
	 * @param threadCount
	 *            线程数（开几个线程下载）
	 * @param savaPath
	 *            保存文件路径（要全路径，即包括文件名，路径不存在会被创建）
	 * @return null | 保存的文件路径
	 */
	public String download(String pathUrl, int startThreadCount, String savaPath) {
		startThreadCount = 1;
		threadCount = startThreadCount;
		finish = false;
		String fileSavePath = null;
		try {
			URL url = new URL(pathUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(5000);
			connection.setRequestProperty("Accept-Encoding", "identity");
			int code = connection.getResponseCode();
			if (code == 200) {
				// int size = connection.getContentLength();
				long size = connection.getContentLengthLong();
				long blocksize = size / threadCount;
				runningThreadCount = threadCount;
				// FileUtil.createDir4file(savaPath);
				// File file = new File(savaPath);
				File file = FileUtil.reNameFile(savaPath);
				fileSavePath = file.getAbsolutePath();
				RandomAccessFile tempFile = new RandomAccessFile(file, "rw");
				System.out.println(size);
				tempFile.setLength(size);
				// Log.i(tag, String.valueOf(runningThreadCount));
				for (int i = 1; i <= threadCount; i++) {
					long startIndex = (i - 1) * blocksize;
					long endIndex = i * blocksize - 1;
					if (i == threadCount) {
						endIndex = size;
					}
					// ThreadPoolService.getThreadPool()
					// .addThread();
					// System.out.println();
					// System.out.println();
					// System.out.println();
					// System.out.println("threadCount = " + i);
					// System.out.println("endIndex = " + endIndex);
					// System.out.println("startIndex = " + startIndex);
					ThreadPoolService.getThreadPool()
							.addThread(new DownloadThread(startIndex, endIndex, i, pathUrl, savaPath));
				}
				tempFile.close();
			}
			connection.disconnect();
			// System.out.println("finish");
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
		while (!finish)
			ThreadUtil.sleep(500);
		return fileSavePath;
	}

	static class DownloadThread implements Runnable {

		private long startIndex;
		private long endIndex;
		private int threadId;
		private String URL_Path;
		private String filePath;
		private static final String TEMP_SUFFIX = ".temp";

		/**
		 * 多线程下载
		 * 
		 * @param startIndex
		 *            开始字节
		 * @param endIndex
		 *            终止字节
		 * @param threadId
		 *            第几个下载的线程
		 * @param URL_Path
		 *            URL路径
		 * @param filePath
		 *            文件路径
		 */
		public DownloadThread(long startIndex, long endIndex, int threadId, String URL_Path, String filePath) {
			super();
			this.startIndex = startIndex;
			this.endIndex = endIndex;
			this.threadId = threadId;
			this.URL_Path = URL_Path;
			this.filePath = filePath;
		}

		@Override
		public void run() {
			String folderPath = FileUtil.getDir2FileDir(filePath);
			int total = 0;
			File pointionFile = new File(folderPath, threadId + TEMP_SUFFIX);
			try {
				if (pointionFile.exists() && pointionFile.length() > 0) {
					pointionFile.delete();
					// 目前不能续下载
					// FileInputStream fos = new FileInputStream(pointionFile);
					// BufferedReader br = new BufferedReader(new InputStreamReader(fos));
					// int lastToal = Integer.valueOf(br.readLine());
					// br.close();
					// startIndex += lastToal;
					// total += lastToal;
					// System.out.println("上次下载大小：" + lastToal);
				}
				URL url = new URL(URL_Path);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setConnectTimeout(5000);
				conn.setAllowUserInteraction(true);
				conn.setRequestProperty("Range", StrUtil.stringBuilder("bytes=", startIndex, "-", endIndex));

				int code = conn.getResponseCode();// 这个应该是206的，但一直不是
				// Log.i(tag, "code=" + code);
				System.out.println("threadId = " + threadId + " code = " + code);
				InputStream is = conn.getInputStream();
				File file = new File(filePath);
				RandomAccessFile raf = new RandomAccessFile(file, "rwd");
				raf.seek(startIndex);
				// System.out.println("第" + threadId + "个线程：写文件的开始位置：" +
				// String.valueOf(startIndex) + " "+ "bytes=" + startIndex + "-" + endIndex);
				// Log.i(tag, "第" + threadId + "个线程：写文件的开始位置：" + String.valueOf(startIndex));
				int len = 0;

				byte[] buffer = new byte[1024 * 1024 * 2];
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
				synchronized ("000") {
					runningThreadCount--;
					if (runningThreadCount < 1) {
						for (int i = 1; i <= threadCount; i++) {
							File f = new File(folderPath, threadId + TEMP_SUFFIX);
							if (f.exists()) {// 下载完成后，删除临时存储的文件
								f.delete();
							}
						}
						finish = true;
					}
				}
			}
		}
	}
}
