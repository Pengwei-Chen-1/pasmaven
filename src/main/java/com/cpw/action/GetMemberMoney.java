package com.cpw.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cpw.factory.ServiceFactory;
import com.cpw.pojo.Family;
import com.cpw.service.IFlexService;
import com.cpw.service.ITallyService;
import com.cpw.service.impl.FlexServiceImpl;
import com.cpw.service.impl.TallyServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 获取成员收支
 * 
 * @author pengwei_chen
 * @date 2014-4-14 上午8:42:23
 */
@SuppressWarnings("serial")
public class GetMemberMoney extends ActionSupport {

	ITallyService tallyService = (TallyServiceImpl) ServiceFactory.getService("tally");
	IFlexService flexService = (FlexServiceImpl) ServiceFactory.getService("flex");

	private Map<String, Object> memberMoneyMap;

	public Map<String, Object> getMemberMoneyMap() {
		return memberMoneyMap;
	}

	public void setMemberMoneyMap(Map<String, Object> memberMoneyMap) {
		this.memberMoneyMap = memberMoneyMap;
	}

	/**
	 * 获取成员以及对应的收支信息
	 * 
	 * @return
	 */
	public String getMemberMoney() {
		memberMoneyMap = new HashMap<String, Object>();
		List<Family> memberList = tallyService.getAllMember();
		List<Object> resultList = new ArrayList<Object>();
		resultList = flexService.getMemberMoney(memberList);
		memberMoneyMap.put("memberResMap", resultList);
		setMemberMoneyMap(memberMoneyMap);
		return "memberMoneyMap";
	}
}
