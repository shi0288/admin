package com.xyauto.qa.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.exif.ExifThumbnailDirectory;
import com.xyauto.qa.cons.CommonCons;
import com.xyauto.qa.cons.ErrorCode;
import com.xyauto.qa.service.FileService;
import com.xyauto.qa.util.ImageHelper;
import com.xyauto.qa.util.QaResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by shiqm on 2017/3/22.
 */

@Controller
public class HomeController extends AjaxBaseController {

	@Autowired
	private FileService fileService;

	@Autowired
	private ImageHelper imageHelper;

	@RequestMapping("/happy/2048")
	public String happy2048() {
		return "happy/2048";
	}

	@RequestMapping("/")
	public String home() {
		return "login";
	}

	@RequestMapping("/index")
	public String index() {
		return "user/index";
	}

	@RequestMapping(value = "uploadPic", method = RequestMethod.POST)
	@ResponseBody
	QaResult uploadPic(MultipartFile file) {
		logger.info("picture original upload:{}", file.getOriginalFilename());
		// TODO: 2017/3/24 加登录验证 文件验证
		String url;
		try {
			url = fileService.uploadFile(file);
		} catch (IOException e) {
			return new QaResult(ErrorCode.OVER, "上传失败!");
		}
		return new QaResult(url);
	}

	@RequestMapping(value = "uploadPics", method = RequestMethod.POST)
	@ResponseBody
	QaResult uploadPics(MultipartFile[] files) {
		MultipartFile file = null;
		String url;
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < files.length; i++) {
			file = files[i];			
			String originalName = file.getOriginalFilename();
			logger.info("文件上传名称："+originalName);
			String fileType = imageHelper.getFileExt(originalName);
			logger.info("文件上传type：{}", fileType);
			boolean typeDone = false;
			for (String type : CommonCons.Config_Flag.PICTURE_TYPE) {
				if (type.toUpperCase().equals(fileType.toUpperCase())) {
					typeDone = true;
					break;
				}
			}
			if (!typeDone) {
				logger.info("文件不允许上传");
				JSONObject obj = new JSONObject();
				obj.put("code", 2998);
				return new QaResult(ErrorCode.OVER, "文件不允许上传!");
			}
			try {
				File f = null;			
					// 调整角度
					try {					
						int angel = getRotateAngleForPhoto(file);
						f = File.createTempFile("tmp", "." + fileType);
						file.transferTo(f);
						System.out.println(angel);			
						if (!(angel == 0)) {
							logger.info("需要调整角度，图片名称："+file.getOriginalFilename());
							BufferedImage src = ImageIO.read(f);
							BufferedImage bi = null;
							int src_width = src.getWidth(null);
							int src_height = src.getHeight(null);
							Rectangle rect_des = CalcRotatedSize(new Rectangle(
									new Dimension(src_width, src_height)), angel);
							bi = new BufferedImage(rect_des.width, rect_des.height,
									BufferedImage.TYPE_INT_RGB);
							Graphics2D g2 = bi.createGraphics();
							g2.translate((rect_des.width - src_width) / 2,
									(rect_des.height - src_height) / 2);
							g2.rotate(Math.toRadians(angel), src_width / 2,
									src_height / 2);
							g2.drawImage(src, null, null);	
							ImageIO.write(bi, fileType, f);
						}	
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						logger.info("调整角度出错");
						continue;
					}
					// 调整角度结束
					url = fileService.uploadFile(f, f.length(), f.getName());
					f.delete();
					logger.info("上传成功:{}", url);
					jsonArray.add(url);
			} catch (IOException e) {
				e.printStackTrace();
				return new QaResult(ErrorCode.OVER, "上传失败!");
			}			
		}
		
