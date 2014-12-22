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
import com.cpw.dto.TemplateDto;
import com.cpw.factory.DaoFactory;
import com.cpw.factory.ServiceFactory;
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

public class ClassifyServiceImpl implements IClassifyService {

	IFirstLevelDao firstLevelDao = (FirstLevelDaoImpl) DaoFactory.getDao("firstLevel");
	ITemplateDao templateDao = (TemplateDaoImpl) DaoFactory.getDao("template");
	IAccountDao accountDao = (AccountDaoImpl) DaoFactory.getDao("account");
	ISecondLevelDao secondDao = (SecondLevelDaoImpl) DaoFactory.getDao("secondLevel");
	IProjectDao projectDao = (ProjectDaoImpl) DaoFactory.getDao("project");
	IFamilyDao familyDao = (FamilyDaoImpl) DaoFactory.getDao("family");
	ITallyService tallyService = (TallyServiceImpl) ServiceFactory.getService("tally");
	IBusinessDao businessDao = (BusinessDaoImpl) DaoFactory.getDao("business");
	ISpendingDao spendingDao = (SpendingDaoImpl) DaoFactory.getDao("spending");
	IIncomeDao incomeDao = (IncomeDaoImpl) DaoFactory.getDao("income");

	@Override
	public void addTemplate(Template template) {
		templateDao.addTemplate(template);
	}

	@Override
	public List<TemplateDto> getAllTemplateDto() {
		List<TemplateDto> templateDtoList = new ArrayList<TemplateDto>();
		List<Template> templateList = templateDao.getAllTemplate();
		List<SecondLevel> secondLevelList;
		TemplateDto tempDto;
		for (Template template : templateList) {
			tempDto = new TemplateDto();
			secondLevelList = new ArrayList<SecondLevel>();
			tempDto.setTemplateId(template.getTemplateId());
			tempDto.setTemplateName(template.getTemplateName());
			int accountId = template.getAccountId();
			String accountName = accountDao.getAccountById(accountId).getAccountName();
			tempDto.setAccountId(accountId);
			tempDto.setAccountName(accountName);
			tempDto.setFirstLevelId(template.getFirstLevelId());
			secondLevelList = tallyService.getSecondListByFirstId(template.getFirstLevelId());
			tempDto.setSecondLevelList(secondLevelList);
			int secondId = template.getSecondLevelId();
			tempDto.setSecondLevelId(secondId);
			String secondName = secondDao.getSecondLevelById(secondId).getSecondLevelName();
			tempDto.setSecondLevelName(secondName);
			tempDto.setBusinessId(template.getBusinessId());
			tempDto.setIoType(template.getIoType());
			tempDto.setMemberId(template.getMemberId());
			tempDto.setMoney(template.getMoney());
			tempDto.setProjectId(template.getProjectId());
			tempDto.setRemark(template.getRemark());
			tempDto.setUserId(template.getUserId());
			templateDtoList.add(tempDto);
		}
		return templateDtoList;
	}

	@Override
	public void delTemplateById(int templateId) {
		Template template = new Template();
		template.setTemplateId(templateId);
		templateDao.delTemplate(template);
	}

	@Override
	public boolean delBussiness(int busId) {
		boolean isDel = false;
		boolean isHave = false;
		List<Spending> allSpendingList = spendingDao.getAllSpending();
		for (Spending spending : allSpendingList) {
			if (spending.getBusinessId() == busId) {
				isHave = true;
			}
		}
		if (!isHave) {
			Business bus = businessDao.getBusinessById(busId);
			businessDao.delBusiness(bus);
			isDel = true;
		}
		return isDel;
	}

	@Override
	public boolean delProject(int proId) {
		boolean isDel = false;
		boolean isHave = false;
		List<Spending> allSpendingList = spendingDao.getAllSpending();
		List<Income> allIncomeList = incomeDao.getAllIncome();
		for (Spending spending : allSpendingList) {
			if (spending.getProjectId() == proId) {
				isHave = true;
			}
		}
		for (Income income : allIncomeList) {
			if (income.getProjectId() == proId) {
				isHave = true;
			}
		}
		if (!isHave) {
			Project pro = projectDao.getProjectById(proId);
			projectDao.delProject(pro);
			isDel = true;
		}
		return isDel;
	}

