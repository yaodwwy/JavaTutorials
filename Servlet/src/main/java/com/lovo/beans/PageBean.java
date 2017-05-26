package com.lovo.beans;

import java.util.List;

/**
 * ��ҳ������
 * 
 * @author ���
 *
 * @param <T> ʵ�����͵ķ��Ͳ���
 */
public class PageBean <T> {
	private List<T> data;
	private int currentPage;
	private int pageSize;
	private int totalPage;
	
	public PageBean() {
	}
	
	/**
	 * ������
	 * @param data ����
	 * @param currentPage ��ǰҳ��
	 * @param size ҳ���С
	 * @param count �ܼ�¼��
	 */
	public PageBean(List<T> data, int currentPage, int size, int count) {
		this.data = data;
		this.currentPage = currentPage;
		this.pageSize = size;
		this.totalPage = count % size == 0? count / size : count / size + 1;
	}

	public List<T> getData() {
		return data;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getTotalPage() {
		return totalPage;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	/**
	 * ��û����һҳ
	 */
	public boolean hasNextPage() {
		return currentPage < totalPage;
	}
	
	/**
	 * ��û����һҳ
	 */
	public boolean hasPrevPage() {
		return currentPage > 1;
	}

}
