package com.lovo.action.stu;

import com.lovo.action.Action;
import com.lovo.action.ActionResult;
import com.lovo.action.ResultContent;
import com.lovo.action.ResultType;
import com.lovo.biz.ServiceFactory;
import com.lovo.biz.StudentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetStudentAction implements Action {
	private int id;
	
	@Override
	public ActionResult execute(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		return new ActionResult(new ResultContent(getStudentService().getStudentById(id)), ResultType.Ajax);
	}
	
	private StudentService getStudentService() {
		return (StudentService) ServiceFactory.factory(StudentService.class);
	}

}
