package com.adam.mipet.user.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.adam.common.entity.Paging;
import com.adam.common.util.MD5Util;
import com.adam.common.util.Uid;
import com.adam.mipet.user.entity.MipetUser;
import com.adam.mipet.user.service.IMipetUserService;
import com.adam.mipet.util.Constant;
import com.adam.mipet.util.HttpSender;

@Controller
@RequestMapping("/mipet")
public class MipetController {
	
	@Resource
	private IMipetUserService mipetUserService;
	
	//短信接口
	public int getMsgCode(String mobile){
		String url = Constant.MSG_PLATFORM.MSG_URL;
		String account = Constant.MSG_PLATFORM.MSG_ACCOUNT;// 账号
		String pswd = Constant.MSG_PLATFORM.MSG_PSWD;// 密码
		int random = (int) (Math.random()*1000000);
		String msg = "亲爱的用户，您的验证码是"+random+"，5分钟内有效。";// 短信内容
		boolean needstatus = true;// 是否需要状态报告，需要true，不需要false
		String product = null;// 产品ID
		String extno = null;// 扩展码
		try {
			HttpSender.batchSend(url, account, pswd, mobile, msg, needstatus, product, extno);
		} catch (Exception e) {
			e.printStackTrace();
			random = -1;
		}
		return random;
	}
	
	/*//定时更新验证码
	public void updateSessionCode(final HttpSession session){
        final long timeInterval = 1000 * 60 * 5;  //5分钟之后操作
        Runnable runnable = new Runnable() {  
            public void run() {  
                while (true) {  
                    try {  
                        Thread.sleep(timeInterval); 
                        int random = (int) (Math.random()*1000000);
                    	session.setAttribute("code", random);
                    } catch (InterruptedException e) {  
                        e.printStackTrace();  
                    }  
                }  
            }  
        };  
        Thread thread = new Thread(runnable);  
        thread.start();  
	}*/
	
	/**
	 * 用户注册验证短信
	 */
	@RequestMapping("reg.do")
	@ResponseBody
	public Map<String, Object> sign(HttpServletRequest request,String phone,String password,String code,String type) {
		Map<String, Object> map = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		if(type != null && phone != null){
			// 检查是否为重复已注册的手机号
			map.put("phone", phone);
			Integer isRepeat = mipetUserService.queryCheckRepeatPhone(map);
			if (null != isRepeat && isRepeat > 0) {
				return Constant.MSG.RESULT_ERROR("用户名已存在");
			}else{
				//获取返回验证码
				Integer msgCode = getMsgCode(phone);
				if(msgCode!=null && msgCode != -1){
					String randomCode = String.valueOf(msgCode);
					session.setAttribute("code", randomCode);
					//设置自动更新验证码
					//updateSessionCode(session);
					return Constant.MSG.RESULT_SUCCESS();
				}else{
					return Constant.MSG.RESULT_ERROR("短信发送失败,请检查手机号!");
				}
			}
		}
		if (null == phone && null == password && null == code) {
			return  Constant.MSG.SYSERROR();
		}
		try {
			//根据Sesson判断第二次提交的CODE是否一至
			if(!code.equalsIgnoreCase((String) session.getAttribute("code"))){
				return Constant.MSG.RESULT_ERROR("验证码错误");
			}
			MipetUser mipetUser = new MipetUser();
			mipetUser.setPhone(phone);
			mipetUser.setPassword(MD5Util.encryption(password));
			mipetUser.setLoginIp(request.getRemoteAddr());
			int insert = mipetUserService.insert(mipetUser);
			if (insert > 0) {
				map.put("phone", phone);
				MipetUser user = mipetUserService.querybyphone(map);
				return Constant.MSG.RESULT_SUCCESS(user);
			}
			return Constant.MSG.RESULT_ERROR();
		} catch (Exception e) {
			return Constant.MSG.SYSERROR();
		}
	}
	
