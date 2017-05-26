package com.lovo.action.stu;

import com.lovo.action.*;
import com.lovo.biz.ServiceFactory;
import com.lovo.biz.StudentService;
import com.lovo.entity.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateStudentAction implements Action {
	private Student stu;
	
	@Override
	public ActionResult execute(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		boolean flag = getStudentService().updateStudent(stu);
		return new ActionResult(new ResultContent(new ResultIndicator(flag)), ResultType.Ajax);
	}

	private StudentService getStudentService() {
		return (StudentService) ServiceFactory.factory(StudentService.class);
	}

}
