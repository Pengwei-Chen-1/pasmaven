package com.cpw.service;

import java.util.List;

import com.cpw.pojo.Account;
import com.cpw.pojo.Business;
import com.cpw.pojo.Family;
import com.cpw.pojo.FirstLevel;
import com.cpw.pojo.Income;
import com.cpw.pojo.Project;
import com.cpw.pojo.SecondLevel;
import com.cpw.pojo.Spending;
import com.cpw.pojo.Template;

/**
 * 记账类服务
 * 
 * @author pengwei_chen
 * @date 2014-4-2 下午2:07:42
 */
public interface ITallyService {

	/**
	 * 获取全部一级分类信息
	 * 
	 * @return list
	 */
	List<FirstLevel> getAllFirstLevel();

	/**
	 * 获得所有支出的一级分类
	 * 
	 * @return list
	 */
	List<FirstLevel> getAllSpendingFirstLevel();

	/**
	 * 获得所有收入的一级分类
	 * 
	 * @return list
	 */
	List<FirstLevel> getAllIncomeFirstLevel();

	/**
	 * 获取全部二级分类
	 * 
	 * @return list
	 */
	List<SecondLevel> getAllSecondLevel();

	/**
	 * 获得所有支出的二级分类
	 * 
	 * @return list
	 */
	List<SecondLevel> getAllSpendingSecondLevel();

	/**
	 * 获得所有收入的二级分类
	 * 
	 * @return list
	 */
	List<SecondLevel> getAllIncomeSecondLevel();

	/**
	 * 获取全部账户信息
	 * 
	 * @return list
	 */
	List<Account> getAllAccount();

	/**
	 * 获取全部成员信息
	 * 
	 * @return list
	 */
	List<Family> getAllMember();

	/**
	 * 获取全部项目信息
	 * 
	 * @return list
	 */
	List<Project> getAllProject();

	/**
	 * 获取全部商家信息
	 * 
	 * @return list
	 */
	List<Business> getAllBusiness();

	/**
	 * 获取全部模板信息
	 * 
	 * @return list
	 */
	List<Template> getAllTemplate();

	/**
	 * 添加一级分类
	 * 
	 * @param firstLevel
	 *            一级分类信息
	 */
	void addFirstLevel(FirstLevel firstLevel);

	/**
	 * 添加账户信息
	 * 
	 * @param account
	 *            账户信息
	 */
	void addAccount(Account account);

	/**
	 * 添加项目信息
	 * 
	 * @param project
	 *            项目信息
	 */
	void addProject(Project project);

	/**
	 * 添加商家信息
	 * 
	 * @param business
	 *            商家信息
	 */
	void addBusiness(Business business);

	/**
	 * 添加家庭成员
	 * 
	 * @param family
	 *            家庭成员信息
	 */
	void addMember(Family family);

	/**
	 * 添加二级分类
	 * 
	 * @param secondLevel
	 *            二级分类信息
	 */
	void addSecondLevel(SecondLevel secondLevel);

	/**
	 * 根据一级分类的编号获取二级分类列表
	 * 
	 * @param firstLevelId
	 *            一级分类的编号
	 * @return 二级分类列表
	 */
	List<SecondLevel> getSecondListByFirstId(int firstLevelId);

	/**
	 * 添加支出信息
	 * 
	 * @param spending
	 *            支出信息
	 */
	void addSpending(Spending spending);

	/**
	 * 添加收入信息
	 * 
	 * @param income
	 *            收入信息
	 */
	void addIncome(Income income);

	/**
	 * 根据编号获取模板信息
	 * 
	 * @param id
	 *            模板编号
	 * @return 模板信息
	 */
	Template getTemplateById(int id);

	/**
	 * 根据条件查询支出信息
	 * 
	 * @param term
	 *            条件
	 * @return 支出信息
	 */
	List<Spending> getSpendingByTerm(String term);

	/**
	 * 根据条件查询收入信息
	 * 
	 * @param term
	 *            条件
	 * @return 收入信息
	 */
	List<Income> getIncomeByTerm(String term);

	/**
	 * 修改收支信息
	 * 
	 * @param obj
	 */
	void saveTally(Object obj);
}
