package ejb.core.aop.ejbtool;

import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class EjbFactory {
	
	public static <T> T getSessionBean(String sessionBeanName,String url) throws NamingException
	{
		Properties props = new Properties();
		// 设置JNDI驱动的类名
		props.setProperty("java.naming.factory.initial",
				"org.jnp.interfaces.NamingContextFactory");
		// 提供命名服务的URL
		props.setProperty("java.naming.provider.url", url);
		InitialContext ctx = new InitialContext(props);
		
		//获取bean
		@SuppressWarnings("unchecked")
		T beanObj = (T) ctx.lookup(sessionBeanName);
		
		//返回
		return beanObj;
		
	}
	
	
	
}
