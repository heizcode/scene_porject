package com.heizai.image.controller;

import com.heizai.common.utils.ZipUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipOutputStream;

/**
 * @author zhulx
 * @version 1.0
 * @ClassName ZipController
 * @Description TODO
 * @Date 2023/3/27 14:27
 * @since JDK1.8
 */
@RestController
@RequestMapping("/common/fileZip/img/")
public class ZipController {

    @GetMapping("downloadZip")
    public void downloadZip(HttpServletResponse httpServletResponse){
        List<String> pictureUrls = new ArrayList<>();
        pictureUrls.add("/images/20230327/1d859c3965be47dfa54863784ad2aaed.png");
        pictureUrls.add("/images/20230327/a00fb0c47e504ae487d126bd4e115d85.png");
        pictureUrls.add("/images/20230327/1d859c3965be47dfa54863784ad2aaed.png");
        //这里把需要生成的图片然后添加进bufferedImages集合里
        ZipUtils.batchDownload(pictureUrls,httpServletResponse);
    }
}
