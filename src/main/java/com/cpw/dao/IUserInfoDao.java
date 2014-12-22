package com.cpw.dao;

import java.util.List;

import com.cpw.pojo.UserInfo;

/**
 * 用户信息Dao
 * 
 * @author pengwei_chen
 * @date 2014-3-12 下午4:27:49
 */
public interface IUserInfoDao {

	/**
	 * 获取全部用户信息
	 * 
	 * @return 用户信息列表
	 */
	List<UserInfo> getAllUser();

	/**
	 * 分页查询用户信息
	 * 
	 * @param pageNum
	 *            页码
	 * @param size
	 *            每页记录数
	 * @param term
	 *            条件
	 * @return
	 */
	List<UserInfo> getUserInfoByPage(int pageNum, int size, String term);

	/**
	 * 根据用户名获取用户信息
	 * @param userName 用户名
	 * @return 用户信息
	 */
	UserInfo getUserByUserName(String userName);

	/**
	 * 根据用户编号获取用户信息
	 * 
	 * @param userId
	 *            用户编号
	 * @return 用户信息列表
	 */
	UserInfo getUserById(int userId);

	/**
	 * 添加用户信息
	 * 
	 * @param user
	 *            用户信息
	 * @return 影响行数
	 */
	int addUserInfo(UserInfo user);

	/**
	 * 删除用户
	 * 
	 * @param user
	 *            用户
	 * @return 影响行数
	 */
	int delUser(UserInfo user);

	/**
	 * 更新用户信息
	 * 
	 * @param user
	 *            用户信息
	 * @return 影响行数
	 */
	int updateUserInfo(UserInfo user);
}
