package com.heizai.easypoi.service.impl;

import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heizai.common.entity.SysLog;
import com.heizai.common.utils.BeanUtils;
import com.heizai.common.utils.ExcelUtils;
import com.heizai.common.vo.SysLogVo;
import com.heizai.easypoi.mapper.ExportMapper;
import com.heizai.easypoi.service.IExportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author zhulx
 * @version 1.0
 * @ClassName ExportServiceImpl
 * @Description TODO
 * @Date 2023/3/14 17:55
 * @since JDK1.8
 */
@Service
@Slf4j
public class ExportServiceImpl extends ServiceImpl<ExportMapper, SysLog> implements IExportService {

    @Resource
    private ExportMapper exportMapper;

    /**
     * 注解方式导出
     * @param response
     */
    @Override
    public void annotationExportList(HttpServletResponse response)  {
        try {
            // 获取数据集合
            List<SysLog> wiseLogs = exportMapper.selectList(Wrappers.<SysLog>lambdaQuery());
            // list对象复制
            List<SysLogVo> wiseLogVoList = BeanUtils.copyProperties(wiseLogs, SysLogVo.class);
            // 调用工具类导出
            ExcelUtils.exportExcel(wiseLogVoList,"操作日志","日志",
                    SysLogVo.class, ExcelType.XSSF,"日志记录",response);
        } catch (Exception e) {
            // Tips： 此处可以添加自定义异常抛出
            log.error("日志数据导出失败：{}", e.getMessage(), e);
            throw new RuntimeException("日志数据导出失败，请联系管理员！");
        }
    }
}
