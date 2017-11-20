package com.atgeretg.util.number;

import com.atgeretg.util.log.Log;
import com.atgeretg.util.string.StrUtil;

public class ByteUtil {
	public static void main(String[] args) {
		Log.systemLog((HexString2Bytes("52E22984500104E0")));
	}
	/**
	 * 字节数组转换成String
	 *
	 * @param arrBytes
	 * @param blank 要不要空格（每个byte字节，最是否用一个“ ”隔开）
	 * @return "" | arrBytes换成的字符串（不存在null）
	 */
	public static String byteArray2HexString(byte[] arrBytes, boolean blank) {
		return byteArray2HexString(arrBytes,Integer.MAX_VALUE,blank);
	}
	
	/**
	 * 字节数组转换成String，指定长度转换长度
	 *
	 * @param arrBytes
	 * @param count 转换长度
	 * @param blank 要不要空格（每个byte字节，最是否用一个“ ”隔开）
	 * @return "" | arrBytes换成的字符串（不存在null）
	 */
	public static String byteArray2HexString(byte[] arrBytes, int count, boolean blank) {
		String ret = "";
		if (arrBytes == null || arrBytes.length < 1)
			return ret;
		if (count > arrBytes.length)
			count = arrBytes.length;
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < count; i++) {
			ret = Integer.toHexString(arrBytes[i] & 0xFF).toUpperCase();
			if (ret.length() == 1)
				builder.append("0").append(ret);
			else
				builder.append(ret);
			if(blank)
				builder.append(" ");
		}
		
