package com.adam.sys.userfunc.service;

import java.util.Map;

import com.adam.common.entity.Paging;
import com.adam.sys.userfunc.entity.User;


public interface IUserService {

    int delete(User record);

    int insert(User record);

    User queryLogin(Map<String, Object> map);

    int update(User record);

    Paging<User> queryAll(Map<String, Object> map);
    
    int queryCheckUserName(String username);
	
    void updatepwd(User user);
}
