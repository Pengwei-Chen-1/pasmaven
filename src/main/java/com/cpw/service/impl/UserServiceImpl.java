package com.cpw.service.impl;

import com.cpw.dao.IAccountDao;
import com.cpw.dao.IBusinessDao;
import com.cpw.dao.IFamilyDao;
import com.cpw.dao.IFirstLevelDao;
import com.cpw.dao.IProjectDao;
import com.cpw.dao.ISecondLevelDao;
import com.cpw.dao.IUserInfoDao;
import com.cpw.dao.IUserLoginDao;
import com.cpw.dao.impl.AccountDaoImpl;
import com.cpw.dao.impl.BusinessDaoImpl;
import com.cpw.dao.impl.FamilyDaoImpl;
import com.cpw.dao.impl.FirstLevelDaoImpl;
import com.cpw.dao.impl.ProjectDaoImpl;
import com.cpw.dao.impl.SecondLevelDaoImpl;
import com.cpw.dao.impl.UserInfoDaoImpl;
import com.cpw.dao.impl.UserLoginDaoImpl;
import com.cpw.factory.DaoFactory;
import com.cpw.pojo.Account;
import com.cpw.pojo.Business;
import com.cpw.pojo.Family;
import com.cpw.pojo.FirstLevel;
import com.cpw.pojo.Project;
import com.cpw.pojo.SecondLevel;
import com.cpw.pojo.UserInfo;
import com.cpw.pojo.UserLogin;
import com.cpw.service.IUserService;
import com.cpw.util.DateUtil;
import com.cpw.util.SysConstant;

public class UserServiceImpl implements IUserService {

	IUserLoginDao userLoginDao = (UserLoginDaoImpl) DaoFactory.getDao("userLogin");
	IFirstLevelDao firstLevelDao = (FirstLevelDaoImpl) DaoFactory.getDao("firstLevel");
	ISecondLevelDao secondLevelDao = (SecondLevelDaoImpl) DaoFactory.getDao("secondLevel");
	IAccountDao accountDao = (AccountDaoImpl) DaoFactory.getDao("account");
	IBusinessDao businessDao = (BusinessDaoImpl) DaoFactory.getDao("business");
	IFamilyDao familyDao = (FamilyDaoImpl) DaoFactory.getDao("family");
	IProjectDao projectDao = (ProjectDaoImpl) DaoFactory.getDao("project");

	IUserInfoDao userInfoDao = (UserInfoDaoImpl) DaoFactory.getDao("userInfo");

	@Override
	public UserLogin getUserLoginByName(String userName) {
		UserLogin userLogin = new UserLogin();
		userLogin = userLoginDao.getUserLoginByName(userName);
		return userLogin;
	}

	@Override
	public UserInfo getUserInfoByName(String userName) {
		UserInfo userInfo = userInfoDao.getUserByUserName(userName);
		return userInfo;
	}

	@Override
	public void addUserInfoAndLogin(UserLogin userLogin) {
		UserInfo userInfo = new UserInfo();
		userInfo.setCreateTime(DateUtil.getNowDate());
		userInfo.setEmail(userLogin.getUserName());
		userInfo.setHeadImage("");
		userInfo.setNickName(userLogin.getUserName());
		userInfo.setUserName(userLogin.getUserName());
		userInfoDao.addUserInfo(userInfo);
		userLoginDao.addUserLogin(userLogin);
	}

	@Override
	public void updateUserLogin(UserLogin userLogin) {
		userLoginDao.updateUserLogin(userLogin);
	}

	@Override
	public void addFirstLevel(String name, String ioType) {
		FirstLevel first = new FirstLevel();
		first.setFirstLevelName(name);
		first.setImage("");
		first.setIoType(ioType);
		first.setUserId(SysConstant.SYS_USER_ID);
		firstLevelDao.addFirstLevel(first);
	}

	@Override
	public void addSecondLevel(String name, FirstLevel first) {
		SecondLevel second = new SecondLevel();
		second.setFirstLevelId(first.getFirstLevelId());
		second.setImage("");
		second.setIoType(first.getIoType());
		second.setSecondLevelName(name);
		second.setUserId(SysConstant.SYS_USER_ID);
		secondLevelDao.addSecondLevel(second);
	}

	@Override
	public void addBusiness(String name) {
		Business bus = new Business();
		bus.setBusinessName(name);
		bus.setUserId(SysConstant.SYS_USER_ID);
		businessDao.addBusiness(bus);
	}

	@Override
	public void addProject(String name) {
		Project project = new Project();
		project.setProjectName(name);
		project.setUserId(SysConstant.SYS_USER_ID);
		projectDao.addProject(project);
	}

	@Override
	public void addFamily(String name) {
		Family fa = new Family();
		fa.setMemberName(name);
		fa.setUserId(SysConstant.SYS_USER_ID);
		familyDao.addFamily(fa);
	}

	@Override
	public void addAcount(String name) {
		Account ac = new Account();
		ac.setAccountName(name);
		ac.setUserId(SysConstant.SYS_USER_ID);
		accountDao.addAccount(ac);
	}
}
