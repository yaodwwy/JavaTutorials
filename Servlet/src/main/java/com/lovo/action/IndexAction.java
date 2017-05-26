package com.lovo.action;

import com.lovo.biz.ClassService;
import com.lovo.biz.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ������ҳ��Action
 * @author ���
 *
 */
public class IndexAction implements Action {

	@Override
	public ActionResult execute(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setAttribute("classList", getClassService().getAllClasses());
		return new ActionResult(new ResultContent("index.jsp"), ResultType.Forward);
	}
	
	private ClassService getClassService() {
		return (ClassService) ServiceFactory.factory(ClassService.class);
	}

}
