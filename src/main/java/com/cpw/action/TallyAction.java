package com.cpw.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.cpw.dao.IAccountDao;
import com.cpw.dao.IBusinessDao;
import com.cpw.dao.IProjectDao;
import com.cpw.dao.ISecondLevelDao;
import com.cpw.dao.impl.AccountDaoImpl;
import com.cpw.dao.impl.BusinessDaoImpl;
import com.cpw.dao.impl.ProjectDaoImpl;
import com.cpw.dao.impl.SecondLevelDaoImpl;
import com.cpw.dto.DayTallyDto;
import com.cpw.dto.IOTallyDto;
import com.cpw.dto.TallyDto;
import com.cpw.factory.DaoFactory;
import com.cpw.factory.ServiceFactory;
import com.cpw.pojo.Account;
import com.cpw.pojo.Business;
import com.cpw.pojo.Family;
import com.cpw.pojo.FirstLevel;
import com.cpw.pojo.Income;
import com.cpw.pojo.Project;
import com.cpw.pojo.SecondLevel;
import com.cpw.pojo.Spending;
import com.cpw.pojo.Template;
import com.cpw.service.IClassifyService;
import com.cpw.service.ITallyService;
import com.cpw.service.impl.ClassifyServiceImpl;
import com.cpw.service.impl.TallyServiceImpl;
import com.cpw.util.DateUtil;
import com.cpw.util.SysConstant;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 记账Action
 * 
 * @author pengwei_chen
 * @date 2014-3-30 上午11:32:21
 */
@SuppressWarnings("serial")
public class TallyAction extends ActionSupport {

	ITallyService tallyService = (TallyServiceImpl) ServiceFactory.getService("tally");
	IClassifyService classifyService = (ClassifyServiceImpl) ServiceFactory.getService("classify");
	IAccountDao accountDao = (AccountDaoImpl) DaoFactory.getDao("account");
	IBusinessDao busDao = (BusinessDaoImpl) DaoFactory.getDao("business");
	IProjectDao projectDao = (ProjectDaoImpl)DaoFactory.getDao("project");
	ISecondLevelDao secondDao = (SecondLevelDaoImpl) DaoFactory.getDao("secondLevel");

	private TallyDto tallyDto;

	private String msg;
	private String par;
	private String searchKey;

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public String getPar() {
		return par;
	}

