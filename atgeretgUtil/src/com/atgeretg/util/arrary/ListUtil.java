package com.atgeretg.util.arrary;

import java.util.ArrayList;
import java.util.List;

public class ListUtil {
	
	
	/**
	 * 判断list是不是null和empty,如果是null和empty返回true
	 * @param list
	 * @return
	 */
	public static boolean isNull(List<?> list) {
		if(list!=null&&!list.isEmpty())
			return false;
		return true;
	}
	
	/**
	 * list<String>集合转成数组<br>
	 * list集合转成数组可用list.toArray(..)
	 * @param list
	 * @return
	 */
	public static String[] list2Aarray(List<String> list) {
		if(list == null)
			return null;
		String[] strings = new String[list.size()];
		return list.toArray(strings);
	}
	
	/**
	 * string数组转成list<String>集合<br>
	 * 数组转成list集合可用java.util.Arrays.asList(..)方法
	 * @param strArray
	 * @return null | List<String>
	 */
	public static List<String> array2List(String[] strArray){
		if(strArray == null)
			return null;
		return  java.util.Arrays.asList(strArray);
	}
	

	/**
	 * 获取两个List的不同元素(list2不包含list1的元素)
	 * 
	 * @param list1
	 * @param list2
	 * @return
	 */
	public static List<String> getDiffrent(List<String> list1, List<String> list2) {
		// long st = System.nanoTime();
		List<String> diff = new ArrayList<String>();
		for (String str : list1) {
			if (!list2.contains(str)) {
				diff.add(str);
			}
		}
		// System.out.println("getDiffrent total times "+(System.nanoTime()-st));
		return diff;
	}

	/**
	 * list2包含list1的元素
	 * 
	 * @param list1
	 * @param list2
	 * @return
	 */
	public static List<String> getLike(List<String> list1, List<String> list2) {
		// long st = System.nanoTime();
		List<String> diff = new ArrayList<String>();
		for (String str : list1) {
			if (list2.contains(str)) {
				diff.add(str);
			}
		}
		// System.out.println("getDiffrent total times "+(System.nanoTime()-st));
		return diff;
	}
}
