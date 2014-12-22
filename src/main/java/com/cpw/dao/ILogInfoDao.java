package com.cpw.dao;

import java.util.List;

import com.cpw.pojo.LogInfo;

/**
 * 日志管理Dao
 * 
 * @author pengwei_chen
 * @date 2014-3-19 上午9:55:23
 */
public interface ILogInfoDao {

	/**
	 * 获取全部日志信息
	 * 
	 * @return list 日志列表
	 */
	List<LogInfo> getAllLog();

	/**
	 * 添加操作日志
	 * 
	 * @param logInfo
	 *            日志信息
	 * @return 影响行数
	 */
	int addLogInfo(LogInfo logInfo);

	/**
	 * 删除操作日志
	 * 
	 * @param logInfo
	 *            日志信息
	 * @return 影响行数
	 */
	int delLogInfoById(LogInfo logInfo);

	/**
	 * 分页获取日志信息
	 * 
	 * @param firstResult
	 *            查询的起始记录
	 * @param pageSize
	 *            查询的记录条数
	 * @return
	 */
	List<LogInfo> getLogInfo(int firstResult, int pageSize);

	/**
	 * 获取日志总条数
	 * 
	 * @return 日志总条数
	 */
	int getCount();
}
