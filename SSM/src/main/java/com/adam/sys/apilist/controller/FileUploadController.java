package com.adam.sys.apilist.controller;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.adam.common.constant.Constant;
import com.adam.common.util.Uid;

@Controller
@RequestMapping("/upload")
public class FileUploadController {

	/**
	 * 文件上传     /upload/fileUpload
	 * @param request
	 * @param response
	 * @param fileObjectId	文件Input的ID
	 * @param MaxSize	最大可上传的文件大小
	 * @param suffixStr	文件扩展名限制
	 * @param savePath	保存路径 
	 * @param width
	 * @param height
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("/fileUpload")
	public void fileUpload(HttpServletRequest request,HttpServletResponse response,
			String fileObjectId,int MaxSize,String suffixStr,String savePath,
			int width,int height) throws IOException{
		MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;  
        MultipartFile file  =  multipartRequest.getFile(fileObjectId);//获取文件流
        String fileName = file.getOriginalFilename();//获取文件名
        String suffix = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());//获取文件后缀名
        String[] suffixs = suffixStr.split(",");
        boolean isSuffix = false;
        String path = "";
        for(String str:suffixs){
        	if(str==suffix||str.equals(suffix)){
        		isSuffix=true;
        	}
        }
        String result = "";
		String msg = "";
		String resultName = Uid.getUid()+"."+suffix;//UID新文件名
        if(isSuffix){
        	if(file.getSize()/1024<=MaxSize){
        		
        		path = request.getSession().getServletContext().getRealPath("")+Constant.PATH.GETPATH(savePath); 
            	File targetFile = new File(path, resultName);  
                if(!targetFile.exists()){ //文件夹是否存在
                    targetFile.mkdirs();  
                }  
                try {
                    file.transferTo(targetFile);
                    if(width!=0&&height!=0){
                        BufferedImage bufferedImg = ImageIO.read(targetFile);
                        if(bufferedImg.getWidth()!=width||bufferedImg.getHeight()!=height){
                        	
                        	if(targetFile.delete()){
                        		
                        	}
                        	result = "false";
                        	msg = Constant.MSG.IMG_WH+width+"*"+height;
                        }else{
                        	result="true";
                        }
                    }else{
                    	result = "true";
                    }
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
        PrintWriter out;
        out = response.getWriter();
        String res = "{ success:'"+result+"', msg:'"+msg+"',imgurl:'" + resultName + "'}";
        out.print(res);
        out.flush();
	}
	
	
	
	
	
	
	@ResponseBody
	@RequestMapping(value="/menuIconUpload")
	public void menuIconUpload(HttpServletRequest request,HttpServletResponse response,String flag) throws IOException{
		String result = "";
		String msg = "";
		MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;  
        MultipartFile file  =  multipartRequest.getFile(flag);//获取文件流
        String fileName = file.getOriginalFilename();//获取文件名
        String suffix = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());//获取文件后缀名
        String resultName = Uid.getUid()+"."+suffix;//UID新文件名
        if(Constant.FILE_TYPE.imgTypes().contains(suffix)){//验证文件格式
        	String path="";
        	if("noticeimg".equals(flag) && file.getSize()/1024<1024){
    			path = request.getSession().getServletContext().getRealPath("")+Constant.PATH.ICON_NOTICE_PATH1;
    			File targetFile = new File(path, resultName);  
                if(!targetFile.exists()){ //文件夹是否存在
                    targetFile.mkdirs();  
                }  
                try {  
                    file.transferTo(targetFile);  
                    result = "true";
                } catch (Exception e) {  
                	msg = Constant.MSG.UNKNOW_ERROR;
                	result = "false";
                } 
    		}else if(file.getSize()/1024<50){
        		
        		
        			path = request.getSession().getServletContext().getRealPath("")+Constant.PATH.ICON_IMAGE_PATH1;	
        		
        		
            	File targetFile = new File(path, resultName);  
                if(!targetFile.exists()){ //文件夹是否存在
                    targetFile.mkdirs();  
                }  
                try {  
                    file.transferTo(targetFile);  
                    result = "true";
                } catch (Exception e) {  
                	msg = Constant.MSG.UNKNOW_ERROR;
                	result = "false";
                } 		
        	}else{
        		msg = Constant.MSG.ICON_SIZE_ERROR;
            	result = "false";
        	}
        }else{
            msg = Constant.MSG.IMG_TYPE_ERROR;
        	result = "false";
        }
        response.setContentType("text/html; charset=UTF-8");  
        PrintWriter out;
        out = response.getWriter();
        String res = "{ success:'"+result+"', msg:'"+msg+"',imgurl:'" + resultName + "'}";
        out.print(res);
        out.flush();
	}
	
	 /**
     * CKEditor 图片上传
     * Create of Elvis
     * @param HttpServletRequest request,HttpServletResponse response
     * @return JavaScript
	 * @throws IOException 
     */
	
    @RequestMapping(value = "/CKImgUpload",method=RequestMethod.POST)
    public void CKImgUpload(HttpServletRequest request,HttpServletResponse response) throws IOException {
    	response.setContentType("text/html; charset=UTF-8");  
    	String CKEditorFuncNum = request.getParameter("CKEditorFuncNum");
    	
    	MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;  
        MultipartFile file  =  multipartRequest.getFile("upload");//获取文件流
        String fileName = file.getOriginalFilename();//获取文件名
        String suffix = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());//获取文件后缀名
        String resultName = Uid.getUid()+"."+suffix;//UID新文件名
        String strScript ="";
        if(Constant.FILE_TYPE.imgTypes().contains(suffix)){//验证文件格式
        	if(file.getSize()/1024<2048){
        		String path = request.getSession().getServletContext().getRealPath("")+Constant.PATH.CK_IMAGE_PATH1; 
            	File targetFile = new File(path, resultName);  
                if(!targetFile.exists()){ //文件夹是否存在
                    targetFile.mkdirs();  
                }  
                try {  
                    file.transferTo(targetFile);  
                	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ request.getContextPath() + "/";
                    strScript=Constant.MSG.getCKEditorMsg(CKEditorFuncNum,basePath+Constant.PATH.CK_IMAGE_PATH+"/"+resultName,"");
                    System.out.println(strScript);
                } catch (Exception e) {  
                	strScript=Constant.MSG.getCKEditorMsg(CKEditorFuncNum,"",Constant.MSG.UNKNOW_ERROR);
                }  	
        	}else{
        		strScript=Constant.MSG.getCKEditorMsg(CKEditorFuncNum,"",Constant.MSG.IMG_SIZE_ERROR);
        	}
        }else{
        	strScript=Constant.MSG.getCKEditorMsg(CKEditorFuncNum,"",Constant.MSG.IMG_TYPE_ERROR);
        }
        
        PrintWriter out;
        out = response.getWriter();
        out.print(strScript);
        out.flush();
    }
    
    public static void main(String[] args) throws Exception{
		File file=new File("E:/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp1/wtpwebapps/aneweb/them/images/publicity/0a231d77-9a45-4cc0-82c2-c17a12037075 - 副本.png");
//		new FileInputStream(file);
		System.out.println(file.delete());
	}
}
