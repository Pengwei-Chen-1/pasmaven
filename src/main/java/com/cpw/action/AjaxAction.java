package com.cpw.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.cpw.dao.IIncomeDao;
import com.cpw.dao.ISpendingDao;
import com.cpw.dao.impl.IncomeDaoImpl;
import com.cpw.dao.impl.SpendingDaoImpl;
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
import com.cpw.pojo.UserInfo;
import com.cpw.pojo.UserLogin;
import com.cpw.service.IClassifyService;
import com.cpw.service.ISetService;
import com.cpw.service.ITallyService;
import com.cpw.service.IUserService;
import com.cpw.service.impl.ClassifyServiceImpl;
import com.cpw.service.impl.SetServiceImpl;
import com.cpw.service.impl.TallyServiceImpl;
import com.cpw.service.impl.UserServiceImpl;
import com.cpw.util.DateUtil;
import com.cpw.util.MD5Util;
import com.cpw.util.SysConstant;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 处理Ajax请求的Action
 * 
 * @author pengwei_chen
 * @date 2014-3-27 上午8:53:46
 */
@SuppressWarnings("serial")
public class AjaxAction extends ActionSupport {

	ITallyService tallyService = (TallyServiceImpl) ServiceFactory.getService("tally");
	IUserService userService = (UserServiceImpl) ServiceFactory.getService("user");
	IClassifyService classifyService = (ClassifyServiceImpl) ServiceFactory.getService("classify");
	ISetService setService = (SetServiceImpl) ServiceFactory.getService("set");
	ISpendingDao spendingDao = (SpendingDaoImpl) DaoFactory.getDao("spending");
	IIncomeDao incomeDao = (IncomeDaoImpl) DaoFactory.getDao("income");
	Map<String, Object> map = new HashMap<String, Object>();

	private String userName;
	private String result;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 检查用户名是否已存在
	 * 
	 * @return result=Y:已存在,result=N:不存在
	 */
	public String checkUserName() {
		String userName = getUserName();
		UserInfo userInfo = userService.getUserInfoByName(userName);
		if (userInfo == null) {
			map.put("result", "N");
		} else {
			map.put("result", "Y");
		}
		JSONObject obj = JSONObject.fromObject(map);
		setResult(obj.toString());
		return SUCCESS;
	}

	/**
	 * 添加一级分类
	 * 
	 * @return
	 */
	public String addFirstLevel() {
		List<FirstLevel> resultLevelList = new ArrayList<FirstLevel>();
		String levelName = getUserName().split(",")[0];
		String IOType = getUserName().split(",")[1];
		FirstLevel firstLevel = new FirstLevel();
		firstLevel.setFirstLevelName(levelName);
		firstLevel.setImage("");
		firstLevel.setIoType(IOType);
		firstLevel.setUserId(SysConstant.SYS_USER_ID);
		tallyService.addFirstLevel(firstLevel);
		if (IOType.equals(SysConstant.IOTYPE_I)) {
			resultLevelList = tallyService.getAllSpendingFirstLevel();
		} else if (IOType.equals(SysConstant.IOTYPE_O)) {
			resultLevelList = tallyService.getAllIncomeFirstLevel();
		}
		map.put("result", resultLevelList);
		JSONObject obj = JSONObject.fromObject(map);
		setResult(obj.toString());
		return SUCCESS;
	}

	/**
	 * 添加账户信息
	 * 
	 * @return
	 */
	public String addAccount() {
		String accountName = getUserName();
		Account account = new Account();
		account.setAccountName(accountName);
		account.setUserId(SysConstant.SYS_USER_ID);
		tallyService.addAccount(account);
		List<Account> accountList = tallyService.getAllAccount();
		map.put("result", accountList);
		JSONObject obj = JSONObject.fromObject(map);
		setResult(obj.toString());
		return SUCCESS;
	}

	/**
	 * 添加家庭成员信息
	 * 
	 * @return
	 */
	public String addMember() {
		String member = getUserName();
		Family family = new Family();
		family.setMemberName(member);
		family.setUserId(SysConstant.SYS_USER_ID);
		tallyService.addMember(family);
		List<Family> familyList = tallyService.getAllMember();
		map.put("result", familyList);
		JSONObject obj = JSONObject.fromObject(map);
		setResult(obj.toString());
		return SUCCESS;
	}

