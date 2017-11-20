package com.atgeretg.util.passworld;

import javax.crypto.Cipher;  
import javax.crypto.spec.SecretKeySpec;  
  
import org.apache.commons.codec.binary.Base64;

import com.atgeretg.util.number.ByteUtil;  

public class AESUtil {//AESUtil 

	public static final String KEY = "1234567XDE234loB"; //只能为16位  
    
	/**
	 * 其它字符加密
	 * @param input
	 * @param key
	 * @return
	 */
    public static String encrypt(String input, String key) {  
        byte[] crypted = null;  
        try {  
            SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");  
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");  
            cipher.init(Cipher.ENCRYPT_MODE, skey);  
            crypted = cipher.doFinal(input.getBytes());  
        } catch (Exception e) {  
        }  
        return new String(Base64.encodeBase64(crypted));  
    }  
  
    /**
     * 其它字符 解密
     * @param input
     * @param key
     * @return
     */
    public static String decrypt(String input, String key) {  
        byte[] output = null;  
        try {  
            SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");  
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");  
            cipher.init(Cipher.DECRYPT_MODE, skey);  
            output = cipher.doFinal(Base64.decodeBase64(input.getBytes()));  
        } catch (Exception e) {  
            System.out.println(e.toString());  
        }  
        return new String(output);  
    }  
      
    /**
     * 十六进制 加密
     * @param input
     * @param key
     * @return
     */
    public static String encryptHex(String input, String key) {  
        byte[] crypted = null;  
        try {  
            SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");  
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");  
            cipher.init(Cipher.ENCRYPT_MODE, skey);  
            crypted = cipher.doFinal(input.getBytes());  
        } catch (Exception e) {  
        }  
        return ByteUtil.byteArray2HexString(crypted, false) ;  
    }  
  
    /**
     * 十六进制 解密
     * @param input
     * @param key
     * @return
     */
    public static String decryptHex(String input, String key) {  
        byte[] output = null;  
        try {  
            SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");  
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");  
            cipher.init(Cipher.DECRYPT_MODE, skey);  
            output = cipher.doFinal(ByteUtil.HexString2Bytes(input));  
        } catch (Exception e) {  
            System.out.println(e.toString());  
        }  
        return new String(output);  
    }  
  
    public static void main(String[] args) {  
//        String data = "lpmsadmin.credit.card.storePass:000000,lpmsadmin.credit.card.keyAlias:lpms,lpmsadmin.credit.card.keyPass:111111";  
        String data = "wrbld123456";  
//        System.out.println(SecUtil.encrypt(data, KEY));  
//        System.out.println(data.equals(SecUtil.decrypt(SecUtil.encrypt(data, KEY), KEY)));  
          
        System.out.println(AESUtil.encryptHex(data, KEY));  
        System.out.println(data.equals(AESUtil.decryptHex(AESUtil.encryptHex(data, KEY), KEY)));  
    }  
}
