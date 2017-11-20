package com.atgeretg.util.json.ali;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * 
 * <p>
 * Title:JacksonUtil.java
 * </p>
 * <p>
 * Description:jackson工具类
 * </p>
 * <p>
 * Copyright:Copyright (c) 2010
 * </p>
 * <p>
 * Company:gxtz
 * </p>
 * 
 * @author chaun.lee
 * @version 1.0 2016年10月28日
 */
public class JacksonUtil {

	// public static void main(String[] args) throws Exception {
	//
	// DjGoods goods = null;
	// // for(int i = 0; i < 100; i ++) {
	// goods = new DjGoods();
	// goods.setGdCode("ii code");
	// goods.setGdName("name i ");
	// goods.setGdCost(8d);
	// goods.setGdInCost(8d);
	// goods.setGdSupplierId(1);
	// goods.setGdStandard("ssss");
	// goods.setGdTypeId(1);
	// goods.setGdTypeSubId(2);
	//
	// // }
	// goods.setGdBai(0d);
	// goods.setGdHong(0d);
	// goods.setGdHuang(0d);
	// goods.setGdInDate(com.atgeretg.util.date.DateUtil.getCurrentTimestamp());
	// goods.setGdDate(com.atgeretg.util.date.DateUtil.getCurrentTimestamp());
	// goods.setGdUpdateCode(1);
	// goods.setGdBlNum(0);
	// goods.setGdBsCode("8888");
	// goods.setGdPayNum(0);
	// goods.setGdState(1);
	//
	// // String json = "{\"gdId\":null,\"gdCode\":\"ii
	// code\",\"gdDate\":\"2017-11-18
	// // 19:07:33\",\"gdName\":\"name i
	// //
	// \",\"gdCost\":8.0,\"gdInCost\":8.0,\"gdBai\":0.0,\"gdHong\":0.0,\"gdHuang\":0.0,\"gdPayNum\":0,\"gdBlNum\":0,\"gdInDate\":\"2017-11-18
	// //
	// 19:07:33\",\"gdOutDate\":null,\"gdState\":1,\"gdBsCode\":\"8888\",\"gdUpdateCode\":1,\"gdNumber\":null,\"gdSupplierId\":1,\"gdStandard\":\"ssss\",\"gdTypeId\":1,\"gdTypeSubId\":2,\"pandianNum\":null}";
	// // System.out.println(json);
	// // DjGoods djGoods = AliJsonUtil.jsonToPojo(json, DjGoods.class);
	// // System.out.println("getGdInCost = " + djGoods.getGdInCost() + " time = " +
	// // DateUtil.formatDateStr_ss(djGoods.getGdDate()));
	//
	// String jsonInfo =
	// "{\"error\":\"0\",\"sms\":\"订单支付成功\",\"order_pay\":{\"od_id\":\"183120\",\"od_date\":\"2017-11-18
	// 17:52:50\",\"od_code\":\"OD101670201711181753015713\",\"od_num\":\"4\",\"od_cost\":\"12\",\"od_old_cost\":\"12\",\"od_pre_cost\":\"0\",\"od_pay_date\":\"2017-11-18
	// 17:54:04\",\"od_pay_cost\":\"12\",\"od_out_cost\":\"0\",\"od_pay_code\":\"148914065220171118175348\",\"od_pay_state\":\"2\",\"od_pay_type\":\"1\",\"od_bai\":\"0.6\",\"od_hong\":\"0\",\"od_huang\":\"0\",\"od_good_intro\":\"恰恰嘎吱脆51g（鸡汁番茄）
	// *
	// 4\",\"od_u_id\":\"0\",\"od_u_mobile\":\"15876203603\",\"od_state\":\"1\",\"od_bs_code\":\"101670\",\"od_hb_state\":\"1\",\"od_hb_id\":\"0\"},\"order_goods\":[{\"og_id\":\"487920\",\"og_date\":\"2017-11-18
	// 17:52:49\",\"og_od_code\":\"OD101670201711181753015713\",\"og_gd_code\":\"6924187839267\",\"og_gd_name\":\"恰恰嘎吱脆51g（鸡汁番茄）\",\"og_gd_cost\":\"3\",\"og_gd_in_cost\":\"2\",\"og_gd_bai\":\"0.15\",\"og_gd_hong\":\"0\",\"og_gd_huang\":\"0\",\"og_gd_num\":\"4\",\"og_gd_max_cost\":\"12\",\"og_bs_code\":\"101670\",\"og_u_id\":\"0\",\"og_state\":\"1\",\"og_out_cost\":\"0\",\"og_out_state\":\"0\",\"og_center_stock\":\"0\"}],\"order_rfids\":[{\"rf_id\":\"833276\",\"rf_code\":\"2681CA8A500104E0\",\"rf_od_code\":\"OD101670201711181753015713\",\"rf_pay_state\":\"0\"},{\"rf_id\":\"833277\",\"rf_code\":\"AF7ECA8A500104E0\",\"rf_od_code\":\"OD101670201711181753015713\",\"rf_pay_state\":\"0\"},{\"rf_id\":\"833278\",\"rf_code\":\"866E9662500104E0\",\"rf_od_code\":\"OD101670201711181753015713\",\"rf_pay_state\":\"0\"},{\"rf_id\":\"833279\",\"rf_code\":\"7181CA8A500104E0\",\"rf_od_code\":\"OD101670201711181753015713\",\"rf_pay_state\":\"0\"}],\"dj_goods\":{\"gdId\":null,\"gdCode\":\"ii
	// code\",\"gdDate\":\"2017-11-18 19:07:33\",\"gdName\":\"name i
	// \",\"gdCost\":8.0,\"gdInCost\":8.0,\"gdBai\":0.0,\"gdHong\":0.0,\"gdHuang\":0.0,\"gdPayNum\":0,\"gdBlNum\":0,\"gdInDate\":\"2017-11-18
	// 19:07:33\",\"gdOutDate\":null,\"gdState\":1,\"gdBsCode\":\"8888\",\"gdUpdateCode\":1,\"gdNumber\":null,\"gdSupplierId\":1,\"gdStandard\":\"ssss\",\"gdTypeId\":1,\"gdTypeSubId\":2,\"pandianNum\":null}}";
	//
	// ResultMsgDto<List<DjOrderGoods>, DjGoods, DjGoods> result =
	// toCollection(jsonInfo,
	// new TypeReference<ResultMsgDto<List<DjOrderGoods>, DjGoods, DjGoods>>() {
	// });
	// System.out.println(result);
	// //
	// // DjOrderGoods od = toObject(jsonInfo,DjOrderGoods.class);
	// // System.out.println(od.getOgGdCode());
	// Map<String, Object> jsonToPojo = json2map(jsonInfo);//
	// AliJsonUtil.jsonToPojo(jsonInfo, Map.class);
	// //
	// //
	// //
	// Iterator<Map.Entry<String, Object>> it = jsonToPojo.entrySet().iterator();
	// while (it.hasNext()) {
	// Map.Entry<java.lang.String, java.lang.Object> entry =
	// (Map.Entry<java.lang.String, java.lang.Object>) it
	// .next();
	// System.out.println(entry.getKey() + " : " + entry.getValue());
	// if ("order_goods".equals(entry.getKey())) {
	// // DjOrderGoods og = map2pojo((Map)entry.getValue(), DjOrderGoods.class);
	// //// List<DjOrderGoods> og = (List<DjOrderGoods>) entry.getValue();
	// // System.out.println(og.getOgDate());
	// // System.out.println(og.getOgGdName());
	// }
	//
	// if ("dj_goods".equals(entry.getKey())) {
	// DjGoods dj = map2pojo((Map) entry.getValue(), DjGoods.class);
	// System.out.println(dj.getGdName());
	// }
	//
	// }
	// }

