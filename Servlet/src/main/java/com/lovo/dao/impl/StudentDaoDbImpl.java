package com.lovo.dao.impl;

import com.lovo.beans.PageBean;
import com.lovo.dao.BaseDaoDbAdapter;
import com.lovo.dao.StudentDao;
import com.lovo.entity.Student;
import com.lovo.exception.DbSessionException;
import com.lovo.util.DbSessionFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StudentDaoDbImpl 
		extends BaseDaoDbAdapter<Student, Integer>
		implements StudentDao {
	private static final String[] fieldNames = { 
			"id", "name", "gender", "tel", "myClass.id"
	};
	private static final String[] colNames = { 
			"id", "name", "gender", "tel", "class_id"
	};

	@Override
	public PageBean<Student> findByClassId(int classId, int page, int size) {
		try {
			ResultSet rs = DbSessionFactory.openSession().executeQuery(
					"select * from tb_student where class_id=? limit ?,?",
					classId, (page - 1) * size, size);
			List<Student> list = fetchMultiEntities(rs, fieldNames, colNames);
			rs = DbSessionFactory.openSession().executeQuery(
					"select count(id) from tb_student where class_id=?",
					classId);
			return new PageBean<Student>(list, page, size,
					rs.next() ? rs.getInt(1) : 0);
		} catch (SQLException e) {
			throw new DbSessionException(e);
		}
	}

	@Override
	public Student findById(Integer id) {
		try {
			return fetchSingleEntity(DbSessionFactory.openSession().executeQuery(
					"select * from tb_student where id=?", id), fieldNames, colNames);
		} catch (SQLException e) {
			throw new DbSessionException(e);
		}
	}

	@Override
	public boolean update(Student entity) {
		return DbSessionFactory.openSession().executeUpdate(
				"update tb_student set name=?, gender=?, tel=? where id=?", 
				entity.getName(), entity.isGender(), entity.getTel(), entity.getId()).getAffectedRows() == 1;
	}

	@Override
	public boolean deleteById(Integer id) {
		return DbSessionFactory.openSession().executeUpdate(
				"delete from tb_student where id=?", id).getAffectedRows() == 1;
	}

	@Override
	public int getStudentsCountByClassId(int classId) {
		try {
			ResultSet rs = DbSessionFactory.openSession().executeQuery(
					"select count(id) from tb_student where class_id=?", classId);
			return rs.next()? rs.getInt(1) : 0;
		} catch (SQLException e) {
			throw new DbSessionException(e);
		}
	}

}
