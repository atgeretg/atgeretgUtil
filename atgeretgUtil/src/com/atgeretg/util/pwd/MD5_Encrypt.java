package com.atgeretg.util.pwd;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.atgeretg.util.file.FileUtil;

public class MD5_Encrypt {
	
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

}
