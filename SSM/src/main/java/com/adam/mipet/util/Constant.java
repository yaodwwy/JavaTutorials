package com.adam.mipet.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.adam.common.util.Resources;


public class Constant {

	public static final class MSG_PLATFORM{
		public static final String CONF_RESOURCE = "resource";
		public static final String MIPET_PATH = Resources.instance(CONF_RESOURCE).getAttributeValue("mipet_path");//mipet文件目录
		public static final String MSG_URL = Resources.instance(CONF_RESOURCE).getAttributeValue("msg_url");//短信平台
		public static final String MSG_ACCOUNT = Resources.instance(CONF_RESOURCE).getAttributeValue("msg_account");//短信平台
		public static final String MSG_PSWD = Resources.instance(CONF_RESOURCE).getAttributeValue("msg_pswd");//短信平台
	}
	
	public static final String IMG_PATH = Resources.instance("resource").getAttributeValue("img_path");//sdkLinuxPath
	public static final class MSG {

		/**
		 * 返回对象
		 */
		public static Map<String,Object> RESULT_SUCCESS(Object params){
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("success",true);
			map.put("info",params);
			return map;
		}
		/**
		 * 操作成功
		 */
		public static Map<String,Object> RESULT_SUCCESS(){
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("success",true);
			map.put("info","操作成功");
			return map;
		}
		/**
		 * 操作成功入参提示
		 */
		public static Map<String,Object> RESULT_SUCCESS(String msg){
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("success",true);
			map.put("info",msg);
			return map;
		}
		/**
		 * 操作失败
		 */
		public static Map<String,Object> RESULT_ERROR(){
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("success",false);
			map.put("info","操作失败");
			return map;
		}
		/**
		 * 操作失败入参提示
		 */
		public static Map<String,Object> RESULT_ERROR(String msg){
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("success",false);
			map.put("info",msg);
			return map;
		}
		/**
		 * 参数非法
		 */
		public static Map<String,Object> MAP_ILLEGAL(){
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("success",false);
			map.put("info","参数非法");
			return map;
		}
		/**
		 * 系统异常
		 */
		public static Map<String,Object> SYSERROR(){
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("success",false);
			map.put("info","系统异常");
			return map;
		}
		
	}
	public static final class PATH {
		public static final String FILE_PATH = "upload/file";// Excel文件和SDK文件上传地址
		public static final String FILE_PATH1 = File.separator+"upload"+File.separator+"file";// Excel文件和SDK文件上传地址获取
		public static final String CK_IMAGE_PATH = "upload/profileHeadPic";// 图片上传地址
		public static final String CK_IMAGE_PATH1 = File.separator+"upload"+File.separator+"imagefile";//图片地址获取
		
		public static final String ICON_IMAGE_PATH = "upload/iconfile";//图标上传地址
		public static final String ICON_IMAGE_PATH1 = File.separator+"upload"+File.separator+"iconfile";
		public static final String ICON_NOTICE_PATH1 = File.separator+"upload"+File.separator+"notice";
		
		public static final String GETPATH(String savePath){
			String[] filePaths = savePath.split(",");
    		String filePath = "";
    		for(String str:filePaths){
    			filePath+=str+File.separator;
    		}
    		filePath=filePath.substring(0,filePath.length()-1);
    		return filePath;
		}
		public static void main(String[]args){
			System.out.println(GETPATH("them,images,banner"));
		}
	}
	
	public static final class TIME_PATH{
		public static final String format(Date date){
			return new SimpleDateFormat("yyyyMMdd").format(date);
		}
	}
	
}
