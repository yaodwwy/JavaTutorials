package com.lovo.biz;

import com.lovo.beans.PageBean;
import com.lovo.entity.Student;

public interface StudentService {

	public PageBean<Student> getStudentsByPage(int classId, int page, int size);
	
	public Student getStudentById(int id);
	
	public boolean updateStudent(Student stu);
	
	public boolean removeStudentById(int id);
}
