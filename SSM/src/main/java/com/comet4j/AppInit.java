package com.comet4j;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.comet4j.core.CometContext;
import org.comet4j.core.CometEngine;
import org.comet4j.core.event.DropEvent;

public class AppInit implements ServletContextListener {

	/**
	 * 用于初始化信息
	 */
	@SuppressWarnings("unchecked")
	public void contextInitialized(ServletContextEvent event) {

		CometContext cc = CometContext.getInstance();
		CometEngine engine = cc.getEngine();

		// 绑定事件侦听(上线 下线)
		engine.addConnectListener(new OnLineListener());
		engine.addListener(DropEvent.class, new OffLineListener());

		cc.registChannel(Constant.APP_CHANNEL);// 注册通道

		// 启动系统监控信息发送器
		 Thread healthSender = new Thread(new HealthSender(), "HealthSender");
		 healthSender.setDaemon(true);
		 healthSender.start();
	}

	public void contextDestroyed(ServletContextEvent arg0) {
	}

}
