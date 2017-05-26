package com.adam.sys.userfunc.service;

import java.util.List;

import com.adam.sys.userfunc.entity.UserRole;

public interface IUserRoleService {
    int insert(List<UserRole> list);

    int delete(String userId);

    List<UserRole> query(String userId);
}
