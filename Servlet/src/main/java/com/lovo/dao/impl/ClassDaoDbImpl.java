package com.lovo.dao.impl;

import com.lovo.dao.BaseDaoDbAdapter;
import com.lovo.entity.MyClass;
import com.lovo.exception.DbSessionException;
import com.lovo.util.DbSessionFactory;
import com.lovo.dao.ClassDao;

import java.util.List;

public class ClassDaoDbImpl extends BaseDaoDbAdapter<MyClass, Integer> implements ClassDao {

	@Override
	public List<MyClass> findAll() {
		try {
			return fetchMultiEntities(DbSessionFactory.openSession()
					.executeQuery("select * from tb_class"));
		} catch (Exception e) {
			throw new DbSessionException(e);
		}
	}

	@Override
	public boolean deleteById(Integer id) {
		return DbSessionFactory.openSession().executeUpdate(
				"delete from tb_class where id=?", id).getAffectedRows() == 1;
	}

	@Override
	public Integer save(MyClass entity) {
		return new Long(DbSessionFactory.openSession().executeUpdate(
				"insert into tb_class values (default, ?, ?)", 
				entity.getName(), entity.getCreateTime()).getGeneratedKey()).intValue();
	}

}
