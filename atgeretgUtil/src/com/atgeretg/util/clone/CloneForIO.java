package com.atgeretg.util.clone;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.SqlTimestampConverter;
import org.apache.poi.ss.formula.functions.T;

/**
 * 通IO流进行克隆一个对象
 * @author atgeretg
 *
 */
public class CloneForIO {
	/**
	 * 通IO流进行克隆一个对象，实体类一个要实现java.io.Serializable接口
	 * 
	 * @param obj
	 * @return null | 传入的对象
	 */
	public static Object copy(Object obj) {
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(obj);
			ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(
					bos.toByteArray()));
			return ois.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 对象属性复制，属性要有get和set
	 * @param clazzDest 对象类型
	 * @param orig 源数据
	 * @return
	 * @throws Exception 
	 */
	public static Object copyObjectProperties(Class clazzDest,Object orig) throws Exception{
		Object n = null;
        try {
        	ConvertUtils.register(new SqlTimestampConverter(null), java.sql.Timestamp.class); 
        	ConvertUtils.register(new SqlTimestampConverter(null), Date.class); 
            n =  clazzDest.newInstance();       
			BeanUtils.copyProperties(n, orig);
		} catch (Exception e) {
			throw e;
		}
        return n;
 }
}
