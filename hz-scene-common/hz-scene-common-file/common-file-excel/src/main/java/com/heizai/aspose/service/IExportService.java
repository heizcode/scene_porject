package com.heizai.aspose.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heizai.common.entity.SysLog;

import javax.servlet.http.HttpServletResponse;

/**
 * @author zhulx
 * @version 1.0
 * @ClassName ExportService
 * @Description TODO
 * @Date 2023/3/14 17:54
 * @since JDK1.8
 */
public interface IExportService extends IService<SysLog> {

    /**
     * 注解方式导出
     * @param response
     */
    void annotationExportList(HttpServletResponse response);

}
