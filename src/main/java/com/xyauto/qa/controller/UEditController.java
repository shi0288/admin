package com.xyauto.qa.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xyauto.qa.cons.ErrorCode;
import com.xyauto.qa.service.FileService;
import com.xyauto.qa.util.QaResult;
import com.xyauto.qa.util.UploadCons;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;

/**
 * Created by shiqm on 2017-10-30.
 */

@Controller
@RequestMapping("user/edit")
public class UEditController extends AjaxBaseController{
	
	@Autowired
	private FileService fileService;

    @RequestMapping("uploadConfig")
    public void uploadConfig(HttpServletRequest request, HttpServletResponse response, 
    		String action,MultipartFile[] multipartFiles) {
        System.out.println("action:"+action);
        String contentType = request.getContentType();
       
        if (action.equals("config")) {
            try {
                response.getWriter().write(UploadCons.jsonObject.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (action.equals("uploadImage")) {
        	 if (contentType != null && contentType.toLowerCase().startsWith("multipart/")) {
                 MultipartHttpServletRequest multipartRequest =
                         WebUtils.getNativeRequest(request, MultipartHttpServletRequest.class);
                 MultipartFile file = multipartRequest.getFile("upfile");   
                 MultipartFile [] files={file};                 
                 QaResult result=fileService.uploadPics(files);
                 JSONObject res= new JSONObject();
                 if (result.getCode()!=ErrorCode.OK) {
                	 res.put("state","FALSE");
				}else{
					JSONArray data=(JSONArray) result.getData();
					 res.put("state","SUCCESS");
					 res.put("url",data.get(0).toString());
				}                 
                 try {
                     response.getWriter().write(res.toString());
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
             }
        }else if("listimage".equals(action)){
        	System.out.println("123");
        }

    }

}
