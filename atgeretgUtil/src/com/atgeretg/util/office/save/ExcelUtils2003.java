package com.atgeretg.util.office.save;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;

import net.sf.excelutils.ExcelException;
import net.sf.excelutils.ExcelUtils;

/**
 * 这个用的是poi-2.5_muban.jar，用了个不能用更高的版本，所以不用这个
 * 
 * @author atgeretg
 *
 */
public class ExcelUtils2003 {
	static Logger log = Logger.getLogger(ExcelUtils2003.class);

	/**
	 * 导出excle文件
	 * 
	 * @param templetPath
	 *            模板文件路径
	 * @param paramName
	 *            调用参数的名字，即如：${list.name}，就是paramName就是“list”，
	 * @param values
	 *            值
	 * @param savePath
	 *            保存位置
	 * @return
	 */
	public static boolean export(String templetPath, String paramName, Object values, String savePath) {
		ExcelUtils.addValue(paramName, values);
		// String config = "F://demo.xls";
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(savePath);
			ExcelUtils.export(templetPath, fileOutputStream);
			return true;
		} catch (FileNotFoundException ex) {
			log.error("文件路径找不到");
		} catch (ExcelException ex) {
			// System.out.println(">>>>>>>>>>>> 错误：ExcelException");
			log.error(ex);
		} finally {
			if (fileOutputStream != null) {
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					log.error(e);
				}
			}
		}
		return false;
	}

	/**
	 * 导出excle文件
	 * 
	 * @param templetPath
	 *            模板文件路径
	 * @param values
	 *            map值
	 * @param savePath
	 *            保存位置
	 * @return
	 */
	public static boolean export(String templetPath, Map<String, Object> values, String savePath) {
		Iterator<String> iterator = values.keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			ExcelUtils.addValue(key, values.get(key));
		}
		// String config = "F://demo.xls";
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(savePath);
			ExcelUtils.export(templetPath, fileOutputStream);
			return true;
		} catch (FileNotFoundException ex) {
			log.error("文件路径找不到");
		} catch (ExcelException ex) {
			// System.out.println(">>>>>>>>>>>> 错误：ExcelException");
			log.error(ex);
		} finally {
			if (fileOutputStream != null) {
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					log.error(e);
				}
			}
		}
		return false;
	}
}
