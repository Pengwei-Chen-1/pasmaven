package com.cpw.action;

import java.util.ArrayList;
import java.util.List;

import com.cpw.dto.FirstClassify;
import com.cpw.dto.TemplateDto;
import com.cpw.factory.ServiceFactory;
import com.cpw.pojo.Account;
import com.cpw.pojo.Business;
import com.cpw.pojo.Family;
import com.cpw.pojo.FirstLevel;
import com.cpw.pojo.Project;
import com.cpw.pojo.SecondLevel;
import com.cpw.service.IClassifyService;
import com.cpw.service.IFlexService;
import com.cpw.service.ITallyService;
import com.cpw.service.impl.ClassifyServiceImpl;
import com.cpw.service.impl.FlexServiceImpl;
import com.cpw.service.impl.TallyServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 分类Action
 * 
 * @author pengwei_chen
 * @date 2014-3-30 下午12:16:23
 */
@SuppressWarnings("serial")
public class ClassifyAtion extends ActionSupport {

	ITallyService tallyService = (TallyServiceImpl) ServiceFactory.getService("tally");
	IFlexService flexService = (FlexServiceImpl) ServiceFactory.getService("flex");
	IClassifyService classifyService = (ClassifyServiceImpl) ServiceFactory.getService("classify");

	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * 初始化方法
	 * 
	 * @return
	 */
	public String init() {
		setMsg("classify");
		List<FirstClassify> firstList = getFirstClassifyList();
		List<Account> accountList = tallyService.getAllAccount();
		List<FirstClassify> firstIncomeList = getFirstIncomClassifyList();
		List<Business> businessList = tallyService.getAllBusiness();
		List<Project> projectList = tallyService.getAllProject();
		List<Family> memberList = tallyService.getAllMember();
		List<FirstLevel> firstLevelSpendingList = tallyService.getAllSpendingFirstLevel();
		List<SecondLevel> secondLevelList = tallyService
				.getSecondListByFirstId(firstLevelSpendingList.get(0).getFirstLevelId());
		List<FirstLevel> firstLevelIncomeList = tallyService.getAllIncomeFirstLevel();
		List<TemplateDto> templateDtoList = classifyService.getAllTemplateDto();
		ActionContext context = ActionContext.getContext();
		context.put("firstList", firstList);
		context.put("secondLevelList", secondLevelList);
		context.put("accountList", accountList);
		context.put("firstIncomeList", firstIncomeList);
		context.put("businessList", businessList);
		context.put("projectList", projectList);
		context.put("memberList", memberList);
		context.put("firstLevelList", firstLevelSpendingList);
		context.put("firstLevelIncomeList", firstLevelIncomeList);
		context.put("templateDtoList", templateDtoList);
		return SUCCESS;
	}

	/**
	 * 获取一级分类信息
	 * 
	 * @return 一级分类信息列表
	 */
	private List<FirstClassify> getFirstClassifyList() {
		List<FirstClassify> firstList = new ArrayList<FirstClassify>();
		List<FirstLevel> firstLevelList = tallyService.getAllSpendingFirstLevel();
		FirstClassify tempfirst;
		for (FirstLevel firstLevel : firstLevelList) {
			tempfirst = new FirstClassify();
			int firstLevelId = firstLevel.getFirstLevelId();
			tempfirst.setFirstId(firstLevelId);
			tempfirst.setFirstName(firstLevel.getFirstLevelName());
			tempfirst.setFirstImage(firstLevel.getImage());
			double firstMoney = flexService.getMoneyByFirstLevelId(firstLevelId);
			tempfirst.setFirstMoney(firstMoney);
			List<SecondLevel> secondLevelList = tallyService.getSecondListByFirstId(firstLevelId);
			tempfirst.setSecondList(secondLevelList);
			firstList.add(tempfirst);
		}
		return firstList;
	}

	/**
	 * 获取一级收入分类信息
	 * 
	 * @return 一级收入分类信息列表
	 */
	private List<FirstClassify> getFirstIncomClassifyList() {
		List<FirstClassify> firstIncomeList = new ArrayList<FirstClassify>();
		List<FirstLevel> firstLevelList = tallyService.getAllIncomeFirstLevel();
		FirstClassify tempfirst;
		for (FirstLevel firstLevel : firstLevelList) {
			tempfirst = new FirstClassify();
			int firstLevelId = firstLevel.getFirstLevelId();
			tempfirst.setFirstId(firstLevelId);
			tempfirst.setFirstName(firstLevel.getFirstLevelName());
			tempfirst.setFirstImage(firstLevel.getImage());
			double firstMoney = flexService.getMoneyByFirstLevelId(firstLevelId);
			tempfirst.setFirstMoney(firstMoney);
			List<SecondLevel> secondLevelList = tallyService.getSecondListByFirstId(firstLevelId);
			tempfirst.setSecondList(secondLevelList);
			firstIncomeList.add(tempfirst);
		}
		return firstIncomeList;
	}

}
