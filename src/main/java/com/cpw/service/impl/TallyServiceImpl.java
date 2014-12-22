package com.cpw.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.cpw.dao.IAccountDao;
import com.cpw.dao.IBusinessDao;
import com.cpw.dao.IFamilyDao;
import com.cpw.dao.IFirstLevelDao;
import com.cpw.dao.IIncomeDao;
import com.cpw.dao.IProjectDao;
import com.cpw.dao.ISecondLevelDao;
import com.cpw.dao.ISpendingDao;
import com.cpw.dao.ITemplateDao;
import com.cpw.dao.impl.AccountDaoImpl;
import com.cpw.dao.impl.BusinessDaoImpl;
import com.cpw.dao.impl.FamilyDaoImpl;
import com.cpw.dao.impl.FirstLevelDaoImpl;
import com.cpw.dao.impl.IncomeDaoImpl;
import com.cpw.dao.impl.ProjectDaoImpl;
import com.cpw.dao.impl.SecondLevelDaoImpl;
import com.cpw.dao.impl.SpendingDaoImpl;
import com.cpw.dao.impl.TemplateDaoImpl;
import com.cpw.factory.DaoFactory;
import com.cpw.pojo.Account;
import com.cpw.pojo.Business;
import com.cpw.pojo.Family;
import com.cpw.pojo.FirstLevel;
import com.cpw.pojo.Income;
import com.cpw.pojo.Project;
import com.cpw.pojo.SecondLevel;
import com.cpw.pojo.Spending;
import com.cpw.pojo.Template;
import com.cpw.service.ITallyService;
import com.cpw.util.SysConstant;

public class TallyServiceImpl implements ITallyService {

	IFirstLevelDao firstLevelDao = (FirstLevelDaoImpl) DaoFactory.getDao("firstLevel");
	ISecondLevelDao secondLevelDao = (SecondLevelDaoImpl) DaoFactory.getDao("secondLevel");
	IAccountDao accountDao = (AccountDaoImpl) DaoFactory.getDao("account");
	IBusinessDao businessDao = (BusinessDaoImpl) DaoFactory.getDao("business");
	IFamilyDao familyDao = (FamilyDaoImpl) DaoFactory.getDao("family");
	IProjectDao projectDao = (ProjectDaoImpl) DaoFactory.getDao("project");
	ITemplateDao templateDao = (TemplateDaoImpl) DaoFactory.getDao("template");
	ISpendingDao spendingDao = (SpendingDaoImpl) DaoFactory.getDao("spending");
	IIncomeDao incomeDao = (IncomeDaoImpl) DaoFactory.getDao("income");

	@Override
	public List<FirstLevel> getAllFirstLevel() {
		return firstLevelDao.getAllFirstLevel();
	}

	@Override
	public List<SecondLevel> getAllSecondLevel() {
		return secondLevelDao.getAllSecondLevel();
	}

	@Override
	public List<Account> getAllAccount() {
		return accountDao.getAllAccount();
	}

	@Override
	public List<Family> getAllMember() {
		return familyDao.getAllFamily();
	}

	@Override
	public List<Project> getAllProject() {
		return projectDao.getAllProject();
	}

	@Override
	public List<Business> getAllBusiness() {
		return businessDao.getAllBusiness();
	}

	@Override
	public List<Template> getAllTemplate() {
		return templateDao.getAllTemplate();
	}

	@Override
	public List<FirstLevel> getAllSpendingFirstLevel() {
		List<FirstLevel> firstLevelList = new ArrayList<FirstLevel>();
		List<FirstLevel> allList = firstLevelDao.getAllFirstLevel();
		for (FirstLevel firstLevel : allList) {
			if (firstLevel.getIoType().equals(SysConstant.IOTYPE_O)) {
				firstLevelList.add(firstLevel);
			}
		}
		return firstLevelList;
	}

	@Override
	public List<FirstLevel> getAllIncomeFirstLevel() {
		List<FirstLevel> firstLevelList = new ArrayList<FirstLevel>();
		List<FirstLevel> allList = firstLevelDao.getAllFirstLevel();
		for (FirstLevel firstLevel : allList) {
			if (firstLevel.getIoType().equals(SysConstant.IOTYPE_I)) {
				firstLevelList.add(firstLevel);
			}
		}
		return firstLevelList;
	}

	@Override
	public List<SecondLevel> getAllSpendingSecondLevel() {
		List<SecondLevel> secondLevelList = new ArrayList<SecondLevel>();
		List<SecondLevel> allList = secondLevelDao.getAllSecondLevel();
		for (SecondLevel secondLevel : allList) {
			if (secondLevel.getIoType().equals(SysConstant.IOTYPE_O)) {
				secondLevelList.add(secondLevel);
			}
		}
		return secondLevelList;
	}

	@Override
	public List<SecondLevel> getAllIncomeSecondLevel() {
		List<SecondLevel> secondLevelList = new ArrayList<SecondLevel>();
		List<SecondLevel> allList = secondLevelDao.getAllSecondLevel();
		for (SecondLevel secondLevel : allList) {
			if (secondLevel.getIoType().equals(SysConstant.IOTYPE_I)) {
				secondLevelList.add(secondLevel);
			}
		}
		return secondLevelList;
	}

	@Override
	public void addFirstLevel(FirstLevel firstLevel) {
		firstLevelDao.addFirstLevel(firstLevel);
	}

	@Override
	public void addAccount(Account account) {
		accountDao.addAccount(account);
	}

	@Override
	public void addProject(Project project) {
		projectDao.addProject(project);
	}

	@Override
	public void addBusiness(Business business) {
		businessDao.addBusiness(business);
	}

	@Override
	public void addMember(Family family) {
		familyDao.addFamily(family);
	}

	@Override
	public void addSecondLevel(SecondLevel secondLevel) {
		secondLevelDao.addSecondLevel(secondLevel);
	}

	@Override
	public List<SecondLevel> getSecondListByFirstId(int firstLevelId) {
		List<SecondLevel> allSecondList = secondLevelDao.getAllSecondLevel();
		List<SecondLevel> resultList = new ArrayList<SecondLevel>();
		for (SecondLevel secondLevel : allSecondList) {
			if (secondLevel.getFirstLevelId() == firstLevelId) {
				resultList.add(secondLevel);
			}
		}
		return resultList;
	}

	@Override
	public void addSpending(Spending spending) {
		spendingDao.addSpending(spending);
	}

	@Override
	public Template getTemplateById(int id) {
		return templateDao.getTemplateById(id);
	}

	@Override
	public void addIncome(Income income) {
		incomeDao.addIncome(income);
	}

	@Override
	public List<Spending> getSpendingByTerm(String term) {
		return spendingDao.getSpendingByTerm(term);
	}

	@Override
	public List<Income> getIncomeByTerm(String term) {
		return incomeDao.getIncomeByTerm(term);
	}

	@Override
	public void saveTally(Object obj) {
		if(obj instanceof Spending){
			spendingDao.updateSpending((Spending)obj);
		} else if(obj instanceof Income){
			incomeDao.updateIncome((Income)obj);
		}
	}

}
