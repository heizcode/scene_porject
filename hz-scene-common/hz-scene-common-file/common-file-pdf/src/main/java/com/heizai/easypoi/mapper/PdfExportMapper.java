package com.heizai.easypoi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heizai.common.entity.SysLog;
import com.heizai.common.vo.SysLogTemplateExportVo;

import java.util.List;

/**
 * @author zhulx
 * @version 1.0
 * @ClassName PdfExportMapper
 * @Description TODO
 * @Date 2023/3/22 10:31
 * @since JDK1.8
 */
public interface PdfExportMapper extends BaseMapper<SysLog> {

    /**
     * 查询 模板导出 数据
     * @return
     */
    List<SysLogTemplateExportVo> queryTemplateExport();

}
