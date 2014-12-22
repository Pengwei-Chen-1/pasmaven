package com.cpw.service;

import java.util.List;
import java.util.Map;

import com.cpw.pojo.Family;
import com.cpw.pojo.SecondLevel;

/**
 * 和flex交互的Service
 * 
 * @author pengwei_chen
 * @date 2014-4-11 下午6:00:35
 */
public interface IFlexService {

	/**
	 * 根据一级分类的编号获取二级分类列表
	 * 
	 * @param firstLevelId
	 *            一级分类的编号
	 * @return 二级分类列表
	 */
	List<SecondLevel> getSecondListByFirstId(int firstLevelId);

	/**
	 * 根据一级分类编号获取对应的分类所有的金额
	 * 
	 * @param firstLevelId
	 *            一级分类编号
	 * @return 金额
	 */
	double getMoneyByFirstLevelId(int firstLevelId);

	/**
	 * 根据年份获取对应的每月的支出金额
	 * 
	 * @param year
	 *            年份
	 * @return 每月对应的支出金额
	 */
	Map<String, String> getSpendingMoneyByMonth(int year);

	/**
	 * 根据年份获取对应的每月的收入金额
	 * 
	 * @param year
	 *            年份
	 * @return 每月对应的收入金额
	 */
	Map<String, String> getIncomeMoneyByMonth(int year);

	/**
	 * 根据成员列表获取对应成员的收支信息
	 * 
	 * @param memberList
	 *            成员列表
	 * @return 成员及其对应的收支信息
	 */
	List<Object> getMemberMoney(List<Family> memberList);
}
