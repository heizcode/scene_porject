package com.heizai.easypoi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heizai.common.entity.WiseLog;

import javax.servlet.http.HttpServletResponse;

/**
 * @author zhulx
 * @version 1.0
 * @ClassName ExportService
 * @Description TODO
 * @Date 2023/3/14 17:54
 * @since JDK1.8
 */
public interface ExportService extends IService<WiseLog> {

    /**
     * 注解方式导出
     */
    void annotationExportList(HttpServletResponse response);

}