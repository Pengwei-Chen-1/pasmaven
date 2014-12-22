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
 * 获取一级分类的支出信息以及金额
 * 
 * @author pengwei_chen
 * @date 2014-4-13 上午10:46:55
 */
@SuppressWarnings("serial")
public class GetLevelSpendingAction extends ActionSupport {

	ITallyService tallyService = (TallyServiceImpl) ServiceFactory.getService("tally");
	IFlexService flexService = (FlexServiceImpl)ServiceFactory.getService("flex");
	
	private Map<String, Object> map;

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	/**
	 * 获取一级分类的支出信息以及金额
	 * 
	 * @return
	 */
	public String getFirstLevelSpending() {
		map = new HashMap<String, Object>();
		List<Object> resultList = new ArrayList<Object>();
		List<FirstLevel> firstLevelList = tallyService.getAllSpendingFirstLevel();
		Map<String, String> tempMap;
		for (FirstLevel firstLevel : firstLevelList) {
			tempMap = new HashMap<String, String>();
			double firstLevelMoney = flexService.getMoneyByFirstLevelId(firstLevel.getFirstLevelId());
			tempMap.put("name", firstLevel.getFirstLevelName());
			tempMap.put("money", firstLevelMoney + "");
			resultList.add(tempMap);
		}
		map.put("mapRes", resultList);
		return "map";
	}
}
