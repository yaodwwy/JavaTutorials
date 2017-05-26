package com.lovo.biz;

import com.lovo.entity.MyClass;
import com.lovo.vo.MyClassVO;

import java.util.List;

public interface ClassService {

	public List<MyClass> getAllClasses();
	
	public boolean removeClass(int id);
	
	public MyClassVO addNewClass(MyClass myClass);
}
