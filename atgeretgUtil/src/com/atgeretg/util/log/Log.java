package com.atgeretg.util.log;

import java.util.Iterator;
import java.util.Map;

import com.atgeretg.util.number.ByteUtil;
import com.atgeretg.util.string.StringUtil;

/**
 * 将数据打印在控制台上（System.out.println（））
 * @author atgeretg
 *
 */
public class Log {
	public static void main(String[] args) {
		systemLog(new String[]{"fd","fd96"});
		systemLog("dfd","fdsa","fdsa");
		systemLog(new Double[]{1d,5d,5d,6d,9d});
	}
	
	/**
	 * 系统的打印输出 Object<br>
	 * 即是：System.out.println()
	 * 
	 * @param object Object
	 */
	public static void systemLog(Object... object) {
		for (Object obj : object) {
			myLog(obj);	
		}
		
	}
	/**
	 * 系统的打印输出 Object,不换行打印<br>
	 * 即是：System.out.println()
	 * 
	 * @param object Object
	 */
	public static void systemLog_notLn(Object... object) {
		for (Object obj : object) {
			System.out.print(obj);	
		}
		myLog("");
	}
	
	
	/**
	 * 系统的打印输出 String数组<br>
	 * 即是：System.out.println()
	 * 
	 * @param o Object
	 */
	public static void systemLog(String... string) {
		for (String s:string) {
			myLog(s);
		}
	}
	
	/**
	 * 系统的打印输出 String数组,不换行打印<br>
	 * 即是：System.out.println()
	 * 
	 * @param o Object
	 */
	public static void systemLog_notLn(String... string) {
		for (String s:string) {
			System.out.print(s);
		}
		System.out.println();
	}
	
	
	/**
	 * 系统的打印输出 Integer<br>
	 * 即是：System.out.println()
	 * 
	 * @param integer Integer对象
	 */
	public static void systemLog(Integer... integer) {
		for (Integer i:integer) {
			myLog(i);
		}
	}
	
	/**
	 * 系统的打印输出 byte[]数组<br>
	 * @param arrBytes
	 */
	public static void systemLog(byte[] arrBytes) {
		myLog(ByteUtil.byteArray2HexString(arrBytes, true));
	}
	
	/**
	 * 系统的打印输出 int[]数组<br>
	 * 即是：System.out.println()
	 * 
	 * @param i int数组
	 */
	public static void systemLog(int[] i) {
		myLog(StringUtil.intArraytoString(i));
	}
	
	/**
	 * 系统的打印输出 double[]数组<br>
	 * 即是：System.out.println()
	 * 
	 * @param d double数组
	 */
	public static void systemLog(double[] d) {
		for (double dd:d) {
			myLog(dd);
		}
		
	}
	
	/**
	 * 系统的打印输出 float[]数组<br>
	 * 即是：System.out.println()
	 * 
	 * @param f float数组
	 */
	public static void systemLog(float[] f) {
		for (float ff:f) {
			myLog(ff);
		}
	}
	
	/**
	 * 系统的打印输出 float[]数组<br>
	 * 即是：System.out.println()
	 * 
	 * @param f float数组
	 */
	public static void systemLog(Map map) {
		Iterator iterator = map.keySet().iterator();
		while(iterator.hasNext()){
			Object key = iterator.next();
			Object values = map.get(key);
			myLog("key = " + key + "  values = " + values);
		}
			
	}
	
	/**
	 * 异常打印输出
	 * 即是：e.printStackTrace();
	 * 
	 * @param e 异常
	 */
	public static void exceptionLog(Exception e){
		e.printStackTrace();
	}
	
	private static void myLog(Object o) {
		System.out.println(o);
	}
}
