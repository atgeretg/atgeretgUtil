package com.atgeretg.util.thread;

public class ThreadUtil {
	public static void sleep(long time){
		try {
			Thread.sleep(time);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
