package com.lzb.rock.admin.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lzb.rock.base.aop.annotation.Log;
import com.lzb.rock.base.common.ResultEnum;
import com.lzb.rock.base.exception.RockException;
import com.lzb.rock.base.properties.RockProperties;
import com.lzb.rock.base.util.UtilDate;
import com.lzb.rock.base.util.UtilFile;
import com.lzb.rock.base.util.UtilString;

@Controller
@RequestMapping("/sys")
public class UploadController {

	@Autowired
	RockProperties rockProperties;

	/**
	 * 文件下载
	 * 
	 * @param picture
	 * @return
	 */
	@RequestMapping(path = "/download/**",method = {RequestMethod.GET,RequestMethod.POST})
	@Log(endAll = false)
	public void download(HttpServletRequest request, HttpServletResponse response) {
      String path =request.getRequestURI();
      path=path.substring(path.indexOf("download")+8);
      String filePath = rockProperties.getFilePath();
      try {  
          byte[] bytes = UtilFile.toByteArray(filePath+path);
          response.getOutputStream().write(bytes);
      }catch (Exception e){
          //如果找不到图片就返回一个默认图片
          try {
              response.sendRedirect("/static/img/girl.gif");
          } catch (IOException e1) {
              e1.printStackTrace();
          }
      }
	}


	/**
	 * 文件上传
	 * 
	 * @param picture
	 * @return
	 */
	@PostMapping(path = "/upload")
	@ResponseBody
	@Log(before = false)
	public String upload(@RequestPart("file") MultipartFile picture,@RequestParam(name = "path",required = false) String path) {

		String fileName = picture.getOriginalFilename();
		String[] arr = fileName.split("\\.");
		StringBuffer pictureName = new StringBuffer();

		if (arr.length > 0) {
			pictureName.append(arr[0]).append("_");
		}
		pictureName.append(UtilDate.getFomtTimeByDateString());
		if (arr.length > 1) {
			pictureName.append(".").append(arr[1]);
		}
		String fileSavePath = null;
		try {
			String filePath = rockProperties.getFilePath();

			if (!filePath.endsWith(File.separator)) {
				fileSavePath = filePath + File.separator;
			} 
			if(UtilString.isNotBlank(path)) {
				if(filePath.startsWith(File.separator)) {
					path=path.substring(File.separator.length());
				}
			}
			
			UtilFile.mkdirs(fileSavePath);
			fileSavePath = fileSavePath + pictureName.toString();
			picture.transferTo(new File(fileSavePath));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RockException(ResultEnum.FILE_UPLOAD_ERR);
		}
		return fileSavePath;
	}
}
