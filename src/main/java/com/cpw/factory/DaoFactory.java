package com.cpw.factory;

import com.cpw.dao.impl.AccountDaoImpl;
import com.cpw.dao.impl.BusinessDaoImpl;
import com.cpw.dao.impl.FamilyDaoImpl;
import com.cpw.dao.impl.FirstLevelDaoImpl;
import com.cpw.dao.impl.IncomeDaoImpl;
import com.cpw.dao.impl.LogInfoDaoImpl;
import com.cpw.dao.impl.ProjectDaoImpl;
import com.cpw.dao.impl.SecondLevelDaoImpl;
import com.cpw.dao.impl.SpendingDaoImpl;
import com.cpw.dao.impl.TemplateDaoImpl;
import com.cpw.dao.impl.UserInfoDaoImpl;
import com.cpw.dao.impl.UserLoginDaoImpl;

/**
 * Dao层工程模式类
 * 
 * @author pengwei_chen
 * @date 2014-3-18 下午5:53:03
 */
public class DaoFactory {

	public static Object getDao(String daoName) {
		Object baseDao = null;
		if (daoName.equals("userInfo")) {
			baseDao = new UserInfoDaoImpl();
		} else if (daoName.equals("userLogin")) {
			baseDao = new UserLoginDaoImpl();
		} else if (daoName.equals("account")) {
			baseDao = new AccountDaoImpl();
		} else if (daoName.equals("business")) {
			baseDao = new BusinessDaoImpl();
		} else if (daoName.equals("family")) {
			baseDao = new FamilyDaoImpl();
		} else if (daoName.equals("firstLevel")) {
			baseDao = new FirstLevelDaoImpl();
		} else if (daoName.equals("income")) {
			baseDao = new IncomeDaoImpl();
		} else if (daoName.equals("logInfo")) {
			baseDao = new LogInfoDaoImpl();
		} else if (daoName.equals("project")) {
			baseDao = new ProjectDaoImpl();
		} else if (daoName.equals("secondLevel")) {
			baseDao = new SecondLevelDaoImpl();
		} else if (daoName.equals("spending")) {
			baseDao = new SpendingDaoImpl();
		} else if (daoName.equals("template")) {
			baseDao = new TemplateDaoImpl();
		}
		return baseDao;
	}
}
