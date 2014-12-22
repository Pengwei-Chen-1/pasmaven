package com.cpw.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cpw.dao.ISpendingDao;
import com.cpw.pojo.Spending;
import com.cpw.util.HibernateUtil;
import com.cpw.util.LogUtil;
import com.cpw.util.SysConstant;

public class SpendingDaoImpl implements ISpendingDao {

	private Session session = null;
	private Transaction tran = null;
	private Query query = null;
	// 影响行数
	private int rows = 0;

	@SuppressWarnings("unchecked")
	@Override
	public List<Spending> getAllSpending() {
		List<Spending> spendingList = new ArrayList<Spending>();
		String hql = "from Spending where userId = " + SysConstant.SYS_USER_ID;
		try {
			session = HibernateUtil.getSession();
			query = session.createQuery(hql);
			spendingList = query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession(session);
		}
		return spendingList;
	}

	@Override
	public Spending getSpendingById(int spendingId) {
		Spending spending = new Spending();
		String hql = "from Spending where spendingId = :spendingId";
		try {
			session = HibernateUtil.getSession();
			Query query = session.createQuery(hql);
			query.setInteger("spendingId", spendingId);
			spending = (Spending) query.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession(session);
		}
		return spending;
	}

	@Override
	public int addSpending(Spending spending) {
		try {
			session = HibernateUtil.getSession();
			tran = session.beginTransaction();
			session.save(spending);
			tran.commit();
			LogUtil.addLog(SysConstant.LOG_TYPE_ADD, "添加支出信息");
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
	public int updateSpending(Spending spending) {
		try {
			session = HibernateUtil.getSession();
			tran = session.beginTransaction();
			session.update(spending);
			tran.commit();
			LogUtil.addLog(SysConstant.LOG_TYPE_UPDATE, "修改支出信息");
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
	public int delSpending(Spending spending) {
		try {
			session = HibernateUtil.getSession();
			tran = session.beginTransaction();
			session.delete(spending);
			tran.commit();
			LogUtil.addLog(SysConstant.LOG_TYPE_DEL, "删除支出信息");
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
	public List<Spending> getSpendingByTerm(String term) {
		List<Spending> spendingList = new ArrayList<Spending>();
		String hql = "from Spending where 1=1 and " + term + " and userId = "
				+ SysConstant.SYS_USER_ID;
		try {
			session = HibernateUtil.getSession();
			query = session.createQuery(hql);
			spendingList = query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession(session);
		}
		return spendingList;
	}

}
