package com.atgeretg.util.arrary;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

/**
 * 此类用了org.apache.commons.lang3.ArrayUtils
 * 
 * @author atgeretg_com
 *
 */
public class ArrayUtil {

	/**
	 * Integer[]转换成int[],这个也可以直接调用org.apache.commons.lang3.ArrayUtils.toPrimitive()，还其它类型直接调用apache这个方法
	 * <br>
	 * 如果是jdk1.8用这个方法用
	 * Arrays.stream(integers).mapToInt(Integer::valueOf).toArray()将Integer[]转换成int[]
	 * 
	 * @param array
	 * @return
	 */
	public static int[] integerArray2Int(Integer[] array) {
		return ArrayUtils.toPrimitive(array);// ArrayUtils.toObject(arraySrc)
	}

	/**
	 * int[]转换成Integer[],这个也可以直接调用org.apache.commons.lang3.ArrayUtils.toObject()，还其它类型直接调用apache这个方法
	 * <br>
	 * 
	 * @param array
	 * @return
	 */
	public static Integer[] intArray2integer(int[] array) {
		return ArrayUtils.toObject(array);
	}

	/**
	 * 将一个数组分割若干个指定的大小的的数组<br>
	 * stringArray为null或size<1,返回空的list
	 * 
	 * @param stringArray
	 *            源数组
	 * @param subSize
	 *            每个小的数组大小
	 * @return List 不会有null
	 */
	public static List<String[]> splitArray(String[] array, int subSize) {
		return new SplitArrayUtil<String>().splitArray(array, subSize);
	}

	/**
	 * 将一个数组分割若干个指定的大小的的数组<br>
	 * integerArray为null或size<1,返回空的list
	 * 
	 * @param arraySrc
	 *            源数组
	 * @param subSize
	 *            每个小的数组大小
	 * @return List 不会有null
	 */
	public static List<Integer[]> splitArray(Integer[] arraySrc, int subSize) {
		return new SplitArrayUtil<Integer>().splitArray(arraySrc, subSize);
	}

	/**
	 * 将一个数组分割若干个指定的大小的的数组<br>
	 * arraySrc为null或size<1,返回空的list<br>
	 * 
	 * @param arraySrc
	 *            源数组
	 * @param subSize
	 *            每个小的数组大小
	 * @return List 不会有null
	 */
	public static List<Integer[]> splitArray(int[] arraySrc, int subSize) {
		return splitArray(ArrayUtils.toObject(arraySrc), subSize);
	}

	/**
	 * 将一个数组分割若干个指定的大小的的数组<br>
	 * arraySrc为null或size<1,返回空的list
	 * 
	 * @param arraySrc
	 *            源数组
	 * @param subSize
	 *            每个小的数组大小
	 * @return List 不会有null
	 */
	public static List<Float[]> splitArray(Float[] arraySrc, int subSize) {
		return new SplitArrayUtil<Float>().splitArray(arraySrc, subSize);
	}

	/**
	 * 将一个数组分割若干个指定的大小的的数组<br>
	 * arraySrc为null或size<1,返回空的list<br>
	 * 
	 * @param arraySrc
	 *            源数组
	 * @param subSize
	 *            每个小的数组大小
	 * @return List 不会有null
	 */
	public static List<Float[]> splitArray(float[] arraySrc, int subSize) {
		return splitArray(ArrayUtils.toObject(arraySrc), subSize);
	}

	/**
	 * 将一个数组分割若干个指定的大小的的数组<br>
	 * doubleArray为null或size<1,返回空的list
	 * 
	 * @param arraySrc
	 *            源数组
	 * @param subSize
	 *            每个小的数组大小
	 * @return List 不会有null
	 */
	public static List<Double[]> splitArray(Double[] arraySrc, int subSize) {
		return new SplitArrayUtil<Double>().splitArray(arraySrc, subSize);
	}

	/**
	 * 将一个数组分割若干个指定的大小的的数组<br>
	 * arraySrc为null或size<1,返回空的list<br>
	 * 
	 * @param floatArray
	 *            源数组
	 * @param subSize
	 *            每个小的数组大小
	 * @return List 不会有null
	 */
	public static List<Double[]> splitArray(double[] arraySrc, int subSize) {
		return splitArray(ArrayUtils.toObject(arraySrc), subSize);
	}

