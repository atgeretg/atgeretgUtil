package com.atgeretg.util.arrary;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.atgeretg.util.number.NumUtils;
import org.apache.commons.lang3.ArrayUtils;

/**
 * 此类用了org.apache.commons.lang3.ArrayUtils
 * 
 * @author atgeretg_com
 *
 */
public class ArrayUtil {
	
	public static void main(String[] args) {
		byte[] b = new byte[9];
		for(int i = 0; i < b.length;i++) {
			b[i] = (byte)i;
		}
//		Byte[] bb = new Byte[9];
		byte[] bb = new byte[9];
		for(int i= 0; i < bb.length;i++) {
			System.out.println(bb[i]);
		}
		System.out.println("------------------------");
		System.arraycopy(b, 0, bb,0, 9);
		for(int i= 0; i < bb.length;i++) {
			System.out.println(bb[i]);
		}
//		String s[] = new String[999999]; 
//		for(int i = 0; i < s.length; i++) {
//			s[i]= "i = " + i%8;
//		}
//		long start = System.currentTimeMillis();
//		String[] array = new ArrayUtilHandle<String>().removeLikeArray(s);
//		System.out.println(System.currentTimeMillis() - start);
//		System.out.println(array.length);
//		System.out.println(Arrays.toString(array));
	}
	
//	public static

	/**
	 * String数组转int数组,不会报错，int数据与传入的String数组大小一样，当String数组字符不是数字时，此值会变成指定失败值
	 * @param failValue 指定失败值
	 * @param strArr
	 * @return
	 */
	public static int[] strArr2intArr(String[] strArr,int failValue) {
		int[] intArr = new int[strArr.length];
//        String str;
		for (int i = 0; i < strArr.length; i++) {
			intArr[i] = NumUtils.string2IntValue(strArr[i], failValue);
		}
		return intArr;
	}

	/**
	 * String数组转int数组，当String数组字符不是数字时会报错
	 *
	 * @param strArr
	 * @return
	 */
	public static int[] strArr2intArr(String[] strArr) {
		int[] intArr = new int[strArr.length];
		for (int i = 0; i < strArr.length; i++) {
			intArr[i] = Integer.valueOf(strArr[i]);
		}
		return intArr;
	}



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
	 * 数组去重<br>
	 * stringArray为null或size<1,返回自身传入的对象
	 * 
	 * @param stringArray
	 *            源数组
	 */
	public static String[] removeLikeElement(String[] array) {
		return new ArrayUtilHandle<String>().removeLikeArray(array);
	}

	
	/**
	 * 数组去重<br>
	 * array为null或size<1,返回自身传入的对象<br/>
	 * 直接调用new ArrayUtilHandle<Integer>().removeLikeArray()
	 * 
	 * @param 
	 *            源数组
	 */
	public static Integer[] removeLikeElement(Integer[] array) {
		return new ArrayUtilHandle<Integer>().removeLikeArray(array);
	}
	
	
	/**
	 * 数组去重<br>
	 * Array为null或size<1,返回自身传入的对象
	 * 
	 * @param array
	 *            源数组
	 */
	public static int[] removeLikeElement(int[] array) {
		return integerArray2Int(new ArrayUtilHandle<Integer>().removeLikeArray(intArray2integer(array)));
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
		return new ArrayUtilHandle<String>().splitArray(array, subSize);
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
		return new ArrayUtilHandle<Integer>().splitArray(arraySrc, subSize);
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
		return new ArrayUtilHandle<Float>().splitArray(arraySrc, subSize);
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
		return new ArrayUtilHandle<Double>().splitArray(arraySrc, subSize);
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
		return new ArrayUtilHandle<Long>().splitArray(arraySrc, subSize);
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
		return new ArrayUtilHandle<Number>().splitArray(arraySrc, subSize);
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
		return new ArrayUtilHandle<Byte>().splitArray(arraySrc, subSize);
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
