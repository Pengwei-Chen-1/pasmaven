package com.cpw.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cpw.dao.IFamilyDao;
import com.cpw.pojo.Family;
import com.cpw.util.HibernateUtil;
import com.cpw.util.LogUtil;
import com.cpw.util.SysConstant;

public class FamilyDaoImpl implements IFamilyDao {

	private Session session = null;
	private Transaction tran = null;
	private Query query = null;
	// 影响行数
	private int rows = 0;

	@SuppressWarnings("unchecked")
	@Override
	public List<Family> getAllFamily() {
		List<Family> familyList = new ArrayList<Family>();
		String hql = "from Family where userId = " + SysConstant.SYS_USER_ID;
		try {
			session = HibernateUtil.getSession();
			query = session.createQuery(hql);
			familyList = query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession(session);
		}
		return familyList;
	}

	@Override
	public Family getFamilyById(int memberId) {
		Family family = new Family();
		String hql = "from Family where memberId = :memberId";
		try {
			session = HibernateUtil.getSession();
			Query query = session.createQuery(hql);
			query.setInteger("memberId", memberId);
			family = (Family) query.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession(session);
		}
		return family;
	}

	@Override
	public int addFamily(Family family) {
		try {
			session = HibernateUtil.getSession();
			tran = session.beginTransaction();
			session.save(family);
			tran.commit();
			LogUtil.addLog(SysConstant.LOG_TYPE_ADD, "添加[" + family.getMemberName() + "]成员信息");
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
	public int updateFamily(Family family) {
		try {
			session = HibernateUtil.getSession();
			tran = session.beginTransaction();
			session.update(family);
			tran.commit();
			LogUtil.addLog(SysConstant.LOG_TYPE_UPDATE, "修改[" + family.getMemberName() + "]成员信息");
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
	public int delFamily(Family family) {
		try {
			session = HibernateUtil.getSession();
			tran = session.beginTransaction();
			session.delete(family);
			tran.commit();
			LogUtil.addLog(SysConstant.LOG_TYPE_DEL, "删除[" + family.getMemberName() + "]成员");
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
