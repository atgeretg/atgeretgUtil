package com.atgeretg.util.json.jsonLib;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.JSONUtils;

public class JsonUtil {
	/**
	 * 日期类型：java.sql.Timestamp
	 */
	public static final int DATE_TYPE_SQL_TIMESTAMP = 1;
	/**
	 * 日期类型：jjava.util.Date
	 */
	public static final int DATE_TYPE_UITL_DATE = 2;

//	static Map<String, Object> MAP_DATA = new ConcurrentHashMap<>();

//	public static void main(String[] args) throws Exception {
//		// String map2jsonStr = getJSONString(MAP_DATA);
////		String map2jsonStr = getJSONString(java.sql.Timestamp.class,
////				MAP_DATA.get("goodsList"));
////		System.out.println(map2jsonStr);
//		// String string = FileUtil.readFile2str(new
//		// File("E:\\atgeretg\\dd.txt"));
//		// Map testJson = testJson(string);
//		// Log.systemLog(testJson);
//		String json = "{\"odBai\":7.68,\"odBsCode\":\"101670\",\"odCode\":\"OD101670201710050801409455\",\"odDate\":\"2017-10-05 08:03:03\",\"odGoodIntro\":\"蒙牛 优益C酸乳菌 原味 340ML * 21 | 可口可乐塑料2L * 1 | 雪碧2L * 2\",\"odHong\":0,\"odHuang\":0,\"odId\":74207,\"odNum\":24,\"odPayCode\":\"201710050803132192982\",\"odPayCost\":145.5,\"odPayDate\":\"2017-10-05 08:03:13\",\"odPayType\":3,\"odState\":1,\"odUId\":3632,\"odUMobile\":\"13632641610\"}";
//		Map map = getMapFromJson(json,DATE_TYPE_SQL_TIMESTAMP);
//		System.out.println(map.get("odCode"));
//		DjGoods goods = null; 
//		List<DjGoods> list = new ArrayList<>();
////		for(int i = 0; i < 100; i ++) {
//			goods = new DjGoods();
//		goods.setGdCode("ii code");
//		goods.setGdName("name i ");
//		goods.setGdCost(8d);
//		goods.setGdInCost(8d);
//		goods.setGdSupplierId(1);
//		goods.setGdStandard("ssss");
//		goods.setGdTypeId(1);
//		goods.setGdTypeSubId(2);
//		
////		}
//		goods.setGdBai(0d);
//		goods.setGdHong(0d);
//		goods.setGdHuang(0d);
//		goods.setGdInDate(com.atgeretg.util.date.DateUtil.getCurrentTimestamp());
//		goods.setGdDate(com.atgeretg.util.date.DateUtil.getCurrentTimestamp());
//		goods.setGdUpdateCode(1);
//		goods.setGdBlNum(0);
//		goods.setGdBsCode("8888");
//		goods.setGdPayNum(0);
//		goods.setGdState(1);
//		list.add(goods);
//		String string = getJSONString(JsonUtil.DATE_TYPE_SQL_TIMESTAMP, list);
////		String string = FileUtil.readFile2str(
////				new File("E:\\atgeretg\\dd1.txt"), FileUtil.GBK);
//		System.out.println(string);
//		List<DjGoods> list2 = getDTOList(string, DjGoods.class,1);
//		for (DjGoods djGoods : list2) {
//			System.out.println(djGoods.getGdName());
//		}
////		Object o = java.sql.Timestamp;
////		if(Timestamp instanceof Date)
////		System.out.println();
//	}
	

	public static Map testJson(String str) {
		JSONObject json = JSONObject.fromObject(str);
		Iterator<?> it = json.keySet().iterator();
		Map map = new HashMap();
		while (it.hasNext()) {
			String key = (String) it.next();
			String value = json.getString(key);
			if (isString(value)) {
				map.put(key, value);
			}
			if (isJson(value)) {
				map.put(key, testJson(value));
			}
			if (isJsonArray(value)) {
				map.put(key, testJsonArray(value));
			}

		}
		return map;
	}

