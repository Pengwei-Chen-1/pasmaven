package com.cpw.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cpw.factory.ServiceFactory;
import com.cpw.pojo.FirstLevel;
import com.cpw.service.IFlexService;
import com.cpw.service.ITallyService;
import com.cpw.service.impl.FlexServiceImpl;
import com.cpw.service.impl.TallyServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 获取一级收入分类以及对应的金额
 * 
 * @author pengwei_chen
 * @date 2014-4-12 上午11:44:34
 */
@SuppressWarnings("serial")
public class GetLevelIncomeAction extends ActionSupport {

	ITallyService tallyService = (TallyServiceImpl) ServiceFactory.getService("tally");
	IFlexService flexService = (FlexServiceImpl)ServiceFactory.getService("flex");
	
	private Map<String, Object> incomeMap;

	public Map<String, Object> getIncomeMap() {
		return incomeMap;
	}

	public void setIncomeMap(Map<String, Object> incomeMap) {
		this.incomeMap = incomeMap;
	}

	/**
	 * 获取一级收入分类以及对应的金额
	 * 
	 * @return
	 */
	public String getFirstLevelIncome() {
		incomeMap = new HashMap<String, Object>();
		List<Object> resultList = new ArrayList<Object>();
		// 获取一级收入分类
		List<FirstLevel> firstIncomeList = tallyService.getAllIncomeFirstLevel();
		Map<String, String> tempMap;
		for (FirstLevel firstLevel : firstIncomeList) {
			tempMap = new HashMap<String, String>();
			double firstLevelMoney = flexService.getMoneyByFirstLevelId(firstLevel
					.getFirstLevelId());
			tempMap.put("name", firstLevel.getFirstLevelName());
			tempMap.put("money", firstLevelMoney + "");
			resultList.add(tempMap);
		}
		incomeMap.put("mapIncomeRes", resultList);
		return "incomeMap";
	}
}