	/**
	 * 将一个数组分割若干个指定的大小的的数组<br>
	 * doubleArray为null或size<1,返回空的list
	 * 
	 * @param arraySrc
	 *            源数组
	 * @param subSize
	 *            每个小的数组大小
	 * @return List 不会有null
	 */
	public static List<Long[]> splitArray(Long[] arraySrc, int subSize) {
		return new SplitArrayUtil<Long>().splitArray(arraySrc, subSize);
	}

	/**
	 * 将一个数组分割若干个指定的大小的的数组<br>
	 * arraySrc为null或size<1,返回空的list<br>
	 * 
	 * @param floatArray
	 *            源数组
	 * @param subSize
	 *            每个小的数组大小
	 * @return List 不会有null
	 */
	public static List<Long[]> splitArray(long[] arraySrc, int subSize) {
		return splitArray(ArrayUtils.toObject(arraySrc), subSize);
	}

	/**
	 * 将一个数组分割若干个指定的大小的的数组<br>
	 * doubleArray为null或size<1,返回空的list
	 * 
	 * @param arraySrc
	 *            源数组
	 * @param subSize
	 *            每个小的数组大小
	 * @return List 不会有null
	 */
	public static List<Number[]> splitArray(Number[] arraySrc, int subSize) {
		return new SplitArrayUtil<Number>().splitArray(arraySrc, subSize);
	}

	/**
	 * 将一个数组分割若干个指定的大小的的数组<br>
	 * arraySrc为null或size<1,返回空的list<br>
	 * 
	 * @param floatArray
	 *            源数组
	 * @param subSize
	 *            每个小的数组大小
	 * @return List 不会有null
	 */
	public static List<Byte[]> splitArray(byte[] arraySrc, int subSize) {
		return splitArray(ArrayUtils.toObject(arraySrc), subSize);
	}

	/**
	 * 将一个数组分割若干个指定的大小的的数组<br>
	 * doubleArray为null或size<1,返回空的list
	 * 
	 * @param arraySrc
	 *            源数组
	 * @param subSize
	 *            每个小的数组大小
	 * @return List 不会有null
	 */
	public static List<Byte[]> splitArray(Byte[] arraySrc, int subSize) {
		return new SplitArrayUtil<Byte>().splitArray(arraySrc, subSize);
	}

	/*****************************
	 * 为什么还会有下面的这些**********************************************
	 * 
	 * byte、int、float、double、 long是基本数据类型，没有Byte、Integer等占用内存那么大，为了减少运算赋值的操作<br>
	 * 所以又再写了下面的方法，因为这相对是固定的，所以代码重复就重复吧
	 * 
	 * 
	 * 
	 ****************************************************************************************/

	/**
	 * 将一个数组分割若干个指定的大小的的数组<br>
	 * arraySrc为null或size<1,返回空的list<br>
	 * 
	 * @param arraySrc
	 *            源数组
	 * @param subSize
	 *            每个小的数组大小
	 * @return List 不会有null
	 */
	public static List<byte[]> splitArray2General(byte[] arraySrc, int subSize) {
		List<byte[]> stringList = new ArrayList<>();
		if (arraySrc.length < subSize) {
			// 指定大小大于源数组大小，直接保存源数组
			stringList.add(arraySrc);
			return stringList;
		}
		if (arraySrc == null || subSize < 1)
			return stringList;
		int multi = arraySrc.length / subSize;
		int j = arraySrc.length % subSize == 0 ? multi : multi + 1;
		byte[] splitTemp = new byte[subSize];
		int length = 0;
		for (int ii = 0; ii < j; ii++) {
			length = arraySrc.length - ii * subSize;
			if (splitTemp.length < length)
				length = splitTemp.length;
			splitTemp = new byte[length];
			System.arraycopy(arraySrc, ii * subSize, splitTemp, 0, length);
			stringList.add(splitTemp);
		}
		return stringList;
	}

