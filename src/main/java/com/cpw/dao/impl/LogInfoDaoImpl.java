package com.cpw.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cpw.dao.ILogInfoDao;
import com.cpw.pojo.LogInfo;
import com.cpw.util.HibernateUtil;
import com.cpw.util.SysConstant;

public class LogInfoDaoImpl implements ILogInfoDao {

	private Session session = null;
	private Transaction tran = null;
	private Query query = null;
	// 影响行数
	private int rows = 0;

	@SuppressWarnings("unchecked")
	@Override
	public List<LogInfo> getAllLog() {
		List<LogInfo> logList = new ArrayList<LogInfo>();
		String hql = "from LogInfo where userId = " + SysConstant.SYS_USER_ID;
		try {
			session = HibernateUtil.getSession();
			query = session.createQuery(hql);
			logList = query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession(session);
		}
		return logList;
	}

	@Override
	public int addLogInfo(LogInfo logInfo) {
		try {
			session = HibernateUtil.getSession();
			tran = session.beginTransaction();
			session.save(logInfo);
			tran.commit();
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
	public int delLogInfoById(LogInfo logInfo) {
		try {
			session = HibernateUtil.getSession();
			tran = session.beginTransaction();
			session.delete(logInfo);
			tran.commit();
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
	public List<LogInfo> getLogInfo(int firstResult, int pageSize) {
		List<LogInfo> logList = new ArrayList<LogInfo>();
		String hql = "from LogInfo where userId = " + SysConstant.SYS_USER_ID;
		try {
			session = HibernateUtil.getSession();
			query = session.createQuery(hql);
			query.setFirstResult(firstResult);
			query.setMaxResults(pageSize);
			logList = query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession(session);
		}
		return logList;
	}

	@Override
	public int getCount() {
		int count = 0;
		String hql = "select count(*) from LogInfo where userId = " + SysConstant.SYS_USER_ID;
		try {
			session = HibernateUtil.getSession();
			query = session.createQuery(hql);
			count = ((Number) query.uniqueResult()).intValue();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession(session);
		}
		return count;
	}

}
