package com.cpw.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cpw.dao.IUserInfoDao;
import com.cpw.pojo.UserInfo;
import com.cpw.util.HibernateUtil;
import com.cpw.util.LogUtil;
import com.cpw.util.SysConstant;

public class UserInfoDaoImpl implements IUserInfoDao {

	private Session session = null;
	private Transaction tran = null;
	// 影响行数
	private int rows = 0;

	@SuppressWarnings("unchecked")
	@Override
	public List<UserInfo> getAllUser() {
		List<UserInfo> list = new ArrayList<UserInfo>();
		String hql = "from UserInfo";
		try {
			session = HibernateUtil.getSession();
			Query query = session.createQuery(hql);
			list = query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession(session);
		}
		return list;
	}

	@Override
	public List<UserInfo> getUserInfoByPage(int pageNum, int size, String term) {
		return null;
	}

	@Override
	public UserInfo getUserByUserName(String userName) {
		UserInfo userInfo = new UserInfo();
		String hql = "from UserInfo where userName like :userName";
		try {
			session = HibernateUtil.getSession();
			Query query = session.createQuery(hql);
			query.setString("userName", userName);
			userInfo = (UserInfo) query.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession(session);
		}
		return userInfo;
	}

	@Override
	public UserInfo getUserById(int userId) {
		UserInfo user = new UserInfo();
		String hql = "from UserInfo where userId = :userId";
		try {
			session = HibernateUtil.getSession();
			Query query = session.createQuery(hql);
			query.setInteger("userId", userId);
			user = (UserInfo) query.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession(session);
		}
		return user;
	}

	@Override
	public int delUser(UserInfo user) {
		try {
			session = HibernateUtil.getSession();
			tran = session.beginTransaction();
			session.delete(user);
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
	public int updateUserInfo(UserInfo user) {
		try {
			session = HibernateUtil.getSession();
			tran = session.beginTransaction();
			session.update(user);
			tran.commit();
			LogUtil.addLog(SysConstant.LOG_TYPE_UPDATE, "修改用户信息");
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
	public int addUserInfo(UserInfo user) {
		try {
			session = HibernateUtil.getSession();
			tran = session.beginTransaction();
			session.save(user);
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
}
