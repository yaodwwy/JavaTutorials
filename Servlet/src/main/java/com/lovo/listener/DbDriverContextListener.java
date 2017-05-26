package com.lovo.listener;

import com.lovo.util.DbResourceManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.SQLException;

/**
 * ���غ�ж�����ݿ������ļ�����
 * @author ���
 *
 */
@WebListener
public class DbDriverContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent evt) {
		try {
			Class.forName("com.DbResourceManager");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent evt) {
		try {
			DbResourceManager.unloadDriver();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
