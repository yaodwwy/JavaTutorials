package com.lovo.biz.impl;

import com.lovo.beans.PageBean;
import com.lovo.biz.StudentService;
import com.lovo.dao.StudentDao;
import com.lovo.dao.impl.StudentDaoDbImpl;
import com.lovo.entity.Student;

public class StudentServiceImpl implements StudentService {
	private StudentDao studentDao = new StudentDaoDbImpl();
	
	@Override
	public PageBean<Student> getStudentsByPage(int classId, int page, int size) {
		return studentDao.findByClassId(classId, page, size);
	}

	@Override
	public Student getStudentById(int id) {
		return studentDao.findById(id);
	}

	@Override
	public boolean updateStudent(Student stu) {
		return studentDao.update(stu);
	}

	@Override
	public boolean removeStudentById(int id) {
		return studentDao.deleteById(id);
	}

}