	/**
	 * 用户密码重设验证短信
	 */
	@RequestMapping("resetpwd.do")
	@ResponseBody
	public Map<String, Object> resetpwd(HttpServletRequest request,String phone,String password,String code,String type,Integer userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		try {
			if (null == type || null == phone) {
				return  Constant.MSG.MAP_ILLEGAL();
			}
			//第一次访问--获取验证码
			if(type.equalsIgnoreCase("code") && phone != null){
				map.put("phone", phone);
				MipetUser user = mipetUserService.querybyphone(map);
				if(user==null){
					return Constant.MSG.RESULT_ERROR("该手机号未注册!");
				}
				//获取返回验证码
				Integer msgCode = getMsgCode(phone);
				if(msgCode!=null && msgCode != -1){
					String randomCode = String.valueOf(msgCode);
					session.setAttribute("code", randomCode);
					//设置自动更新验证码
					//updateSessionCode(session);
					return Constant.MSG.RESULT_SUCCESS();
				}else{
					return Constant.MSG.RESULT_ERROR("短信发送失败,请检查手机号!");
				}
			//第二次访问--获取用户Id和手机号
			}else if(type.equalsIgnoreCase("start") && phone != null && code != null){
				if(code.equalsIgnoreCase((String) session.getAttribute("code"))){
					map.put("phone", phone);
					MipetUser user = mipetUserService.querybyphone(map);
					return Constant.MSG.RESULT_SUCCESS(user);
				}else{
					return Constant.MSG.RESULT_ERROR("验证码错误");
				}
			//第三次访问--修改密码
			}else if(type.equalsIgnoreCase("end") && userId != null && password != null ){
				MipetUser user = mipetUserService.querybyid(userId);
				user.setPassword(MD5Util.encryption(password));
				mipetUserService.updatepwd(user);
				return Constant.MSG.RESULT_SUCCESS(user);
			}else{
				return  Constant.MSG.MAP_ILLEGAL();
			}
		} catch (Exception e) {
			return Constant.MSG.SYSERROR();
		}
	}
	
	/**
	 * 修改密码操作
	 */
	@RequestMapping("updatepwd.do")
	@ResponseBody
	public Map<String, Object> updatepwd(String phone, String pwdold, String pwdnew) {
		if (null == phone || null == pwdold ||null == pwdnew) {
			return Constant.MSG.MAP_ILLEGAL();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("phone", phone);
		map.put("password", MD5Util.encryption(pwdold));
		MipetUser mipetUser = mipetUserService.queryLogin(map);
		if (null == mipetUser) {
			return Constant.MSG.RESULT_ERROR("旧密码验证失败");
		} else {
			mipetUser.setPassword(MD5Util.encryption(pwdnew));
			mipetUserService.updatepwd(mipetUser);
			return Constant.MSG.RESULT_SUCCESS();
		}
	}
	
	/**
	 * 用户登录
	 */
	@RequestMapping("login.do")
	@ResponseBody
	public Map<String, Object> login(HttpServletRequest request , String phone,String password,String clientId) {
		if (null == phone && null == password && null == clientId) {
			return Constant.MSG.MAP_ILLEGAL();
		}
		try {
			/**
			 * 验证密码
			 */
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("phone", phone);
			map.put("password", MD5Util.encryption(password));
			MipetUser mipetUser = mipetUserService.queryLogin(map);
			if (null == mipetUser) {
				return Constant.MSG.RESULT_ERROR("用户名或密码错误");
			}
			/**
			 * 验证用户状态是否为黑名单
			 */
			if (mipetUser.getEnabled() > 0) {
				return Constant.MSG.RESULT_ERROR("用户名账户异常，请联系管理员");
			}
			mipetUser.setLoginIp(request.getRemoteAddr());
			//更新设备ID
			if(null != clientId && !clientId.equals(mipetUser.getClientId())){
				mipetUser.setClientId(clientId);
				mipetUserService.updateClientId(mipetUser);
			}
			mipetUserService.update(mipetUser);
			return Constant.MSG.RESULT_SUCCESS(mipetUser);
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.MSG.SYSERROR();
		}
	}
	
	/**
	 * 根据ID查用户
	 */
	@RequestMapping("queryById.do")
	@ResponseBody
	public MipetUser queryById(Integer id) {
		MipetUser mipetUser = new MipetUser();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			Paging<MipetUser> query = mipetUserService.query(map);
			mipetUser = query.getRows().get(0);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			mipetUser.setNickname("管理员");
		}
		return mipetUser;
	}
	
