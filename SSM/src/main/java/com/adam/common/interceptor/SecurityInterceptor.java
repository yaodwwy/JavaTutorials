package com.adam.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.adam.sys.userfunc.entity.User;
/**
 * 类描述： 用户登录拦截器
 *
 */
public class SecurityInterceptor extends HandlerInterceptorAdapter  {

	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String url = request.getRequestURL().toString();
		if(checkStaticResource(url)) {
			return true;
		}
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("admin");
		//首页和登录过滤掉
		if(url.indexOf("/user/tologin") != -1) {
			if(user != null) {
				response.sendRedirect("../user/login");
				return false;
			}
			return true;
		}
		//session失效,且非登录操作
		boolean isSysUrl = url.indexOf("menu/topage.do") > 0 || url.indexOf("document/menu")>0;
		if(user == null && isSysUrl) {
			response.sendRedirect("../user/tologin");
			return false;
		}
		return true;
	}

	/**
	 * checkStaticResource：处理静态资源
	 * 创建人：liuhongdong
	 * 修改人：liuhongdong
	 * @param url
	 * @return boolean
	 * @exception
	 * @since  1.0.0
	 */
	private boolean checkStaticResource(String url) {
		if(url.indexOf(".js") != -1 || url.indexOf(".css") != -1 || url.indexOf(".png") != -1
				|| url.indexOf(".jpg") != -1 || url.indexOf(".ico") != -1 || url.indexOf(".apk") != -1
				|| url.indexOf(".gif") != -1 || url.indexOf(".bmp") != -1 || url.indexOf(".jpeg") != -1) {
			return true;
		}
		return false;
	}
	

	

	
}
