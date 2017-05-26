package com.adam.common.util;

import java.lang.reflect.Field;
import java.util.Date;

public class MybatisUtil {
	
	
	@SuppressWarnings("all")
	public static String getClassMapperString(Class cls){
		Field[] fs=cls.getDeclaredFields();
		String str = "";
		for(Field f:fs){
			String type="";
			if(f.getType()==String.class){
				type="VARCHAR";
			}else if(f.getType()==Date.class){
				type="DATE";
			}else if(Integer.class.isAssignableFrom(f.getType())){
				type="INTEGER";
			}
			str += "<result column=\""+buildField(f.getName())+"\" property=\""+f.getName()+"\" jdbcType=\""+type+"\"/>\n";
		}
		return str;
	}
	
	public static String getSQLName(String str){
		return buildField(str);
	}
	
	public static void main(String[]args){
		
	}
	static String buildField(String name){
		String n="";
		for(char c:name.toCharArray()){
			if(c<'a'){
				n+="_";
			}
			n+=c;
		}
		return n.toUpperCase();
	}
}
