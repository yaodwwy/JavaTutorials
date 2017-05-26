package com.adam.common.constant;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.adam.common.util.Resources;


public class Constant {
	public static final String CONF_RESOURCE = "resource";
	public static final String HTTP_URL = Resources.instance(CONF_RESOURCE).getAttributeValue("http_url");//接口url
	public static final String EXCEL_PATH = Resources.instance(CONF_RESOURCE).getAttributeValue("excel_path");//excelLinuxPath
	public static final String SDK_PATH = Resources.instance(CONF_RESOURCE).getAttributeValue("sdk_path");//sdkLinuxPath
	public static final String IMG_PATH = Resources.instance(CONF_RESOURCE).getAttributeValue("img_path");//imgLinuxPath
	public static final String LB_APPKEY = Resources.instance(CONF_RESOURCE).getAttributeValue("appkey");//imgLinuxPath
	public static final String LB_SECRETKEY = Resources.instance(CONF_RESOURCE).getAttributeValue("secretkey");//imgLinuxPath
	
	
	public static final class PAGE{
		/**
		 * 指向:sys/userlogin/controller/UserController.java
		 */
		public static final String PLOGIN = "sys/login";
		/**
		 * 指向:webapp/WEB-INF/sys/main.jsp
		 */
		public static final String PMAIN = "sys/main";
	}
	
	public static final class MSG {
		public static final String CODE_NULL = "用户名不能为空";
		public static final String PWD_NULL = "密码不能为空";
		public static final String ADMIN_NULL = "该用户不存在";
		public static final String ROLE_NULL = "对不起，您没有权限";
		public static final String SYS_ERR = "系统错误，请联系管理员";
		public static final String IMG_WH = "图片长宽必须为";
		public static final String IMG_TYPE_ERROR = "图片格式不正确（必须为.jpg/.gif/.bmp/.jpeg/.png文件）";
		public static final String IMG_SIZE_ERROR = "图片大小不能超过2M";
		public static final String ICON_SIZE_ERROR = "图片大小不能超过50KB";
		public static final String UNKNOW_ERROR = "未知错误，请联系管理员";
		public static final String FILE_SIZE = "文件大小不可超过";
		public static final String FILE_TYPE = "文件格式不符，请确认后上传";
		public static final String SAVE_OK = "{\"success\":\"true\",\"title\":\"成功\",\"content\":\"保存成功\"}";
		public static final String DEL_OK ="{\"success\":\"true\",\"title\":\"成功\",\"content\":\"删除成功\"}";
		public static final String UPDATE_OK ="{\"success\":\"true\",\"title\":\"成功\",\"content\":\"修改成功\"}";
		public static final String SAVE_ERR = "{\"success\":\"false\",\"title\":\"失败\",\"content\":\"保存失败\"}";
		public static final String DEL_ERR = "{\"success\":\"false\",\"title\":\"失败\",\"content\":\"删除失败\"}";
		public static final String UPDATE_ERR = "{\"success\":\"false\",\"title\":\"失败\",\"content\":\"修改失败\"}";
		
		/**
		 * 正常返回
		 */
		public static Map<String,Object> RESULT(Object params){
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("result",true);
			map.put("code",1001);
			map.put("info",params);
			map.put("reason",null);
			return map;
		}
		
		public static Map<String,Object> MAP_ILLEGAL(){
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("title","提示");
			map.put("content","参数非法！");
			map.put("success","error");
			return map;
		}
		
		public static Map<String,Object> MAP_NOEMAIL(){
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("title","提示");
			map.put("content","该邮箱未注册");
			map.put("success","error");
			return map;
		}
		
		public static Map<String,Object> MAP_CHECKCODE_ERROR(){
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("title","提示");
			map.put("content","验证码错误");
			map.put("success","error");
			return map;
		}
		
		public static Map<String,Object> MAP_SESSIONERR(){
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("title","提示");
			map.put("content","登录超时");
			map.put("success","session");
			return map;
		}
		
		public static Map<String,Object> MAP_SYSERROR(){
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("title","提示");
			map.put("content","系统异常");
			map.put("success","error");
			return map;
		}
		
		public static Map<String,Object> MAP_COMPANYERR(){
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("title","提示");
			map.put("content","您未绑定公司信息，不允许发出邀请！");
			map.put("success","session");
			return map;
		}
		/**
		 * 操作失败
		 */
		public static Map<String,Object> MAP_ERR(){
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("title","提示");
			map.put("content","操作失败");
			map.put("success","error");
			return map;
		}
		
		public static Map<String,Object> MAP_ERR_COMMON(String  msg){
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("title","提示");
			map.put("content",msg);
			map.put("success","error");
			return map;
		}
		
		
		public static Map<String,Object> MAP_PARAMERROR(){
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("title","提示");
			map.put("content","请求参数错误！");
			map.put("success","error");
			return map;
		}
		
		public static Map<String,Object> MAP_PWDERROR(){
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("title","提示");
			map.put("content","密码错误！");
			map.put("success","error");
			return map;
		}
		
		public static Map<String,Object> MAP_LOGINERR(){
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("title","提示");
			map.put("content","用户名或密码错误");
			map.put("success","error");
			return map;
		}
		
