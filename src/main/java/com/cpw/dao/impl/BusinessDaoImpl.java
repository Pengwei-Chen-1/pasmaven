package com.cpw.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cpw.dao.IBusinessDao;
import com.cpw.pojo.Business;
import com.cpw.util.HibernateUtil;
import com.cpw.util.LogUtil;
import com.cpw.util.SysConstant;

public class BusinessDaoImpl implements IBusinessDao {

	private Session session = null;
	private Transaction tran = null;
	private Query query = null;
	// 影响行数
	private int rows = 0;

	@SuppressWarnings("unchecked")
	@Override
	public List<Business> getAllBusiness() {
		List<Business> businessList = new ArrayList<Business>();
		String hql = "from Business where userId = " + SysConstant.SYS_USER_ID;
		try {
			session = HibernateUtil.getSession();
			query = session.createQuery(hql);
			businessList = query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession(session);
		}
		return businessList;
	}

	@Override
	public Business getBusinessById(int businessId) {
		Business business = new Business();
		String hql = "from Business where businessId = :businessId";
		try {
			session = HibernateUtil.getSession();
			Query query = session.createQuery(hql);
			query.setInteger("businessId", businessId);
			business = (Business) query.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession(session);
		}
		return business;
	}

	@Override
	public int addBusiness(Business business) {
		try {
			session = HibernateUtil.getSession();
			tran = session.beginTransaction();
			session.save(business);
			tran.commit();
			LogUtil.addLog(SysConstant.LOG_TYPE_ADD, "添加[" + business.getBusinessName() + "]商家信息");
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
	public int updateBusiness(Business business) {
		try {
			session = HibernateUtil.getSession();
			tran = session.beginTransaction();
			session.update(business);
			tran.commit();
			LogUtil.addLog(SysConstant.LOG_TYPE_UPDATE, "修改[" + business.getBusinessName()
					+ "]商家信息");
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
	public int delBusiness(Business business) {
		try {
			session = HibernateUtil.getSession();
			tran = session.beginTransaction();
			session.delete(business);
			tran.commit();
			LogUtil.addLog(SysConstant.LOG_TYPE_DEL, "删除[" + business.getBusinessName() + "]商家");
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