	public void setPar(String par) {
		this.par = par;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * 记账页面初始化执行的方法
	 * 
	 * @return
	 */
	public String init() {
		setMsg("tally");
		List<FirstLevel> firstLevelList = tallyService.getAllSpendingFirstLevel();
		List<FirstLevel> incomeFristList = tallyService.getAllIncomeFirstLevel();
		List<SecondLevel> secondLevelList = tallyService.getSecondListByFirstId(firstLevelList.get(
				0).getFirstLevelId());
		List<Account> accountList = tallyService.getAllAccount();
		List<Business> businessList = tallyService.getAllBusiness();
		List<Family> familyList = tallyService.getAllMember();
		List<Project> projectList = tallyService.getAllProject();
		List<Template> templateList = classifyService.getSpendingTemplate();
		ActionContext context = ActionContext.getContext();
		context.put("firstLevelList", firstLevelList);
		context.put("incomeFristList", incomeFristList);
		context.put("secondLevelList", secondLevelList);
		context.put("accountList", accountList);
		context.put("businessList", businessList);
		context.put("familyList", familyList);
		context.put("projectList", projectList);
		context.put("templateList", templateList);
		context.put("dayTallyList", getTallyByDay());
		context.put("tallyDto", tallyDto);
		return SUCCESS;
	}

	/**
	 * 获取全部收支信息并按天分组
	 * 
	 * @return
	 */
	private List<DayTallyDto> getTallyByDay() {
		List<DayTallyDto> dayTallyList = new ArrayList<DayTallyDto>();
		DayTallyDto tempDayTally;
		List<Object> allTallyList = getAllTallyList();
		Spending tempSpending1, tempSpending2;
		Income tempIncome1, tempIncome2;
		Date day1 = null, day2 = null;
		// 存放已遍历过的收支信息序号
		List<Integer> isSearchList = new ArrayList<Integer>();
		for (int i = 0; i < allTallyList.size(); i++) {
			if (isSearchList.contains(i)) {
				continue;
			}
			tempDayTally = new DayTallyDto();
			List<IOTallyDto> ioTallyList = new ArrayList<IOTallyDto>();
			Object obj = allTallyList.get(i);
			if (obj instanceof Spending) {
				tempSpending1 = (Spending) obj;
				day1 = tempSpending1.getCreateTime();
			} else if (obj instanceof Income) {
				tempIncome1 = (Income) obj;
				day1 = tempIncome1.getCreateTime();
			}
			ioTallyList.add(changeType(obj));
			isSearchList.add(i);
			for (int j = 0; j < allTallyList.size(); j++) {
				if (isSearchList.contains(j)) {
					continue;
				}
				Object object = allTallyList.get(j);
				if (object instanceof Spending) {
					tempSpending2 = (Spending) object;
					day2 = tempSpending2.getCreateTime();
				} else if (object instanceof Income) {
					tempIncome2 = (Income) object;
					day2 = tempIncome2.getCreateTime();
				}
				if (DateUtil.isSameDay(day1, day2)) {
					ioTallyList.add(changeType(object));
					isSearchList.add(j);
				}
			}
			tempDayTally.setDay(DateUtil.getDateDay(day1));
			tempDayTally.setMonth(DateUtil.getDateMonth(day1));
			tempDayTally.setIncomeMoney(getTallyMoney(ioTallyList, "I"));
			tempDayTally.setSpendingMoney(getTallyMoney(ioTallyList, "O"));
			tempDayTally.setIoTallyList(ioTallyList);
			dayTallyList.add(tempDayTally);
		}
		return dayTallyList;
	}

	/**
	 * 获取当天的收支金额
	 * 
	 * @param ioTallyList
	 *            当天收支信息
	 * @param ioType
	 *            收支类型
	 * @return 收支金额
	 */
	private double getTallyMoney(List<IOTallyDto> ioTallyList, String ioType) {
		double money = 0;
		for (IOTallyDto ioTallyDto : ioTallyList) {
			if (ioTallyDto.getIoTallyType().equals(ioType)) {
				money += ioTallyDto.getMoney();
			}
		}
		return money;
	}

	/**
	 * 改变obj为IOTallyDto类型并赋值
	 * 
	 * @param obj
	 * @return
	 */
	private IOTallyDto changeType(Object obj) {
		IOTallyDto tempioTally = new IOTallyDto();
		if (obj instanceof Spending) {
			Spending tempS = (Spending) obj;
			int accountId = tempS.getAccountId();
			int busId = tempS.getBusinessId();
			int proId = tempS.getProjectId();
			tempioTally.setSpendingId(tempS.getSpendingId());
			tempioTally.setAccountId(accountId);
			tempioTally.setAccountName(accountDao.getAccountById(accountId).getAccountName());
			tempioTally.setBusinessId(busId);
			tempioTally.setBusinessName(busDao.getBusinessById(busId).getBusinessName());
			tempioTally.setFirstLevelId(tempS.getFirstLevelId());
			tempioTally.setImage(tempS.getImage());
			tempioTally.setIoTallyType("O");
			tempioTally.setMemberId(tempS.getMemberId());
			tempioTally.setMoney(tempS.getMoney());
			tempioTally.setProjectId(proId);
			tempioTally.setProjectName(projectDao.getProjectById(proId).getProjectName());
			tempioTally.setCreateTime(tempS.getCreateTime());
			int secondId = tempS.getSecondLevelId();
			tempioTally.setSecondLevelId(secondId);
			tempioTally.setSecondLevelName(secondDao.getSecondLevelById(secondId)
					.getSecondLevelName());
			tempioTally.setRemark(tempS.getRemark());
		} else if (obj instanceof Income) {
			Income tempI = (Income) obj;
			int accountId = tempI.getAccountId();
			int proId = tempI.getProjectId();
			tempioTally.setSpendingId(tempI.getIncomeId());
			tempioTally.setAccountId(accountId);
			tempioTally.setAccountName(accountDao.getAccountById(accountId).getAccountName());
			tempioTally.setFirstLevelId(tempI.getFirstLevelId());
			tempioTally.setImage(tempI.getImage());
			tempioTally.setIoTallyType("I");
			tempioTally.setMemberId(tempI.getMemberId());
			tempioTally.setMoney(tempI.getMoney());
			tempioTally.setProjectId(proId);
			tempioTally.setProjectName(projectDao.getProjectById(proId).getProjectName());
			tempioTally.setCreateTime(tempI.getCreateTime());
			int secondId = tempI.getSecondLevelId();
			tempioTally.setSecondLevelId(secondId);
			tempioTally.setSecondLevelName(secondDao.getSecondLevelById(secondId)
					.getSecondLevelName());
			tempioTally.setRemark(tempI.getRemark());
		}
		int firstId = tempioTally.getFirstLevelId();
		tempioTally.setSecondList(tallyService.getSecondListByFirstId(firstId));
		return tempioTally;
	}

	/**
	 * 按条件获取全部收支信息
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<Object> getAllTallyList() {
		String term = "1=1";
		String par = getPar() == null ? SysConstant.TALLY_MONTH_TEXT : getPar();
		tallyDto = new TallyDto();
		if (par != null && par.equals(SysConstant.TALLY_ALL_TEXT)) {
			term = "1=1";
			tallyDto.setEndDate(DateUtil.getNowDateStr(new Date()));
		} else if (par != null && par.equals(SysConstant.TALLY_MONTH_TEXT)) {
			term = "date_format(CREATETIME,'%Y-%m') = date_format(now(),'%Y-%m')";
			tallyDto.setBeginDate(DateUtil.getDateYear(new Date()) + "."
					+ DateUtil.getDateMonth(new Date()) + "." + "1");
			tallyDto.setEndDate(DateUtil.getNowDateStr(new Date()));
		} else if (par != null && par.equals(SysConstant.TALLY_YEAR_TEXT)) {
			term = "date_format(CREATETIME,'%Y') = date_format(now(),'%Y')";
			tallyDto.setBeginDate(DateUtil.getDateYear(new Date()) + "." + "1" + "." + "1");
			tallyDto.setEndDate(DateUtil.getNowDateStr(new Date()));
		}
		String key = getSearchKey();
		if (key != null) {
			term += " and remark like '%" + key + "%'";
		}
		List<Object> allTallyList = new ArrayList<Object>();
		List<Spending> allSpendingList = tallyService.getSpendingByTerm(term);
		List<Income> allIncomeList = tallyService.getIncomeByTerm(term);
		double allSpending = 0, allIncome = 0;
		for (Income income : allIncomeList) {
			allTallyList.add(income);
			allIncome += income.getMoney();
		}
		for (Spending spending : allSpendingList) {
			allTallyList.add(spending);
			allSpending += spending.getMoney();
		}
		tallyDto.setAllIncomeMoney(allIncome);
		tallyDto.setAllSpendingMoney(allSpending);
		// 按时间排序
		Collections.sort(allTallyList, new Comparator() {
			@Override
			public int compare(Object o1, Object o2) {
				Date d1 = null, d2 = null;
				if (o1 instanceof Spending && o2 instanceof Spending) {
					d1 = ((Spending) o1).getCreateTime();
					d2 = ((Spending) o2).getCreateTime();
				} else if (o1 instanceof Spending && o2 instanceof Income) {
					d1 = ((Spending) o1).getCreateTime();
					d2 = ((Income) o2).getCreateTime();
				} else if (o1 instanceof Income && o2 instanceof Spending) {
					d1 = ((Income) o1).getCreateTime();
					d2 = ((Spending) o2).getCreateTime();
				} else if (o1 instanceof Income && o2 instanceof Income) {
					d1 = ((Income) o1).getCreateTime();
					d2 = ((Income) o2).getCreateTime();
				}
				if (d1.after(d2)) {
					return -1;
				} else if (d2.after(d1)) {
					return 1;
				} else {
					return 0;
				}
			}
		});
		if (par != null && par.equals(SysConstant.TALLY_ALL_TEXT)) {
			Object o = allTallyList.get(allTallyList.size() - 1);
			Date tempDate = null;
			if (o instanceof Spending) {
				tempDate = ((Spending) o).getCreateTime();
			} else if (o instanceof Income) {
				tempDate = ((Income) o).getCreateTime();
			}
			tallyDto.setBeginDate(DateUtil.getNowDateStr(tempDate));
		}
		return allTallyList;
	}
}