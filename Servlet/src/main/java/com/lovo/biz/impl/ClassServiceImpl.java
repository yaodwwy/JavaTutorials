package com.lovo.biz.impl;

import com.lovo.biz.ClassService;
import com.lovo.dao.ClassDao;
import com.lovo.dao.StudentDao;
import com.lovo.dao.impl.ClassDaoDbImpl;
import com.lovo.dao.impl.StudentDaoDbImpl;
import com.lovo.entity.MyClass;
import com.lovo.util.CommonUtil;
import com.lovo.vo.MyClassVO;

import java.util.Date;
import java.util.List;

public class ClassServiceImpl implements ClassService {
	private ClassDao classDao = new ClassDaoDbImpl();
	private StudentDao studentDao = new StudentDaoDbImpl();
	
	@Override
	public List<MyClass> getAllClasses() {
		return classDao.findAll();
	}

	@Override
	public boolean removeClass(int id) {
		if(studentDao.getStudentsCountByClassId(id) == 0) {
			return classDao.deleteById(id);
		}
		return false;
	}

	@Override
	public MyClassVO addNewClass(MyClass myClass) {
		MyClassVO vo = null;
		myClass.setCreateTime(new Date());
		Integer classId = classDao.save(myClass);
		if(classId != null) {
			vo = new MyClassVO();
			vo.setId(classId);
			vo.setName(myClass.getName());
			vo.setCreateTime(CommonUtil.formatDateToString(myClass.getCreateTime()));
		}
		return vo;
	}

}
