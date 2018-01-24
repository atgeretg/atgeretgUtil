package com.atgeretg.util.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolService {
	private ExecutorService pool;
	private static ThreadPoolService tps;
	public static ThreadPoolService getThreadPool(){
		if(tps == null)
			tps = new ThreadPoolService();
    	return tps;
    }
	
	private  ThreadPoolService(){
		pool = Executors.newFixedThreadPool(500);
//		pool.
	}
	public void addThread(Runnable thread){
		pool.execute(thread);
	}
	public void close(){
		if(!pool.isShutdown()){
		pool.shutdown();
		tps = null;
		pool = null;
		
		}
	}
	
//	 public static void main(String[] args) throws IOException, InterruptedException {
//	        // 创建一个固定大小的线程池
//	        ExecutorService service = Executors.newFixedThreadPool(3);
//	        for (int i = 0; i < 1000; i++) {
//	            System.out.println("创建线程" + i);
//	            Runnable run = new Runnable() {
//	                @Override
//	                public void run() {
//	                	int ii = 0;
//	                	while(ii<50000){
//	                		ii++;
//	                		System.out.println("启动线程" + ii);
//	                	}
//	                }
//	            };
//	            // 在未来某个时间执行给定的命令
//	            service.execute(run);
//	        }
//	        // 关闭启动线程
//	        service.shutdown();
//	        // 等待子线程结束，再继续执行下面的代码
//	        service.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
//	        System.out.println("all thread complete");
//	    }
//	
	
	
	
}