		public static Map<String,Object> MAP_BLACKUSER(){
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("title","提示");
			map.put("content","用户帐户异常，请联系管理员");
			map.put("success","error");
			return map;
		}
		
		public static Map<String,Object> MAP_JIHUOERR(){
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("title","提示");
			map.put("content","链接已失效");
			map.put("success","error");
			return map;
		}
		public static Map<String,Object> MAP_OK(){
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("title","提示");
			map.put("content","操作成功");
			map.put("success","info");
			return map;
		}
		
		/**
		 *提示相关内容
		 **/
		public static Map<String,Object> MAP_OK(String msg){
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("title","提示");
			map.put("content",msg);
			map.put("success","info");
			return map;
		}
		
		public static Map<String,Object> MAP_USNM(){
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("title","提示");
			map.put("content","用户名已存在");
			map.put("success","error");
			return map;
		}
		
		public static Map<String,Object> MAP_OLDPWD(){
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("title","提示");
			map.put("content","旧密码验证失败");
			map.put("success","error");
			return map;
		}
		
		public static Map<String,Object> MAP_DICCODECF(){
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("title","提示");
			map.put("content","CODE已存在");
			map.put("success","error");
			return map;
		}
		

		
		public static Map<String,Object> MAP_DELIVER_ERR(String errorMsg){
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("title","提示");
			map.put("content",errorMsg);
			map.put("success","repeat");
			return map;
		}
		
		

		
		public static Map<String,Object> MAP_INDEXMAX(){
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("title","提示");
			map.put("content","已经置底，无法下移");
			map.put("success","error");
			return map;
		}
		
		public static Map<String,Object> MAP_INDEXMIN(){
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("title","提示");
			map.put("content","已经置顶，无法上移");
			map.put("success","error");
			return map;
		}
		public static final Map<String,Object> ERR_COMMON(String msg){
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("title","失败");
			map.put("content",msg);
			map.put("success","error");
			return map;
		}
		
		public static final Map<String,Object> OK_COMMON(String msg){
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("title","成功");
			map.put("content",msg);
			map.put("success","info");
			return map;
		}
		
		public static final Map<String,Object> SAVE_OK(){
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("title","成功");
			map.put("content","保存成功");
			map.put("success","info");
			return map;
		}
		
		public static final Map<String,Object> UPD_OK(){
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("title","成功");
			map.put("content","修改成功");
			map.put("success","info");
			return map;
		}
		public static final Map<String,Object> MAIL_OK(){
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("title","成功");
			map.put("content","邮件发送成功");
			map.put("success","info");
			return map;
		}
		public static final Map<String,Object> ARTICLE_OK(){
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("title","成功");
			map.put("content","文章发表成功");
			map.put("success","info");
			return map;
		}
		public static final Map<String,Object> ARTICLE_ERROR(){
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("title","失败");
			map.put("content","文章发表失败");
			map.put("success","error");
			return map;
		}
		public static final Map<String,Object> MAIL_ERROR(){
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("title","失败");
			map.put("content","邮件发送失败");
			map.put("success","error");
			return map;
		}
		
		public static final Map<String,Object> DEL_OK(){
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("title","成功");
			map.put("content","删除成功");
			map.put("success","info");
			return map;
		}
		
		public static final Map<String,Object> SAVE_ERROR(){
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("title","失败");
			map.put("content","保存失败");
			map.put("success","error");
			return map;
		}
		public static final Map<String,Object> SAVE_ERROR(String str){
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("title","失败");
			map.put("content",str);
			map.put("success","error");
			return map;
		}
		
		public static final Map<String,Object> UPD_ERROR(){
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("title","失败");
			map.put("content","修改失败");
			map.put("success","error");
			return map;
		}
		
		public static final Map<String,Object> DEL_ERROR(){
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("title","失败");
			map.put("content","删除失败");
			map.put("success","error");
			return map;
		}
		public static String getCKEditorMsg(String CKEditorFuncNum,
				String imgName, String errorMsg) {
			return "<script type=\"text/javascript\">window.parent.CKEDITOR.tools.callFunction("
					+ CKEditorFuncNum
					+ ", '"
					+ imgName
					+ "','"
					+ errorMsg
					+ "');</script>";
		}
	}
	public static final class PATH {
		public static final String FILE_PATH = "upload/file";// Excel文件和SDK文件上传地址
		public static final String FILE_PATH1 = File.separator+"upload"+File.separator+"file";// Excel文件和SDK文件上传地址获取
		public static final String CK_IMAGE_PATH = "upload/imagefile";// 图片上传地址
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
	public static final class FILE_TYPE {
		public static List<String> imgTypes() {
			List<String> fileTypes = new ArrayList<String>();
			fileTypes.add("jpg");
			fileTypes.add("jpeg");
			fileTypes.add("bmp");
			fileTypes.add("gif");
			fileTypes.add("png");
			return fileTypes;
		}
		public static List<String> sdkFileTypes(){
			List<String> fileTypes = new ArrayList<String>();
			fileTypes.add("zip");
			fileTypes.add("rar");
			fileTypes.add("xls");
			fileTypes.add("xlsx");
			return fileTypes;
		}
	}
}
