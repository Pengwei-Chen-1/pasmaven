package com.cpw.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Hibernate底层工具类
 * 
 * @author pengwei_chen
 * @date 2014-2-25 下午2:48:22
 */
public class HibernateUtil {

	private static final SessionFactory sessionFactory;

	static {
		try {
			sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * 获取Session
	 * 
	 * @return Session
	 */
	public static Session getSession() {
		return getSessionFactory().openSession();
	}

	/**
	 * 关闭Session
	 * 
	 * @param session
	 *            Session
	 */
	public static void closeSession(Session session) {
		if (session != null && session.isOpen()) {
			session.close();
		}
	}
}
