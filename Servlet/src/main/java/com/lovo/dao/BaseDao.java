package com.lovo.dao;

import com.lovo.beans.PageBean;
import com.lovo.beans.QueryBean;

import java.util.List;

/**
 * ͨ�����ݷ��ʶ���ӿ�
 * @author ���
 *
 * @param <E> ʵ������
 * @param <K> ʵ���ʶ�ֶε�����
 */
public interface BaseDao <E, K> {
	/**
	 * ����
	 * @param entity ҵ��ʵ�����
	 * @return ���ӳɹ�����true���򷵻�false
	 */
	public K save(E entity);
	
	/**
	 * ɾ��
	 * @param entity ҵ��ʵ�����
	 * @return ɾ���ɹ�����true���򷵻�false
	 */
	public boolean delete(E entity);
	
	/**
	 * ����IDɾ��
	 * @param id ҵ��ʵ�����ı�ʶ
	 * @return ɾ���ɹ�����true���򷵻�false
	 */
	public boolean deleteById(K id);
	
	/**
	 * �޸�
	 * @param entity ҵ��ʵ�����
	 * @return �޸ĳɹ�����true���򷵻�false
	 */
	public boolean update(E entity);
	
	/**
	 * ����ID����ҵ��ʵ�����
	 * @param id ҵ��ʵ�����ı�ʶ
	 * @return ҵ��ʵ���������null
	 */
	public E findById(K id);
	
	/**
	 * ��������ҵ��ʵ�����
	 * @return װ����ҵ��ʵ�������б�����
	 */
	public List<E> findAll();
	
	/**
	 * ��ҳ����ҵ��ʵ�����
	 * @param page ҳ��
	 * @param size ҳ���С
	 * @return ��ǰҳ��ҵ��ʵ�������б�����
	 */
	public PageBean<E> findByPage(int page, int size);

	/**
	 * ��ҳ����ҵ��ʵ�����
	 * @param queryBean ��ѯ��������
	 * @param page ҳ��
	 * @param size ҳ���С
	 * @return ��ǰҳ��ҵ��ʵ�������б�����
	 */
	public PageBean<E> findByPage(QueryBean queryBean, int page, int size);
	
}