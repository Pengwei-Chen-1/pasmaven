package com.cpw.dao;

import java.util.List;

import com.cpw.pojo.Income;

/**
 * 收入管理Dao
 * 
 * @author pengwei_chen
 * @date 2014-3-19 上午9:54:15
 */
public interface IIncomeDao {

	/**
	 * 获取全部收入信息
	 * 
	 * @return 收入信息列表
	 */
	List<Income> getAllIncome();

	/**
	 * 根据收入编号获取收入信息
	 * 
	 * @param incomeId
	 *            收入编号
	 * @return 收入信息
	 */
	Income getIncomeById(int incomeId);

	/**
	 * 添加收入信息
	 * 
	 * @param income
	 *            收入信息
	 * @return 影响行数
	 */
	int addIncome(Income income);

	/**
	 * 修改收入信息
	 * 
	 * @param income
	 *            收入信息
	 * @return 影响行数
	 */
	int updateIncome(Income income);

	/**
	 * 删除收入信息
	 * 
	 * @param income
	 *            收入信息
	 * @return 影响行数
	 */
	int delIncome(Income income);

	/**
	 * 根据条件获取收入信息
	 * 
	 * @param term
	 *            条件
	 * @return 收入信息
	 */
	List<Income> getIncomeByTerm(String term);
}
