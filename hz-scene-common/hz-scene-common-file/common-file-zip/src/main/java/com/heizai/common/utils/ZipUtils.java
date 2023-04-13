package com.heizai.common.utils;

import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author zhulx
 * @version 1.0
 * @ClassName ZipUtils
 * @Description TODO
 * @Date 2023/3/27 14:20
 * @since JDK1.8
 */
@Slf4j
public class ZipUtils {

    private static final String BASE_URL = "http://183.6.74.73:38007/portal/oss/file";

    /**
     * 把生成的图片打进压缩包并下载
     *
     * @param images: 二维码流集合
     * @date 2022/4/8 15:26
     * @author
     */
    public static void compressImageToZip(List<BufferedImage> images, HttpServletResponse httpServletResponse) throws IOException {
        //重置HttpServletResponse防止乱码
        httpServletResponse.reset();
        ZipOutputStream zos = new ZipOutputStream(httpServletResponse.getOutputStream());
        //文件的名称
        String downloadFilename = "测试图片压缩包.zip";
        //转换中文否则可能会产生乱码
        downloadFilename = URLEncoder.encode(downloadFilename, "UTF-8");
        // 指明response的返回对象是文件流
        httpServletResponse.setContentType("application/octet-stream; charset=UTF-8");
        //设置下载格式和名称
        httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" + downloadFilename);
        //添加二维码进zip压缩包
        images.forEach(
                image -> {
                    try {
                        byte[] buf = new byte[8192];
                        int len;
                        //添加二维码进zip
                        ZipEntry entry = new ZipEntry(UUID.randomUUID() + ".png");
                        zos.putNextEntry(entry);
                        InputStream inputStream = bufferedImageToInputStream(image);
                        //输出压缩包
                        while ((len = inputStream.read(buf)) > 0) {
                            zos.write(buf, 0, len);
                        }
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );
        zos.close();
    }

    /**
     * 将BufferedImage转换为InputStream
     *
     * @param image: 图片流
     * @date 2022/4/8 15:29
     * @author
     */
    public static InputStream bufferedImageToInputStream(BufferedImage image) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(image, "png", os);
        try (InputStream input = new ByteArrayInputStream(os.toByteArray())) {
            return input;
        }
    }


    public static void batchDownload(List<String> pictureUrls, HttpServletResponse response) {
        String zipName = "通行证" + ".zip";
        response.setHeader("content-type", "application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + zipName);
        response.setCharacterEncoding("utf-8");
        ZipOutputStream zipOutputStream = null;

        try {
            zipOutputStream = new ZipOutputStream(response.getOutputStream());
            for (String pictureUrl : pictureUrls) {
                pictureUrl = BASE_URL + pictureUrl;
                log.info("图片地址:" + pictureUrl);
                String fileName = pictureUrl.substring(pictureUrl.lastIndexOf("/") + 1);
                zipOutputStream.putNextEntry(new ZipEntry(UUID.randomUUID() +"_"+ fileName));

                URL url = new URL(pictureUrl);
                InputStream inputStream = new DataInputStream(url.openStream());

                byte[] buff = new byte[1024];
                int len;
                while ((len = inputStream.read(buff)) != -1) {
                    zipOutputStream.write(buff, 0, len);
                }
                zipOutputStream.closeEntry();
                inputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (zipOutputStream != null) {
                try {
                    zipOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
