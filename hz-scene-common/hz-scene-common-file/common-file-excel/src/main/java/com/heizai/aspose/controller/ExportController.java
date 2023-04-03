package com.heizai.aspose.controller;

import com.heizai.aspose.service.ExportService;
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
@RequestMapping("/common/fileExcel/easypoi/")
public class ExportController {

    @Resource
    private ExportService exportService;

    /**
     * 注解方式导出
     * @param response
     */
    @PostMapping("export")
    private void annotationExport(HttpServletResponse response) {
        // Tips:如果需要补充参数自行补充即可
        exportService.annotationExportList(response);
    }

}
