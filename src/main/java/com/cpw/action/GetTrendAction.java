package com.cpw.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cpw.factory.ServiceFactory;
import com.cpw.service.IFlexService;
import com.cpw.service.ITallyService;
import com.cpw.service.impl.FlexServiceImpl;
import com.cpw.service.impl.TallyServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class GetTrendAction extends ActionSupport {

	ITallyService tallyService = (TallyServiceImpl) ServiceFactory.getService("tally");
	IFlexService flexService = (FlexServiceImpl) ServiceFactory.getService("flex");

	private Map<String, Object> trendMap;

	public Map<String, Object> getTrendMap() {
		return trendMap;
	}

	public void setTrendMap(Map<String, Object> trendMap) {
		this.trendMap = trendMap;
	}

	public String getTrend() {
		trendMap = new HashMap<String, Object>();
		Map<String, String> spendingMap = flexService.getSpendingMoneyByMonth(2014);
		Map<String, String> incomeMap = flexService.getIncomeMoneyByMonth(2014);
		List<Object> resultList = new ArrayList<Object>();
		Map<String, String> tempMap;
		for (String spendingKey : spendingMap.keySet()) {
			for (String incomeKey : incomeMap.keySet()) {
				if (spendingKey.equals(incomeKey)) {
					tempMap = new HashMap<String, String>();
					tempMap.put("month", incomeKey);
					tempMap.put("spendingM", spendingMap.get(spendingKey));
					tempMap.put("incomeM", incomeMap.get(incomeKey));
					resultList.add(tempMap);
				}
			}
		}
		trendMap.put("trendRes", sortList(resultList));
		return "trendMap";
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
}
