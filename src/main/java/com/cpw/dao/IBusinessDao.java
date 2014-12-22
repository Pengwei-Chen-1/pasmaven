package com.cpw.dao;

import java.util.List;

import com.cpw.pojo.Business;

/**
 * 商家管理Dao
 * 
 * @author pengwei_chen
 * @date 2014-3-19 上午9:51:40
 */
public interface IBusinessDao {

	/**
	 * 获取全部商家信息
	 * 
	 * @return 商家信息列表
	 */
	List<Business> getAllBusiness();

	/**
	 * 根据商家编号获取商家信息
	 * @param businessId 商家编号
	 * @return 商家信息
	 */
	Business getBusinessById(int businessId);

	/**
	 * 添加商家信息
	 * @param business 商家信息
	 * @return 影响行数
	 */
	int addBusiness(Business business);

	/**
	 * 修改商家信息
	 * @param business 商家信息
	 * @return 影响行数
	 */
	int updateBusiness(Business business);

	/**
	 * 删除商家信息
	 * @param business 商家信息
	 * @return 影响行数
	 */
	int delBusiness(Business business);
}
