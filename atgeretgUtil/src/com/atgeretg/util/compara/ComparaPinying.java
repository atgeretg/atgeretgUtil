package com.atgeretg.util.compara;

import java.util.Comparator;

import net.sourceforge.pinyin4j.PinyinHelper;

public class ComparaPinying {

	/**  
     * 功能：实现汉语拼音序比较  
     *  
     */    
    static  class ComparatorPinYin implements Comparator<String>{    
        @Override    
        public int compare(String o1, String o2) {    
            return ToPinYinString(o1).compareTo(ToPinYinString(o2));    
        }    
            
        private String ToPinYinString(String str){    
                
            StringBuilder sb=new StringBuilder();    
            String[] arr=null;    
                
            for(int i=0;i<str.length();i++){    
                arr=PinyinHelper.toHanyuPinyinStringArray(str.charAt(i));    
                if(arr!=null && arr.length>0){    
                    for (String string : arr) {    
                        sb.append(string);    
                    }    
                }    
            }    
                
            return sb.toString();    
        }    
            
    }    
	
}
