package com.atgeretg.util.url;

import java.io.UnsupportedEncodingException;

public class UrlUtil {
	private final static String ENCODE = "UTF8"; 
    /**
     * URL 解码，默认编码格式UFT8
     * @param str 转码字符
     * @return String
     */
    public static String getURLDecoderString(String str) {
        return getURLDecoderString(str,ENCODE);
    }
    
    /**
     * URL 解码
     * @param str 转码字符
     * @param encode 编码格式
     * @return
     */
    public static String getURLDecoderString(String str, String encode) {
        String result = "";
        if (str == null) {//不能去" "（空字符）
            return "";
        }
        try {
            result = java.net.URLDecoder.decode(str, encode);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    /**
     * URL 转码,默认编码格式UFT8
     *@param str 转码字符
     * @return String
     */
    public static String getURLEncoderString(String str) {
        return getURLEncoderString(str, ENCODE);
    }
    
    /**
     * URL 转码
     * @param str 转码字符
     * @param encode 编码格式
     * @return String 没null
     */
    public static String getURLEncoderString(String str, String encode) {
        String result = "";
        if (str == null) {//不能去" "（空字符）
            return "";
        }
        try {
            result = java.net.URLEncoder.encode(str, encode);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

//    public static void main(String[] args) {
//        String str = "测试1";
//        String s = "%E6%B5%8B%E8%AF%951";
//        System.out.println(getURLEncoderString(" "));
//        System.out.println(getURLDecoderString("s"));
//        
//    }
}
