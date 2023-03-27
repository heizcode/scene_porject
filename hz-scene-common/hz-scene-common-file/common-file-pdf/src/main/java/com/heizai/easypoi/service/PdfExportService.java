package com.heizai.easypoi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heizai.common.entity.SysLog;

import javax.servlet.http.HttpServletResponse;

/**
 * @author zhulx
 * @version 1.0
 * @ClassName ExportService
 * @Description TODO
 * @Date 2023/3/22 10:29
 * @since JDK1.8
 */
public interface PdfExportService extends IService<SysLog> {

    /**
     * 模板方式导出
     * @param response
     */
    void templateExport(HttpServletResponse response);

}
