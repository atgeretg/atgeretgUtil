package com.atgeretg.util.MD5;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.atgeretg.util.file.FileUtil;

public class BackMD5 {
	public static void main(String[] args) {
		String md5_1 = getMD5("1016701");
		System.out.println(md5_1);
		String md5_2 = getMD5(md5_1);
		System.out.println(md5_2);
	}
	
	/**
	 * 用UFF8格式读取转码
	 * @param content 
	 * @return null | String
	 */
	public static String getMD5(String content){
		try {
			return getMD5(content.getBytes(FileUtil.UTF8));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 用指定格式读取转码
	 * @param content 
	 * @param encode 编码格式
	 * @return null | String
	 */
	public static String getMD5(String content,String encode){
		try {
			return getMD5(content.getBytes(FileUtil.UTF8));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getMD5(byte[] source){  
        String s=null;  
        //用来将字节转换成16进制表示的字符  
        char[] hexDigits={'0','1','2','3','4','5','6','7','8','9',  
                'a','b','c','d','e','f'};  
        try {  
            MessageDigest md=MessageDigest.getInstance("MD5");  
            md.update(source);  
            //MD5的计算结果是一个128位的长整数，用字节表示为16个字节  
            byte[] tmp=md.digest();  
            //每个字节用16进制表示的话，使用2个字符(高4位一个,低4位一个)，所以表示成16进制需要32个字符  
            char[] str=new char[16*2];  
            int k=0;//转换结果中对应的字符位置  
            for(int i=0;i<16;i++){//对MD5的每一个字节转换成16进制字符  
                byte byte0=tmp[i];  
                str[k++]=hexDigits[byte0>>>4 & 0xf];//对字节高4位进行16进制转换  
                str[k++]=hexDigits[byte0 & 0xf];    //对字节低4位进行16进制转换  
            }  
            s=new String(str);  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        }  
        return s;  
    }
	
//    public static void main(String[] args) {  
//    	String s = "{\"goodsList\":[{\"gdBai\":2,\"gdBlNum\":0,\"gdCode\":\"6920202888883\",\"gdCost\":5,\"gdDate\":\"2017-08-09 16:08:21\",\"gdHong\":0,\"gdHuang\":0,\"gdId\":1865,\"gdInCost\":3,\"gdInDate\":\"2017-08-09 16:08:21\",\"gdName\":\"红牛\",\"gdOutDate\":null,\"gdPayNum\":0,\"gdState\":1,\"shopNum\":2}],\"rfidUidList\":[\"6F8BF86B500104E0\",\"E14B976B500104E0\"],\"order\":{\"odBai\":2,\"odCode\":\"\",\"odDate\":\"2017-08-09 18:37:59\",\"odGoodIntro\":\"红牛\",\"odHong\":0,\"odHuang\":0,\"odId\":0,\"odNum\":2,\"odPayCode\":\"\",\"odPayCost\":10,\"odPayDate\":null,\"odPayType\":0,\"odState\":1,\"odUId\":0,\"odUMobile\":\"\"}}";
//    	BackMD5 md5Util=new BackMD5();  
//        String result=md5Util.getMD5(s.getBytes());  
//        System.out.println(result);  
//    }  
	
	

//	public static String getStringMD5(String string) {
//		MessageDigest md5 = null;
//
//		try {
//
//			md5 = MessageDigest.getInstance("MD5");
//
//		} catch (Exception e) {
//
//			System.out.println(e.toString());
//			e.printStackTrace();
//
//			return "";
//		}
//
//		char[] charArray = string.toCharArray();
//		byte[] byteArray = new byte[charArray.length];
//
//		for (int i = 0; i < charArray.length; i++)
//			byteArray[i] = (byte) charArray[i];
//
//		byte[] md5Bytes = md5.digest(byteArray);
//		StringBuffer hexValue = new StringBuffer();
//
//		for (int i = 0; i < md5Bytes.length; i++) {
//
//			int val = ((int) md5Bytes[i]) & 0xff;
//			if (val < 16)
//				hexValue.append("0");
//			hexValue.append(Integer.toHexString(val));
//		}
//
//		return hexValue.toString();
//	}
}
