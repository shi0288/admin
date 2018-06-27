package com.xyauto.qa.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.tobato.fastdfs.FdfsClientConfig;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.proto.storage.DownloadCallback;
import com.github.tobato.fastdfs.service.DefaultFastFileStorageClient;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.xyauto.qa.Constants;
import com.xyauto.qa.cons.CommonCons;
import com.xyauto.qa.cons.ErrorCode;
import com.xyauto.qa.core.annotation.Log;
import com.xyauto.qa.util.ImageHelper;
import com.xyauto.qa.util.QaResult;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.Charset;

import javax.imageio.ImageIO;

@Import(FdfsClientConfig.class)
@Service
public class FileService {

	@Log
    protected Logger logger;
    @Autowired
    private FastFileStorageClient storageClient;
    @Autowired
    private Constants constants;
    @Autowired
	private ImageHelper imageHelper;

    public QaResult uploadPics(MultipartFile[] files){
    	MultipartFile file = null;
		String url;
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < files.length; i++) {
			file = files[i];			
			String originalName = file.getOriginalFilename();
			System.out.println("文件上传名称："+originalName);
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
						int angel = imageHelper.getRotateAngleForPhoto(file);
						f = File.createTempFile("tmp", "." + fileType);
						file.transferTo(f);
						System.out.println(angel);			
						if (!(angel == 0)) {
							logger.info("需要调整角度，图片名称："+file.getOriginalFilename());
							BufferedImage src = ImageIO.read(f);
							BufferedImage bi = null;
							int src_width = src.getWidth(null);
							int src_height = src.getHeight(null);
							Rectangle rect_des = imageHelper.CalcRotatedSize(new Rectangle(
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
					url = this.uploadFile(f, f.length(), f.getName());
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
    
    public String uploadFile(MultipartFile file) throws IOException {
        DefaultFastFileStorageClient client = (DefaultFastFileStorageClient) storageClient;
        StorePath storePath = client.uploadFile(Constants.fileGroupName, file.getInputStream(), file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()));
        String pathUrl = storePath.getFullPath();
        if (pathUrl.toLowerCase().startsWith("http://") || pathUrl.toLowerCase().startsWith("https://")) {
            return pathUrl;
        }
        if (pathUrl.startsWith("group1")) {
            pathUrl = constants.getAvatarGroup1Root() + pathUrl;
        }
        if (pathUrl.startsWith("group2")) {
            pathUrl = constants.getAvatarGroup2Root() + pathUrl;
        }
        return pathUrl;
    }

    public String uploadFile(File file, long size, String originalName) throws IOException {
        InputStream in = new FileInputStream(file);
        DefaultFastFileStorageClient client = (DefaultFastFileStorageClient) storageClient;
        StorePath storePath = client.uploadFile(Constants.fileGroupName, in, size, FilenameUtils.getExtension(originalName));
        String pathUrl = storePath.getFullPath();
        if (pathUrl.toLowerCase().startsWith("http://") || pathUrl.toLowerCase().startsWith("https://")) {
            return pathUrl;
        }
        if (pathUrl.startsWith("group1")) {
            pathUrl = constants.getAvatarGroup1Root() + pathUrl;
        }
        if (pathUrl.startsWith("group2")) {
            pathUrl = constants.getAvatarGroup2Root() + pathUrl;
        }
        return pathUrl;
    }
    
    public String uploadFile(InputStream inputStream, long size, String originalName) throws IOException {
        InputStream in = inputStream;
        DefaultFastFileStorageClient client = (DefaultFastFileStorageClient) storageClient;
        StorePath storePath = client.uploadFile(Constants.fileGroupName, in, size, FilenameUtils.getExtension(originalName));
        String pathUrl = storePath.getFullPath();
        if (pathUrl.toLowerCase().startsWith("http://") || pathUrl.toLowerCase().startsWith("https://")) {
            return pathUrl;
        }
        if (pathUrl.startsWith("group1")) {
            pathUrl = constants.getAvatarGroup1Root() + pathUrl;
        }
        if (pathUrl.startsWith("group2")) {
            pathUrl = constants.getAvatarGroup2Root() + pathUrl;
        }
        return pathUrl;
    }


    public String uploadImageAndCrtThumbImage(MultipartFile file) throws IOException {
        StorePath storePath = storageClient.uploadImageAndCrtThumbImage(file.getInputStream(), file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()), null);
        return storePath.getFullPath();
    }

    public String uploadFile(String content, String fileExtension) {
        byte[] buff = content.getBytes(Charset.forName("UTF-8"));
        ByteArrayInputStream stream = new ByteArrayInputStream(buff);
        StorePath storePath = storageClient.uploadFile(stream, buff.length, fileExtension, null);
        return storePath.getFullPath();
    }

    public byte[] downloadFile(String path) {
        StorePath storePath = StorePath.praseFromUrl(path);
        byte[] file = storageClient.downloadFile(storePath.getGroup(), storePath.getPath(), new DownloadCallback<byte[]>() {
            @Override
            public byte[] recv(InputStream ins) throws IOException {
                ByteArrayOutputStream infoStream = new ByteArrayOutputStream();
                byte[] bcache = new byte[2048];
                int readSize = 0;
                while ((readSize = ins.read(bcache)) > 0) {
                    infoStream.write(bcache, 0, readSize);
                }
                return infoStream.toByteArray();
            }
        });
        return file;
    }

}
