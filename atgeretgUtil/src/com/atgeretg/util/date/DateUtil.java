package com.atgeretg.util.date;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.SimpleTimeZone;

import com.atgeretg.util.string.StrUtil;

//import org.apache.commons.lang3.time.DateUtils;

/**
 * java.util.Date日期格式为：年月日时分秒 java.sql.Date日期格式为：年月日 java.sql.Time日期格式为：时分秒
 * java.sql.Timestamp日期格式为：年月日时分秒纳秒（毫微秒）
 * 
 * @author atgeretg
 * 
 */
public class DateUtil {
	/**
	 * 格式：yyyy-MM-dd
	 */
	public static final String Y_M_D = "yyyy-MM-dd";
	
	/**
	 * 格式：yyyy-MM-dd HH:mm
	 */
	public static final String Y_M_D_HM = "yyyy-MM-dd HH:mm";
	
	/**
	 * 格式：yyyy-MM-dd HH:mm:ss
	 */
	public static final String Y_M_D_HMS = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 格式：yyyyMM
	 */
	public static final String YM = "yyyyMM";
	
	/**
	 * 格式：yyyyMMdd
	 */
	public static final String YMD = "yyyyMMdd";
	
	/**
	 * 格式：yyyyMMddHHmm
	 */
	public static final String YMDHM = "yyyyMMddHHmm";
	
	/**
	 * 格式：yyyyMMddHHmmss
	 */
	public static final String YMDHMS = "yyyyMMddHHmmss";
	
	/**
	 * 格式：yyyy/MM/dd
	 */
	public static final String ymd = "yyyy/MM/dd";
	
	/**
	 * 格式：yyyy/MM/dd HH:mm
	 */
	public static final String ymd_HM = "yyyy/MM/dd HH:mm";
	
	/**
	 * 格式：yyyy/MM/dd HH:mm:ss
	 */
	public static final String ymd_HMS = "yyyy/MM/dd HH:mm:ss";
	
	/**
	 * 格式：yyyy年MM月dd日
	 */
	public static final String ymd_China = "yyyy年MM月dd日";
	
	/**
	 * 格式：yyyyMMddhhmmssSSS
	 */
	public static final String ymdhmss = "yyyyMMddhhmmssSSS";// 时间戳

	// public static SimpleDateFormat dateFormat = new SimpleDateFormat(
	// "yyyy-MM-dd");
	// public static SimpleDateFormat dateFormat2 = new
	// SimpleDateFormat("yyyy-MM");
	// public static SimpleDateFormat dateFormatDB = new SimpleDateFormat(
	// "yyyyMMdd");// 数据库使用的日期格式
	// public static SimpleDateFormat dataTimeFormat = new SimpleDateFormat(
	// "yyyy-MM-dd HH:mm:ss");

	public static void main(String[] args) throws Exception {
//		long l = diffDateHours(getDateByMinuteAdd(new Date(),-110),new Date());
//		if(l<2) 
//		System.out.println(l);
//		int i_day = diffDate(new Date(), diffDate(new Date(), 5));
//		int i_day_h = (int) diffDateMinute(new Date(), getDateByMinuteAdd(new Date(),60));
//		System.out.println(i_day_h);
//		System.out.println(getWeekMondayOfThis());
		// System.out.println(format(new Date(), "-"));
//		List<String> datePositionStr = getDatePositionStr(5);
//		for (String string : datePositionStr) {
//			System.out.println(string);
//		}
		// getSubTwoTime("2016-04-13","2016-04-15");
		// SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		// getSubTwoTime
		// System.out.println(getSubTwoTime1("2017-07-10 17:56:18.138",
		// "2017-07-10 17:56:17.13"));
		// Log.systemLog(formatStr2date("2017-07-10"));
		// Log.systemLog(getWeekOfDateInt(formatSmart("2017-07-10"))+" "+
		// getWeekOfDateString(formatSmart("2017-07-10")));
		// List<String> ymList = getBetweenDateMonthList("2017-07", "2017-09",
		// "YYYY-MM");
		// for (String string : ymList) {
		// Log.systemLog(string);
		// }
		// List<String> ymdList = getDatePosition(50,"MM-dd");
		// for (String string : ymdList) {
		// Log.systemLog(string);
		// }
		// Log.systemLog(formatDateStr_ss(calDateSubHour(new Date(),-6)));
		// Log.systemLog(formatDateStr_ss(newDateFormat(ymdhmss).parse(getTimeStamp())));
	}

	/**
	 * java.util.Date日期格式为：年月日时分秒
	 * 
	 * @return java.util.Date
	 */
	public static java.util.Date getCurrentDate() {
		return new java.util.Date();
	}

	/**
	 * 日期格式为：年月日时分秒纳秒（毫微秒）
	 * 
	 * @return
	 */
	public static java.sql.Timestamp getCurrentTimestamp() {

		return new java.sql.Timestamp(new java.util.Date().getTime());
	}

	/**
	 * java.sql.Date日期格式为：输出时只有年月日<br>
	 * 内部的有其它时间不为0，依旧存在着
	 * 
	 * @return java.sql.Date
	 */
	public static java.sql.Date getCurrentOnlyDate() {
		return new java.sql.Date(new java.util.Date().getTime());
	}

	/**
	 * java.sql.Time日期格式为：输出时只有时分秒<br>
	 * 内部的有其它时间不为0，依旧存在着
	 * @return java.sql.Time
	 */
	public static java.sql.Time getCurrentOnlyTime() {
		return new java.sql.Time(new java.util.Date().getTime());
	}

	/**
	 * 根据指定的日期得到格式为 "yyyy-MM-dd"的Date<br>
	 * 没什么必要，可以直接创建 new java.sql.Date(date.getTime())
	 * 
	 * @param Date
	 *            date 日期对象
	 * @return java.sql.Date
	 */
	@Deprecated
	public static java.sql.Date formatDate(Date date) {
		return new java.sql.Date(date.getTime());
	}