	@Override
	public boolean delMember(int memberId) {
		boolean isDel = false;
		boolean isHave = false;
		List<Spending> allSpendingList = spendingDao.getAllSpending();
		List<Income> allIncomeList = incomeDao.getAllIncome();
		for (Spending spending : allSpendingList) {
			if (spending.getMemberId() == memberId) {
				isHave = true;
			}
		}
		for (Income income : allIncomeList) {
			if (income.getMemberId() == memberId) {
				isHave = true;
			}
		}
		if (!isHave) {
			Family family = familyDao.getFamilyById(memberId);
			familyDao.delFamily(family);
			isDel = true;
		}
		return isDel;
	}

	@Override
	public boolean delFirstOut(int outId) {
		boolean isDel = false;
		boolean isHave = false;
		List<Spending> allSpendingList = spendingDao.getAllSpending();
		for (Spending spending : allSpendingList) {
			if (spending.getFirstLevelId() == outId) {
				isHave = true;
			}
		}
		if (!isHave) {
			FirstLevel firstLevel = firstLevelDao.getFirstLevelById(outId);
			firstLevelDao.delFirstLevel(firstLevel);
			isDel = true;
		}
		return isDel;
	}

	@Override
	public boolean delFirstIn(int inId) {
		boolean isDel = false;
		boolean isHave = false;
		List<Income> allIncomeList = incomeDao.getAllIncome();
		for (Income income : allIncomeList) {
			if (income.getFirstLevelId() == inId) {
				isHave = true;
			}
		}
		if (!isHave) {
			FirstLevel firstLevel = firstLevelDao.getFirstLevelById(inId);
			firstLevelDao.delFirstLevel(firstLevel);
			isDel = true;
		}
		return isDel;
	}

	@Override
	public boolean delSecondOut(int outId) {
		boolean isDel = false;
		boolean isHave = false;
		List<Spending> allSpendingList = spendingDao.getAllSpending();
		for (Spending spending : allSpendingList) {
			if (spending.getSecondLevelId() == outId) {
				isHave = true;
			}
		}
		if (!isHave) {
			SecondLevel secondLevel = secondDao.getSecondLevelById(outId);
			secondDao.delSecondLevel(secondLevel);
			isDel = true;
		}
		return isDel;
	}

	@Override
	public boolean delSecondIn(int inId) {
		boolean isDel = false;
		boolean isHave = false;
		List<Income> allIncomeList = incomeDao.getAllIncome();
		for (Income income : allIncomeList) {
			if (income.getSecondLevelId() == inId) {
				isHave = true;
			}
		}
		if (!isHave) {
			SecondLevel secondLevel = secondDao.getSecondLevelById(inId);
			secondDao.delSecondLevel(secondLevel);
			isDel = true;
		}
		return isDel;
	}

	@Override
	public void updateClassify(Object obj) {
		if (obj instanceof FirstLevel) {
			firstLevelDao.updateFirstLevel((FirstLevel) obj);
		} else if (obj instanceof SecondLevel) {
			secondDao.updateSecondLevel((SecondLevel) obj);
		} else if (obj instanceof Project) {
			projectDao.updateProject((Project) obj);
		} else if (obj instanceof Business) {
			businessDao.updateBusiness((Business) obj);
		} else if (obj instanceof Family) {
			familyDao.updateFamily((Family) obj);
		}
	}

	@Override
	public Object getClassifyById(Object obj, int id) {
		Object tempObj = new Object();
		if (obj instanceof FirstLevel) {
			tempObj = firstLevelDao.getFirstLevelById(id);
		} else if (obj instanceof SecondLevel) {
			tempObj = secondDao.getSecondLevelById(id);
		} else if (obj instanceof Project) {
			tempObj = projectDao.getProjectById(id);
		} else if (obj instanceof Business) {
			tempObj = businessDao.getBusinessById(id);
		} else if (obj instanceof Family) {
			tempObj = familyDao.getFamilyById(id);
		}
		return tempObj;
	}

	@Override
	public void updateTemplate(Template template) {
		templateDao.updateTemplate(template);
	}

	@Override
	public List<Template> getSpendingTemplate() {
		List<Template> tempList = new ArrayList<Template>();
		String term = "ioType = 'O'";
		tempList = templateDao.getTemplate(term);
		return tempList;
	}

	@Override
	public List<Template> getIncomeTemplate() {
		List<Template> tempList = new ArrayList<Template>();
		String term = "ioType = 'I'";
		tempList = templateDao.getTemplate(term);
		return tempList;
	}
}
