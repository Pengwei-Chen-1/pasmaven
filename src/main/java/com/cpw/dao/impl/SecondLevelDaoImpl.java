package com.cpw.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cpw.dao.ISecondLevelDao;
import com.cpw.pojo.SecondLevel;
import com.cpw.util.HibernateUtil;
import com.cpw.util.LogUtil;
import com.cpw.util.SysConstant;

public class SecondLevelDaoImpl implements ISecondLevelDao {

	private Session session = null;
	private Transaction tran = null;
	private Query query = null;
	// 影响行数
	private int rows = 0;

	@SuppressWarnings("unchecked")
	@Override
	public List<SecondLevel> getAllSecondLevel() {
		List<SecondLevel> secondLevelList = new ArrayList<SecondLevel>();
		String hql = "from SecondLevel where userId = " + SysConstant.SYS_USER_ID;
		try {
			session = HibernateUtil.getSession();
			query = session.createQuery(hql);
			secondLevelList = query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession(session);
		}
		return secondLevelList;
	}

	@Override
	public SecondLevel getSecondLevelById(int secondLevelId) {
		SecondLevel secondLevle = new SecondLevel();
		String hql = "from SecondLevel where secondLevelId = :secondLevelId";
		try {
			session = HibernateUtil.getSession();
			Query query = session.createQuery(hql);
			query.setInteger("secondLevelId", secondLevelId);
			secondLevle = (SecondLevel) query.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession(session);
		}
		return secondLevle;
	}

	@Override
	public int addSecondLevel(SecondLevel secondLevel) {
		try {
			session = HibernateUtil.getSession();
			tran = session.beginTransaction();
			session.save(secondLevel);
			tran.commit();
			LogUtil.addLog(SysConstant.LOG_TYPE_ADD, "添加二级分类");
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
	public int updateSecondLevel(SecondLevel secondLevel) {
		try {
			session = HibernateUtil.getSession();
			tran = session.beginTransaction();
			session.update(secondLevel);
			tran.commit();
			LogUtil.addLog(SysConstant.LOG_TYPE_UPDATE, "修改二级分类");
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
	public int delSecondLevel(SecondLevel secondLevel) {
		try {
			session = HibernateUtil.getSession();
			tran = session.beginTransaction();
			session.delete(secondLevel);
			tran.commit();
			LogUtil.addLog(SysConstant.LOG_TYPE_DEL, "删除二级分类");
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
