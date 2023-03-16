package com.heizai.easypoi.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heizai.common.entity.WiseLog;
import com.heizai.common.utils.BeanUtils;
import com.heizai.common.utils.ExcelUtils;
import com.heizai.common.vo.WiseLogVo;
import com.heizai.easypoi.mapper.ExportMapper;
import com.heizai.easypoi.service.ExportService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
public class ExportServiceImpl extends ServiceImpl<ExportMapper, WiseLog> implements ExportService {

    @Resource
    private ExportMapper exportMapper;

    /**
     * 注解方式导出
     * @param response
     */
    @Override
    public void annotationExportList(HttpServletResponse response) {
        try {
            // 获取数据集合
            List<WiseLog> wiseLogs = exportMapper.selectList(Wrappers.<WiseLog>lambdaQuery());
            // list对象复制
            List<WiseLogVo> wiseLogVoList = BeanUtils.copyProperties(wiseLogs, WiseLogVo.class);
            // 调用工具类导出
            ExcelUtils.exportExcel(wiseLogVoList,"操作日志","日志",WiseLogVo.class,"日志记录",response);
        } catch (IOException e) {
            // Tips： 此处可以添加自定义异常抛出
            e.printStackTrace();
        }
    }
}
