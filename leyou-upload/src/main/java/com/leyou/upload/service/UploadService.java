package com.leyou.upload.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author dn
 * @create 2021-01-07 10:38
 */
@Service
public class UploadService {

    private static final List<String> content_type = Arrays.asList("image/gif", "image/jpeg","image/jpg");

    private static final Logger LOGGER = LoggerFactory.getLogger(UploadService.class);

    public String uploadImage(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String contentType = file.getContentType();
        //1.文件类型
        if (!content_type.contains(contentType)) {
            LOGGER.info("文件上传失败: {},文件类型不合法!", originalFilename);
            return null;
        }
        try {
            //2.校验文件内容
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            if (bufferedImage == null || bufferedImage.getHeight() == 0 || bufferedImage.getWidth() == 0) {
                LOGGER.info("文件上传失败: {},文件内容不合法!", originalFilename);
                return null;
            }

            //3.保存到服务器
            file.transferTo(new File("E:\\hm53\\images\\" + originalFilename));
            //4.返回url路径
            return "E:\\hm53\\images" + originalFilename;
        } catch (IOException e) {
            LOGGER.info("文件上传失败: {},服务器异常!", originalFilename);
            e.printStackTrace();
        }
        return null;
    }
}
