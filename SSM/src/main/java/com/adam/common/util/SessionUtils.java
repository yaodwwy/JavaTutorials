package com.adam.common.util;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.adam.sys.userfunc.entity.User;

public final class SessionUtils {
	
//	protected static final Logger logger = Logger.getLogger(SessionUtils.class);
	
	private static final String SESSION_USER = "admin";

	private static final String SESSION_VALIDATECODE = "session_validatecode";//验证码
	
	
	private static final String SESSION_ACCESS_URLS = "session_access_urls"; //系统能够访问的URL
	
	private static final String SESSION_MENUBTN_MAP = "session_menubtn_map"; //系统菜单按钮
	
	private static final String SESSION_F_KEY = "session_fkey"; //key
	
	private static final String SESSION_S_KEY = "session_skey"; //key
	
	private static final String SESSION_T_KEY = "session_tkey"; //key

	
	/**
	  * 设置session的值
	  * @param request
	  * @param key
	  * @param value
	  */
	 public static void setAttr(HttpServletRequest request,String key,Object value){
		 request.getSession(true).setAttribute(key, value);
	 }
	 
	 
	 /**
	  * 获取session的值
	  * @param request
	  * @param key
	  * @param value
	  */
	 public static Object getAttr(HttpServletRequest request,String key){
		 return request.getSession(true).getAttribute(key);
	 }
	 
	 /**
	  * 删除Session值
	  * @param request
	  * @param key
	  */
	 public static void removeAttr(HttpServletRequest request,String key){
		 request.getSession(true).removeAttribute(key);
	 }
	 
	 /**
	  * 设置管理员信息 到session
	  * @param request
	  * @param user
	  */
	 public static void setUser(HttpServletRequest request,User user){
		 request.getSession(true).setAttribute(SESSION_USER, user);
	 }
	 
	 /**
	  * 从session中获取用户信息
	  * @param request
	  * @return Employee
	  */
	 public static User getUser(HttpServletRequest request){
		return (User)request.getSession(true).getAttribute(SESSION_USER);
	 }
	 
	 /**
	  * 从cookies中获取用户密码
	  * @param request
	  * @return Employee
	  */
	 public static String getUserByCookie(HttpServletRequest request){
		 Cookie[] cookies = request.getCookies();
			String[] cooks = null;  
			String password = null;  
			if (cookies != null) {  
		    for (Cookie coo : cookies) {  
		        String aa = coo.getValue();  
		        cooks = aa.split("==");  
		        if (cooks.length == 2) {  
		            password = cooks[1];  
		        }  
		    }  
		}
			return password;
	 }
	 /**
	  * 从session中获取用户信息
	  * @param request
	  * @return SysUser
	  */
	 public static Integer getUserId(HttpServletRequest request){
		 User user = getUser(request);
		 if(user != null){
			 return user.getId();
		 }
		return null; 
	 }
	 
	 
	 /**
	  * 从session中获取用户信息
	  * @param request
	  * @return SysUser
	  */
	 public static void removeUser(HttpServletRequest request){
		removeAttr(request, SESSION_USER);
	 }
	 
	 
	 /**
	  * 设置验证码 到session
	  * @param request
	  * @param user
	  */
	 public static void setValidateCode(HttpServletRequest request,String validateCode){
		 request.getSession(true).setAttribute(SESSION_VALIDATECODE, validateCode);
	 }
	 
	 
	 /**
	  * 从session中获取验证码
	  * @param request
	  * @return SysUser
	  */
	 public static String getValidateCode(HttpServletRequest request){
		return (String)request.getSession(true).getAttribute(SESSION_VALIDATECODE);
	 }
	 

	 /**
	  * 设置验证码 到session
	  * @param request
	  * @param user
	  */
	 public static void setKeys(HttpServletRequest request,String code1,String code2,String code3){
		 request.getSession(true).setAttribute(SESSION_F_KEY, code1);
		 request.getSession(true).setAttribute(SESSION_S_KEY, code2);
		 request.getSession(true).setAttribute(SESSION_T_KEY, code3);
	 }
	 
	 
	 /**
	  * 从session中获取fkey
	  * @param request
	  * @return SysUser
	  */
	 public static String getFkey(HttpServletRequest request){
		return (String)request.getSession(true).getAttribute(SESSION_F_KEY);
	 }
	 
	 /**
	  * 从session中获取skey
	  * @param request
	  * @return SysUser
	  */
	 public static String getSkey(HttpServletRequest request){
		return (String)request.getSession(true).getAttribute(SESSION_S_KEY);
	 }
	 
	 /**
	  * 从session中获取tkey
	  * @param request
	  * @return SysUser
	  */
	 public static String getTkey(HttpServletRequest request){
		 return (String)request.getSession(true).getAttribute(SESSION_T_KEY);
	 }
	 
	 /**
	  * 从session中获删除验证码
	  * @param request
	  * @return SysUser
	  */
	 public static void removeValidateCode(HttpServletRequest request){
		removeAttr(request, SESSION_VALIDATECODE);
	 }
	 
	 /**
	  * 设置可访问url
	  * @param request
	  * @return
	  */
	 public static void setAccessUrl(HttpServletRequest request,List<String> accessUrls){ //判断登录用户是否超级管理员
		 setAttr(request, SESSION_ACCESS_URLS, accessUrls);
	 }
	 
	 
	 
	 /**
	  * 判断URL是否可访问
	  * @param request
	  * @return
	  */
	 public static boolean isAccessUrl(HttpServletRequest request,String url){ 
		 if(url.lastIndexOf(".do")>-1){//目前不做按钮权限控制  请求都需以“。do”请求
			 return true;
		 }
		 List<String> accessUrls = (List)getAttr(request, SESSION_ACCESS_URLS);
		 if(accessUrls == null ||accessUrls.isEmpty() || !accessUrls.contains(url)){
			 return false;
		 }
		 return true;
	 }
	 
	 
	 /**
	  * 设置菜单按钮
	  * @param request
	  * @param btnMap
	  */
	 public static void setMemuBtnMap(HttpServletRequest request,Map<String,List> btnMap){ //判断登录用户是否超级管理员
		 setAttr(request, SESSION_MENUBTN_MAP, btnMap);
	 }
	 
	 /**
	  * 获取菜单按钮
	  * @param request
	  * @param btnMap
	  */
	 public static List<String> getMemuBtnListVal(HttpServletRequest request,String menuUri){ //判断登录用户是否超级管理员
		 Map btnMap  = (Map)getAttr(request, SESSION_MENUBTN_MAP);
		 if(btnMap == null || btnMap.isEmpty()){
			 return null;
		 }
		 return (List<String>)btnMap.get(menuUri);
	 }
	 
//		private static final String SESSION_ACCESS_URLS = "session_access_urls"; //系统能够访问的URL
//		
//		
//		private static final String SESSION_MENUBTN_MAP = "session_menubtn_map"; //系统菜单按钮
	
}