	/**
	 * 将"yyyy-MM-dd"字符格式化为日期对象
	 * 
	 * @param strDate
	 *            "yyyy-MM-dd"字符串
	 * @return null | java.util.Date
	 */
	public static java.util.Date formatStr2date(String strDate) {
		java.util.Date date = null;
		try {
			date = newLongYMDFormat().parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;

	}
	
	/**
	 * 将"yyyy-MM-dd"字符格式化为日期对象，无论怎么格式化只有年月日，其它的都是0;如：2017-08-02 00:00:00
	 * 
	 * @param date
	 *            要格式化的日期
	 * @return java.util.Date
	 */
	public static java.util.Date formatOnlyYMD(java.util.Date date) {
		return formatStr2date(formatDateStr(date));
		

	}

	/**
	 * 
	 * 解析数据库中的时间字符串 格式:yyyy-MM-dd HH:mm:ss
	 * 
	 * @param dateTimeStr
	 *            "yyyy-MM-dd HH:mm:ss"字符串
	 * @return null | java.util.Date
	 */
	public static java.util.Date formatStr2Time(String dateTimeStr) {
		Date date = null;
		try {
			date = newLongYMDHMSFormat().parse(dateTimeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 
	 * 解析数据库中的时间字符串 格式:yyyy-MM-dd HH:mm:ss
	 * 
	 * @param dateTimeStr
	 *            "yyyy-MM-dd HH:mm:ss"字符串
	 * @return null | java.sql.Timestamp
	 */
	public static java.sql.Timestamp formatStr2Timestamp(String dateTimeStr) {
		java.sql.Timestamp date = null;
		try {
			date = new java.sql.Timestamp(newLongYMDHMSFormat().parse(dateTimeStr).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 智能转换日期,传入一个日期字符串，智能将其转换成日期对象
	 * 
	 * @param text
	 * @return null | Date对象
	 */
	public static Date formatSmart(String text) {
		Date date = null;
		try {
			if (StrUtil.isEmpty(text)) {
				date = null;
			}
			switch (text.length()) {
			case 8:
				date = formatStringToDate(text, YMD);
				break;
			case 10:
				if (text.contains("/"))
					date = formatStringToDate(text, ymd);
				else
					date = formatStringToDate(text, Y_M_D);
				break;
			case 13:
				date = new Date(Long.parseLong(text));
				break;
			case 14:
				date = formatStringToDate(text, YMDHMS);
				break;
			case 16:
				if (text.contains("/"))
					date = formatStringToDate(text, ymd_HM);
				else
					date = formatStringToDate(text, Y_M_D_HM);

				break;
			case 19:
				if (text.contains("/"))
					date = formatStringToDate(text, ymd_HMS);
				else
					date = formatStringToDate(text, Y_M_D_HMS);
				break;

			default:
				throw new IllegalArgumentException("日期长度不符合要求!");
			}

		} catch (Exception e) {
			throw new IllegalArgumentException("日期转换失败!");
		}
		return date;
	}

	/**
	 * 把字符串格式化成日期,转换的格式为空时，默认用类似"yyyy-MM-dd"这种带"-"的格式
	 * 
	 * @param strDate
	 *            日期字符串
	 * @param strFormat
	 *            转换的格式
	 * @throws Exception
	 * @return
	 */
	public static Date formatStringToDate(String strDate, String strFormat) throws Exception {
		if (strDate == null || strDate.trim().length() < 1) {
			throw new Exception("参数[日期]不能为空!");
		}
		if (com.atgeretg.util.string.StringUtil.isEmpty(strFormat)) {
			strFormat = Y_M_D;
			if (strDate.length() > 16) {
				strFormat = Y_M_D_HMS;
			} else if (strDate.length() > 10) {
				strFormat = Y_M_D_HM;
			}
		}
		SimpleDateFormat sdfFormat = newDateFormat(strFormat);
		// 严格模式
		sdfFormat.setLenient(false);
		try {
			return sdfFormat.parse(strDate);
		} catch (ParseException e) {
			throw new Exception(e);
		}
	}

	/********************************************************************************
	 * 
	 * 
	 * 
	 * String型
	 * 
	 * 
	 * 
	 * 
	 * 
	 *********************************************************************************/

	/**
	 * 获取当前日期 :"yyyy-MM-dd" 字符串
	 * 
	 * @return "yyyy-MM-dd" 字符串
	 */
	public static String formatDateStr() {
		return newLongYMDFormat().format(new Date());
	}

	/**
	 * 将日期格式化为 "yyyy-MM-dd" 字符串
	 * 
	 * @param Date
	 *            date 日期对象
	 * @return String "yyyy-MM-dd" 字符串
	 */
	public static String formatDateStr(Date date) {
		return newLongYMDFormat().format(date);
	}

	/**
	 * 获取当前日期 : "yyyy-MM-dd HH:mm:ss"
	 * 
	 * @return "yyyy-MM-dd HH:mm:ss"字符串
	 */
	public static String formatDateStr_ss() {
		return newLongYMDHMSFormat().format(new Date());
	}

	/**
	 * 将日期格式化为 "yyyy-MM-dd HH:mm:ss"
	 * 
	 * @param Date
	 *            date 日期对象
	 * @return "yyyy-MM-dd HH:mm:ss"字符串
	 */
	public static String formatDateStr_ss(Date date) {
		return newLongYMDHMSFormat().format(date);
	}

	/**
	 * 获得"yyyyMMdd"格式的当前日期
	 * 
	 * @return 返回"yyyyMMdd"格式的当前日期
	 */
	public static String formatDateStrYMD() {
		return newShortYMDFormat().format(new Date());
	}

	/**
	 * 
	 * 获取当前日期 :yyyy年MM月dd日
	 * 
	 * @return
	 */
	public static String formatDateStrChina() {
		return newDateFormat("yyyy年MM月dd日").format(new Date());
		// return str.split("-")[0] + "年" + str.split("-")[1] + "月"
		// + str.split("-")[2] + "日";
	}

	/**
	 * 获得"yyyyMMddHHmmss"格式的当前日期
	 * 
	 * @return 返回"yyyyMMddHHmmss"格式的当前时间
	 */
	public static String getShortYMDHMS() {
		return newShortYMDHMSFormat().format(new Date());
	}

	/**
	 * 获得"yyyymmddhhmmssSSS"格式的当前日期(时间戳)
	 * 
	 * @return 返回"yyyymmddhhmmssSSS"格式的当前时间
	 */
	public static String getTimeStamp() { // 返回
		return newDateFormat(ymdhmss).format(new Date());
	}

	/**
	 * 日期是否合法，日期格式yyyy-MM-dd
	 * 
	 * @param dateStr
	 *            日期
	 * @return true｜false
	 */
	public static boolean isValidDate(String dateStr) {
		boolean convertSuccess = true;
		// 指定日期格式为四位年/两位月份/两位日期，注意yyyy-MM-dd区分大小写；
		SimpleDateFormat format = newLongYMDFormat();
		try {
			// 设置lenient为false.
			// 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
			format.setLenient(false);
			format.parse(dateStr);
		} catch (ParseException e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			convertSuccess = false;
		}
		return convertSuccess;
	}

	/**
	 * 指定日期格式进行判断日期是否合法
	 * 
	 * @param dateStr
	 *            日期
	 * @param formatStr
	 *            指定的日期格式 如："yyyy-MM-dd"
	 * @return
	 */
	public static synchronized boolean isValidDate(String dateStr, String formatStr) {
		boolean convertSuccess = true;
		// 指定日期格式为四位年/两位月份/两位日期，注意yyyy-MM-dd区分大小写；
		SimpleDateFormat format = newDateFormat(formatStr);
		try {
			// 设置lenient为false.
			// 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
			format.setLenient(false);
			format.parse(dateStr);
		} catch (ParseException e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			convertSuccess = false;
		}
		return convertSuccess;
	}

	/** 锁对象 */
	private static final Object lockObj = new Object();
	/** 存放不同的日期模板格式的sdf的Map */
	private static Map<String, ThreadLocal<SimpleDateFormat>> sdfMap = new HashMap<String, ThreadLocal<SimpleDateFormat>>();

	/**
	 * 返回一个ThreadLocal的sdf,每个线程只会new一次sdf
	 * 
	 * @param pattern
	 * @return
	 */
	private static SimpleDateFormat getSdf(final String pattern) {
		ThreadLocal<SimpleDateFormat> tl = sdfMap.get(pattern);

		// 此处的双重判断和同步是为了防止sdfMap这个单例被多次put重复的sdf
		if (tl == null) {
			synchronized (lockObj) {
				tl = sdfMap.get(pattern);
				if (tl == null) {
					// 只有Map中还没有这个pattern的sdf才会生成新的sdf并放入map
					// System.out.println("put new sdf of pattern " + pattern +
					// " to map");

					// 这里是关键,使用ThreadLocal<SimpleDateFormat>替代原来直接new
					// SimpleDateFormat
					tl = new ThreadLocal<SimpleDateFormat>() {

						@Override
						protected SimpleDateFormat initialValue() {
							// System.out.println("thread: " +
							// Thread.currentThread() + " init pattern: " +
							// pattern);
							return new SimpleDateFormat(pattern);
						}
					};
					sdfMap.put(pattern, tl);
				}
			}
		}

		return tl.get();
	}

	/**
	 * 创建一个指定的字符串的日期的格式化对象
	 * 
	 * @param format
	 *            指定的字符串 如："yyyy-MM-dd"、"yyyy/MM/dd HH:mm"、"yyyy年MM月dd日"等等
	 * @return 指定的日期的格式化对象
	 */
	public static SimpleDateFormat newDateFormat(String format) {
		return getSdf(format);
	}

	/**
	 * 创建一个"yyyyMM"日期的格式化对象
	 * 
	 * @return "yyyyMM"日期的格式化对象
	 */
	private static SimpleDateFormat newShortYMFormat() {
		return getSdf("yyyyMM");
	}

	/**
	 * 创建一个"yyyyMMdd"日期的格式化对象,数据库使用的日期格式
	 * 
	 * @return "yyyyMMdd"日期的格式化对象
	 */
	private static SimpleDateFormat newShortYMDFormat() {
		return getSdf("yyyyMMdd");
	}

	/**
	 * 创建一个"yyyy/MM/dd"日期的格式化对象
	 * 
	 * @return "yyyy/MM/dd"日期的格式化对象
	 */
	private static SimpleDateFormat newShortYMDFormat_() {
		return getSdf("yyyy/MM/dd");
	}

	/**
	 * 创建一个"yyyyMMddHHmmss"日期的格式化对象
	 * 
	 * @return "yyyyMMddHHmmss"日期的格式化对象
	 */
	private static SimpleDateFormat newShortYMDHMSFormat() {
		return getSdf("yyyyMMddHHmmss");
	}

	/**
	 * 创建一个"yyyy-MM-dd"日期的格式化对象
	 * 
	 * @return "yyyy-MM-dd"日期的格式化对象
	 */
	private static SimpleDateFormat newLongYMDFormat() {
		return getSdf("yyyy-MM-dd");
	}

	/**
	 * 创建一个"yyyy-MM-dd HH:mm:ss"日期的格式化对象
	 * 
	 * @return "yyyy-MM-dd HH:mm:ss"日期的格式化对象
	 */
	private static SimpleDateFormat newLongYMDHMSFormat() {
		return getSdf("yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 两时间相减精确到秒, 返回相差天数、小时、分钟、秒数的绝对值<br>
	 * String格式：yyyy-MM-dd HH:mm:ss
	 * 
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @return 天-小时-分-秒 | 错误: null
	 */
	public static String getSubTwoTime1(String startTime, String endTime) {
		DateFormat df = newLongYMDHMSFormat();

		try {
			Date d1 = df.parse(startTime);
			Date d2 = df.parse(endTime);
			long diff = d2.getTime() - d1.getTime();// 这样得到的差值是微秒级别
			if (diff < 0)
				diff = 0 - diff;
			long days = diff / (1000 * 60 * 60 * 24);

			long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
			long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
			long second = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60) - minutes * (1000 * 60))
					/ 1000;
			return new StringBuffer().append(days).append("-").append(hours).append("-").append(minutes).append("-")
					.append(second).toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 两时间相减 返回 相差值精确到分钟,绝对值<br>
	 * String格式：yyyy-MM-dd HH:mm:ss
	 * 
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @return 天-小时-分 | 错误: null
	 */
	public static String getSubTwoTime(String startTime, String endTime) {
		DateFormat df = newLongYMDHMSFormat();
		try {

			Date d1 = df.parse(startTime);
			Date d2 = df.parse(endTime);
			long diff = d1.getTime() - d2.getTime();// 这样得到的差值是微秒级别
			long days = diff / (1000 * 60 * 60 * 24);

			long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
			long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
			// System.out.println(""+days+"天"+hours+"小时"+minutes+"分");
			if (hours < 0) {
				hours = new BigDecimal(hours).abs().intValue();
			}
			if (minutes < 0) {
				minutes = new BigDecimal(minutes).abs().intValue();
			}
			return new StringBuffer().append(days).append("-").append(hours).append("-").append(minutes).toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 字符串类型两时间相减 返回相差天数 <br>
	 * String格式：yyyy-MM-dd
	 * 
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @return long类型的天数|出错为null
	 */
	public static Long getSubTwoTimeYY(String endTime, String startTime) {
		DateFormat df = newLongYMDFormat();
		try {
			Date d1 = df.parse(startTime);
			Date d2 = df.parse(endTime);
			long diff = d1.getTime() - d2.getTime();// 这样得到的差值是微秒级别
			long days = diff / (1000 * 60 * 60 * 24);
			return days;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将yyyy-MM-dd HH:mm:ss转换Unix时间
	 * 
	 * @param dateTime
	 *            格式：yyyy-MM-dd HH:mm:ss
	 * @return null | unix 时间字符串
	 */
	public static String getUnixTimeStamp(String dateTime) {

		Calendar c = Calendar.getInstance();
		try {
			c.setTime(newLongYMDHMSFormat().parse(dateTime));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}

		return (c.getTimeInMillis() / 1000) + "";
	}

	/**
	 * 将unix 时间 转换成自定义的的时间格式
	 * 
	 * @param timestampString
	 *            1252639886
	 * @param formats
	 *            自定义的的时间格式 如："yyyy-MM-dd"
	 * @return null | formats格式字符串
	 */
	public static String gerUnixTime2String(String timestampString, String formats) {
		if (StrUtil.isEmpty(timestampString) || "null".equals(timestampString)) {
			return null;
		}
		Long timestamp = Long.parseLong(timestampString) * 1000;
		String date = newDateFormat(formats).format(new java.util.Date(timestamp));
		return date;
	}

	/**
	 * 获取当前的unix 时间
	 * 
	 * @return unix 时间字符串(1252639886)
	 */
	public static String getCurrentUnixTimeStamp() {

		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		return (c.getTimeInMillis() / 1000) + "";
	}

	/**
	 * "yyyyMMddHHmmss"格式的日期转换为"yyyy-MM-dd HH:mm:ss"格式的日期
	 * 
	 * @param shortYMDHMS
	 *            "yyyyMMddHHmmss"格式的日期
	 * @return "yyyy-MM-dd HH:mm:ss"格式的日期
	 * @throws ParseException
	 */
	public static String changeYMDto_HMS(String shortYMDHMS) throws ParseException {
		return newLongYMDHMSFormat().format(newShortYMDHMSFormat().parse(shortYMDHMS));
	}

	/**
	 * "yyyyMMdd"格式的日期转换为"yyyy-MM-dd"格式的日期
	 * 
	 * @param shortYMD
	 *            "yyyyMMdd"格式的日期
	 * @return "yyyy-MM-dd"格式的日期
	 * @throws ParseException
	 */
	public static String toLongYMD(String shortYMD) {
		try {
			return newLongYMDFormat().format(newShortYMDFormat().parse(shortYMD));
		} catch (ParseException e) {
			// Auto-generated catch block

			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 
	 * 功能：把日期yyyy-MM-dd格式转换成yyyyMMDD日期格式
	 * 
	 * @param dateStr
	 *            "yyyy-MM-dd"日期字符串
	 * @return null | "yyyyMMDD"日期格式
	 */
	public static String changeDateToDbDate(String dateStr) {
		String dbDateStr = null;
		try {
			dbDateStr = newShortYMDFormat().format(newLongYMDFormat().parse(dateStr));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dbDateStr;
	}

	/**
	 * 将UNIX时间转换成标准时间 如："1817449326" => 2027-08-05 15:02:06
	 * 
	 * @param timestampString
	 * @return "yyyy-MM-dd HH:mm:ss"格式的字符串
	 */
	public static String getDate(String timestampString) {
		Long timestamp = Long.parseLong(timestampString) * 1000;
		String date = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(timestamp));
		return date;
	}

	/**
	 * 
	 * 格式化日期字符串 例如：2008-8-8 转换为2008-08-08
	 * 
	 * @param dateStr
	 *            要转的字符串 如："2008-8-8"的字符串
	 * @return 失败：null ｜成功： 类似“2008-08-08”的字符串
	 */
	public static String changeDateStrFormat(String dateStr) {
		try {
			SimpleDateFormat dateFormat = newLongYMDFormat();
			Date date = dateFormat.parse(dateStr);
			return dateFormat.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * 功能：格式化日期字符串 例如：2008-8 转换为2008-08
	 * 
	 * @param dateStr
	 *            要转的字符串 如："2008-8"的字符串
	 * @return 失败：null ｜成功： 类似“2008-08”的字符串
	 */
	public static String getDateStrFormat2(String dateStr) {
		try {
			SimpleDateFormat dateFormat2 = newDateFormat("yyyy-MM");
			Date date = dateFormat2.parse(dateStr);
			return dateFormat2.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 格式化日期字符串 例如：2008-8-8 转 20080808
	 * 
	 * @param dateStr
	 *            要转的字符串 如："2008-8-8"的字符串
	 * @return 失败：null ｜成功： 类似“20080808”的字符串
	 */
	public static String changeDateStrFormatyyyyMMdd(String dateStr) {
		try {
			Date date = newLongYMDFormat().parse(dateStr);
			return newShortYMDFormat().format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 计算两个日期之间的天数，"yyyy-MM-dd"日期字符串
	 * 
	 * @param startDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @return -1 | 相差天数（绝对值）
	 */
	public static int calcDay4(String startDate, String endDate) {
		int days = 1;
		try {
			SimpleDateFormat dateFormat = newLongYMDFormat();
			long start = dateFormat.parse(startDate).getTime();
			long end = dateFormat.parse(endDate).getTime();
			days = (int) ((end - start) / (24 * 60 * 60 * 1000));
			if (days < 0)
				days = 0 - days;
		} catch (ParseException e) {
			e.printStackTrace();
			return -1;
		}
		return days;
	}

	/**
	 * 计算两个日期之间的天数，"yyyyMMdd"日期字符串
	 * 
	 * @param startDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @return -1 | 相差天数（绝对值）
	 */
	public static int calcDay(String startDate, String endDate) {
		int days = 1;
		try {
			SimpleDateFormat dateFormatDB = newShortYMDFormat();
			long start = dateFormatDB.parse(startDate).getTime();
			long end = dateFormatDB.parse(endDate).getTime();
			days = (int) ((end - start) / (24 * 60 * 60 * 1000));
			if (days < 0)
				days = 0 - days;
		} catch (ParseException e) {
			e.printStackTrace();
			return -1;
		}
		return days;
	}

	/**
	 * 功能：指定日期加上指定天数
	 * 
	 * @param date
	 *            日期
	 * @param day
	 *            天数
	 * @return 返回相加后的日期
	 */
	public static Date addDate(Date date, int day) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) + ((long) day) * 24 * 3600 * 1000);
		return c.getTime();
	}

	/**
	 * 功能：指定日期加上指定天数
	 * 
	 * @param date
	 *            日期
	 * @param minute
	 *            分钟
	 * @return 返回相加后的日期
	 */
	public static Date addMinute(Date date, int minute) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) + ((long) minute) * 60 * 1000);
		return c.getTime();
	}

	/**
	 * 
	 * 功能：添加指定秒杀的时间
	 * 
	 * @param date
	 * @param second
	 * @return
	 */
	public static Date addSecond(Date date, int second) {
		long s = date.getTime();
		s = s + second * 1000;
		return new Date(s);
	}

	/**
	 * 功能：指定日期减去指定天数
	 * 
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date diffDate(Date date, int day) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) - ((long) day) * 24 * 3600 * 1000);
		return c.getTime();
	}

	/**
	 * 
	 * 功能：分钟相差 minute的时间值
	 * 
	 * @param date
	 * @param minute
	 * @return
	 */
	public static Date getDateByMinuteAdd(Date date, int minute) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minute);
		return calendar.getTime();
	}

	/**
	 * 功能:两个日期相隔天数,不够一天为0
	 * 
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @return 返回相减后的相差天数(非绝对值）
	 */
	public static int diffDate(Date startDate, Date endDate) {
		long endMillis = endDate.getTime();
		long startMillis = startDate.getTime();
		long s = (endMillis - startMillis) / (24 * 3600 * 1000);
		return (int) s;
	}
	
	/**
	 * 功能:两个日期相隔小时数
	 * 
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @return 返回相减后的相差小时数(非绝对值）
	 */
	public static long diffDateHours(Date startDate, Date endDate) {
		long endMillis = endDate.getTime();
		long startMillis = startDate.getTime();
		return (endMillis - startMillis) / (3600 * 1000);
	}

	/**
	 * 功能:两个日期相隔分钟
	 * 
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @return 返回相减后的相差分钟数(非绝对值）
	 */
	public static long diffDateMinute(Date startDate, Date endDate) {
		long endMillis = endDate.getTime();
		long startMillis = startDate.getTime();
		return (endMillis - startMillis) / (60 * 1000);
	}
	
	
	/**
	 * 功能：传入时间按所需格式返回时间字符串
	 * 
	 * @param date
	 *            java.util.Date格式
	 * @param format
	 *            yyyy-MM-dd HH:mm:ss | yyyy年MM月dd日 HH时mm分ss秒 等....
	 * @return date为空，默认现在Date，format为空：默认格式为"yyyy-MM-dd" 不会有null返回
	 */
	public static String format(Date date, String format) {
		if (date == null) {
			date = new Date();// 如果时间为空,则默认为当前时间
		}
		if (StrUtil.isEmpty(format) || format.length() < 4) {// 默认格式化形式
			format = "yyyy-MM-dd";
		}
		DateFormat df = newDateFormat(format);

		return df.format(date);
	}

	/**
	 * 功能：传入时间字符串按所需格式返回时间
	 * 
	 * @param dateStr
	 *            时间字符串
	 * @param format
	 *            跟传入dateStr时间的格式必须一样 如：yyyy-MM-dd HH:mm:ss | yyyy年MM月dd日 HH时mm分ss秒
	 *            等等
	 * @return dateStr为空：返回现在Date，format为空：默认格式为"yyyy-MM-dd"，格式化出错返回null
	 */
	public static Date format(String dateStr, String format) {
		if (StrUtil.isEmpty(dateStr)) {
			return new Date();
		}
		if (StrUtil.isEmpty(format)) {
			format = "yyyy-MM-dd";
		}
		Date date = null;
		try {
			DateFormat f = newDateFormat(format);
			date = f.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;

	}

	/**
	 * 功能：时间字符串格式转换
	 * 
	 * @param dateStr
	 *            时间字符串
	 * @param format
	 *            时间字符串的格式
	 * @param toFormat
	 *            格式为的（转换后的）格式
	 * @return dateStr为空：返回现在Date格式化后的String，format为空：默认格式为"yyyy-MM-dd"，格式化出错返回null
	 */
	public static String format(String dateStr, String format, String toFormat) {
		Date date = format(dateStr, format);
		if (date == null)
			return null;
		return format(date, toFormat);
	}

	/**
	 * 格式化rss的时间 ,如果时间为空,则默认为当前时间
	 * 
	 * @param date
	 * @return
	 */
	public static String formatRssDate(Date date) {
		if (date == null) {
			date = new Date();// 如果时间为空,则默认为当前时间
		}
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
		SimpleTimeZone zone = new SimpleTimeZone(8, "GMT");
		sdf.setTimeZone(zone);
		return sdf.format(date);
	}

	/**
	 * 功能：返回年
	 * 
	 * @param date
	 * @return
	 */
	public static int getYear(Date date) {
		if (date == null) {
			date = new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.YEAR);

	}

	/**
	 * 功能：返回月
	 * 
	 * @param date
	 * @return
	 */
	public static int getMonth(Date date) {
		if (date == null) {
			date = new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MONTH) + 1;
	}

	/**
	 * 功能：返回日
	 * 
	 * @param date
	 * @return
	 */
	public static int getDay(Date date) {
		if (date == null) {
			date = new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 功能：返回小时
	 * 
	 * @param date
	 * @return
	 */
	public static int getHour(Date date) {
		if (date == null) {
			date = new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 功能：返回分
	 * 
	 * @param date
	 * @return
	 */
	public static int getMinute(Date date) {
		if (date == null) {
			date = new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MINUTE);
	}

	/**
	 * 功能：返回秒
	 * 
	 * @param date
	 * @return
	 */
	public static int getSecond2(Date date) {
		if (date == null) {
			date = new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.SECOND);
	}

	/**
	 * 功能：返回毫秒
	 * 
	 * @param date
	 * @return
	 */
	public static long getMillis(Date date) {
		if (date == null) {
			date = new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.getTimeInMillis();
	}

	/**
	 * 功能：获取当前月的第一天日期
	 * 
	 * @return
	 */
	public static Date getMonFirstDay() {
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.set(getYear(date), getMonth(date) - 1, 1);
		return c.getTime();
	}

	/**
	 * 功能：获取当前月的最后一天日期
	 * 
	 * @return
	 */
	public static Date getMonLastDay() {
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.set(getYear(date), getMonth(date), 1);
		c.setTimeInMillis(c.getTimeInMillis() - (24 * 3600 * 1000));
		return c.getTime();
	}

	/**
	 * 功能：获取上个月的最后一天日期
	 * 
	 * @return
	 */
	public static Date getMonUpDay() {
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.set(getYear(date), getMonth(date) - 1, 1);
		c.setTimeInMillis(c.getTimeInMillis() - (24 * 3600 * 1000));
		return c.getTime();
	}

	/** 获得本月的第一天的日期 */
	public static String getCurrMonthFirstDay() {
		Calendar cal = Calendar.getInstance();
		String s = (getYear(cal)) + "-" + (getMonth(cal)) + "-01";
		return s;
	}

	/** 获得当前月份2015-11 */
	public static String getCurrMonth() {
		Calendar cal = Calendar.getInstance();
		String s = (getYear(cal)) + "-" + (getMonth(cal));
		return getDateStrFormat2(s);
	}

	/** 获得本月的最后一天的日期 */
	public static String getCurrMonthLastDay() {
		Calendar cal = Calendar.getInstance();
		String s = (getYear(cal)) + "-" + (getMonth(cal)) + "-" + getDays(cal);
		return s;
	}

	/** 获得给定日期当月的天数 */
	public static int getDays(Calendar cal) {
		return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/** 获得给定日历的年 */
	public static int getYear(Calendar cal) {
		return cal.get(Calendar.YEAR);
	}

	/** 获得给定日历的月 */
	public static int getMonth(Calendar cal) {
		return (cal.get(Calendar.MONTH) + 1);
	}

	/** 获得给定日期字符串对应的年 */
	public static int getYear(String date_str, String type) {
		return (convertStrToCal(date_str, type).get(Calendar.YEAR));
	}

	/** 日期转日历* */
	public static Calendar convertDateToCal(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	/** 字符转换日历(动态格式转换) */
	public static Calendar convertStrToCal(String date_str, String type) {
		Calendar cal = Calendar.getInstance();
		java.util.Date date = format(date_str, type); // format
		// convertStrToDate(date_str, type);
		cal.setTime(date);
		return cal;
	}

	/** 字符转换日期(动态格式转换) */
	// public static Date convertStrToDate(String date_str, String format) {
	// SimpleDateFormat dateformat = newDateFormat(format);
	// try {
	// return dateformat.parse(date_str);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return null;
	// }

	/**
	 * 获得距离输入月份的diffMonth月的日期
	 * 
	 * @param month
	 *            "yyyyMM"格式的日期
	 * @param diffMonth
	 *            相差的月数
	 * @return "yyyyMM"格式的日期
	 * @throws ParseException
	 */
	public static String getShortYMDiffMonth(String month, int diffMonth) {
		SimpleDateFormat sdf = newShortYMFormat();
		try {
			sdf.parse(month);
		} catch (ParseException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		Calendar c = sdf.getCalendar();
		c.add(Calendar.MONTH, diffMonth);
		return sdf.format(c.getTime());
	}

	/**
	 * 获得距离给定日期diffDay天的日期
	 * 
	 * @param shortYMD
	 *            "yyyyMMdd"格式的日期
	 * @param diffDay
	 *            相差的天数
	 * @return "yyyyMMdd"格式的日期
	 * @throws ParseException
	 */
	public static String getShortYMDDiffDay(String shortYMD, int diffDay) {
		SimpleDateFormat sdf = newShortYMDFormat();
		try {
			sdf.parse(shortYMD);
		} catch (ParseException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		Calendar c = sdf.getCalendar();
		c.add(Calendar.DATE, diffDay);
		return sdf.format(c.getTime());
	}

	/**
	 * 当前时间加、减days
	 * 
	 * @param shortYMD
	 * @param diffDay
	 * @return "yyyy年MM月dd日 HH:mm"格式的字符串
	 */
	public static String getAddDay(int diffDay) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, diffDay);
		return newDateFormat("yyyy年MM月dd日 HH:mm").format(c.getTime());
	}

	/**
	 * 获取某月份的最后一天
	 * 
	 * @param shortYM
	 *            月份
	 * @return 输入月份的最后一天
	 * @throws Exception
	 */
	public static String getEndDayOfMonth(String shortYM) {
		String month = "";
		try {
			month = DateUtil.getShortYMDiffMonth(shortYM, 1);
		} catch (Exception e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		return DateUtil.getShortYMDDiffDay(month + "01", -1);
	}

	/**
	 * 获取当天及前（过去）n（几）天的日期的String的list集合
	 * 
	 * @param n
	 *            前几天，即再向前（过去）推几天
	 * @return "MM月dd日"格式的字符串的List<String>
	 */
	public static List<String> getDatePositionBeforStr(int n) {

		return getDatePositionStr(n, -1);
	}

	/**
	 * 获取当天及后（未来）n（几）天的日期的String的list集合
	 * 
	 * @param n
	 *            后几天，即再向后（未来）推几天
	 * @return "MM月dd日"格式的字符串的List<String>
	 */
	public static List<String> getDatePositionAfterStr(int n) {

		return getDatePositionStr(n, 1);
	}

	/**
	 * 获取当天的日期向前移（过去）或向后移（未来）的String的list集合，direction是每次移动的天数
	 * 
	 * @param n
	 *            推（移动）的次数
	 * @param direction
	 *            推（移动）的方向，向前移（过去）或向后移（未来）<br>
	 *            负数向前移（过去）移动，正数向后移（未来）移动
	 * 
	 * @return "MM月dd日"格式的字符串的List<String>
	 */
	public static List<String> getDatePositionStr(int n, int direction) {
		Calendar calendar = Calendar.getInstance();
		List<String> dateList = new ArrayList<String>();
		String dateStr;
		for (int i = 0; i <= n; i++) {
			dateStr = new StringBuffer().append(calendar.get(Calendar.MONTH) + 1).append("月")
					.append(calendar.get(Calendar.DAY_OF_MONTH)).append("日").toString();
			dateList.add(dateStr);
			calendar.add(Calendar.DATE, direction);// 让日期加变化

		}
		return dateList;
	}

	/**
	 * 获取当天及后（未来）n（几）天的日期String的list集合
	 * 
	 * @param n
	 *            后（未来）几天，即再向后（未来）推几天
	 * @param outFormat
	 *            指定的输出字符串格式
	 * @return 指定的字符串格式的的List<String>
	 */
	public static List<String> getDatePositionAfterStr(int n, String outFormat) {
		return getDatePositionStr(n, 1, outFormat);
	}

	/**
	 * 获取当天及前（过去）n（几）天的日期String的list集合
	 * 
	 * @param n
	 *            前（过去）几天，即再向前（过去）推几天
	 * @param outFormat
	 *            指定的输出字符串格式
	 * @return 指定的字符串格式的的List<String>
	 */
	public static List<String> getDatePositionBeforStr(int n, String outFormat) {

		return getDatePositionStr(n, -1, outFormat);
	}

	/**
	 * 获取当天的日期向前移（过去）或向后移（未来）的String的list集合，direction是每次移动的天数
	 * 
	 * @param n
	 *            推（移动）的次数
	 * @param direction
	 *            推（移动）的方向，向前移（过去）或向后移（未来）<br>
	 *            负数向前移（过去）移动，正数向后移（未来）移动
	 * @param outFormat
	 *            指定的输出字符串格式
	 * @return 指定的字符串格式的的List<String>
	 */
	public static List<String> getDatePositionStr(int n, int direction, String outFormat) {
		Calendar calendar = Calendar.getInstance();
		List<String> dateList = new ArrayList<String>();
		SimpleDateFormat dateFormat = newDateFormat(outFormat);
		String dateStr;
		for (int i = 0; i <= n; i++) {
			dateStr = dateFormat.format(calendar.getTime());
			dateList.add(dateStr);
			calendar.add(Calendar.DATE, direction);// 让日期加变化
		}
		return dateList;
	}

	/**
	 * 获取当天的日期向前移（过去）或向后移（未来）的Date的list集合，direction是每次移动的天数
	 * 
	 * @param n
	 *            推（移动）的次数
	 * @param direction
	 *            推（移动）的方向，向前移（过去）或向后移（未来）的天数<br>
	 *            负数向前移（过去）移动，正数向后移（未来）移动
	 * 
	 * @return List<String>
	 */
	public static List<Date> getDatePositionDate(int n, int direction) {
		Calendar calendar = Calendar.getInstance();
		List<Date> dateList = new ArrayList<Date>();
		for (int i = 0; i <= n; i++) {
			dateList.add(calendar.getTime());
			calendar.add(Calendar.DATE, direction);// 让日期加变化

		}
		return dateList;
	}

	/**
	 * 获取当天的日期向前移（过去）或向后移（未来）的Date
	 * 
	 * @param n
	 *            天数；负数向前移（过去）移动，正数向后移（未来）移动
	 * @return 移动后的Date
	 */
	public static Date getDatePosition(int n) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, n);
		return calendar.getTime();
	}

	/**
	 * 获取指定的日期向前移（过去）或向后移（未来）的Date
	 * 
	 * @param n
	 *            天数；负数向前移（过去）移动，正数向后移（未来）移动
	 * @param date
	 *            指定的日期
	 * @return 移动后的Date
	 */
	public static Date getDatePosition4date(int n, Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, n);
		return calendar.getTime();
	}

	/**
	 * 获得两个日期!!日!!之间的所有日期列表（包括起始日期和结束日期）
	 * 
	 * @param startShortYMD
	 *            "yyyyMMdd"格式的起始日期
	 * @param endShortYMD
	 *            "yyyyMMdd"格式的结束日期
	 * @return "yyyyMMdd"格式的日期列表
	 * @throws ParseException
	 */
	public static List<String> getBetweenDateDayList(String startShortYMD, String endShortYMD) throws ParseException {
		SimpleDateFormat dateFormat = newShortYMDFormat();
		Calendar startCal = Calendar.getInstance();
		startCal.setTime(dateFormat.parse(startShortYMD));

		Calendar endCal = Calendar.getInstance();
		endCal.setTime(dateFormat.parse(endShortYMD));

		List<String> dateList = new ArrayList<String>();
		while (startCal.before(endCal)) {
			dateList.add(dateFormat.format(startCal.getTime()));
			startCal.add(Calendar.DATE, 1);
		}

		if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
			dateList.add(dateFormat.format(endCal.getTime()));
		}

		return dateList;
	}

	/**
	 * 获得指定格式的两个日期！！日！！之间的所有日期列表（包括起始日期和结束日期）
	 * 
	 * @param startDateYMD
	 *            起始日期
	 * @param endDateYMD
	 *            结束日期
	 * @param format
	 *            指定的格式
	 * 
	 * @return "yyyyMMdd"格式的日期列表
	 * @throws ParseException
	 */
	public static List<String> getBetweenDateDayList(String startDateYMD, String endDateYMD, String format)
			throws ParseException {
		SimpleDateFormat dateFormat = newDateFormat(format);
		Calendar startCal = Calendar.getInstance();
		startCal.setTime(dateFormat.parse(startDateYMD));

		Calendar endCal = Calendar.getInstance();
		endCal.setTime(dateFormat.parse(endDateYMD));

		List<String> dateList = new ArrayList<String>();
		while (startCal.before(endCal)) {
			dateList.add(dateFormat.format(startCal.getTime()));
			startCal.add(Calendar.DATE, 1);
		}

		if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
			dateList.add(dateFormat.format(endCal.getTime()));
		}

		return dateList;
	}

	/**
	 * 获得两个日期!!月！！之间的所有日期列表（包括起始日期和结束日期）
	 * 
	 * @param startDateYM
	 *            "yyyyMM"格式的起始日期
	 * @param endDateYM
	 *            "yyyyMM"格式的结束日期
	 * @return "yyyyMM"格式的日期列表
	 * @throws ParseException
	 */
	public static List<String> getBetweenDateMonthList(String startDateYM, String endDateYM) throws ParseException {
		SimpleDateFormat dateFormat = newShortYMFormat();
		Calendar startCal = Calendar.getInstance();
		startCal.setTime(dateFormat.parse(startDateYM));

		Calendar endCal = Calendar.getInstance();
		endCal.setTime(dateFormat.parse(endDateYM));
		List<String> dateList = new ArrayList<String>();
		while (startCal.before(endCal)) {
			dateList.add(dateFormat.format(startCal.getTime()));
			startCal.add(Calendar.MONTH, 1);
		}

		if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
			dateList.add(dateFormat.format(endCal.getTime()));
		}

		return dateList;
	}

	/**
	 * 获得指定格式的两个日期！！月！！之间的所有日期列表（包括起始日期和结束日期）
	 * 
	 * @param startDateYM
	 *            起始日期
	 * @param endDateYM
	 *            结束日期
	 * @param format
	 *            指定的格式
	 * @return 指定的格式的日期列表
	 * @throws ParseException
	 */
	public static List<String> getBetweenDateMonthList(String startDateYM, String endDateYM, String format)
			throws ParseException {
		SimpleDateFormat dateFormat = newDateFormat(format);
		Calendar startCal = Calendar.getInstance();
		startCal.setTime(dateFormat.parse(startDateYM));

		Calendar endCal = Calendar.getInstance();
		endCal.setTime(dateFormat.parse(endDateYM));
		List<String> dateList = new ArrayList<String>();
		while (startCal.before(endCal)) {
			dateList.add(dateFormat.format(startCal.getTime()));
			startCal.add(Calendar.MONTH, 1);
		}

		if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
			dateList.add(dateFormat.format(endCal.getTime()));
		}

		return dateList;
	}

	/**
	 * 是否是闰年
	 */
	public static boolean isLeapYear(int year) {
		return ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0);
	}

	/**
	 * 
	 * 方法用途: 字符串的时间进行比较<br>
	 * 字符串时间格式必需是："yyyy-MM-dd"、yyyyMMdd、"yyyy-MM-dd HH:mm:ss"、"yyyy/MM/dd"中的一种,
	 * 两个日期格式可不同<br>
	 * 如果相等返回0，如果date2大于date1返回1，如果date2小于date1返回-1<br>
	 * 
	 * @param date1
	 *            时间1
	 * @param date2
	 *            时间2
	 * @return
	 * @throws Exception
	 */
	public static int compareData4string(String date1, String date2) throws Exception {
		long s = 0;
		if (8 == date1.length()) {
			s = newShortYMDFormat().parse(date1).getTime();
		} else if (10 == date1.length()) {
			if (date1.contains("/"))
				s = newShortYMDFormat_().parse(date1).getTime();
			else
				s = newLongYMDFormat().parse(date1).getTime();
		} else {
			s = newLongYMDHMSFormat().parse(date1).getTime();
		}
		long e = 0;
		if (8 == date2.length()) {
			e = newShortYMDFormat().parse(date2).getTime();
		} else if (10 == date2.length()) {
			if (date1.contains("/"))
				s = newShortYMDFormat_().parse(date2).getTime();
			else
				e = newLongYMDFormat().parse(date2).getTime();
		} else {
			e = newLongYMDHMSFormat().parse(date2).getTime();
		}
		if (e > s) {
			return 1;
		} else if (e < s) {
			return -1;
		}
		return 0;
	}

	/**
	 * 获取当前日期是星期几的数字<br>
	 * 
	 * @return 当前日期数 如：2
	 */
	public static int getWeekOfDateInt() {
		Calendar cal = Calendar.getInstance();
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		return w <= 0 ? 7 : w;
	}
	
	/**
	  * 得到本周周一,时分秒都为0了
	  * 
	  * @return yyyy-MM-dd 的Date
	  */
	 public static Date getWeekMondayOfThis() {
	  Calendar c = Calendar.getInstance();
	  int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
	  if (day_of_week == 0)
	   day_of_week = 7;
	  c.add(Calendar.DATE, -day_of_week + 1);
	  return formatOnlyYMD(c.getTime());
	 }

	 

	 /**
	  * 得到本周周日，时分秒都为0了
	  * 
	  * @return yyyy-MM-dd 的Date
	  */
	 public static Date getWeekSundayOfThis() {
	  Calendar c = Calendar.getInstance();
	  int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
	  if (day_of_week == 0)
	   day_of_week = 7;
	  c.add(Calendar.DATE, -day_of_week + 7);
	  return formatOnlyYMD(c.getTime());
	 }
	
	
	
	/**
	 * 获取当前日期是星期几<br>
	 * 
	 * @return 当前日期是星期几 如：星期二
	 */
	public static String getWeekOfDateString() {
		// String[] week级Days = { "7", "1", "2", "3", "4", "5", "6" };
		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance();
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		return weekDays[w];
	}

	/**
	 * 获取指定的日期是星期几的数字<br>
	 * 
	 * @param date
	 *            转入的日期
	 * @return 当前日期数 如：2
	 */
	public static int getWeekOfDateInt(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		return w <= 0 ? 7 : w;// weekDays[w];
	}

	/**
	 * 获取指定的日期是星期几<br>
	 * 
	 * @param date
	 *            转入的日期
	 * @return 当前日期是星期几 如：星期二
	 */
	public static String getWeekOfDateString(Date date) {
		// String[] week级Days = { "7", "1", "2", "3", "4", "5", "6" };
		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		return weekDays[w];
	}

	/**
	 * 返回上旬/中旬/下旬 1 ：上旬 2： 中旬 3： 下旬
	 * 
	 * @param date
	 * @return 1 | 2 | 3
	 */
	public static int getEarlyMidLate(Date date) {
		int day = getDay(date);
		int earlyMidLate = 0;
		if (1 <= day && day <= 10) {
			earlyMidLate = 1;
		}
		if (11 <= day && day <= 20) {
			earlyMidLate = 2;
		}
		if (20 < day) {
			earlyMidLate = 3;
		}
		return earlyMidLate;
	}

	/**
	 * 将日期转换成Julian日期，即为哪一年的第几天
	 * 
	 * @param date
	 * @return
	 */
	public static int dateToJulian(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR) % 100;
		int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
		return year * 1000 + dayOfYear;
	}

	/**
	 * 将Julian日期转化为date，即为哪一年的第几天
	 * 
	 * @param date
	 * @return
	 */
	public static Date JulianToDate(int date) {
		int year = (date / 1000) + 2000;
		int dayOfYear = date % 1000;
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.DAY_OF_YEAR, dayOfYear);
		return calendar.getTime();
	}

	/**
	 * 返回当前月份的第一天
	 * 
	 * @return Date
	 */
	public static Date getCurrentMonthFirstDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1); // 设置为1号，当前日期既为本月第一天
		return calendar.getTime();
	}

	/**
	 * 返回当前月份的最后一天
	 * 
	 * @return Date
	 */
	public static Date getCurrentMonthLastDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}

	/**
	 * 计算两个日期相差几天
	 * 
	 * @param startDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @return 相差天数（非绝对值）
	 */
	public static int calDateSubDay(Date startDate, Date endDate) {

		Calendar fromCalendar = Calendar.getInstance();
		fromCalendar.setTime(startDate);
		fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
		fromCalendar.set(Calendar.MINUTE, 0);
		fromCalendar.set(Calendar.SECOND, 0);
		fromCalendar.set(Calendar.MILLISECOND, 0);

		Calendar toCalendar = Calendar.getInstance();
		toCalendar.setTime(endDate);
		toCalendar.set(Calendar.HOUR_OF_DAY, 0);
		toCalendar.set(Calendar.MINUTE, 0);
		toCalendar.set(Calendar.SECOND, 0);
		toCalendar.set(Calendar.MILLISECOND, 0);
		Long tempString = (toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24);
		return Integer.valueOf(tempString.toString());
	}

	/**
	 * 时间加上（减去） 几小时 返回时间
	 * 
	 * @param date
	 *            被加上（减去）的时间
	 * @param hour
	 *            负数为减去，正数为加
	 * @return Date
	 */
	public static Date calDateSubHour(Date date, int hour) {
		if (hour == 0)
			return date;
		try {
			Calendar dar = Calendar.getInstance();
			dar.setTime(date);
			dar.add(java.util.Calendar.HOUR_OF_DAY, hour);
			return dar.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
