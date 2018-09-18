package com.atgeretg.util.number;

public class NumTool {

	
	/**
	 * 判断一个数值是否在一个区间内，两边都是开区间,是在此区间返回原值，否返回设定的值
	 * @param value
	 * @param minNum
	 * @param maxNum
	 * @param hopeNum
	 * @return
	 */
	public static int isAtIntervalBothOpenRe(int value, int minNum, int maxNum, int hopeNum) {
		if(minNum<value && value<maxNum)
			return value;
		return hopeNum;
	}
	
	/**
	 * 判断一个数值是否在一个区间内，两边都是开区间，是在此区间返回原值，否返回设定的值
	 * @param value
	 * @param minNum
	 * @param maxNum
	 * @param hopeNum
	 * @return
	 */
	public static double isAtIntervalBothOpenRe(double value, double minNum, double maxNum,double hopeNum) {
		if(minNum<value && value<maxNum)
			return value;
		return hopeNum;
	}
	
	/**
	 * 判断一个数值是否在一个区间内，左边开区间，右边闭区间,是在此区间返回原值，否返回设定的值
	 * @param value
	 * @param minNum
	 * @param maxNum
	 * @param hopeNum
	 * @return
	 */
	public static int isAtIntervalMinOpenRe(int value, int minNum, int maxNum,int hopeNum) {
		if(minNum<value && value<=maxNum)
			return value;
		return hopeNum;
	}
	
	/**
	 * 判断一个数值是否在一个区间内，左边开区间，右边闭区间,是在此区间返回原值，否返回设定的值
	 * @param value
	 * @param minNum
	 * @param maxNum
	 * @param hopeNum
	 * @return
	 */
	public static double isAtIntervalMinOpenRe(double value, double minNum, double maxNum,double hopeNum) {
		if(minNum<value && value<=maxNum)
			return value;
		return hopeNum;
	}

	/**
	 * 判断一个数值是否在一个区间内，右边开区间,左边闭区间,是在此区间返回原值，否返回设定的值
	 * @param value
	 * @param minNum
	 * @param maxNum
	 * @param hopeNum
	 * @return
	 */
	public static double isAtIntervalMaxOpenRe(double value, double minNum, double maxNum,double hopeNum) {
		if(minNum<=value && value<maxNum)
			return value;
		return hopeNum;
	}
	
	/**
	 * 判断一个数值是否在一个区间内，右边开区间,左边闭区间,是在此区间返回原值，否返回设定的值
	 * @param value
	 * @param minNum
	 * @param maxNum
	 * @param hopeNum
	 * @return
	 */
	public static int isAtIntervalMaxOpenRe(int value, int minNum, int maxNum, int hopeNum) {
		if(minNum<=value && value<maxNum)
			return value;
		return hopeNum;
	}
	
	/**
	 * 判断一个数值是否在一个区间内，两边都闭区间,是在此区间返回原值，否返回设定的值
	 * @param value
	 * @param minNum
	 * @param maxNum
	 * @param hopeNum
	 * @return
	 */
	public static double isAtIntervalBothCloseRe(double value, double minNum, double maxNum, double hopeNum) {
		if(minNum<=value && value<=maxNum)
			return value;
		return hopeNum;
	}
	
	/**
	 * 判断一个数值是否在一个区间内，右边开区间,左边闭区间,是在此区间返回原值，否返回设定的值
	 * @param value
	 * @param minNum
	 * @param maxNum
	 * @param hopeNum
	 * @return
	 */
	public static int isAtIntervalBothCloseRe(int value, int minNum, int maxNum,int hopeNum) {
		if(minNum<=value && value<=maxNum)
			return value;
		return hopeNum;
	}
	
	
	
	/**
	 * 判断一个数值是否在一个区间内，两边都是开区间
	 * @param value
	 * @param minNum
	 * @param maxNum
	 * @return
	 */
	public static boolean isAtIntervalBothOpen(int value, int minNum, int maxNum) {
		if(minNum<value && value<maxNum)
			return true;
		return false;
	}
	
	/**
	 * 判断一个数值是否在一个区间内，两边都是开区间
	 * @param value
	 * @param minNum
	 * @param maxNum
	 * @return
	 */
	public static boolean isAtIntervalBothOpen(double value, double minNum, double maxNum) {
		if(minNum<value && value<maxNum)
			return true;
		return false;
	}
	
	/**
	 * 判断一个数值是否在一个区间内，左边开区间，右边闭区间
	 * @param value
	 * @param minNum
	 * @param maxNum
	 * @return
	 */
	public static boolean isAtIntervalMinOpen(int value, int minNum, int maxNum) {
		if(minNum<value && value<=maxNum)
			return true;
		return false;
	}
	
	/**
	 * 判断一个数值是否在一个区间内，左边开区间，右边闭区间
	 * @param value
	 * @param minNum
	 * @param maxNum
	 * @return
	 */
	public static boolean isAtIntervalMinOpen(double value, double minNum, double maxNum) {
		if(minNum<value && value<=maxNum)
			return true;
		return false;
	}

	/**
	 * 判断一个数值是否在一个区间内，右边开区间,左边闭区间
	 * @param value
	 * @param minNum
	 * @param maxNum
	 * @return
	 */
	public static boolean isAtIntervalMaxOpen(double value, double minNum, double maxNum) {
		if(minNum<=value && value<maxNum)
			return true;
		return false;
	}
	
	/**
	 * 判断一个数值是否在一个区间内，右边开区间,左边闭区间
	 * @param value
	 * @param minNum
	 * @param maxNum
	 * @return
	 */
	public static boolean isAtIntervalMaxOpen(int value, int minNum, int maxNum) {
		if(minNum<=value && value<maxNum)
			return true;
		return false;
	}
	
	/**
	 * 判断一个数值是否在一个区间内，两边都闭区间
	 * @param value
	 * @param minNum
	 * @param maxNum
	 * @return
	 */
	public static boolean isAtIntervalBothClose(double value, double minNum, double maxNum) {
		if(minNum<=value && value<=maxNum)
			return true;
		return false;
	}
	
	/**
	 * 判断一个数值是否在一个区间内，右边开区间,左边闭区间
	 * @param value
	 * @param minNum
	 * @param maxNum
	 * @return
	 */
	public static boolean isAtIntervalBothClose(int value, int minNum, int maxNum) {
		if(minNum<=value && value<=maxNum)
			return true;
		return false;
	}
	
}
