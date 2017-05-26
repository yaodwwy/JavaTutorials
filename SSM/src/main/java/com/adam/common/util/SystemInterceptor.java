package com.adam.common.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class SystemInterceptor extends HandlerInterceptorAdapter {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String uri = request.getRequestURI();



		Object obj = request.getSession().getAttribute("admin");
		if (null == obj) {		
			String path = request.getContextPath();
			String loginURL = request.getScheme() + "://"+ request.getServerName() + ":"+ request.getServerPort() + path + "/login.jsp";
			if (request.getHeader("x-requested-with") != null
					&& request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
				response.setHeader("sessionstatus", "timeout");
				response.setHeader("forwordpath", loginURL );
				return false;
			}else{
//				PrintWriter out = response.getWriter();
//				StringBuilder builder = new StringBuilder();
//				builder.append("<script type=\"text/javascript\" charset=\"UTF-8\">");
//				builder.append("alert(\"页面过期，请重新登录\");");
//				builder.append("window.top.location.href=\"");
//				builder.append(request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ request.getContextPath() + "/");
//				builder.append("index.jsp\";</script>");
//				out.print(builder.toString());
//				out.close();
				String Suffix = uri.substring(uri.indexOf(".")+1);
				if(Suffix.equals("do")){
					response.sendRedirect(request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ request.getContextPath() + "/index.jsp");
				}else{
					response.sendRedirect(request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ request.getContextPath() + "/login.html");
				}
			}
		}

		return super.preHandle(request, response, handler);
	}	
}

