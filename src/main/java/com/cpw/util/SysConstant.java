package com.cpw.util;

/**
 * 全局静态常量类
 * 
 * @author pengwei_chen
 * @date 2014-4-5 下午2:02:17
 */
public class SysConstant {

	// 用户名
	public static String SYS_USER_NAME = "";
	// 用户编号
	public static int SYS_USER_ID = 0;
	// 收支类型-收入
	public static String IOTYPE_I = "I";
	// 收支类型-支出
	public static String IOTYPE_O = "O";
	// 全局用户名text
	public static String SYS_NAME_TEXT = "pas_userName";
	// 用户昵称text
	public static String SYS_NICK_TEXT = "nickName";
	// 本月收支记录
	public static String TALLY_MONTH_TEXT = "fb-month";
	// 本年收支记录
	public static String TALLY_YEAR_TEXT = "fb-year";
	// 全部收支记录
	public static String TALLY_ALL_TEXT = "fb-all";
	// 日志操作类型--登录
	public static String LOG_TYPE_LOGIN = "I";
	// 日志操作类型--退出
	public static String LOG_TYPE_LOGOUT = "O";
	// 日志操作类型--添加
	public static String LOG_TYPE_ADD = "A";
	// 日志操作类型--修改
	public static String LOG_TYPE_UPDATE = "U";
	// 日志操作类型--删除
	public static String LOG_TYPE_DEL = "D";
	// 分页显示时每页的记录数
	public static int PAGE_SIZE = 10;
}
