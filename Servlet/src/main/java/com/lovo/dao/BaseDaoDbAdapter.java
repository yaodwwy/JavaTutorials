package com.lovo.dao;


import com.lovo.util.ReflectionUtil;

import java.lang.reflect.ParameterizedType;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 通用数据访问对象(BaseDao)接口的基于数据库的缺省适配器
 * @author 骆昊
 *
 * @param <E> 实体类型
 * @param <K> 实体标志字段的类型
 */
@SuppressWarnings("unchecked")
public abstract class BaseDaoDbAdapter<E, K> extends BaseDaoAdapter<E, K> {
	private Class<?> entityClass;
	
	public BaseDaoDbAdapter() {
		ParameterizedType pt = (ParameterizedType) getClass().getGenericSuperclass();
		entityClass = (Class<?>) pt.getActualTypeArguments()[0];
	}
	
	/**
	 * 通过游标获取单个实体对象
	 * @param rs 游标
	 * @return 实体对象 
	 */
	protected E fetchSingleEntity(ResultSet rs) throws SQLException {
		E entity = null;
		ResultSetMetaData rsmd = rs.getMetaData();
		if(rs.next()) {
			try {
				entity = (E) entityClass.getConstructor().newInstance();
				String[] fieldNames = ReflectionUtil.getFieldNames(entity);
				for(int i = 0; i < fieldNames.length && i < rsmd.getColumnCount(); i++) {
					ReflectionUtil.setValue(entity, fieldNames[i], rs.getObject(fieldNames[i]));
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return entity;
	}
	
	/**
	 * 通过游标获取单个实体对象
	 * @param rs 游标
	 * @param fieldNames 实体字段的名字
	 * @param colNames 数据库中和实体字段对应的列的名字
	 * @return 实体对象 
	 */
	protected E fetchSingleEntity(ResultSet rs, String[] fieldNames, String[] colNames) 
			throws SQLException {
		E entity = null;
		ResultSetMetaData rsmd = rs.getMetaData();
		if(rs.next()) {
			try {
				entity = (E) entityClass.getConstructor().newInstance();
				for(int i = 0; i < colNames.length && i < rsmd.getColumnCount(); i++) {
					ReflectionUtil.setValue(entity, fieldNames[i], rs.getObject(colNames[i]));
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return entity;
	}
	
	/**
	 * 通过游标获取多个实体对象
	 * @param rs 游标
	 * @return 实体对象的列表容器
	 */
	protected List<E> fetchMultiEntities(ResultSet rs) throws SQLException {
		List<E> list = new ArrayList<>();
		ResultSetMetaData rsmd = rs.getMetaData();
		while(rs.next()) {
			try {
				E entity = (E) entityClass.getConstructor().newInstance();
				String[] fieldNames = ReflectionUtil.getFieldNames(entity);
				for(int i = 0; i < fieldNames.length && i < rsmd.getColumnCount(); i++) {
					ReflectionUtil.setValue(entity, fieldNames[i], rs.getObject(fieldNames[i]));
				}
				list.add(entity);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return list.size() == 0 ? Collections.EMPTY_LIST : list;
	}
	
	/**
	 * 通过游标获取多个实体对象
	 * @param rs 游标
	 * @param fieldNames 实体字段的名字
	 * @param colNames 数据库中和实体字段对应的列的名字
	 * @return 实体对象的列表容器
	 */
	protected List<E> fetchMultiEntities(ResultSet rs, String[] fieldNames, String[] colNames) 
			throws SQLException {
		List<E> list = new ArrayList<>();
		ResultSetMetaData rsmd = rs.getMetaData();
		while(rs.next()) {
			try {
				E entity = (E) entityClass.getConstructor().newInstance();
				for(int i = 0; i < colNames.length && i < rsmd.getColumnCount(); i++) {
					ReflectionUtil.setValue(entity, fieldNames[i], rs.getObject(colNames[i]));
				}
				list.add(entity);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return list.size() == 0 ? Collections.EMPTY_LIST : list;
	}
}
