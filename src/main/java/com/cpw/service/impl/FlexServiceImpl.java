package com.cpw.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cpw.dao.IFirstLevelDao;
import com.cpw.dao.IIncomeDao;
import com.cpw.dao.ISecondLevelDao;
import com.cpw.dao.ISpendingDao;
import com.cpw.dao.impl.FirstLevelDaoImpl;
import com.cpw.dao.impl.IncomeDaoImpl;
import com.cpw.dao.impl.SecondLevelDaoImpl;
import com.cpw.dao.impl.SpendingDaoImpl;
import com.cpw.factory.DaoFactory;
import com.cpw.pojo.Family;
import com.cpw.pojo.Income;
import com.cpw.pojo.SecondLevel;
import com.cpw.pojo.Spending;
import com.cpw.service.IFlexService;

public class FlexServiceImpl implements IFlexService {

	IFirstLevelDao firstLevelDao = (FirstLevelDaoImpl) DaoFactory.getDao("firstLevel");
	ISecondLevelDao secondLevelDao = (SecondLevelDaoImpl) DaoFactory.getDao("secondLevel");
	ISpendingDao spendingDao = (SpendingDaoImpl) DaoFactory.getDao("spending");
	IIncomeDao incomeDao = (IncomeDaoImpl) DaoFactory.getDao("income");

	@Override
	public List<SecondLevel> getSecondListByFirstId(int firstLevelId) {
		List<SecondLevel> allSecondList = secondLevelDao.getAllSecondLevel();
		List<SecondLevel> resultList = new ArrayList<SecondLevel>();
		for (SecondLevel secondLevel : allSecondList) {
			if (secondLevel.getFirstLevelId() == firstLevelId) {
				resultList.add(secondLevel);
			}
		}
		return resultList;
	}

	@Override
	public double getMoneyByFirstLevelId(int firstLevelId) {
		double resultMoney = 0;
		List<Spending> allSpending = new ArrayList<Spending>();
		List<Income> allIncom = new ArrayList<Income>();
		allSpending = spendingDao.getAllSpending();
		allIncom = incomeDao.getAllIncome();
		for (Spending spending : allSpending) {
			if (spending.getFirstLevelId() == firstLevelId) {
				resultMoney += spending.getMoney();
			}
		}
		for (Income income : allIncom) {
			if (income.getFirstLevelId() == firstLevelId) {
				resultMoney += income.getMoney();
			}
		}
		return resultMoney;
	}

	@Override
	public Map<String, String> getSpendingMoneyByMonth(int year) {
		// 存放月份和对应的金额
		Map<String, String> tempMap = new HashMap<String, String>();
		// 存放改月的金额
		double mouthMoney;
		// 创建一个日历
		Calendar cal = Calendar.getInstance();
		// 获取当年月份
		int tempMonth = cal.get(Calendar.MONTH) + 1;
		// 获取所有支出
		List<Spending> allSpending = new ArrayList<Spending>();
		allSpending = spendingDao.getAllSpending();
		for (int i = 1; i <= tempMonth; i++) {
			mouthMoney = 0;
			for (Spending spending : allSpending) {
				Date tempDate = spending.getCreateTime();
				cal.setTime(tempDate);
				if (i == (cal.get(Calendar.MONTH) + 1) && year == (cal.get(Calendar.YEAR))) {
					mouthMoney += spending.getMoney();
				}
			}
			// 将当月月份和对应的支出金额放入map
			tempMap.put("" + i, "" + mouthMoney);
		}
		return tempMap;
	}

	@Override
	public Map<String, String> getIncomeMoneyByMonth(int year) {
		// 存放月份和对应的金额
		Map<String, String> tempMap = new HashMap<String, String>();
		// 存放改月的金额
		double mouthMoney;
		// 创建一个日历
		Calendar cal = Calendar.getInstance();
		// 获取当年月份
		int tempMonth = cal.get(Calendar.MONTH) + 1;
		// 获取所有支出
		List<Income> allIncome = new ArrayList<Income>();
		allIncome = incomeDao.getAllIncome();
		for (int i = 1; i <= tempMonth; i++) {
			mouthMoney = 0;
			for (Income income : allIncome) {
				Date tempDate = income.getCreateTime();
				cal.setTime(tempDate);
				if (i == (cal.get(Calendar.MONTH) + 1) && year == (cal.get(Calendar.YEAR))) {
					mouthMoney += income.getMoney();
				}
			}
			// 将当月月份和对应的支出金额放入map
			tempMap.put("" + i, "" + mouthMoney);
		}
		return tempMap;
	}

	@Override
	public List<Object> getMemberMoney(List<Family> memberList) {
		List<Object> resultList = new ArrayList<Object>();
		// 获取所有支出
		List<Spending> allSpending = new ArrayList<Spending>();
		allSpending = spendingDao.getAllSpending();
		// 获取所有支出
		List<Income> allIncome = new ArrayList<Income>();
		allIncome = incomeDao.getAllIncome();
		// 存放成员的支出的金额
		double memberOMoney;
		// 存放成员的收入的金额
		double memberIMoney;
		for (Family member : memberList) {
			int memberId = member.getMemberId();
			String memberName = member.getMemberName();
			memberOMoney = 0;
			memberIMoney = 0;
			for (Spending spending : allSpending) {
				if (spending.getMemberId() == memberId) {
					memberOMoney += spending.getMoney();
				}
			}

			for (Income income : allIncome) {
				if (income.getMemberId() == memberId) {
					memberIMoney += income.getMoney();
				}
			}
			Map<String, String> tempMap = new HashMap<String, String>();
			tempMap.put("memberName", memberName);
			tempMap.put("memberO", memberOMoney + "");
			tempMap.put("memberI", memberIMoney + "");
			resultList.add(tempMap);
		}
		return resultList;
	}

}
