package com.atgeretg.util.arrary;

import java.util.ArrayList;
import java.util.List;

public class ListUtil {

	/**
     * 获取两个List的不同元素(list2不包含list1的元素)
     * @param list1
     * @param list2
     * @return
     */
    public static List<String> getDiffrent(List<String> list1, List<String> list2) {
//        long st = System.nanoTime();
        List<String> diff = new ArrayList<String>();
        for(String str:list1)
        {
            if(!list2.contains(str))
            {
                diff.add(str);
            }
        }
//        System.out.println("getDiffrent total times "+(System.nanoTime()-st));
        return diff;
    }
    
    
    /**
     * list2包含list1的元素
     * @param list1
     * @param list2
     * @return
     */
    public static List<String> getLike(List<String> list1, List<String> list2) {
//        long st = System.nanoTime();
        List<String> diff = new ArrayList<String>();
        for(String str:list1)
        {
            if(list2.contains(str))
            {
                diff.add(str);
            }
        }
//        System.out.println("getDiffrent total times "+(System.nanoTime()-st));
        return diff;
    }
}
