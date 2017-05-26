package com.lovo.action;

import javax.servlet.http.Part;

/**
 * ֧���ļ��ϴ��Ľӿ�
 * @author ���
 *
 */
public interface Uploadable {
	
	/**
	 * �����ļ���
	 * @param filenames �ļ���������
	 */
	public void setFilenames(String[] filenames);
	
	/**
	 * �����ϴ��ĸ���
	 * @param parts ����������
	 */
	public void setParts(Part[] parts);
	
}
