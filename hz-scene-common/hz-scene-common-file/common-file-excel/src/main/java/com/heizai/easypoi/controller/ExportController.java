package com.heizai.easypoi.controller;

import com.heizai.easypoi.service.IExportService;
import com.heizai.log.annotation.SysLog;
import com.heizai.log.enums.BusinessTypeEnum;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RestController
@RequestMapping("/common/fileExcel/easypoi/")
public class ExportController {

    @Resource
    private IExportService exportService;

    /**
     * 问题：
     * 1.注解导出的时候 解决 时间格式化、枚举替换、下标、校验（未解决）
     * 2.模板导出的时候空数据会存在 {{ 的问题
     */

    /**
     * 注解方式导出
     * @param response
     */
    @SysLog(remark = "注解方式导出Excel", businessType = BusinessTypeEnum.EXPORT)
    @PostMapping("export")
    public void annotationExport(HttpServletResponse response) {
        // Tips:如果需要补充参数自行补充即可
        exportService.annotationExportList(response);
    }

}