	public static List testJsonArray(String str) {
		JSONArray jsonArr = JSONArray.fromObject(str);
		List list = new ArrayList();
		for (Object json : jsonArr) {
			String jsonStr = json.toString();
			if (isString(jsonStr)) {
				list.add(jsonStr);
			}
			if (isJson(jsonStr)) {
				list.add(testJson(jsonStr.toString()));
			}
			if (isJsonArray(jsonStr)) {
				list.add(testJsonArray(jsonStr.toString()));
			}
		}
		return list;
	}

	@Deprecated
	public static String map2jsonStr(Map map) {
		JSONArray jsonArr = JSONArray.fromObject(map);
		return jsonArr.toString();
	}

	public static boolean isJson(String s) {
		boolean flag = true;
		try {
			JSONObject.fromObject(s);
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	public static boolean isJsonArray(String s) {
		boolean flag = true;
		try {
			JSONArray.fromObject(s);
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	public static boolean isString(String s) {
		return isJson(s) && isJsonArray(s);
	}

	
	
	/**
	 * 从一个JSON 对象字符格式中得到一个java对象，形如： {"id" : idValue, "name" : nameValue,
	 * "aBean" : {"aBeanId" : aBeanIdValue, ...}}
	 *   date类型：java.util.Date
	 * @param object
	 * @param clazz Bean对像
	 * @return
	 */
	public static Object getDTO(String jsonString, Class clazz) {
		JSONObject jsonObject = null;
		try {
			setDataFormat2JAVA(2);
			jsonObject = JSONObject.fromObject(jsonString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONObject.toBean(jsonObject, clazz);
	}
	
	/**
	 * 从一个JSON 对象字符格式中得到一个java对象，形如： {"id" : idValue, "name" : nameValue,
	 * "aBean" : {"aBeanId" : aBeanIdValue, ...}}
	 * 
	 * dateType
	 *            clazz中存在日期类型时，是哪种类型的日期 type = 1
	 *            (JsonUtil.DATE_TYPE_SQL_TIMESTAMP)时设置日期类型：java.sql.Timestamp<br>
	 *            type = 2 (JsonUtil.DATE_TYPE_UITL_DATE)时设置日期类型：java.util.Date<br>
	 * 
	 * @param object
	 * @param clazz Bean对像
	 * @return
	 */
	public static Object getDTO(String jsonString, Class clazz,int dateType) {
		JSONObject jsonObject = null;
		try {
			setDataFormat2JAVA(dateType);
			jsonObject = JSONObject.fromObject(jsonString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONObject.toBean(jsonObject, clazz);
	}

	

	/**
	 * 从一个JSON 对象字符格式中得到一个java对象，其中beansList是一类的集合，形如： {"id" : idValue, "name" :
	 * nameValue, "aBean" : {"aBeanId" : aBeanIdValue, ...}, beansList:[{}, {},
	 * ...]}<br>
	 * dateType的值取JsonUtil.DATE_TYPE_SQL_TIMESTAMP ｜
	 * JsonUtil.DATE_TYPE_UITL_DATE
	 * 
	 * @param jsonString
	 * @param clazz
	 * @param map
	 *            集合属性的类型 (key : 集合属性名, value : 集合属性类型class) eg: ("beansList" :
	 *            Bean.class)
	 * @param dateType
	 *            clazz中存在日期类型时，是哪种类型的日期 type = 1
	 *            (JsonUtil.DATE_TYPE_SQL_TIMESTAMP)时设置日期类型：java.sql.Timestamp<br>
	 *            type = 2 (JsonUtil.DATE_TYPE_UITL_DATE)时设置日期类型：java.util.Date<br>
	 * @return
	 */
	public static Object getDTO(String jsonString, Class clazz, Map map,
			int dateType) {
		JSONObject jsonObject = null;
		try {
			setDataFormat2JAVA(dateType);
			jsonObject = JSONObject.fromObject(jsonString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONObject.toBean(jsonObject, clazz, map);
	}

	/**
	 * 从一个JSON数组得到一个java对象数组，形如： [{"id" : idValue, "name" : nameValue}, {"id" :
	 * idValue, "name" : nameValue}, ...]
	 * 
	 * @param object
	 * @param clazz
	 * @param dateType
	 *            clazz中存在日期类型时，是哪种类型的日期 type = 1
	 *            (JsonUtil.DATE_TYPE_SQL_TIMESTAMP)时设置日期类型：java.sql.Timestamp<br>
	 *            type = 2 (JsonUtil.DATE_TYPE_UITL_DATE)时设置日期类型：java.util.Date<br>
	 * @return
	 */
	public static Object[] getDTOArray(String jsonString, Class clazz,
			int dateType) {
		setDataFormat2JAVA(dateType);
		JSONArray array = JSONArray.fromObject(jsonString);
		Object[] obj = new Object[array.size()];
		for (int i = 0; i < array.size(); i++) {
			JSONObject jsonObject = array.getJSONObject(i);
			obj[i] = JSONObject.toBean(jsonObject, clazz);
		}
		return obj;
	}

	/**
	 * 从一个JSON数组得到一个java对象数组，形如： [{"id" : idValue, "name" : nameValue}, {"id" :
	 * idValue, "name" : nameValue}, ...]
	 * 
	 * @param object
	 * @param clazz
	 * @param map
	 * @param dateType
	 *            clazz中存在日期类型时，是哪种类型的日期 type = 1
	 *            (JsonUtil.DATE_TYPE_SQL_TIMESTAMP)时设置日期类型：java.sql.Timestamp<br>
	 *            type = 2 (JsonUtil.DATE_TYPE_UITL_DATE)时设置日期类型：java.util.Date<br>
	 * @return
	 */
	public static Object[] getDTOArray(String jsonString, Class clazz, Map map,int dateType) {
		setDataFormat2JAVA( dateType);
		JSONArray array = JSONArray.fromObject(jsonString);
		Object[] obj = new Object[array.size()];
		for (int i = 0; i < array.size(); i++) {
			JSONObject jsonObject = array.getJSONObject(i);
			obj[i] = JSONObject.toBean(jsonObject, clazz, map);
		}
		return obj;
	}

	/**
	 * 从一个JSON数组得到一个java对象集合
	 * 
	 * @param object
	 * @param clazz
	 * @param dateType
	 *            clazz中存在日期类型时，是哪种类型的日期 type = 1
	 *            (JsonUtil.DATE_TYPE_SQL_TIMESTAMP)时设置日期类型：java.sql.Timestamp<br>
	 *            type = 2 (JsonUtil.DATE_TYPE_UITL_DATE)时设置日期类型：java.util.Date<br>
	 * @return
	 */
	public static List getDTOList(String jsonString, Class clazz,int dateType) {
		setDataFormat2JAVA( dateType);
		JSONArray array = JSONArray.fromObject(jsonString);
		List list = new ArrayList();
		for (Iterator iter = array.iterator(); iter.hasNext();) {
			JSONObject jsonObject = (JSONObject) iter.next();
			list.add(JSONObject.toBean(jsonObject, clazz));
		}
		return list;
	}

	/**
	 * 从一个JSON数组得到一个java对象集合，其中对象中包含有集合属性
	 * 
	 * @param object
	 * @param clazz
	 * @param map
	 *            集合属性的类型
	 * @param dateType
	 *            clazz中存在日期类型时，是哪种类型的日期 type = 1
	 *            (JsonUtil.DATE_TYPE_SQL_TIMESTAMP)时设置日期类型：java.sql.Timestamp<br>
	 *            type = 2 (JsonUtil.DATE_TYPE_UITL_DATE)时设置日期类型：java.util.Date<br>
	 * @return
	 */
	public static List getDTOList(String jsonString, Class clazz, Map map,int dateType) {
		setDataFormat2JAVA( dateType);
		JSONArray array = JSONArray.fromObject(jsonString);
		List list = new ArrayList();
		for (Iterator iter = array.iterator(); iter.hasNext();) {
			JSONObject jsonObject = (JSONObject) iter.next();
			list.add(JSONObject.toBean(jsonObject, clazz, map));
		}
		return list;
	}

	/**
	 * 从json HASH表达式中获取一个map，该map支持嵌套功能 形如：{"id" : "johncon", "name" : "小强"}
	 * 注意commons
	 * -collections版本，必须包含org.apache.commons.collections.map.MultiKeyMap
	 * 
	 * @param object
	 * @param dateType
	 *            Map中存在日期类型时，是哪种类型的日期
	 *            type = 1 (JsonUtil.DATE_TYPE_SQL_TIMESTAMP)时设置日期类型：java.sql.Timestamp<br>
	 *            type = 2 (JsonUtil.DATE_TYPE_UITL_DATE)时设置日期类型：java.util.Date<br>
	 * @return
	 */
	public static Map getMapFromJson(String jsonString,int dateType) {
		setDataFormat2JAVA(dateType);
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		Map map = new HashMap();
		for (Iterator iter = jsonObject.keys(); iter.hasNext();) {
			String key = (String) iter.next();
			map.put(key, jsonObject.get(key));
		}
		return map;
	}

	/**
	 * 从json数组中得到相应java数组 json形如：["123", "456"]
	 * 
	 * @param jsonString
	 * @return
	 */
	public static Object[] getObjectArrayFromJson(String jsonString) {
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		return jsonArray.toArray();
	}

	/**
	 * 把数据对象转换成json字符串 DTO对象形如：{"id" : idValue, "name" : nameValue, ...}
	 * 数组对象形如：[{}, {}, {}, ...] map对象形如：{key1 : {"id" : idValue, "name" :
	 * nameValue, ...}, key2 : {}, ...} <br>
	 * 
	 * @param dateClassType
	 *            object对象中存在的日期类型，如果object对象有java.sql.Timestamp类日期，则传：java.sql.
	 *            Timestamp.class，没有日期对象随便传的日期类型，如：java.util.Date.class
	 * @param object
	 * @return
	 */
	public static String getJSONString(Class dateClassType, Object object)
			throws Exception {
		String jsonString = null;
		// 日期值处理器
		JsonConfig jsonConfig = new JsonConfig();
		if (dateClassType == null)
			dateClassType = java.util.Date.class;
		jsonConfig.registerJsonValueProcessor(dateClassType,
				new JsonDateValueProcessor());
		if (object != null) {
			if (object instanceof Collection || object instanceof Object[]) {
				jsonString = JSONArray.fromObject(object, jsonConfig)
						.toString();
			} else {
				jsonString = JSONObject.fromObject(object, jsonConfig)
						.toString();
			}
		}
		return jsonString == null ? "{}" : jsonString;
	}
	
	/**
	 * 把数据对象转换成json字符串 DTO对象形如：{"id" : idValue, "name" : nameValue, ...}
	 * 数组对象形如：[{}, {}, {}, ...] map对象形如：{key1 : {"id" : idValue, "name" :
	 * nameValue, ...}, key2 : {}, ...} <br>
	 * dateClassType = 1 JsonUtil.DATE_TYPE_SQL_TIMESTAMP 时设置日期类型：java.sql.Timestamp<br>
	 * dateClassType = 2 JsonUtil.DATE_TYPE_UITL_DATE时设置日期类型：java.util.Date<br>
	 * 
	 * @param dateClassType 日期类型
	 * @param object
	 * @return
	 */
	public static String getJSONString(int dateClassType, Object object)
			throws Exception {
		switch (dateClassType) {
		case JsonUtil.DATE_TYPE_SQL_TIMESTAMP:
			return getJSONString(java.sql.Timestamp.class,object);
		case JsonUtil.DATE_TYPE_UITL_DATE:
			return getJSONString(java.util.Date.class,object);

		default:
			break;
		}
		return null;
	}

	/**
	 * 设定日期类型<br>
	 * type = 1 (JsonUtil.DATE_TYPE_SQL_TIMESTAMP) 时设置日期类型：java.sql.Timestamp<br>
	 * type = 2 (JsonUtil.DATE_TYPE_UITL_DATE) 时设置日期类型：java.util.Date<br>
	 * 
	 * @param type
	 *            JsonUtil.DATE_TYPE_SQL_TIMESTAMP |
	 *            JsonUtil.DATE_TYPE_UITL_DATE
	 */
	private static void setDataFormat2JAVA(int type) {
		if (type == DATE_TYPE_SQL_TIMESTAMP)
			// 设定日期转换格式 : java.sql.Timestamp
			JSONUtils.getMorpherRegistry().registerMorpher(
					new TimestampMorpher(new String[] { "yyyy-MM-dd HH:mm:ss",
							"yyyy-MM-dd" }));
		// 设定日期转换格式: java.util.Date
		else if (type == DATE_TYPE_UITL_DATE)
			JSONUtils.getMorpherRegistry().registerMorpher(
					new DateMorpher(new String[] { "yyyy-MM-dd",
							"yyyy-MM-dd HH:mm:ss" }));
	}

}
