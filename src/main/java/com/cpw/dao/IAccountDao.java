package com.cpw.dao;

import java.util.List;

import com.cpw.pojo.Account;

/**
 * 账户管理Dao类
 * 
 * @author pengwei_chen
 * @date 2014-3-18 下午5:48:12
 */
public interface IAccountDao {

	/**
	 * 获取全部账户信息
	 * 
	 * @return list 账户信息列表
	 */
	List<Account> getAllAccount();
	
	/**
	 * 根据账户编号获取账户信息
	 * @param accountId 账户编号
	 * @return 账户信息
	 */
	Account getAccountById(int accountId);

	/**
	 * 添加账户信息
	 * 
	 * @param account 账户信息
	 * @return 影响行数
	 */
	int addAccount(Account account);

	/**
	 * 修改账户信息
	 * 
	 * @param account 账户信息
	 * @return 影响行数
	 */
	int updateAccount(Account account);

	/**
	 * 删除账户信息
	 * 
	 * @param account 账户信息
	 * @return 影响行数
	 */
	int deleteAccount(Account account);
}
