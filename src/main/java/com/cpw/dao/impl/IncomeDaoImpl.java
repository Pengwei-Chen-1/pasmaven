package com.cpw.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cpw.dao.IIncomeDao;
import com.cpw.pojo.Income;
import com.cpw.util.HibernateUtil;
import com.cpw.util.LogUtil;
import com.cpw.util.SysConstant;

public class IncomeDaoImpl implements IIncomeDao {

	private Session session = null;
	private Transaction tran = null;
	private Query query = null;
	// 影响行数
	private int rows = 0;

	@SuppressWarnings("unchecked")
	@Override
	public List<Income> getAllIncome() {
		List<Income> incomeList = new ArrayList<Income>();
		String hql = "from Income where userId = " + SysConstant.SYS_USER_ID;
		try {
			session = HibernateUtil.getSession();
			query = session.createQuery(hql);
			incomeList = query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession(session);
		}
		return incomeList;
	}

	@Override
	public Income getIncomeById(int incomeId) {
		Income income = new Income();
		String hql = "from Income where incomeId = :incomeId";
		try {
			session = HibernateUtil.getSession();
			Query query = session.createQuery(hql);
			query.setInteger("incomeId", incomeId);
			income = (Income) query.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession(session);
		}
		return income;
	}

	@Override
	public int addIncome(Income income) {
		try {
			session = HibernateUtil.getSession();
			tran = session.beginTransaction();
			session.save(income);
			tran.commit();
			LogUtil.addLog(SysConstant.LOG_TYPE_ADD, "添加收入信息");
		} catch (HibernateException e) {
			e.printStackTrace();
			if (tran != null) {
				tran.rollback();
			}
			rows = -1;
		} finally {
			HibernateUtil.closeSession(session);
		}
		return rows;
	}

	@Override
	public int updateIncome(Income income) {
		try {
			session = HibernateUtil.getSession();
			tran = session.beginTransaction();
			session.update(income);
			tran.commit();
			LogUtil.addLog(SysConstant.LOG_TYPE_UPDATE, "修改收入信息");
		} catch (HibernateException e) {
			e.printStackTrace();
			if (tran != null) {
				tran.rollback();
			}
			rows = -1;
		} finally {
			HibernateUtil.closeSession(session);
		}
		return rows;
	}

	@Override
	public int delIncome(Income income) {
		try {
			session = HibernateUtil.getSession();
			tran = session.beginTransaction();
			session.delete(income);
			tran.commit();
			LogUtil.addLog(SysConstant.LOG_TYPE_DEL, "删除收入");
		} catch (HibernateException e) {
			e.printStackTrace();
			if (tran != null) {
				tran.rollback();
			}
			rows = -1;
		} finally {
			HibernateUtil.closeSession(session);
		}
		return rows;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Income> getIncomeByTerm(String term) {
		List<Income> incomeList = new ArrayList<Income>();
		String hql = "from Income where 1=1 and " + term + " and userId = "
				+ SysConstant.SYS_USER_ID;
		try {
			session = HibernateUtil.getSession();
			query = session.createQuery(hql);
			incomeList = query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession(session);
		}
		return incomeList;
	}

}
