package com.heizai.easypoi.service.impl;

import cn.afterturn.easypoi.word.WordExportUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heizai.common.utils.AsposeUtil;
import com.heizai.common.vo.SysLogTemplateExportVo;
import com.heizai.easypoi.service.PdfExportService;
import com.heizai.common.entity.SysLog;
import com.heizai.easypoi.mapper.PdfExportMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * @author zhulx
 * @version 1.0
 * @ClassName PdfExportServiceImpl
 * @Description TODO
 * @Date 2023/3/22 10:30
 * @since JDK1.8
 */
@Slf4j
@Service
public class PdfExportServiceImpl extends ServiceImpl<PdfExportMapper, SysLog> implements PdfExportService {

    @Resource
    private PdfExportMapper pdfExportMapper;

    /**
     * 模板方式导出
     * @param response
     * https://blog.csdn.net/w15201128473/article/details/127072249
     */
    @Override
    public void templateExport(HttpServletResponse response){
        List<SysLogTemplateExportVo> sysLogTemplateExportVoList = pdfExportMapper.queryTemplateExport();
        String time = "2021-06";
        try {
            //读取模板
            File rootFile = new File((ResourceUtils.getURL("classpath:").getPath()));
            File templateFile = new File(rootFile, "/pdftemplate/operation_sys_log_template.docx");
            Map<String, Object> maps = new HashMap<>();
            Object[] split = time.split("-");
            //存储模板数据
            maps.put("operationList", sysLogTemplateExportVoList);
            maps.put("yaer", split[0]);
            maps.put("month", split[1]);
            maps.put("textLog", "这是一段很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长的测试内容!");
            XWPFDocument word = WordExportUtil.exportWord07(templateFile.getPath(), maps);
            String fileName = "后台系统（" + time + "）月操作详情_" + new Date();
            /*转换pdf*/
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            word.write(bos);
            InputStream input = new ByteArrayInputStream(bos.toByteArray());
            AsposeUtil.wordToPdf(input,fileName,response);
            word.close();
        } catch (Exception e) {
            log.error("导出异常{}", e.getMessage(), e);
            e.printStackTrace();
        }
    }
}