	private static Logger logger = LoggerFactory.getLogger(JacksonUtil.class);

	private static final ObjectMapper objectMapper;
	static {
		objectMapper = new ObjectMapper();
		// 去掉默认的时间戳格式
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		// 设置为中国上海时区
		objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
		// 空值不序列化
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		// 反序列化时，属性不存在的兼容处理
		objectMapper.getDeserializationConfig().withoutFeatures(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		// 序列化时，日期的统一格式
		// DeserializationConfig cfg = objectMapper.getDeserializationConfig();
		// //设置JSON时间格式
		// cfg.set//setDateFormat(myDateFormat);
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		// 单引号处理
		objectMapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
	}

	public static <T> T toObject(String json, Class<T> clazz) {
		try {
			return objectMapper.readValue(json, clazz);
		} catch (JsonParseException e) {
			logger.error(e.getMessage(), e);
		} catch (JsonMappingException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	public static <T> String toJson(T entity) {
		try {
			return objectMapper.writeValueAsString(entity);
		} catch (JsonGenerationException e) {
			logger.error(e.getMessage(), e);
		} catch (JsonMappingException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 这个方法好，自己定义类型来进行转换，复杂的json可用它，<br>
	 * ResultMsgDto<E, F, G> E、F、G为java对象 ResultMsgDto<List<DjOrderGoods>, DjGoods,
	 * xxx> result = toCollection(jsonInfo,<br>
	 * new TypeReference<ResultMsgDto<List<DjOrderGoods>, DjGoods, xxx>>() {});
	 * 
	 * @param json
	 * @param typeReference
	 * @return
	 */
	public static <T> T toCollection(String json, TypeReference<T> typeReference) {
		try {
			return objectMapper.readValue(json, typeReference);
		} catch (JsonParseException e) {
			logger.error(e.getMessage(), e);
		} catch (JsonMappingException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * json string convert to map
	 */
	@SuppressWarnings("unchecked")
	public static <T> Map<String, Object> json2map(String jsonStr) throws Exception {
		return objectMapper.readValue(jsonStr, Map.class);
	}

	/**
	 * json string convert to map with javaBean
	 */
	public static <T> Map<String, T> json2map(String jsonStr, Class<T> clazz) throws Exception {
		Map<String, Map<String, Object>> map = objectMapper.readValue(jsonStr, new TypeReference<Map<String, T>>() {
		});
		Map<String, T> result = new HashMap<String, T>();
		for (Map.Entry<String, Map<String, Object>> entry : map.entrySet()) {
			result.put(entry.getKey(), map2pojo(entry.getValue(), clazz));
		}
		return result;
	}

	/**
	 * json array string convert to list with javaBean
	 */
	public static <T> List<T> json2list(String jsonArrayStr, Class<T> clazz) throws Exception {
		List<Map<String, Object>> list = objectMapper.readValue(jsonArrayStr, new TypeReference<List<T>>() {
		});
		List<T> result = new ArrayList<T>();
		for (Map<String, Object> map : list) {
			result.add(map2pojo(map, clazz));
		}
		return result;
	}

	/**
	 * map转成javaBean对象
	 */
	public static <T> T map2pojo(Map map, Class<T> clazz) {
		return objectMapper.convertValue(map, clazz);
	}
}
