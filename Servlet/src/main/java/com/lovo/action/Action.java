package com.lovo.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * �����û�����Ŀ������ӿ�
 * @author ���
 *
 */
public interface Action {

	public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException;
}
