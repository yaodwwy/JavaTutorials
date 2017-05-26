package com.adam.sys.excelupload.controller;

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
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.adam.common.constant.Constant;
import com.adam.sys.excelupload.entity.ExcelForm;
import com.adam.sys.excelupload.service.IExcelFormService;
import com.adam.sys.userfunc.entity.User;
@Controller
@RequestMapping("/excel")
public class ExcelFormController {
	@Resource
	private IExcelFormService excelFormService;
	
	@RequestMapping("query")
	@ResponseBody
	public ExcelForm query(){
		ExcelForm query = excelFormService.query();
		return query;
	}
	
	@RequestMapping("update")
	@ResponseBody
	public Map<String, Object> update(ExcelForm excel,HttpSession session){
		try {
			User user = (User) session.getAttribute("admin");
			excel.setUpdator(user.getUsername());
			if(excelFormService.update(excel)>0){
				return Constant.MSG.SAVE_OK();
			}
			return Constant.MSG.SAVE_ERROR();
		} catch (Exception e) {
			return Constant.MSG.SAVE_ERROR();
		}
	}
	
	@RequestMapping("download")
	public void download(HttpServletRequest request,HttpServletResponse response){
		try {
			response.setContentType("application/x-download");
			ExcelForm query = excelFormService.query();
			String excelName = query.getUrl().substring(query.getUrl().lastIndexOf("/")+1,query.getUrl().length());
			/**
			 * 判断Linux还是Windows系统，设置文件保存路径。
			 */
			String filedownload=Constant.EXCEL_PATH+excelName;
			//判断windows
			String osName = System.getProperty("os.name");
			if(StringUtils.isNotBlank(osName)&&osName.startsWith("Windows")){
				filedownload = request.getServletContext().getRealPath("\\")+"\\upload\\excel\\"+excelName;
			}
			/**
			 * -*----------------------------------------------------****
			 */
			excelName = URLEncoder.encode(excelName,"UTF-8");   
			response.addHeader("Content-Disposition","attachment;filename=" + excelName);   
			OutputStream os = response.getOutputStream(); 
			FileInputStream fin = new FileInputStream(filedownload);
			byte[] b = new byte[1024];
			int i = 0;
			while((i=fin.read(b))>0){
				os.write(b, 0, i);
			}
			os.flush();
			if(fin!=null){
				fin.close();
			}
			if(os!=null){
				os.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("fileUpload")
	public void fileUpload(HttpServletRequest request,HttpServletResponse response,
			String fileObjectId,int MaxSize,String suffixStr,String savePath) throws IOException{
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
		String path = "";
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("-yyyyMMddhhmmss");
		String resultName = fileFirstName+sdf.format(date)+"."+suffix;//新文件名
        if(isSuffix){
        	if(file.getSize()/1024<=MaxSize){
        		//判断是否为windows
        		String os = System.getProperty("os.name");
        		if(StringUtils.isNotBlank(os) && os.startsWith("Windows")){
        			path = request.getSession().getServletContext().getRealPath("")+Constant.PATH.GETPATH(savePath); 
        		}else{
        			path = Constant.EXCEL_PATH;
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
        PrintWriter out = response.getWriter();
        String res = "{ success:'"+result+"', msg:'"+msg+"',fileUrl:'"+resultName+"'}";
        out.print(res);
        out.flush();
	}
	
}
