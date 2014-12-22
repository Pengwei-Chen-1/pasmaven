package com.cpw.service;

import com.cpw.pojo.FirstLevel;
import com.cpw.pojo.UserInfo;
import com.cpw.pojo.UserLogin;

/**
 * 用户服务类
 * 
 * @author pengwei_chen
 * @date 2014-3-25 下午2:47:04
 */
public interface IUserService {

	/**
	 * 根据用户名获取用户登录信息
	 * 
	 * @param userName
	 *            用户登录名
	 * @return 用户登录信息
	 */
	UserLogin getUserLoginByName(String userName);

	/**
	 * 根据用户名获取用户信息
	 * 
	 * @param userName
	 *            用户名
	 * @return 用户信息
	 */
	UserInfo getUserInfoByName(String userName);

	/**
	 * 添加用户信息和用户登录信息
	 * 
	 * @param userLogin
	 *            用户登录信息
	 */
	void addUserInfoAndLogin(UserLogin userLogin);

	/**
	 * 修改用户登录信息
	 * 
	 * @param userLogin
	 *            用户登录信息
	 */
	void updateUserLogin(UserLogin userLogin);

	/**
	 * 添加一级分类
	 * 
	 * @param name
	 *            分类名称
	 * @param ioType
	 *            收支类型
	 */
	void addFirstLevel(String name, String ioType);

	/**
	 * 添加二级分类
	 * 
	 * @param name
	 *            分类名称
	 * @param first
	 *            一级分类
	 */
	void addSecondLevel(String name, FirstLevel first);

	/**
	 * 添加商家
	 * 
	 * @param name
	 *            商家名称
	 */
	void addBusiness(String name);

	/**
	 * 添加项目
	 * @param name 项目名称
	 */
	void addProject(String name);

	/**
	 * 添加家庭成员
	 * @param name 成员名称
	 */
	void addFamily(String name);

	/**
	 * 添加账户
	 * @param name 账户名称
	 */
	void addAcount(String name);

}
