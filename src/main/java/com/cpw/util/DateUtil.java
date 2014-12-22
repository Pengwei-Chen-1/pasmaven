package com.cpw.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 * 
 * @author pengwei_chen
 * @date 2014-3-2 下午3:40:38
 */
public class DateUtil {

	/**
	 * 获取当前时间的字符串
	 * 
	 * @return 系统当前时间的字符串
	 */
	public static String getNowStr() {
		String strNow = "";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		strNow = df.format(new Date());// new Date()为获取当前系统时间
		return strNow;
	}

	/**
	 * 获取当前时间格式化后的Date类型
	 * 
	 * @return 系统当前时间的Date类型
	 */
	public static Date getNowDate() {
		Date date = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		try {
			String strNow = df.format(new Date());
			date = df.parse(strNow);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 将日期格式化为 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 *            日期
	 * @return 格式化后的字符串
	 */
	public static String dateFormat(Date date) {
		String strDate = "";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		strDate = df.format(date);
		return strDate;
	}

	/**
	 * 获取对应日期的天数
	 * 
	 * @param date
	 *            日期
	 * @return 天数
	 */
	public static String getDateDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_MONTH) + "";
	}

	/**
	 * 获取对应日期的月份
	 * 
	 * @param date
	 *            日期
	 * @return 月份
	 */
	public static String getDateMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MONTH) + 1 + "";
	}

	/**
	 * 获取对应日期的年份
	 * 
	 * @param date
	 *            日期
	 * @return 年份
	 */
	public static String getDateYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR) + "";
	}

	/**
	 * 获取对应日期的字符串
	 * 
	 * @param date
	 *            日期
	 * @return yyyy.MM.dd
	 */
	public static String getNowDateStr(Date date) {
		String str = "";
		SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd");// 设置日期格式
		str = df.format(date);
		return str;
	}

	/**
	 * 判断两个日期是否为同一天
	 * 
	 * @param day1
	 *            日期1
	 * @param day2
	 *            日期2
	 * @return
	 */
	public static boolean isSameDay(Date day1, Date day2) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String ds1 = sdf.format(day1);
		String ds2 = sdf.format(day2);
		if (ds1.equals(ds2)) {
			return true;
		} else {
			return false;
		}
	}
}
