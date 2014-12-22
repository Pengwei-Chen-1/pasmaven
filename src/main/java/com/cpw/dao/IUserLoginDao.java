package com.cpw.dao;

import com.cpw.pojo.UserLogin;

/**
 * 用户登录Dao
 * 
 * @author pengwei_chen
 * @date 2014-3-18 下午5:41:15
 */
public interface IUserLoginDao {

	/**
	 * 根据用户登录名获取用户密码
	 * 
	 * @param userName
	 *            用户登录名
	 * @return 用户密码
	 */
	String getPassByName(String userName);
	
	/**
	 * 根据用户名获取用户登录信息
	 * @param userName 用户名
	 * @return 用户信息
	 */
	UserLogin getUserLoginByName(String userName);

	/**
	 * 添加用户登录信息
	 * 
	 * @param userLogin
	 *            用户登录信息
	 * @return 影响行数
	 */
	int addUserLogin(UserLogin userLogin);

	/**
	 * 更新用户登录信息
	 * 
	 * @param userLogin
	 *            用户登录信息
	 * @return 影响行数
	 */
	int updateUserLogin(UserLogin userLogin);

	/**
	 * 删除用户登录信息
	 * 
	 * @param userLogin
	 *            用户登录信息
	 * @return 影响行数
	 */
	int delUserLogin(UserLogin userLogin);
}