		return new QaResult(jsonArray);
	}

	@RequestMapping(value = "uploadMobile", method = RequestMethod.POST)
	@ResponseBody
	QaResult uploadMobile(MultipartFile[] files, HttpServletResponse response) {
		// TODO: 2017/3/24 加登录验证 文件验证
		MultipartFile file = null;
		String url;
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < files.length; i++) {
			file = files[i];
			String originalName = file.getOriginalFilename();
			logger.info("文件上传名称：{}", originalName);
			String fileType = imageHelper.getFileExt(originalName);
			logger.info("文件上传type：{}", fileType);
			boolean typeDone = false;
			for (String type : CommonCons.Config_Flag.PICTURE_TYPE) {
				if (type.toUpperCase().equals(fileType.toUpperCase())) {
					typeDone = true;
					break;
				}
			}
			if (!typeDone) {
				logger.info("文件不允许上传");
				JSONObject obj = new JSONObject();
				obj.put("code", 2998);
				return new QaResult(ErrorCode.OVER, "文件不允许上传!");
			}
			File f = null;
			try {
				// 调整角度
				try {
					int angel = getRotateAngleForPhoto(file);
					f = File.createTempFile("tmp", "." + fileType);
					file.transferTo(f);
					System.out.println(angel);
					if (!(angel == 0)) {
						logger.info("需要调整角度，图片名称：" + file.getOriginalFilename());
						BufferedImage src = ImageIO.read(f);
						BufferedImage bi = null;
						int src_width = src.getWidth(null);
						int src_height = src.getHeight(null);
						Rectangle rect_des = CalcRotatedSize(new Rectangle(
								new Dimension(src_width, src_height)), angel);
						bi = new BufferedImage(rect_des.width, rect_des.height,
								BufferedImage.TYPE_INT_RGB);
						Graphics2D g2 = bi.createGraphics();
						g2.translate((rect_des.width - src_width) / 2,
								(rect_des.height - src_height) / 2);
						g2.rotate(Math.toRadians(angel), src_width / 2,
								src_height / 2);
						g2.drawImage(src, null, null);
						ImageIO.write(bi, fileType, f);
					}

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					logger.info("调整角度出错");
					continue;
				}
				// 调整角度结束
				// 压缩
				imageHelper.scaleImageWithParams(f, f, fileType,
						CommonCons.Config_Flag.PICTURE_PC_HEIGHT,
						CommonCons.Config_Flag.PICTURE_PC_HEIGHT);
				url = fileService.uploadFile(f, f.length(), f.getName());
				f.delete();
				logger.info("上传成功:{}", url);
				jsonArray.add(url);
			} catch (IOException e) {
				continue;
				// return new QaResult(ErrorCode.OVER, "上传失败!");
			}
		}
		return new QaResult(jsonArray);

	}

	/**
	 * 图片翻转时，计算图片翻转到正常显示需旋转角度
	 */
	public int getRotateAngleForPhoto(MultipartFile file) {

		int angel = 0;

		Metadata metadata;
		try {
			logger.info("校验图片是：" + file.getOriginalFilename());
			if (file.isEmpty() || (file.getInputStream() == null)) {
				logger.info("传递的图片信息为null");
				return angel;
			}
			metadata = ImageMetadataReader.readMetadata(file.getInputStream());
			Directory directory = metadata
					.getDirectory(ExifIFD0Directory.class);

			if (directory!=null&&directory.containsTag(ExifIFD0Directory.TAG_ORIENTATION)) {
				// Exif信息中方向　　
				int orientation = directory
						.getInt(ExifIFD0Directory.TAG_ORIENTATION);

				// 原图片的方向信息
				if (6 == orientation) {
					// 6旋转90
					angel = 90;
				} else if (3 == orientation) {
					// 3旋转180
					angel = 180;
				} else if (8 == orientation) {
					// 8旋转90
					angel = 270;
				}
			}
		} catch (JpegProcessingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("图片旋转角度：" + angel);
		return angel;
	}

	public static Rectangle CalcRotatedSize(Rectangle src, int angel) {
		// if angel is greater than 90 degree,we need to do some conversion.
		if (angel > 90) {
			if (angel / 9 % 2 == 1) {
				int temp = src.height;
				src.height = src.width;
				src.width = temp;
			}
			angel = angel % 90;
		}

		double r = Math.sqrt(src.height * src.height + src.width * src.width) / 2;
		double len = 2 * Math.sin(Math.toRadians(angel) / 2) * r;
		double angel_alpha = (Math.PI - Math.toRadians(angel)) / 2;
		double angel_dalta_width = Math.atan((double) src.height / src.width);
		double angel_dalta_height = Math.atan((double) src.width / src.height);

		int len_dalta_width = (int) (len * Math.cos(Math.PI - angel_alpha
				- angel_dalta_width));
		int len_dalta_height = (int) (len * Math.cos(Math.PI - angel_alpha
				- angel_dalta_height));
		int des_width = src.width + len_dalta_width * 2;
		int des_height = src.height + len_dalta_height * 2;
		return new java.awt.Rectangle(new Dimension(des_width, des_height));
	}
	

}
