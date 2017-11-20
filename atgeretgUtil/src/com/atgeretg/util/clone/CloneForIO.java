package com.atgeretg.util.clone;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
}
