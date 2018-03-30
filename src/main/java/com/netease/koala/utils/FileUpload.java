package com.netease.koala.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class FileUpload {
	// 文件上传
	public static String uploadFile(MultipartFile file, HttpServletRequest request)
			throws IOException {
		try {
			String fileName = file.getOriginalFilename();
			//String path = request.getSession().getServletContext().getRealPath("\\asset\\images");
			String path = "C:\\images";
			String picUrl = new Date().getTime() + fileName.substring(fileName.lastIndexOf("."),fileName.length());
			File tempFile = new File(path, picUrl);
			if (!tempFile.getParentFile().exists()) {
				tempFile.getParentFile().mkdir();
			}
			if (!tempFile.exists()) {
				tempFile.createNewFile();
			}
			file.transferTo(tempFile);
			return picUrl;
		} catch (Exception e) {
			return null;
		}
		// TODO: handle exception
	}

}