	/**
	 * 将一个数组分割若干个指定的大小的的数组<br>
	 * arraySrc为null或size<1,返回空的list<br>
	 * 
	 * @param arraySrc
	 *            源数组
	 * @param subSize
	 *            每个小的数组大小
	 * @return List 不会有null
	 */
	public static List<int[]> splitArray2General(int[] arraySrc, int subSize) {
		List<int[]> stringList = new ArrayList<>();
		if (arraySrc.length < subSize) {
			// 指定大小大于源数组大小，直接保存源数组
			stringList.add(arraySrc);
			return stringList;
		}
		if (arraySrc == null || subSize < 1)
			return stringList;
		int multi = arraySrc.length / subSize;
		int j = arraySrc.length % subSize == 0 ? multi : multi + 1;
		int[] splitTemp = new int[subSize];
		int length = 0;
		for (int ii = 0; ii < j; ii++) {
			length = arraySrc.length - ii * subSize;
			if (splitTemp.length < length)
				length = splitTemp.length;
			splitTemp = new int[length];
			System.arraycopy(arraySrc, ii * subSize, splitTemp, 0, length);
			stringList.add(splitTemp);
		}
		return stringList;
	}

	/**
	 * 将一个数组分割若干个指定的大小的的数组<br>
	 * arraySrc为null或size<1,返回空的list<br>
	 * 
	 * @param arraySrc
	 *            源数组
	 * @param subSize
	 *            每个小的数组大小
	 * @return List 不会有null
	 */
	public static List<float[]> splitArray2General(float[] arraySrc, int subSize) {
		List<float[]> stringList = new ArrayList<>();
		if (arraySrc.length < subSize) {
			// 指定大小大于源数组大小，直接保存源数组
			stringList.add(arraySrc);
			return stringList;
		}
		if (arraySrc == null || subSize < 1)
			return stringList;
		int multi = arraySrc.length / subSize;
		int j = arraySrc.length % subSize == 0 ? multi : multi + 1;
		float[] splitTemp = new float[subSize];
		int length = 0;
		for (int ii = 0; ii < j; ii++) {
			length = arraySrc.length - ii * subSize;
			if (splitTemp.length < length)
				length = splitTemp.length;
			splitTemp = new float[length];
			System.arraycopy(arraySrc, ii * subSize, splitTemp, 0, length);
			stringList.add(splitTemp);
		}
		return stringList;
	}

	/**
	 * 将一个数组分割若干个指定的大小的的数组<br>
	 * arraySrc为null或size<1,返回空的list<br>
	 * 
	 * @param arraySrc
	 *            源数组
	 * @param subSize
	 *            每个小的数组大小
	 * @return List 不会有null
	 */
	public static List<double[]> splitArray2General(double[] arraySrc, int subSize) {
		List<double[]> stringList = new ArrayList<>();
		if (arraySrc.length < subSize) {
			// 指定大小大于源数组大小，直接保存源数组
			stringList.add(arraySrc);
			return stringList;
		}
		if (arraySrc == null || subSize < 1)
			return stringList;
		int multi = arraySrc.length / subSize;
		int j = arraySrc.length % subSize == 0 ? multi : multi + 1;
		double[] splitTemp = new double[subSize];
		int length = 0;
		for (int ii = 0; ii < j; ii++) {
			length = arraySrc.length - ii * subSize;
			if (splitTemp.length < length)
				length = splitTemp.length;
			splitTemp = new double[length];
			System.arraycopy(arraySrc, ii * subSize, splitTemp, 0, length);
			stringList.add(splitTemp);
		}
		return stringList;
	}

	/**
	 * 将一个数组分割若干个指定的大小的的数组<br>
	 * arraySrc为null或size<1,返回空的list<br>
	 * 
	 * @param arraySrc
	 *            源数组
	 * @param subSize
	 *            每个小的数组大小
	 * @return List 不会有null
	 */
	public static List<long[]> splitArray2General(long[] arraySrc, int subSize) {
		List<long[]> stringList = new ArrayList<>();
		if (arraySrc.length < subSize) {
			// 指定大小大于源数组大小，直接保存源数组
			stringList.add(arraySrc);
			return stringList;
		}
		if (arraySrc == null || subSize < 1)
			return stringList;
		int multi = arraySrc.length / subSize;
		int j = arraySrc.length % subSize == 0 ? multi : multi + 1;
		long[] splitTemp = new long[subSize];
		int length = 0;
		for (int ii = 0; ii < j; ii++) {
			length = arraySrc.length - ii * subSize;
			if (splitTemp.length < length)
				length = splitTemp.length;
			splitTemp = new long[length];
			System.arraycopy(arraySrc, ii * subSize, splitTemp, 0, length);
			stringList.add(splitTemp);
		}
		return stringList;
	}

}
