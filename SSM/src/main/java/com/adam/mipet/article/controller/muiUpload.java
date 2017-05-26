package com.adam.mipet.article.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import sun.misc.BASE64Decoder;

import com.adam.common.util.Uid;
import com.adam.mipet.article.dao.ArticleImageMapper;
import com.adam.mipet.util.Constant;

@Controller
@RequestMapping("/muiUpload")
public class muiUpload {
	
	@Resource
	ArticleImageMapper articleImageMapper;
	
	// base64方式上传压缩图片
	@ResponseBody
	@RequestMapping("base64Image.do")
	public Map<String, Object> uploadBase64Image(HttpServletRequest request, String imageFile) {
	
		if (imageFile == null) {
			return Constant.MSG.RESULT_ERROR();
		}
		String resultName = Uid.getUid() + "." + imageFile.substring(imageFile.indexOf("/")+1,imageFile.indexOf(";"));// 新文件名
		String subPath = "\\upload\\headImage\\" + Constant.TIME_PATH.format(new Date());
		String path = request.getSession().getServletContext().getRealPath("") + subPath;
		
		String str = "";
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] bytes = decoder.decodeBuffer(imageFile.split(",")[1]);
			for (int i = 0; i < bytes.length; ++i) {
				if (bytes[i] < 0) {// 调整异常数据
					bytes[i] += 256;
				}
			}
			File targetFile = new File(path);
			if (!targetFile.exists()) { // 文件夹是否存在,自动新建文件夹
				targetFile.mkdirs();
			}
			// 生成jpeg图片
			OutputStream out = new FileOutputStream(path+"\\"+resultName);
			out.write(bytes);
			out.flush();
			out.close();
		} catch (Exception e) {
			return Constant.MSG.SYSERROR();
		}
		str = "/mipet/upload/headImage/" + Constant.TIME_PATH.format(new Date()) + "/" + resultName;
		return Constant.MSG.RESULT_SUCCESS(str);
	}
	
	@ResponseBody
	@RequestMapping("image.do")
	public Map<String, Object> uploadImage(HttpServletRequest request) {
	
		// 将请求转换成
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile imageFile = multipartRequest.getFile("imageFile");
		String fileName = imageFile.getOriginalFilename();// 获取文件名
		String suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());// 获取文件后缀名
		String resultName = Uid.getUid() + "." + suffix;// 新文件名
		String subPath = "\\upload\\headImage\\" + Constant.TIME_PATH.format(new Date());
		String path = request.getSession().getServletContext().getRealPath("") + subPath;
		File targetFile = new File(path, resultName);
		if (!targetFile.exists()) { // 文件夹是否存在,自动新建文件夹
			targetFile.mkdirs();
		}
		String str = "/mipet/upload/headImage/" + Constant.TIME_PATH.format(new Date()) + "/" + resultName;
		try {
			// 复制到服务器上
			imageFile.transferTo(targetFile);
		} catch (Exception e) {
			return Constant.MSG.SYSERROR();
		}
		return Constant.MSG.RESULT_SUCCESS(str);
	}
	
	@RequestMapping("fileUpload")
	public void fileUpload(HttpServletRequest request, HttpServletResponse response, String fileObjectId, int MaxSize, String suffixStr, String savePath, int width, int height) throws IOException {
	
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = multipartRequest.getFile(fileObjectId);// 获取文件流
		String fileName = file.getOriginalFilename();// 获取文件全名
		String suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());// 获取文件扩展名
		String[] suffixs = suffixStr.split(",");
		boolean isSuffix = false;
		for (String str : suffixs) {
			if (str == suffix || str.equals(suffix)) {
				isSuffix = true;
			}
		}
		String result = "";
		String msg = "";
		String path = "";
		String resultName = Uid.getUid() + "." + suffix;// 新文件名
		if (isSuffix) {
			if (file.getSize() / 1024 <= MaxSize) {
				path = request.getSession().getServletContext().getRealPath("") + Constant.PATH.GETPATH(savePath);
				File targetFile = new File(path, resultName);
				if (!targetFile.exists()) { // 文件夹是否存在,自动新建
					targetFile.mkdirs();
				}
				try {
					// 复制到服务器上
					file.transferTo(targetFile);
					if (width != 0 && height != 0) {
						BufferedImage bufferedImg = ImageIO.read(targetFile);
						if (bufferedImg.getWidth() != width || bufferedImg.getHeight() != height) {
							if (targetFile.delete()) {
							}
							result = "false";
							msg = com.adam.common.constant.Constant.MSG.IMG_WH + width + "*" + height;
						} else {
							result = "true";
						}
					} else {
						result = "true";
					}
				} catch (Exception e) {
					msg = com.adam.common.constant.Constant.MSG.UNKNOW_ERROR;
				}
			} else {
				msg = com.adam.common.constant.Constant.MSG.FILE_SIZE + MaxSize + "KB";
				result = "false";
			}
		} else {
			msg = com.adam.common.constant.Constant.MSG.FILE_TYPE;
			result = "false";
		}
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String res = "{ success:'" + result + "', msg:'" + msg + "',fileUrl:'" + resultName + "'}";
		out.print(res);
		out.flush();
	}
	
	@ResponseBody
	@RequestMapping("ArticleImg.do")
	public Map<String, Object> uploadArticleImg(HttpServletRequest request) {
	
		// 将请求转换成
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile imageFile = multipartRequest.getFile("imageFiles");
		/*
		 * List<MultipartFile> files = multipartRequest.getFiles("imageFiles");
		 * for (MultipartFile multipartFile : files) { String originalFilename =
		 * multipartFile.getOriginalFilename();
		 * 
		 * }
		 */
		if (imageFile == null) {
			return Constant.MSG.SYSERROR();
		}
		String fileName = imageFile.getOriginalFilename();// 获取文件名
		String suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());// 获取文件后缀名
		String resultName = Uid.getUid() + "." + suffix;// 新文件名
		String subPath = "\\upload\\ArticleImage\\" + Constant.TIME_PATH.format(new Date());
		String path = request.getSession().getServletContext().getRealPath("") + subPath;
		File targetFile = new File(path, resultName);
		if (!targetFile.exists()) { // 文件夹是否存在,自动新建文件夹
			targetFile.mkdirs();
		}
