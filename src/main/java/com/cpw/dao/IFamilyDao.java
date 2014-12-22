package com.cpw.dao;

import java.util.List;

import com.cpw.pojo.Family;

/**
 * 家庭成员管理Dao
 * @author pengwei_chen
 * @date 2014-3-19 上午9:52:23
 */
public interface IFamilyDao {

	/**
	 * 获取全部家庭成员信息
	 * @return 成员列表
	 */
	List<Family> getAllFamily();
	
	/**
	 * 根据成员编号获取家庭成员信息
	 * @param memberId 成员编号
	 * @return 成员信息
	 */
	Family getFamilyById(int memberId);
	
	/**
	 * 添加家庭成员
	 * @param family 家庭成员信息
	 * @return 影响行数
	 */
	int addFamily(Family family);
	
	/**
	 * 修改家庭成员信息
	 * @param family 家庭成员信息
	 * @return 影响行数
	 */
	int updateFamily(Family family);
	
	/**
	 * 删除家庭成员信息
	 * @param family 家庭成员信息
	 * @return 影响行数
	 */
	int delFamily(Family family);
}
