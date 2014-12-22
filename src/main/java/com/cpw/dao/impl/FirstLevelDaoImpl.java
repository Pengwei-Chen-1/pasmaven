package com.cpw.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cpw.dao.IFirstLevelDao;
import com.cpw.pojo.FirstLevel;
import com.cpw.util.HibernateUtil;
import com.cpw.util.LogUtil;
import com.cpw.util.SysConstant;

public class FirstLevelDaoImpl implements IFirstLevelDao {

	private Session session = null;
	private Transaction tran = null;
	private Query query = null;
	// 影响行数
	private int rows = 0;

	@SuppressWarnings("unchecked")
	@Override
	public List<FirstLevel> getAllFirstLevel() {
		List<FirstLevel> firstLevelList = new ArrayList<FirstLevel>();
		String hql = "from FirstLevel where userId = " + SysConstant.SYS_USER_ID;
		try {
			session = HibernateUtil.getSession();
			query = session.createQuery(hql);
			firstLevelList = query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession(session);
		}
		return firstLevelList;
	}

	@Override
	public FirstLevel getFirstLevelById(int firstLevelId) {
		FirstLevel firstLevel = new FirstLevel();
		String hql = "from FirstLevel where firstLevelId = :firstLevelId";
		try {
			session = HibernateUtil.getSession();
			Query query = session.createQuery(hql);
			query.setInteger("firstLevelId", firstLevelId);
			firstLevel = (FirstLevel) query.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession(session);
		}
		return firstLevel;
	}

	@Override
	public int addFirstLevel(FirstLevel firstLevel) {
		try {
			session = HibernateUtil.getSession();
			tran = session.beginTransaction();
			session.save(firstLevel);
			tran.commit();
			LogUtil.addLog(SysConstant.LOG_TYPE_ADD, "添加[" + firstLevel.getFirstLevelName()
					+ "]一级分类");
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
	public int updateFirstLevel(FirstLevel firstLevel) {
		try {
			session = HibernateUtil.getSession();
			tran = session.beginTransaction();
			session.update(firstLevel);
			tran.commit();
			LogUtil.addLog(SysConstant.LOG_TYPE_UPDATE, "修改[" + firstLevel.getFirstLevelName()
					+ "]一级分类信息");
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
	public int delFirstLevel(FirstLevel firstLevel) {
		try {
			session = HibernateUtil.getSession();
			tran = session.beginTransaction();
			session.delete(firstLevel);
			tran.commit();
			LogUtil.addLog(SysConstant.LOG_TYPE_DEL, "删除[" + firstLevel.getFirstLevelName()
					+ "]一级分类");
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

}