//		System.out.println(targetFile.toPath() + "---->>" + targetFile.toURI());
		String str = "/mipet/upload/ArticleImage/" + Constant.TIME_PATH.format(new Date()) + "/" + resultName;
		try {
			// 复制到服务器上
			imageFile.transferTo(targetFile);
		} catch (Exception e) {
			return Constant.MSG.SYSERROR();
		}
		return Constant.MSG.RESULT_SUCCESS(str);
	}
	
	/**
	 * CKEditor 图片上传
	 */
	@RequestMapping(value = "/CKImgUpload", method = RequestMethod.POST)
	public void CKImgUpload(HttpServletRequest request, HttpServletResponse response) throws IOException {
	
		response.setContentType("text/html; charset=UTF-8");
		String CKEditorFuncNum = request.getParameter("CKEditorFuncNum");
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = multipartRequest.getFile("upload");// 获取文件流
		String fileName = file.getOriginalFilename();// 获取文件名
		String suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());// 获取文件后缀名
		String resultName = Uid.getUid() + "." + suffix;// UID新文件名
		String strScript = "";
		if (com.adam.common.constant.Constant.FILE_TYPE.imgTypes().contains(suffix)) {// 验证文件格式
			if (file.getSize() / 1024 < 2048) {
				String path = request.getSession().getServletContext().getRealPath("") + "\\upload\\htmlContentImage";
				File targetFile = new File(path, resultName);
				if (!targetFile.exists()) { // 文件夹是否存在
					targetFile.mkdirs();
				}
				try {
					file.transferTo(targetFile);
					strScript = com.adam.common.constant.Constant.MSG.getCKEditorMsg(CKEditorFuncNum, "/mipet/upload/htmlContentImage/" + resultName, "");
				} catch (Exception e) {
					strScript = com.adam.common.constant.Constant.MSG.getCKEditorMsg(CKEditorFuncNum, "", com.adam.common.constant.Constant.MSG.UNKNOW_ERROR);
				}
			} else {
				strScript = com.adam.common.constant.Constant.MSG.getCKEditorMsg(CKEditorFuncNum, "", com.adam.common.constant.Constant.MSG.IMG_SIZE_ERROR);
			}
		} else {
			strScript = com.adam.common.constant.Constant.MSG.getCKEditorMsg(CKEditorFuncNum, "", com.adam.common.constant.Constant.MSG.IMG_TYPE_ERROR);
		}
		PrintWriter out;
		out = response.getWriter();
		out.print(strScript);
		out.flush();
	}
	
	@ResponseBody
	@RequestMapping("deleteImg.do")
	public Map<String, Object> deleteArticleImg(HttpServletRequest request, String imgName) {
	
		if (null == imgName) {
			return Constant.MSG.MAP_ILLEGAL();
		}
		try {
			String realPath = request.getSession().getServletContext().getRealPath("");
			String sub = "/upload/ArticleImage/";
			String dirName = imgName.substring(imgName.indexOf(sub), imgName.length());
			String fileName = realPath + dirName.replace('/', '\\');
			String path = fileName.substring(0, fileName.lastIndexOf("\\"));
			fileName = fileName.substring(fileName.lastIndexOf("\\") + 1, fileName.length());
			File targetFile = new File(path, fileName);
			if (targetFile.exists()) { // 文件夹是否存在,自动新建文件夹
				targetFile.delete();
				return Constant.MSG.RESULT_SUCCESS();
			}
			return Constant.MSG.RESULT_ERROR();
		} catch (Exception e) {
			return Constant.MSG.SYSERROR();
		}
	}
}