		return builder.toString();
		
	}

	/**
	 * 字节数组(short)转换成String
	 *
	 * @param shorts
	 * @param blank 要不要空格（每个byte字节，最是否用一个“ ”隔开）
	 * @return "" | shorts换成的字符串（不存在null）
	 */
	public static String byteArray2HexString(short[] shorts,boolean blank) {
		String ret = "";
		if (shorts == null||shorts.length < 1)
			return ret;
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < shorts.length; i++) {
			ret = Integer.toHexString(shorts[i] & 0xFF).toUpperCase();
			if (ret.length() == 1)
				builder.append("0").append(ret);
			else
				builder.append(ret);
			if(blank)
				builder.append(" ");
		}
		return builder.toString();
	}

	

	/**
	 * 字节转String
	 *
	 * @param b
	 * @return
	 */
	public static String byte2String(byte b) {
		return Integer.toHexString(b & 0xFF);
	}

	/**
	 * 字节转int
	 *
	 * @param b
	 * @return
	 */
	public static int byte2Int(byte b) {
		return (int) (b & 0xFF);
	}

	/**
	 * 将指定字符串src，以每两个字符分割转换为16进制形式 如："2B44EFD9" --> byte[]{0x2B, 0x44, 0xEF, 0xD9}
	 * 
	 * @param src
	 *            String
	 * @return null | byte[]
	 */
	public static byte[] HexString2Bytes(String src) {
//		String strTemp = "";
		if(StrUtil.isEmpty(src))
			return null;
		StringBuilder builder = new StringBuilder();
		for (char c : src.trim().toCharArray()) {
			/*去除中间的空格*/
			if (c != ' ') {
				builder.append(c);
			}
		}
		src = builder.toString();
		byte[] ret = new byte[src.length() / 2];
		byte[] tmp = src.getBytes();
		for (int i = 0; i < src.length() / 2; i++) {
			ret[i] = uniteBytes(tmp[i * 2], tmp[i * 2 + 1]);
		}
		return ret;
	}

	/**
	 * 将两个ASCII字符合成一个字节； 如："EF"--> 0xEF
	 * 
	 * @param src0
	 *            byte
	 * @param src1
	 *            byte
	 * @return byte
	 */
	public static byte uniteBytes(byte src0, byte src1) {
		byte _b0 = Byte.decode("0x" + new String(new byte[] { src0 })).byteValue();
		_b0 = (byte) (_b0 << 4);
		byte _b1 = Byte.decode("0x" + new String(new byte[] { src1 })).byteValue();
		byte ret = (byte) (_b0 ^ _b1);
		return ret;
	}

	/**
	 * 判断字符是否是正确的16进制
	 * 
	 * @param num
	 * @return
	 */
	public static boolean judge16(String num) {
		String model = "0123456789ABCDEF";
		char[] charArray = num.toUpperCase().toCharArray();
		for (char c : charArray) {
			if (!model.contains(c + ""))
				return false;
		}
		return true;
	}

	/**
	 * 判断一个字符数组，每个字符是否是正确的16进制
	 * 
	 * @param numArr
	 * @return
	 */
	public static boolean judge16(String[] numArr) {
		for (String s : numArr) {
			if (!judge16(s))
				return false;
		}
		return true;
	}
	
	/**
	 * 字节数组转换成int数组,一个个的区分
	 *
	 * @param arrBytes
	 * @return
	 */
	public static int[] byteArray2int(byte[] arrBytes) {
		int[] array = new int[arrBytes.length];
		for (int i = 0; i < arrBytes.length; i++) {
			array[i] =arrBytes[i];
		}
		return array;
	}

	/**
	 * 字节数组转换成int数组,一个个的区分,指定转换长度
	 *
	 * @param arrBytes
	 * @return
	 */
	public static int[] byteArray2int(byte[] arrBytes,int count) {
		if(arrBytes.length < count){
			count = arrBytes.length;
		}
		int[] array = new int[count];
		for (int i = 0; i < count; i++) {
			array[i] =arrBytes[i];
		}
		return array;
	}

	/**
	 * int转char
	 * @param ASCII
	 * @return
	 */
	public static char ascii2Char(int ASCII) {
		return (char) ASCII;
	}

	/**
	 * int的ascii数组转string
	 * @param ASCIIs
	 * @return
	 */
	public static String ascii2String(int[] ASCIIs) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < ASCIIs.length; i++) {
			sb.append(ascii2Char(ASCIIs[i]));
			if(ASCIIs.length>5 && (i+1)%6 == 0){
					sb.append("\r\n");
			}
		}
		return sb.toString();
	}
	
	/** 
	 * 合并数组 
	 *  
	 * @param firstArray 
	 *            第一个数组 
	 * @param secondArray 
	 *            第二个数组 
	 * @return 合并后的数组 
	 */  
	public static byte[] concat(byte[] firstArray, byte[] secondArray) {  
	    if (firstArray == null || secondArray == null) {  
	    	if(firstArray != null)
	    		return firstArray;
	    	if(secondArray !=null)
	    		return secondArray;
	        return null;  
	    }  
	    byte[] bytes = new byte[firstArray.length + secondArray.length];  
	    System.arraycopy(firstArray, 0, bytes, 0, firstArray.length);  
	    System.arraycopy(secondArray, 0, bytes, firstArray.length,  
	            secondArray.length);  
	    return bytes;  
	}
	
	
	/**
	 * char转换为byte[2]数组
 	 * @param c
	 * @return
	 */
	public static byte[] getByteArray(char c) {
		byte[] b = new byte[2];
		b[0] = (byte) ((c & 0xff00) >> 8);
		b[1] = (byte) (c & 0x00ff);
		return b;
	}

	/**
	 * 从byte数组的index处的连续两个字节获得一个char
	 * @param arr
	 * @param index
	 * @return
	 */
	public static char getChar(byte[] arr, int index) {
		return (char) (0xff00 & arr[index] << 8 | (0xff & arr[index + 1]));
	}

	/**
	 * short转换为byte[2]数组
 	 * @param s
	 * @return
	 */
	public static byte[] getByteArray(short s) {
		byte[] b = new byte[2];
		b[0] = (byte) ((s & 0xff00) >> 8);
		b[1] = (byte) (s & 0x00ff);
		return b;
	}

	/**
	 * 从byte数组的index处的连续两个字节获得一个short
	 * @param arr
	 * @param index
	 * @return
	 */
	public static short getShort(byte[] arr, int index) {
		return (short) (0xff00 & arr[index] << 8 | (0xff & arr[index + 1]));
	}

	/**
	 * int转换为byte[4]数组
	 * @param i
	 * @return
	 */
	public static byte[] getByteArray(int i) {
		byte[] b = new byte[4];
		b[0] = (byte) ((i & 0xff000000) >> 24);
		b[1] = (byte) ((i & 0x00ff0000) >> 16);
		b[2] = (byte) ((i & 0x0000ff00) >> 8);
		b[3] = (byte)  (i & 0x000000ff);
		return b;
	}

	/**
	 * 从byte数组的index处的连续4个字节获得一个int
 	 * @param arr
	 * @param index
	 * @return
	 */
	public static int getInt(byte[] arr, int index) {
		return  (0xff000000     & (arr[index+0] << 24))  |
				(0x00ff0000     & (arr[index+1] << 16))  |
				(0x0000ff00     & (arr[index+2] << 8))   |
				(0x000000ff     &  arr[index+3]);
	}

	/**
	 * float转换为byte[4]数组
	 * @param f
	 * @return
	 */
	public static byte[] getByteArray(float f) {
		int intbits = Float.floatToIntBits(f);//将float里面的二进制串解释为int整数
		return getByteArray(intbits);
	}

	/**
	 * 从byte数组的index处的连续4个字节获得一个float
	 * @param arr
	 * @param index
	 * @return
	 */
	public static float getFloat(byte[] arr, int index) {
		return Float.intBitsToFloat(getInt(arr, index));
	}

	/**
	 * long转换为byte[8]数组
	 * @param l
	 * @return
	 */
	public static byte[] getByteArray(long l) {
		byte b[] = new byte[8];
		b[0] = (byte)  (0xff & (l >> 56));
		b[1] = (byte)  (0xff & (l >> 48));
		b[2] = (byte)  (0xff & (l >> 40));
		b[3] = (byte)  (0xff & (l >> 32));
		b[4] = (byte)  (0xff & (l >> 24));
		b[5] = (byte)  (0xff & (l >> 16));
		b[6] = (byte)  (0xff & (l >> 8));
		b[7] = (byte)  (0xff & l);
		return b;
	}

	/**
	 * 从byte数组的index处的连续8个字节获得一个long
	 * @param arr
	 * @param index
	 * @return
	 */
	public static long getLong(byte[] arr, int index) {
		return  (0xff00000000000000L    & ((long)arr[index+0] << 56))  |
				(0x00ff000000000000L    & ((long)arr[index+1] << 48))  |
				(0x0000ff0000000000L    & ((long)arr[index+2] << 40))  |
				(0x000000ff00000000L    & ((long)arr[index+3] << 32))  |
				(0x00000000ff000000L    & ((long)arr[index+4] << 24))  |
				(0x0000000000ff0000L    & ((long)arr[index+5] << 16))  |
				(0x000000000000ff00L    & ((long)arr[index+6] << 8))   |
				(0x00000000000000ffL    &  (long)arr[index+7]);
	}

	/**
	 * double转换为byte[8]数组
	 * @param d
	 * @return
	 */
	public static byte[] getByteArray(double d) {
		long longbits = Double.doubleToLongBits(d);
		return getByteArray(longbits);
	}

	/**
	 * 从byte数组的index处的连续8个字节获得一个double
	 * @param arr
	 * @param index
	 * @return
	 */
	public static double getDouble(byte[] arr, int index) {
		return Double.longBitsToDouble(getLong(arr, index));
	}
	
	
}
