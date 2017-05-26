package com.lovo.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ��ͼƬ�Ƿ����ú͵����ӵĹ�����
 * @author ���
 *
 */
@WebFilter(
		urlPatterns = { "*.jpg", "*.gif", "*.png" }, 
		initParams = { @WebInitParam(name = "MyURL", value = "http://localhost:8080/hw") }
)
public class ImageProtectionFilter implements Filter {
	private String myURL;
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		myURL = config.getInitParameter("MyURL");
	}
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hsRequest = (HttpServletRequest) req;
		
		String referer = hsRequest.getHeader("referer");
		
		if(referer != null) {
			if(referer.toLowerCase().startsWith(myURL.toLowerCase())) {
				chain.doFilter(req, resp);
			}
			else {
				((HttpServletResponse)resp).sendError(403);
			}
		}
		else {
			throw new ServletException();
		}
	}
	
	@Override
	public void destroy() {
	}
}
