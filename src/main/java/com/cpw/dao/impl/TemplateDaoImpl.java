package com.cpw.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cpw.dao.ITemplateDao;
import com.cpw.pojo.Template;
import com.cpw.util.HibernateUtil;
import com.cpw.util.LogUtil;
import com.cpw.util.SysConstant;

public class TemplateDaoImpl implements ITemplateDao {

	private Session session = null;
	private Transaction tran = null;
	private Query query = null;
	// 影响行数
	private int rows = 0;

	@SuppressWarnings("unchecked")
	@Override
	public List<Template> getAllTemplate() {
		List<Template> templateList = new ArrayList<Template>();
		String hql = "from Template where userId = " + SysConstant.SYS_USER_ID;
		try {
			session = HibernateUtil.getSession();
			query = session.createQuery(hql);
			templateList = query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession(session);
		}
		return templateList;
	}

	@Override
	public Template getTemplateById(int templateId) {
		Template template = new Template();
		String hql = "from Template where templateId = :templateId";
		try {
			session = HibernateUtil.getSession();
			Query query = session.createQuery(hql);
			query.setInteger("templateId", templateId);
			template = (Template) query.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession(session);
		}
		return template;
	}

	@Override
	public int addTemplate(Template template) {
		try {
			session = HibernateUtil.getSession();
			tran = session.beginTransaction();
			session.save(template);
			tran.commit();
			LogUtil.addLog(SysConstant.LOG_TYPE_ADD, "添加[" + template.getTemplateName() + "]模板");
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
	public int updateTemplate(Template template) {
		try {
			session = HibernateUtil.getSession();
			tran = session.beginTransaction();
			session.update(template);
			tran.commit();
			LogUtil.addLog(SysConstant.LOG_TYPE_UPDATE, "修改[" + template.getTemplateName() + "]模板");
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
	public int delTemplate(Template template) {
		try {
			session = HibernateUtil.getSession();
			tran = session.beginTransaction();
			session.delete(template);
			tran.commit();
			LogUtil.addLog(SysConstant.LOG_TYPE_DEL, "删除[" + template.getTemplateName() + "]模板");
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
	public List<Template> getTemplate(String term) {
		List<Template> tempList = new ArrayList<Template>();
		String hql = "from Template where " + term + " and userId = " + SysConstant.SYS_USER_ID;
		try {
			session = HibernateUtil.getSession();
			query = session.createQuery(hql);
			tempList = query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession(session);
		}
		return tempList;
	}

}
