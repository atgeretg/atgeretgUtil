package com.atgeretg.util.arrary;

import java.util.ArrayList;
import java.util.List;

public class SplitArrayUtil<T>{
	
	/**
	 * 将一个数组分割若干个指定的大小的的数组<br>
	 * stringArray为null或size<1,返回空的list
	 * 
	 * @param <T>
	 * 
	 * @param stringArray
	 *            源数组
	 * @param subSize
	 *            每个小的数组大小
	 * @return List 不会有null
	 */
	@SuppressWarnings("unchecked")
	public List<T[]> splitArray(T[] array, int subSize) {

		// Object[] objectArray = (Object[]) array;
		List<T[]> stringList = new ArrayList<>();
		if (array == null || subSize < 1)
			return stringList;
		int arrayLength = array.length;
		if (arrayLength <= subSize) {
			// 指定大小大于源数组大小，直接保存源数组
			stringList.add(array);
			return stringList;
		}
		
		int multi = arrayLength / subSize;
		int j = arrayLength % subSize == 0 ? multi : multi + 1;
		T[] splitTemp = (T[])java.lang.reflect.Array.
				newInstance(array.getClass().getComponentType(), subSize);
		int length = 0;
		for (int ii = 0; ii < j; ii++) {
			length = arrayLength - ii * subSize;
			if (splitTemp.length < length)
				length = splitTemp.length;
			splitTemp =  (T[])java.lang.reflect.Array.
					newInstance(array.getClass().getComponentType(), length);
			System.arraycopy(array, ii * subSize, splitTemp, 0, length);
			stringList.add(splitTemp);
		}
		return stringList;
	}
//
//	@SuppressWarnings("unchecked")
//	private  <T> T[] getGeArray(T[] array, int size) {
//		if (array instanceof Integer[]) {
//			return (T[]) new Integer[size];
//		} else if (array instanceof Byte[]) {
//			return (T[]) new Byte[size];
//		} else if (array instanceof String[]) {
//			return (T[]) new String[size];
//		} else if (array instanceof Float[]) {
//			return (T[]) new Float[size];
//		} else if (array instanceof Double[]) {
//			return (T[]) new Double[size];
//		} else if (array instanceof Long[]) {
//			return (T[]) new Long[size];
//		} else if (array instanceof Number[]) {
//			return (T[]) new Number[size];
//		} else if (array instanceof Date[]) {
//			return (T[]) new Date[size];
//		}
//		return (T[]) new Object[size];
//	}
}