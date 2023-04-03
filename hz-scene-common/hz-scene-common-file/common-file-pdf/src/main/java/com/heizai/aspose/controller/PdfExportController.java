package com.heizai.aspose.controller;

import com.heizai.aspose.service.PdfExportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhulx
 * @version 1.0
 * @ClassName ExportController
 * @Description TODO aspose word 转 pdf 导出使用
 * @Date 2023/3/14 16:16
 * @since JDK1.8
 */
@RestController
@RequestMapping("/common/filePdf/aspose/")
public class PdfExportController {

    @Resource
    private PdfExportService pdfExportService;

    /**
     * word 转 pdf
     * @param response
     */
    //@PostMapping("templateExport")
    @GetMapping("templateExport")
    private void annotationExport(HttpServletResponse response) {
        pdfExportService.templateExport(response);
    }

}