	/**
	 * 修改用户个人设置
	 */
	@RequestMapping("update.do")
	@ResponseBody
	public Map<String, Object> update(Integer id,String nickname,Integer gender,String family,String birthday,String headPic) {
		
		if (null == id || nickname == null || gender == null || family == null || birthday == null || headPic == null) {
			return Constant.MSG.MAP_ILLEGAL();
		}
		try {
			Date birth = new SimpleDateFormat("yyyy-MM-dd").parse(birthday);
			MipetUser mipetUser = new MipetUser(id,nickname,gender,birth,family,headPic);
			mipetUserService.update(mipetUser);
			return Constant.MSG.RESULT_SUCCESS();
		} catch (Exception e) {
			return Constant.MSG.SYSERROR();
		}
	}
	
	/**
	 * 修改用户个人设置
	 */
	@RequestMapping("updateStatus.do")
	@ResponseBody
	public Map<String, Object> updateStatus(Integer id,Integer enabled) {
		
		if (null == id || enabled == null ) {
			return Constant.MSG.MAP_ILLEGAL();
		}
		try {
			MipetUser mipetUser = new MipetUser();
			mipetUser.setId(id);
			mipetUser.setEnabled(enabled);
			mipetUserService.updateStatus(mipetUser);
			return Constant.MSG.RESULT_SUCCESS();
		} catch (Exception e) {
			return Constant.MSG.SYSERROR();
		}
	}
	
	/**
	 * 删除用户
	 */
	@RequestMapping("delete.do")
	@ResponseBody
	public Map<String, Object> delete(MipetUser mipetUser) {
		if (null == mipetUser) {
			return Constant.MSG.MAP_ILLEGAL();
		}
		try {
			mipetUserService.delete(mipetUser);
			return Constant.MSG.RESULT_SUCCESS();
		} catch (Exception e) {
			return Constant.MSG.SYSERROR();
		}
	}
	
	/**
	 * 全部用户数据分页查询
	 */
	@RequestMapping("queryall.do")
	@ResponseBody
	public Paging<MipetUser> query(MipetUser mipetUser, int page, int rows) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (null != mipetUser && !"".equals(mipetUser.getNickname())) {
			map.put("nickname", mipetUser.getNickname());
		}
		if (null != mipetUser && !"".equalsIgnoreCase(mipetUser.getPhone())) {
			map.put("phone", mipetUser.getPhone());
		}
		int start = rows * (page - 1);
		map.put("start", start);
		map.put("rows", rows);
		Paging<MipetUser> paging = mipetUserService.query(map);
		return paging;
	}
	
	/**
	 * 图片上传
	 * @param request 
	 * @param fileObjectId input文件域ID
	 * @param MaxSize 允许最大大小
	 * @param suffixStr 格式逗号分割
	 * @param savePath 保存路径
	 * @return
	 */
		@RequestMapping("imageUpload")
		public Map<String, Object> fileUpload(HttpServletRequest request,String fileObjectId,int MaxSize,String suffixStr,String savePath){
			MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;  
	        MultipartFile file  =  multipartRequest.getFile(fileObjectId);//获取文件流
	        String fileName = file.getOriginalFilename();//获取文件名
	        String suffix = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());//获取文件后缀名
	        String[] suffixs = suffixStr.split(",");
	        boolean isSuffix = false;
	        for(String str:suffixs){
	        	if(str==suffix||str.equals(suffix)){
	        		isSuffix=true;
	        	}
	        }
	        Map<String, Object> result = null;
			String path = Constant.IMG_PATH;
			String resultName = Uid.getUid()+"."+suffix;//新文件名
	        if(isSuffix){
	        	if(file.getSize()/1024<=MaxSize){
	        		//判断windows
	        		String osName = System.getProperty("os.name");
	        		if(StringUtils.isNotBlank(osName)&&osName.startsWith("Windows")){
	        			path = request.getSession().getServletContext().getRealPath("")+Constant.PATH.GETPATH(savePath); 
	        		}
	            	File targetFile = new File(path, resultName);
	                if(!targetFile.exists()){ //文件夹是否存在,自动新建文件夹
	                    targetFile.mkdirs();  
	                }  
	                try {
	                	//复制到服务器上
	                    file.transferTo(targetFile);
	                    result = Constant.MSG.RESULT_SUCCESS(resultName);
	                } catch (Exception e) {  
	                	return Constant.MSG.SYSERROR();
	                }  	
	        	}else{
	        		result = Constant.MSG.RESULT_ERROR("图片大小超过限制");
	        	}
	        }else{
	        	result = Constant.MSG.RESULT_ERROR("图片类型不正确");
	        }
			return result;
		}
		
		
	
}
