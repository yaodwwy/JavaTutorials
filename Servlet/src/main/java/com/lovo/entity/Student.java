package com.lovo.entity;

import java.io.Serializable;

/**
 * ѧ��
 * 
 * @author ���
 *
 */
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private boolean gender;
	private String tel;
	private MyClass myClass;

	public Student() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public MyClass getMyClass() {
		return myClass;
	}

	public void setMyClass(MyClass myClass) {
		this.myClass = myClass;
	}

}
