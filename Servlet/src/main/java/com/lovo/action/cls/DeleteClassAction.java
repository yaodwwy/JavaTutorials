package com.lovo.action.cls;

import com.lovo.action.*;
import com.lovo.biz.ClassService;
import com.lovo.biz.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ɾ���༶��Action
 * @author ���
 *
 */
public class DeleteClassAction implements Action {
	private int id;
	
	@Override
	public ActionResult execute(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		boolean flag = getClassService().removeClass(id);
		return new ActionResult(new ResultContent(new ResultIndicator(flag)), ResultType.Ajax);
	}
	
	private ClassService getClassService() {
		return (ClassService) ServiceFactory.factory(ClassService.class);
	}

}
