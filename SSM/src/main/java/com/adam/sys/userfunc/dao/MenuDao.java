package com.adam.sys.userfunc.dao;

import java.util.List;
import java.util.Map;

import com.adam.sys.userfunc.entity.Menu;

public interface MenuDao {
	int delete(Menu record);

	int insert(Menu record);

	int update(Menu record);

	
	String queryNameByid(Integer id);
	
	List<Menu> queryMainMenu(Map<String, Object> map);

	List<Menu> queryMenuY(Map<String, Object> map);

	List<Menu> queryMenuN(Map<String, Object> map);


	String queryPrecMenuBySort(String menuId);
	String queryNextMenuBySort(String menuId);


	Integer updateSortUp(String menuId);
	Integer updateSortDown(String menuId);
    
    List<Menu> queryMenuByParentId(int parentId);
    
    String queryParentMenuName(int menuId);
}