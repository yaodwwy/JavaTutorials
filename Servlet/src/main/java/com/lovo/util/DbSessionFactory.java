package com.lovo.util;


/**
 * ���ݿ�Ự����
 * @author ���
 *
 */
public class DbSessionFactory {
	private static final ThreadLocal<DbSession> threadLocal = new ThreadLocal<DbSession>();
	
	private DbSessionFactory() {
		throw new AssertionError();
	}
	
	/**
	 * �򿪻Ự
	 * @return DbSession����
	 */
	public static DbSession openSession() {
		DbSession session = threadLocal.get();
		
		if(session == null) {
			session = new DbSession();
			threadLocal.set(session);
		}
		
		session.open();
		
		return session;
	}
	
	/**
	 * �رջỰ
	 */
	public static void closeSession() {
		DbSession session = threadLocal.get();
		threadLocal.set(null);
		
		if(session != null) {
			session.close();
		}
	}
	
}
