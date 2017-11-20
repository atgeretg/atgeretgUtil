package com.atgeretg.util.office.read;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 读取Excel
 * 
 */
public class ReadExcelUtilsTest {

	public static void main(String[] args) {
//		GoodsService goodsService = new GoodsServiceImpl();
		try {
			ReadExcelUtils excelReader;
			String filepath2 = "F:\\gz\\模板1.xls";
			 excelReader = new ReadExcelUtils(filepath2);
			
			Map<Integer, Map<Integer,Object>> map = excelReader.readExcelContent();
			System.out.println("获得Excel表格的内容:");
			Iterator<Map.Entry<Integer, Map<Integer, Object>>> iterator = map.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<java.lang.Integer,java.util.Map<java.lang.Integer,java.lang.Object>> entry = (Map.Entry<java.lang.Integer,java.util.Map<java.lang.Integer,java.lang.Object>>) iterator
						.next();
				Map<Integer, Object> goodValue = entry.getValue();
				Iterator<Map.Entry<Integer, Object>> iterator2 = goodValue.entrySet().iterator();
				while (iterator2.hasNext()) {
					Map.Entry<java.lang.Integer,java.lang.Object> entry2 = (Map.Entry<java.lang.Integer,java.lang.Object>) iterator2
							.next();
					System.out.println(" key = " + entry2.getKey() + " values = " + entry2.getValue());
					switch(entry2.getKey().intValue()) {
					case 0:
//						uid.setUid((String)entry2.getValue());
//						uid.setNum(num);
//						System.out.println(i);
						break;
					case 1:
//						System.out.println(" key = " + entry2.getKey() + " values = " + entry2.getValue());
						break;
					case 2://name
//						System.out.println(" key = " + entry2.getKey() + " values = " + entry2.getValue());
						String name = (String)entry2.getValue();
//						if(name.contains("#"))
//						name = name.substring(1);
//					if(name.contains(split))
//						name = name.split(split)[0];
						break;
					case 3://零售价
//						System.out.println(" key = " + entry2.getKey() + " values = " + entry2.getValue());
//						System.out.println(name);
						break;
//					case 6://规格
//						System.out.println(" key = " + entry2.getKey() + " values = " + entry2.getValue());
//						
//						goods.setGdCost(new BigDecimal((String)entry2.getValue()).doubleValue());
//						break;
					case 7://规格
//						System.out.println(" key = " + entry2.getKey() + " values = " + entry2.getValue());
						break;
					case 4://进货价
//						Double cost = (Double)entry2.getValue();
//						goods.setGdInCost(new BigDecimal((String)entry2.getValue()).doubleValue());
						
//						goods.setGdInCost((Double)entry2.getValue());
						break;
//					case 7:
//						goods.setGdInCost((Double)entry2.getValue());
//						break;
//					case 7:
//						goods.setGdInCost((Double)entry2.getValue());
//						break;
					}
					
				}
//				System.out.println(goods.getGdInCost());
				
			}
//			}
			String path = "E:\\atgeretg\\jsonStringGuang.txt";
//			String jsonString = JsonUtil.getJSONString(JsonUtil.DATE_TYPE_SQL_TIMESTAMP, list);
//			FileUtil.saveFile4StrCreate(jsonString, path, FileUtil.UTF8, true);
//			String string = FileUtil.readFile2str(
//					new File(path), FileUtil.UTF8);
//			System.out.println(string);
		} catch (FileNotFoundException e) {
			System.out.println("未找到指定路径的文件!");
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
