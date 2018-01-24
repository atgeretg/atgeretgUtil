package com.atgeretg.util.http;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

import com.atgeretg.util.url.UrlUtil;

public class MultiDownload {

    static int ThreadCount = 3;
    static int finishedThread = 0;
    //确定下载地址
    static String path = "http://192.168.13.13:8080/QQPlayer.exe";
    final static String filePath = "E:\\testDownload\\tool001df0.java";
    public static void main(String[] args) {
    	String pathGet = "http://localhost:8080/myDownload/DownloadServlet?download=";// tool%25281%2529.jar
		// String pathGet =
		// "http://47.52.227.171:8080/myDownload/DownloadServlet?download=";//
		// %25E5%2595%2586%25E5%2593%2581%25E4%25B8%258A%25E6%259E%25B61-15.jar";
		// String name = pathGet.split("download=")[1];
		// String decoderString =
		// UrlUtil.getURLDecoderString("%25E5%2595%2586%25E5%2593%2581%25E4%25B8%258A%25E6%259E%25B61-15.jar");
		// String decoderString2 = UrlUtil.getURLDecoderString(name);
		// HttpClientUtil.getInstance().httpDownload(,
		// "E:\\tool.jar.temp");
//		download(, 5,"E:\\testDownload\\tool1df0.java");
    	
		path = pathGet + UrlUtil.getURLEncoderString("MainActivity.java", 2);
        //发送get请求，请求这个地址的资源
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            
            if(conn.getResponseCode() == 200){
                //拿到所请求资源文件的长度
                int length = conn.getContentLength();
                
                File file = new File(filePath);
                //生成临时文件
                RandomAccessFile raf = new RandomAccessFile(file, "rwd");
                //设置临时文件的大小
                raf.setLength(length);
                raf.close();
                //计算出每个线程应该下载多少字节
                int size = length / ThreadCount;
                
                for (int i = 0; i < ThreadCount; i++) {
                    //计算线程下载的开始位置和结束位置
                    int startIndex = i * size;
                    int endIndex = (i + 1) * size - 1;
                    //如果是最后一个线程，那么结束位置写死
                    if(i == ThreadCount - 1){
                        endIndex = length - 1;
                    }
//                    System.out.println("线程" + i + "的下载区间是：" + startIndex + "---" + endIndex);
                    new DownLoadThread(startIndex, endIndex, i).start();
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
   static class DownLoadThread extends Thread{
        int startIndex;
        int endIndex;
        int threadId;
        
        public DownLoadThread(int startIndex, int endIndex, int threadId) {
            super();
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.threadId = threadId;
        }

        @Override
        public void run() {
            //再次发送http请求，下载原文件
            try {
                File progressFile = new File(threadId + ".txt");
                //判断进度临时文件是否存在
                if(progressFile.exists()){
                    FileInputStream fis = new FileInputStream(progressFile);
                    BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                    //从进度临时文件中读取出上一次下载的总进度，然后与原本的开始位置相加，得到新的开始位置
                    startIndex += Integer.parseInt(br.readLine());
                    fis.close();
                }
                System.out.println("线程" + threadId + "的下载区间是：" + startIndex + "---" + endIndex);
                HttpURLConnection conn;
                URL url = new URL(MultiDownload.path);
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(5000);
                conn.setReadTimeout(5000);
                //设置本次http请求所请求的数据的区间
                conn.setRequestProperty("Range", "bytes=" + startIndex + "-" + endIndex);
                
                //请求部分数据，相应码是206
                if(conn.getResponseCode() == 206){
                    //流里此时只有1/3原文件的数据
                    InputStream is = conn.getInputStream();
                    byte[] b = new byte[1024];
                    int len = 0;
                    int total = 0;
                    //拿到临时文件的输出流
                    File file = new File(MultiDownload.filePath);
                    RandomAccessFile raf = new RandomAccessFile(file, "rwd");
                    //把文件的写入位置移动至startIndex
                    raf.seek(startIndex);
                    while((len = is.read(b)) != -1){
                        //每次读取流里数据之后，同步把数据写入临时文件
                        raf.write(b, 0, len);
                        total += len;
//                        System.out.println("线程" + threadId + "下载了" + total);
                        
                        //生成一个专门用来记录下载进度的临时文件
                        RandomAccessFile progressRaf = new RandomAccessFile(progressFile, "rwd");
                        //每次读取流里数据之后，同步把当前线程下载的总进度写入进度临时文件中
                        progressRaf.write((total + "").getBytes());
                        progressRaf.close();
                    }
                    System.out.println("线程" + threadId + "下载完毕-------------------小志参上！");
                    raf.close();
                    
                    MultiDownload.finishedThread++;
                    synchronized (MultiDownload.path) {
                        if(MultiDownload.finishedThread == MultiDownload.ThreadCount){
                            for (int i = 0; i < MultiDownload.ThreadCount; i++) {
                                File f = new File(i + ".txt");
                                f.delete();
                            }
                            MultiDownload.finishedThread = 0;
                        }
                    }
                    
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
}




