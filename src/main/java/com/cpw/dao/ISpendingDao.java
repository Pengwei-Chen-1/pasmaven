package com.cpw.dao;

import java.util.List;

import com.cpw.pojo.Spending;

/**
 * 支出Dao
 * 
 * @author pengwei_chen
 * @date 2014-3-19 上午10:39:27
 */
public interface ISpendingDao {

	/**
	 * 获取全部支出信息
	 * 
	 * @return 支出信息列表
	 */
	List<Spending> getAllSpending();

	/**
	 * 根据支出编号获取支出信息
	 * 
	 * @param spendingId
	 *            支出编号
	 * @return 支出信息
	 */
	Spending getSpendingById(int spendingId);

	/**
	 * 添加支出信息
	 * 
	 * @param spending
	 *            支出信息
	 * @return 影响行数
	 */
	int addSpending(Spending spending);

	/**
	 * 修改支出信息
	 * 
	 * @param spending
	 *            支出信息
	 * @return 影响行数
	 */
	int updateSpending(Spending spending);

	/**
	 * 删除支出信息
	 * 
	 * @param spending
	 *            支出信息
	 * @return 影响行数
	 */
	int delSpending(Spending spending);

	/**
	 * 根据条件获取支出信息
	 * 
	 * @param term
	 *            条件
	 * @return 支出信息
	 */
	List<Spending> getSpendingByTerm(String term);
}
