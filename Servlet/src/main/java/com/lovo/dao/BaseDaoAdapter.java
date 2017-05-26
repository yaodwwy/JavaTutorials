package com.lovo.dao;


import com.lovo.beans.PageBean;
import com.lovo.beans.QueryBean;

import java.util.List;

/**
 * ͨ�����ݷ��ʶ���(BaseDao)�ӿڵ�ȱʡ������
 * @author ���
 *
 * @param <E> ʵ������
 * @param <K> ʵ���ʶ�ֶε�����
 */
public abstract class BaseDaoAdapter<E, K> implements BaseDao<E, K> {

	@Override
	public K save(E entity) {
		return null;
	}

	@Override
	public boolean delete(E entity) {
		return false;
	}

	@Override
	public boolean deleteById(K id) {
		return false;
	}

	@Override
	public boolean update(E entity) {
		return false;
	}

	@Override
	public E findById(K id) {
		return null;
	}

	@Override
	public List<E> findAll() {
		return null;
	}

	@Override
	public PageBean<E> findByPage(int page, int size) {
		return null;
	}

	@Override
	public PageBean<E> findByPage(QueryBean queryBean, int page, int size) {
		return null;
	}
	
}
