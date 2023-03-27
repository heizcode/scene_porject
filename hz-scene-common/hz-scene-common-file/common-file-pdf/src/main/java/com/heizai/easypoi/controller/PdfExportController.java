package com.heizai.easypoi.controller;

import com.heizai.easypoi.service.PdfExportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhulx
 * @version 1.0
 * @ClassName ExportController
 * @Description TODO easypoi EasyPoi导出使用
 * @Date 2023/3/14 16:16
 * @since JDK1.8
 */
@RestController
@RequestMapping("/common/filePdf/easypoi/")
public class PdfExportController {

    @Resource
    private PdfExportService pdfExportService;

    /**
     * 模板方式导出
     * @param response
     */
    //@PostMapping("templateExport")
    @GetMapping("templateExport")
    private void annotationExport(HttpServletResponse response) {
        pdfExportService.templateExport(response);
    }

}
