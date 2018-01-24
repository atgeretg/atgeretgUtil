package com.atgeretg.util.arrary;

import java.util.ArrayList;
import java.util.List;

public class SplitListUtil<T>{
	
	/**
	 * 将一个list集合分割若干个指定的大小的的list集合<br>
	 * list为null或size<1,返回空的list
	 * 
	 * @param <T>
	 * 
	 * @param list
	 *            源集合
	 * @param subSize
	 *            每个小的集合大小
	 * @return List<List<T>> 不会有null
	 */
	public List<List<T>> splitList(List<T> list, int subSize) {
		List<List<T>> listAll = new ArrayList<>();

		if (ListUtil.isNull(list))
			return listAll;
		int size = list.size();
		if (list.size() < subSize) {
			// 指定大小大于源数据大小，直接保存源数据
			listAll.add(list);
			return listAll;
		}

		int multi = size / subSize;
		int j = size % subSize == 0 ? multi : multi + 1;
		int length = 0;
		for (int ii = 0; ii < j; ii++) {
			length = (ii + 1) * subSize;
			if (size < length)
				length = size;
			listAll.add(list.subList(ii * subSize, length));
		}
		return listAll;
	}
}