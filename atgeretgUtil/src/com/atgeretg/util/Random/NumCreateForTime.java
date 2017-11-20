package com.atgeretg.util.Random;

import java.util.Random;

import com.atgeretg.util.date.DateUtil;

public class NumCreateForTime {
	// private SimpleDateFormat sim=null;//用来获取时间

	/**
	 * 根据时间戳和前后在时间前后加上一些随机数生成唯一的一个编号
	 * 
	 * @return 唯一的字符串编号
	 */
	public static String getTimeRand() {
		StringBuffer sbf = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < 6; i++) {
			sbf.append(random.nextInt(10));
		}
		sbf.append(DateUtil.getTimeStamp()); // 返回时间戳
		// 要产生随机数
		for (int i = 0; i < 3; i++) { // 产生三位随机数
			sbf.append(random.nextInt(10)); // 每位随机数都不超过10
		}
		return sbf.toString();
	}

	/**
	 * 指定字符串长度根据时间戳和前后在时间前后加上一些随机数生成唯一的一个编号
	 * 
	 * @param length
	 *            生成的字符串长度，不要小于17，小于默认17
	 * @return 唯一的字符串编号
	 */
	public static String getTimeRand(int length) {
		StringBuffer sbf = new StringBuffer();
		Random random = new Random();
		//sbf.append(random.nextInt(10));
		int j = length - 17;
		if (j > 0 && j % 2 != 0)
			sbf.append(random.nextInt(10));
		for (int i = 0; i < j / 2; i++) {// 要产生随机数
			sbf.append(random.nextInt(10));
		}
		sbf.append(DateUtil.getTimeStamp()); // 返回时间戳
		// 要产生随机数
		for (int i = 0; i < j / 2; i++) { // 产生三位随机数
			sbf.append(random.nextInt(10)); // 每位随机数都不超过10
		}
		return sbf.toString();
	}

	/**
	 * 指定字符串长度,在时间戳中插入一些随机数生成唯一的一个编号
	 * 
	 * @param length
	 *            生成的字符串长度，不要小于17，小于默认17
	 * @return 唯一的字符串编号
	 */
	public static String getTimeRandInsert(int length) {
		
		String timeStamp = DateUtil.getTimeStamp();
		//sbf.append(random.nextInt(10));
		int j = length - 17;
		if(j <= 0)
			return timeStamp;
		StringBuffer sbf = new StringBuffer(timeStamp);
//		System.out.println("timeStamp = " + timeStamp);
		Random random = new Random();
		for (int i = 0; i < j; i++) {// 要产生随机数
			//array[i] = random.nextInt(10);
			if(j<5){
				sbf.insert((i+1)*4+i, random.nextInt(10));
			}else{
				sbf.insert(random.nextInt(sbf.length()), random.nextInt(10));
			}
		}
		return sbf.toString();
	}
	
	/**
	 * 指定字符串长度,在时间(秒级别)中插入一些随机数生成唯一的一个编号
	 * 
	 * @param length 指定字符串长度
	 *            
	 * @return 唯一的字符串编号
	 */
	public static String getDateRand(int length) {
		
		String timeStamp = DateUtil.getShortYMDHMS();
		//sbf.append(random.nextInt(10));
		int j = length - 17;
		if(j <= 0)
			return timeStamp;
		StringBuffer sbf = new StringBuffer(timeStamp);
//		System.out.println("timeStamp = " + timeStamp);
		Random random = new Random();
		for (int i = 0; i < j; i++) {// 要产生随机数
			sbf.append(random.nextInt(10));
		}
		return sbf.toString();
	}
	
	/**
	 * 指定时间(秒级别)字符串后加入几个随机数，生成唯一的一个编号
	 * 
	 * @param length 最后加入的几位随机数
	 *            
	 * @return 唯一的字符串编号
	 */
	public static String getDateRandLastAdd(int length) {
		
		String timeStamp = DateUtil.getShortYMDHMS();
		//sbf.append(random.nextInt(10));
		int j = length;
		if(j <= 0)
			return timeStamp;
		StringBuffer sbf = new StringBuffer(timeStamp);
//		System.out.println("timeStamp = " + timeStamp);
		Random random = new Random();
		for (int i = 0; i < j; i++) {// 要产生随机数
			sbf.append(random.nextInt(10));
		}
		return sbf.toString();
	}

	
	// 做测试
	public static void main(String[] ary) {
		//IpTimeStarmp IpTimeStamp = new IpTimeStarmp("172.168.3.222");// 调用有参数的构造方法
		System.out.println(getTimeRand());
		String timeRand = getTimeRandInsert(22);
		System.out.println(timeRand + "  length = " + timeRand.length());
	}
}
