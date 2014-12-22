package com.cpw.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cpw.dao.IUserLoginDao;
import com.cpw.pojo.UserLogin;
import com.cpw.util.HibernateUtil;
import com.cpw.util.LogUtil;
import com.cpw.util.SysConstant;

public class UserLoginDaoImpl implements IUserLoginDao {

	private Session session = null;
	private Transaction tran = null;
	// 影响行数
	private int rows = 0;
	
	@Override
	public String getPassByName(String userName) {
		String userPass = "";
		String hql = "select userPass from UserLogin where userName = :userName";
		try {
			session = HibernateUtil.getSession();
			Query query = session.createQuery(hql);
			query.setString("userName", userName);
			userPass = (String) query.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession(session);
		}
		return userPass;
	}

	@Override
	public int addUserLogin(UserLogin userLogin) {
		try {
			session = HibernateUtil.getSession();
			tran = session.beginTransaction();
			session.save(userLogin);
			tran.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if(tran != null){
				tran.rollback();
			}
			rows = -1;
		} finally {
			HibernateUtil.closeSession(session);
		}
		return rows;
	}

	@Override
	public int updateUserLogin(UserLogin userLogin) {
		try {
			session = HibernateUtil.getSession();
			tran = session.beginTransaction();
			session.update(userLogin);
			tran.commit();
			LogUtil.addLog(SysConstant.LOG_TYPE_UPDATE, "修改用户密码");
		} catch (HibernateException e) {
			e.printStackTrace();
			if(tran != null){
				tran.rollback();
			}
			rows = -1;
		} finally {
			HibernateUtil.closeSession(session);
		}
		return rows;
	}

	@Override
	public int delUserLogin(UserLogin userLogin) {
		try {
			session = HibernateUtil.getSession();
			tran = session.beginTransaction();
			session.delete(userLogin);
			tran.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if(tran != null){
				tran.rollback();
			}
			rows = -1;
		} finally {
			HibernateUtil.closeSession(session);
		}
		return rows;
	}

	@Override
	public UserLogin getUserLoginByName(String userName) {
		UserLogin userLogin = new UserLogin();
		String hql="from UserLogin where userName = :userName";
		try {
			session = HibernateUtil.getSession();
			Query query = session.createQuery(hql);
			query.setString("userName", userName);
			userLogin = (UserLogin) query.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession(session);
		}
		return userLogin;
	}

}
