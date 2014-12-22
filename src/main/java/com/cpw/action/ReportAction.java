package com.cpw.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cpw.factory.ServiceFactory;
import com.cpw.pojo.Family;
import com.cpw.pojo.FirstLevel;
import com.cpw.service.IFlexService;
import com.cpw.service.ITallyService;
import com.cpw.service.impl.FlexServiceImpl;
import com.cpw.service.impl.TallyServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 报表Action
 * 
 * @author pengwei_chen
 * @date 2014-3-30 下午12:16:08
 */
@SuppressWarnings("serial")
public class ReportAction extends ActionSupport {

	ITallyService tallyService = (TallyServiceImpl) ServiceFactory.getService("tally");
	IFlexService flexService = (FlexServiceImpl)ServiceFactory.getService("flex");
	
	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String init() {
		setMsg("report");
		// 一级支出分类以及对应的金额列表
		List<Object> firstSpendingMoneyList = new ArrayList<Object>();
		// 一级收入分类以及对应的金额列表
		List<Object> firstIncomeMoneyList = new ArrayList<Object>();
		// 获取一级支出分类
		List<FirstLevel> firstSpendingLevelList = tallyService.getAllSpendingFirstLevel();
		// 获取一级收入分类
		List<FirstLevel> firstIncomeList = tallyService.getAllIncomeFirstLevel();
		Map<String, String> tempMap;
		// 所有的支出金额
		double allSpendingMoney = 0;
		double allIncomeMoney = 0;
		// 获取一级支出分类以及对应的金额
		for (FirstLevel firstLevel : firstSpendingLevelList) {
			tempMap = new HashMap<String, String>();
			double firstLevelMoney = flexService.getMoneyByFirstLevelId(firstLevel
					.getFirstLevelId());
			tempMap.put("name", firstLevel.getFirstLevelName());
			tempMap.put("money", firstLevelMoney + "");
			firstSpendingMoneyList.add(tempMap);
			allSpendingMoney += firstLevelMoney;
		}
		// 获取一级收入分类以及对应的金额
		for (FirstLevel firstLevel : firstIncomeList) {
			tempMap = new HashMap<String, String>();
			double firstLevelMoney = flexService.getMoneyByFirstLevelId(firstLevel
					.getFirstLevelId());
			tempMap.put("name", firstLevel.getFirstLevelName());
			tempMap.put("money", firstLevelMoney + "");
			firstIncomeMoneyList.add(tempMap);
			allIncomeMoney += firstLevelMoney;
		}
		// 获取每月的月份以及对应的收支金额
		List<Object> monthMoneyList = getMonthMoney();
		// 获取成员以及对应的收支信息
		List<Object> memberMoneyList = getMemberMoney();
		
		ActionContext context = ActionContext.getContext();
		context.put("firstSpendingMoneyList", firstSpendingMoneyList);
		context.put("allSpendingMoney", allSpendingMoney);
		context.put("firstIncomeMoneyList", firstIncomeMoneyList);
		context.put("allIncomeMoney", allIncomeMoney);
		context.put("monthMoneyList", monthMoneyList);
		context.put("memberMoneyList", memberMoneyList);
		return SUCCESS;
	}
	
	/**
	 * 获取每月的月份以及对应的收支金额
	 * @return
	 */
	private List<Object> getMonthMoney(){
		List<Object> resultList = new ArrayList<Object>();
		Map<String, String> spendingMap = flexService.getSpendingMoneyByMonth(2014);
		Map<String ,String> incomeMap = flexService.getIncomeMoneyByMonth(2014);
		Map<String, String> tempMap;
		for (String spendingKey : spendingMap.keySet()) {
			for (String incomeKey : incomeMap.keySet()) {
				if(spendingKey.equals(incomeKey)){
					tempMap = new HashMap<String, String>();
					tempMap.put("month", incomeKey);
					tempMap.put("spendingM", spendingMap.get(spendingKey));
					tempMap.put("incomeM", incomeMap.get(incomeKey));
					resultList.add(tempMap);
				}
			}
		}
		return sortList(resultList);
	}
	
	/**
	 * 将list排序
	 * 
	 * @param list
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<Object> sortList(List<Object> list) {
		List<Object> resList = new ArrayList<Object>();
		for (int i = 1; i <= list.size(); i++) {
			for (Object obj : list) {
				Map<String, String> tempMap = (Map<String, String>) obj;
				int month = Integer.parseInt(tempMap.get("month"));
				if (month == i) {
					resList.add(obj);
				}
			}
		}
		return resList;
	}
	
	/**
	 * 获取成员以及对应的收支信息
	 * @return
	 */
	private List<Object> getMemberMoney(){
		List<Family> memberList = tallyService.getAllMember();
		List<Object> resultList = new ArrayList<Object>();
		resultList = flexService.getMemberMoney(memberList);
		return resultList;
	}
}
