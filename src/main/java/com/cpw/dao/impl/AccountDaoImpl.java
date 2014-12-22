package com.cpw.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cpw.dao.IAccountDao;
import com.cpw.pojo.Account;
import com.cpw.util.HibernateUtil;
import com.cpw.util.LogUtil;
import com.cpw.util.SysConstant;

public class AccountDaoImpl implements IAccountDao {

	private Session session = null;
	private Transaction tran = null;
	// 影响行数
	private int rows = 0;

	@SuppressWarnings("unchecked")
	@Override
	public List<Account> getAllAccount() {
		List<Account> accountList = new ArrayList<Account>();
		String hql = "from Account where userId = " + SysConstant.SYS_USER_ID;
		try {
			session = HibernateUtil.getSession();
			Query query = session.createQuery(hql);
			accountList = query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession(session);
		}
		return accountList;
	}

	@Override
	public int addAccount(Account account) {
		try {
			session = HibernateUtil.getSession();
			tran = session.beginTransaction();
			session.save(account);
			tran.commit();
			LogUtil.addLog(SysConstant.LOG_TYPE_ADD, "添加[" + account.getAccountName() + "]账户信息");
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
	public int updateAccount(Account account) {
		try {
			session = HibernateUtil.getSession();
			tran = session.beginTransaction();
			session.update(account);
			tran.commit();
			LogUtil.addLog(SysConstant.LOG_TYPE_UPDATE, "修改[" + account.getAccountName() + "]账户信息");
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
	public int deleteAccount(Account account) {
		try {
			session = HibernateUtil.getSession();
			tran = session.beginTransaction();
			session.delete(account);
			tran.commit();
			LogUtil.addLog(SysConstant.LOG_TYPE_DEL, "删除[" + account.getAccountName() + "]账户");
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
	public Account getAccountById(int accountId) {
		Account account = new Account();
		String hql = "from Account where accountId = :accountId";
		try {
			session = HibernateUtil.getSession();
			Query query = session.createQuery(hql);
			query.setInteger("accountId", accountId);
			account = (Account) query.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession(session);
		}
		return account;
	}

}