	/**
	 * 添加项目信息
	 * 
	 * @return
	 */
	public String addProject() {
		String projectName = getUserName();
		Project project = new Project();
		project.setProjectName(projectName);
		project.setUserId(SysConstant.SYS_USER_ID);
		tallyService.addProject(project);
		List<Project> projectList = tallyService.getAllProject();
		map.put("result", projectList);
		JSONObject obj = JSONObject.fromObject(map);
		setResult(obj.toString());
		return SUCCESS;
	}

	/**
	 * 添加商家信息
	 * 
	 * @return
	 */
	public String addBusiness() {
		String businessName = getUserName();
		Business business = new Business();
		business.setBusinessName(businessName);
		business.setUserId(SysConstant.SYS_USER_ID);
		tallyService.addBusiness(business);
		List<Business> businessList = tallyService.getAllBusiness();
		map.put("result", businessList);
		JSONObject obj = JSONObject.fromObject(map);
		setResult(obj.toString());
		return SUCCESS;
	}

	/**
	 * 添加二级分类
	 * 
	 * @return
	 */
	public String addSecondLevel() {
		String secondLevelName = getUserName().split(",")[0];
		int firstLevelId = Integer.parseInt(getUserName().split(",")[1]);
		String IOType = getUserName().split(",")[2];
		SecondLevel secondLevel = new SecondLevel();
		secondLevel.setFirstLevelId(firstLevelId);
		secondLevel.setImage("");
		secondLevel.setIoType(IOType);
		secondLevel.setSecondLevelName(secondLevelName);
		secondLevel.setUserId(SysConstant.SYS_USER_ID);
		tallyService.addSecondLevel(secondLevel);
		List<SecondLevel> secondList = tallyService.getSecondListByFirstId(firstLevelId);
		map.put("result", secondList);
		JSONObject obj = JSONObject.fromObject(map);
		setResult(obj.toString());
		return SUCCESS;
	}

	/**
	 * 根据一级分类编号获取二级分类列表
	 * 
	 * @return
	 */
	public String getSecondLevelByFirstId() {
		int firstLevelId = Integer.parseInt(getUserName());
		List<SecondLevel> secondLevelList = tallyService.getSecondListByFirstId(firstLevelId);
		map.put("result", secondLevelList);
		JSONObject obj = JSONObject.fromObject(map);
		setResult(obj.toString());
		return SUCCESS;
	}

