package com.cpw.service;

import java.util.List;

import com.cpw.pojo.LogInfo;
import com.cpw.pojo.UserInfo;

/**
 * 设置模块服务
 * 
 * @author pengwei_chen
 * @date 2014-4-22 下午1:37:35
 */
public interface ISetService {

	/**
	 * 根据用户编号获取用户信息
	 * 
	 * @param id
	 *            用户编号
	 * @return 用户信息
	 */
	UserInfo getUserInfoById(int id);

	/**
	 * 修改用户信息
	 * 
	 * @param userInfo
	 *            用户信息
	 */
	void updateUserInfo(UserInfo userInfo);

	/**
	 * 分页获取日志记录
	 * 
	 * @param pageNum
	 *            页数
	 * @param pageSize
	 *            每页记录数
	 * @return 日志记录列表
	 */
	List<LogInfo> getLogInfo(int pageNum, int pageSize);

	/**
	 * 获取日志条数
	 * 
	 * @return 日志条数
	 */
	int getLogCount();

	/**
	 * 根据日志编号删除日志
	 * 
	 * @param logId
	 *            日志编号
	 */
	void delLogById(int logId);
}
