package com.lovo.dao;

import com.lovo.beans.PageBean;
import com.lovo.entity.Student;

public interface StudentDao extends BaseDao<Student, Integer> {

	public PageBean<Student> findByClassId(int classId, int page, int size);
	
	public int getStudentsCountByClassId(int classId);
	
}
