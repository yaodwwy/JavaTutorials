package com.adam.sys.userfunc.dao;

import java.util.List;

import com.adam.sys.userfunc.entity.UserRole;

public interface UserRoleDao {
   
    int insert(List<UserRole> list);

    int delete(String userId);

    List<UserRole> query(String userId);
}