package com.adam.sys.userfunc.dao;

import java.util.List;

import com.adam.sys.userfunc.entity.Role;

public interface RoleDao {
	
	List<Role> queryAll();
	
    int delete(Role record);

    int insert(Role record);

    int update(Role record);
    
}