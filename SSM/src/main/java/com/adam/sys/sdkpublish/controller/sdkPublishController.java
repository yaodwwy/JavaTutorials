package com.adam.sys.sdkpublish.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.adam.common.constant.Constant;
import com.adam.sys.sdkpublish.entity.SdkPublishEntity;
import com.adam.sys.sdkpublish.service.SdkPublishService;

/**
 * 类名称：sdkpublishController
 * 类描述：sdk版本控制
 *
 */
@Controller
@RequestMapping("/sdk")
public class sdkPublishController {

	@Resource
	private SdkPublishService sdkService;
	private static Logger logger = Logger.getLogger(sdkPublishController.class);
	
	
	@ResponseBody
	@RequestMapping("update")
	public Map<String, Object> update(SdkPublishEntity entity) {
		try {
			sdkService.update(entity);
			return Constant.MSG.MAP_OK();
		} catch (Exception e) {
			return Constant.MSG.MAP_ERR();
		}
	}
	
	@ResponseBody
	@RequestMapping("query")
	public SdkPublishEntity query() {
		return sdkService.query(); 
	}
	
	/**
	 * SDK上传
	 */
	@RequestMapping("/fileUpload")
	public void fileUpload(HttpServletRequest request,HttpServletResponse response,
			String fileObjectId,int MaxSize,String suffixStr,String savePath){
		MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;  
        MultipartFile file  =  multipartRequest.getFile(fileObjectId);//获取文件流
        String fileName = file.getOriginalFilename();//获取文件名
        String fileFirstName = fileName.substring(0,fileName.lastIndexOf("."));//获取文件后缀名
        String suffix = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());//获取文件后缀名
        String[] suffixs = suffixStr.split(",");
        boolean isSuffix = false;
        for(String str:suffixs){
        	if(str==suffix||str.equals(suffix)){
        		isSuffix=true;
        	}
        }
        String result = "";
		String msg = "";
		String path = Constant.SDK_PATH;
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("-yyyyMMddhhmmss");
		String resultName = fileFirstName+sdf.format(date)+"."+suffix;//新文件名
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
                    result="true";
                } catch (Exception e) {  
                	msg = Constant.MSG.UNKNOW_ERROR;
                }  	
        	}else{
        		msg = Constant.MSG.FILE_SIZE+MaxSize+"KB";
        		result = "false";
        	}
        }else{
        	msg = Constant.MSG.FILE_TYPE;
        	result = "false";
        }
        response.setContentType("text/html; charset=UTF-8");  
        PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			logger.info(e.getMessage());
			System.out.println("上传sdk错误："+e);
		}
        String res = "{ success:'"+result+"', msg:'"+msg+"',fileUrl:'"+resultName+"'}";
        out.print(res);
        out.flush();
	}
	/**
	 * sdk下载
	 */
	@RequestMapping("/download")
	public void download(HttpServletRequest request,HttpServletResponse response){
		response.setContentType("application/x-download");
		SdkPublishEntity sdk = sdkService.query();
		String sdkName = sdk.getUrl().substring(sdk.getUrl().lastIndexOf("/")+1,sdk.getUrl().length());
		String downLoadUrl = Constant.SDK_PATH+sdkName;
		String osName = System.getProperty("os.name");
		if(StringUtils.isNotBlank(osName)&&osName.startsWith("Windows")){
			downLoadUrl = request.getServletContext().getRealPath("\\")+"\\upload\\sdk\\"+sdkName;
		}
		try {
			sdkName = URLEncoder.encode(sdkName, "UTF-8");
			response.addHeader("Content-Disposition", "attachment;filename="+sdkName);
			OutputStream os = response.getOutputStream();
			FileInputStream fis = new FileInputStream(downLoadUrl);
			byte[] b = new byte[1024];
			int i = 0;
			while ((i = fis.read(b)) > 0) {
				os.write(b, 0, i);
			}
			os.flush();
			if (fis != null) {
				fis.close();
			}
			if (os != null) {
				os.close();
			}
		} catch (IOException e) {
			logger.info(e.getMessage());
		}
		
	}
	
}