	/**
	 * 添加支出信息
	 * 
	 * @return
	 */
	public String addSpending() {
		// 将前台获取到的JSON转换成Spending
		String jsonStr = getUserName();
		JSONObject jsonObj = JSONObject.fromObject(jsonStr);
		// 设置日期
		Date date = new Date();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			date = sdf.parse(jsonObj.getString("createTime"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Spending spending = (Spending) JSONObject.toBean(jsonObj, Spending.class);
		spending.setCreateTime(date);
		spending.setUserId(SysConstant.SYS_USER_ID);
		tallyService.addSpending(spending);
		map.put("result", "Y");
		JSONObject obj = JSONObject.fromObject(map);
		setResult(obj.toString());
		return SUCCESS;
	}

	/**
	 * 添加收入信息
	 * 
	 * @return
	 */
	public String addIncome() {
		// 将前台获取到的JSON转换成Spending
		String jsonStr = getUserName();
		JSONObject jsonObj = JSONObject.fromObject(jsonStr);
		// 设置日期
		Date date = new Date();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			date = sdf.parse(jsonObj.getString("createTime"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Income income = (Income) JSONObject.toBean(jsonObj, Income.class);
		income.setCreateTime(date);
		income.setUserId(SysConstant.SYS_USER_ID);
		tallyService.addIncome(income);
		map.put("result", "Y");
		JSONObject obj = JSONObject.fromObject(map);
		setResult(obj.toString());
		return SUCCESS;
	}

	/**
	 * 根据收支类型获得一级类型列表
	 * 
	 * @return
	 */
	public String getAllFirstLevelByType() {
		String type = getUserName();
		List<FirstLevel> firstList = new ArrayList<FirstLevel>();
		List<Template> tempList = new ArrayList<Template>();
		if (type.equals("spending")) {
			firstList = tallyService.getAllSpendingFirstLevel();
			tempList = classifyService.getSpendingTemplate();
		} else if (type.equals("income")) {
			firstList = tallyService.getAllIncomeFirstLevel();
			tempList = classifyService.getIncomeTemplate();
		}
		map.put("result", firstList);
		map.put("tempList", tempList);
		JSONObject obj = JSONObject.fromObject(map);
		setResult(obj.toString());
		return SUCCESS;
	}

	/**
	 * 添加模板信息
	 * 
	 * @return
	 */
	public String addTemplate() {
		// 将前台获取到的JSON转换成Spending
		String jsonStr = getUserName();
		JSONObject jsonObj = JSONObject.fromObject(jsonStr);
		Template template = (Template) JSONObject.toBean(jsonObj, Template.class);
		template.setUserId(SysConstant.SYS_USER_ID);
		template.setCreateTime(DateUtil.getNowDate());
		classifyService.addTemplate(template);
		map.put("result", "Y");
		JSONObject obj = JSONObject.fromObject(map);
		setResult(obj.toString());
		return SUCCESS;
	}

	/**
	 * 修改模板信息
	 * 
	 * @return
	 */
	public String updateTemplate() {
		// 将前台获取到的JSON转换成Spending
		String jsonStr = getUserName();
		int tempId = Integer.parseInt(jsonStr.split("-")[1]);
		JSONObject jsonObj = JSONObject.fromObject(jsonStr.split("-")[0]);
		Template template = (Template) JSONObject.toBean(jsonObj, Template.class);
		template.setTemplateId(tempId);
		template.setUserId(SysConstant.SYS_USER_ID);
		template.setCreateTime(DateUtil.getNowDate());
		classifyService.updateTemplate(template);
		map.put("result", "Y");
		JSONObject obj = JSONObject.fromObject(map);
		setResult(obj.toString());
		return SUCCESS;
	}

	/**
	 * 删除模板
	 * 
	 * @return
	 */
	public String delTemplate() {
		int templateId = Integer.parseInt(getUserName());
		classifyService.delTemplateById(templateId);
		map.put("result", "Y");
		JSONObject obj = JSONObject.fromObject(map);
		setResult(obj.toString());
		return SUCCESS;
	}

	/**
	 * 删除商家
	 * 
	 * @return
	 */
	public String delBusiness() {
		int busId = Integer.parseInt(getUserName());
		boolean isDel = classifyService.delBussiness(busId);
		if (isDel) {
			map.put("result", "Y");
		} else {
			map.put("result", "N");
		}
		JSONObject obj = JSONObject.fromObject(map);
		setResult(obj.toString());
		return SUCCESS;
	}

	/**
	 * 删除项目
	 * 
	 * @return
	 */
	public String delProject() {
		int proId = Integer.parseInt(getUserName());
		boolean isDel = classifyService.delProject(proId);
		if (isDel) {
			map.put("result", "Y");
		} else {
			map.put("result", "N");
		}
		JSONObject obj = JSONObject.fromObject(map);
		setResult(obj.toString());
		return SUCCESS;
	}

	/**
	 * 删除成员
	 * 
	 * @return
	 */
	public String delMember() {
		int memberId = Integer.parseInt(getUserName());
		boolean isDel = classifyService.delMember(memberId);
		if (isDel) {
			map.put("result", "Y");
		} else {
			map.put("result", "N");
		}
		JSONObject obj = JSONObject.fromObject(map);
		setResult(obj.toString());
		return SUCCESS;
	}

	/**
	 * 删除一级支出分类
	 * 
	 * @return
	 */
	public String delFirstOut() {
		boolean isDel = false;
		int outId = Integer.parseInt(getUserName());
		isDel = classifyService.delFirstOut(outId);
		if (isDel) {
			map.put("result", "Y");
		} else {
			map.put("result", "N");
		}
		JSONObject obj = JSONObject.fromObject(map);
		setResult(obj.toString());
		return SUCCESS;
	}

	/**
	 * 删除一级收入分类
	 * 
	 * @return
	 */
	public String delFirstIn() {
		boolean isDel = false;
		int inId = Integer.parseInt(getUserName());
		isDel = classifyService.delFirstIn(inId);
		if (isDel) {
			map.put("result", "Y");
		} else {
			map.put("result", "N");
		}
		JSONObject obj = JSONObject.fromObject(map);
		setResult(obj.toString());
		return SUCCESS;
	}

	/**
	 * 删除二级支出分类
	 * 
	 * @return
	 */
	public String delSecondOut() {
		boolean isDel = false;
		int outId = Integer.parseInt(getUserName());
		isDel = classifyService.delSecondOut(outId);
		if (isDel) {
			map.put("result", "Y");
		} else {
			map.put("result", "N");
		}
		JSONObject obj = JSONObject.fromObject(map);
		setResult(obj.toString());
		return SUCCESS;
	}

	/**
	 * 删除二级收入分类
	 * 
	 * @return
	 */
	public String delSecondIn() {
		boolean isDel = false;
		int inId = Integer.parseInt(getUserName());
		isDel = classifyService.delSecondIn(inId);
		if (isDel) {
			map.put("result", "Y");
		} else {
			map.put("result", "N");
		}
		JSONObject obj = JSONObject.fromObject(map);
		setResult(obj.toString());
		return SUCCESS;
	}

	/**
	 * 添加分类信息
	 * 
	 * @return
	 */
	public String addClassify() {
		String str = getUserName();
		String[] strList = str.split("-");
		String typeName = strList[0];
		if (strList[1].equals("first")) {
			FirstLevel firstLevel = new FirstLevel();
			firstLevel.setFirstLevelName(typeName);
			firstLevel.setIoType(strList[2]);
			firstLevel.setUserId(SysConstant.SYS_USER_ID);
			tallyService.addFirstLevel(firstLevel);
		} else if (strList[1].equals("second")) {
			SecondLevel secondLevel = new SecondLevel();
			secondLevel.setIoType(strList[2]);
			secondLevel.setSecondLevelName(typeName);
			secondLevel.setFirstLevelId(Integer.parseInt(strList[3]));
			secondLevel.setUserId(SysConstant.SYS_USER_ID);
			tallyService.addSecondLevel(secondLevel);
		} else if (strList[1].equals("business")) {
			Business bus = new Business();
			bus.setBusinessName(typeName);
			bus.setUserId(SysConstant.SYS_USER_ID);
			tallyService.addBusiness(bus);
		} else if (strList[1].equals("project")) {
			Project pro = new Project();
			pro.setProjectName(typeName);
			pro.setUserId(SysConstant.SYS_USER_ID);
			tallyService.addProject(pro);
		} else if (strList[1].equals("member")) {
			Family family = new Family();
			family.setMemberName(typeName);
			family.setUserId(SysConstant.SYS_USER_ID);
			tallyService.addMember(family);
		}
		map.put("result", "Y");
		JSONObject obj = JSONObject.fromObject(map);
		setResult(obj.toString());
		return SUCCESS;
	}

	/**
	 * 更新分类信息
	 * 
	 * @return
	 */
	public String updateClassify() {
		String str = getUserName();
		String[] strList = str.split("-");
		int classifyId = Integer.parseInt(str.split("-")[1]);
		String newName = strList[2];
		if (strList[0].equals("first")) {
			FirstLevel firstLevel = new FirstLevel();
			firstLevel = (FirstLevel) classifyService.getClassifyById(firstLevel, classifyId);
			firstLevel.setFirstLevelName(newName);
			classifyService.updateClassify(firstLevel);
		} else if (strList[0].equals("second")) {
			SecondLevel secondLevel = new SecondLevel();
			secondLevel = (SecondLevel) classifyService.getClassifyById(secondLevel, classifyId);
			secondLevel.setSecondLevelName(newName);
			classifyService.updateClassify(secondLevel);
		} else if (strList[0].equals("business")) {
			Business bus = new Business();
			bus = (Business) classifyService.getClassifyById(bus, classifyId);
			bus.setBusinessName(newName);
			classifyService.updateClassify(bus);
		} else if (strList[0].equals("project")) {
			Project pro = new Project();
			pro = (Project) classifyService.getClassifyById(pro, classifyId);
			pro.setProjectName(newName);
			classifyService.updateClassify(pro);
		} else if (strList[0].equals("member")) {
			Family family = new Family();
			family = (Family) classifyService.getClassifyById(family, classifyId);
			family.setMemberName(newName);
			classifyService.updateClassify(family);
		}
		map.put("result", "Y");
		JSONObject obj = JSONObject.fromObject(map);
		setResult(obj.toString());
		return SUCCESS;
	}

	/**
	 * 根据模板编号获取模板信息
	 * 
	 * @return
	 */
	public String getTemplateById() {
		Template template = new Template();
		int tempId = Integer.parseInt(getUserName());
		template = tallyService.getTemplateById(tempId);
		map.put("result", template);
		JSONObject obj = JSONObject.fromObject(map);
		setResult(obj.toString());
		return SUCCESS;
	}

	/**
	 * 删除收支信息
	 * 
	 * @return
	 */
	public String delTally() {
		String[] strList = getUserName().split("-");
		if (strList[0].equals("O")) {
			Spending s = new Spending();
			s.setSpendingId(Integer.parseInt(strList[1]));
			spendingDao.delSpending(s);
		} else if (strList[0].equals("I")) {
			Income i = new Income();
			i.setIncomeId(Integer.parseInt(strList[1]));
			incomeDao.delIncome(i);
		}
		map.put("result", "Y");
		JSONObject obj = JSONObject.fromObject(map);
		setResult(obj.toString());
		return SUCCESS;
	}

	/**
	 * 修改收支信息
	 * 
	 * @return
	 */
	public String saveTally() {
		// 将前台获取到的JSON转换成Spending
		String jsonStr = getUserName();
		String ioType = jsonStr.split("@")[1];
		JSONObject jsonObj = JSONObject.fromObject(jsonStr.split("@")[0]);
		// 设置日期
		Date date = new Date();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			date = sdf.parse(jsonObj.getString("createTime"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (ioType.equals(SysConstant.IOTYPE_O)) {
			Spending spending = (Spending) JSONObject.toBean(jsonObj, Spending.class);
			spending.setUserId(SysConstant.SYS_USER_ID);
			spending.setCreateTime(date);
			tallyService.saveTally(spending);
		} else if (ioType.equals(SysConstant.IOTYPE_I)) {
			Income income = (Income) JSONObject.toBean(jsonObj, Income.class);
			income.setUserId(SysConstant.SYS_USER_ID);
			income.setCreateTime(date);
			tallyService.saveTally(income);
		}
		map.put("result", "Y");
		JSONObject obj = JSONObject.fromObject(map);
		setResult(obj.toString());
		return SUCCESS;
	}

	/**
	 * 修改用户昵称
	 * 
	 * @return
	 */
	public String changeNickName() {
		String nickName = getUserName();
		UserInfo user = setService.getUserInfoById(SysConstant.SYS_USER_ID);
		user.setNickName(nickName);
		setService.updateUserInfo(user);
		map.put("result", "Y");
		JSONObject obj = JSONObject.fromObject(map);
		setResult(obj.toString());
		return SUCCESS;
	}

	/**
	 * 检查用户原始密码
	 * 
	 * @return
	 */
	public String checkOldPass() {
		String oldPass = MD5Util.MD5(getUserName());
		UserLogin userLogin = userService.getUserLoginByName(SysConstant.SYS_USER_NAME);
		if (oldPass.equals(userLogin.getUserPass())) {
			map.put("result", "Y");
		} else {
			map.put("result", "N");
		}
		JSONObject obj = JSONObject.fromObject(map);
		setResult(obj.toString());
		return SUCCESS;
	}

	/**
	 * 修改密码
	 * 
	 * @return
	 */
	public String changePass() {
		String newPass = MD5Util.MD5(getUserName());
		UserLogin userLogin = userService.getUserLoginByName(SysConstant.SYS_USER_NAME);
		userLogin.setUserPass(newPass);
		userService.updateUserLogin(userLogin);
		map.put("result", "Y");
		JSONObject obj = JSONObject.fromObject(map);
		setResult(obj.toString());
		return SUCCESS;
	}

	/**
	 * 删除日志
	 * 
	 * @return
	 */
	public String delLog() {
		int logId = Integer.parseInt(getUserName());
		setService.delLogById(logId);
		map.put("result", "Y");
		JSONObject obj = JSONObject.fromObject(map);
		setResult(obj.toString());
		return SUCCESS;
	}
}