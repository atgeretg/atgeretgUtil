package com.atgeretg.util.office.save;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.atgeretg.util.date.DateUtil;
import com.atgeretg.util.number.ByteUtil;

/**
 * @Comments   : 导入导出Excel工具类
 * @Version    : 1.0.0
 */

public class ExcelUtil  {
	Logger log= Logger.getLogger(ExcelUtil.class);
	String head[];
	Map<Integer, Map<Integer,Object>> values;
	
	public ExcelUtil(String head[],Map<Integer, Map<Integer,Object>> values) {
		this.head = head;
		this.values = values;
	}

	/**
	 * 返回保存路径，失败返回null
	 * @param savePath
	 * @return
	 */
	public String createEXCEL(String savePath) {
		try {
			// 输出流
			OutputStream os = new FileOutputStream(savePath);
			// 工作区
			XSSFWorkbook wb = new XSSFWorkbook();
			XSSFSheet sheet = wb.createSheet("sheet1");
			XSSFRow row;
			setHead(sheet);
			Iterator<Map.Entry<Integer, Map<Integer,Object>>> itRow = values.entrySet().iterator();
			String textValue = null;
			while (itRow.hasNext()) {
				Map.Entry<Integer, Map<Integer, Object>> entry = (Map.Entry<Integer, java.util.Map<Integer, Object>>) itRow
						.next();//第几行
				row = sheet.createRow(entry.getKey());
				//第几例
				Iterator<Map.Entry<Integer,Object>> it2 = entry.getValue().entrySet().iterator();
				while (it2.hasNext()) {
					Map.Entry<java.lang.Integer, java.lang.Object> entry2 = (Map.Entry<java.lang.Integer, java.lang.Object>) it2
							.next();
					if (entry2.getValue() instanceof Date) {
						textValue = DateUtil.formatDateStr_ss((Date)entry2.getValue());
						
					} else if (entry2.getValue() instanceof byte[]) {
						ByteUtil.byteArray2HexString(( byte[])entry2.getValue(), true);
					} else {
						if(entry2.getValue() == null)
							continue;
						// 其它数据类型都当作字符串简单处理
						textValue = entry2.getValue().toString();
					}
					row.createCell(entry2.getKey()).setCellValue(textValue);
				}
				
			}
			
			// 写文件
			wb.write(os);
			// 关闭输出流
			os.close();
			return savePath;
		} catch (Exception e) {
			log.error(e);
		}
		return null;
	}

	private void setHead(XSSFSheet sheet) {
		XSSFRow row = sheet.createRow(0);
		for (int i = 0; i < head.length; i++) {
			row.createCell(i).setCellValue(head[i]);
		}
	}
	
	
	private static void createEXCEL() {
		try {
			// 输出流
			OutputStream os = new FileOutputStream("D:/export2007_"
					+ System.currentTimeMillis() + ".xlsx");
			// 工作区
			XSSFWorkbook wb = new XSSFWorkbook();
			XSSFSheet sheet = wb.createSheet("test");
			for (int i = 0; i < 1000; i++) {
				// 创建第一个sheet
				// 生成第一行
				XSSFRow row = sheet.createRow(i);
				// 给这一行的第一列赋值
				row.createCell(0).setCellValue("column1");
				// 给这一行的第一列赋值
				row.createCell(1).setCellValue("column2");
				row.createCell(2).setCellValue("column3");
				System.out.println(i);
			}
			// 写文件
			wb.write(os);
			// 关闭输出流
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		createEXCEL();
	}
}
