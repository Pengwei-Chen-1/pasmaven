package com.cpw.util;

import com.cpw.dao.ILogInfoDao;
import com.cpw.dao.impl.LogInfoDaoImpl;
import com.cpw.factory.DaoFactory;
import com.cpw.pojo.LogInfo;

/**
 * 日志操作类
 * 
 * @author pengwei_chen
 * @date 2014-4-22 下午3:15:43
 */
public class LogUtil {

	static ILogInfoDao logDao = (LogInfoDaoImpl) DaoFactory.getDao("logInfo");

	/**
	 * 添加操作日志
	 * 
	 * @param type
	 *            操作类型
	 * @param des
	 *            详细描述
	 */
	public static void addLog(String type, String des) {
		LogInfo log = new LogInfo();
		log.setOperateDes(des);
		log.setOperateType(type);
		log.setUserId(SysConstant.SYS_USER_ID);
		log.setOperateTime(DateUtil.getNowDate());
		logDao.addLogInfo(log);
	}
	
	/**
	 * 添加用户登录操作
	 */
	public static void addLogin(){
		LogInfo log = new LogInfo();
		log.setOperateDes("登录系统");
		log.setOperateType(SysConstant.LOG_TYPE_LOGIN);
		log.setUserId(SysConstant.SYS_USER_ID);
		log.setOperateTime(DateUtil.getNowDate());
		logDao.addLogInfo(log);
	}
	
	/**
	 * 添加用户退出操作
	 */
	public static void addLogout(){
		LogInfo log = new LogInfo();
		log.setOperateDes("退出系统");
		log.setOperateType(SysConstant.LOG_TYPE_LOGOUT);
		log.setUserId(SysConstant.SYS_USER_ID);
		log.setOperateTime(DateUtil.getNowDate());
		logDao.addLogInfo(log);
	}
}
