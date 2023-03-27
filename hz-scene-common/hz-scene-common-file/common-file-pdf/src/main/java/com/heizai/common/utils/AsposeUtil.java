package com.heizai.common.utils;

import com.aspose.words.*;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


/**
 * @author zhulx
 * @version 1.0
 * @ClassName AsposeUtil
 * @Description TODO Aspose 工具类
 * @Date 2023/3/24 16:35
 * @since JDK1.8
 */
@Slf4j
public class AsposeUtil {

    /**
     * 验证License 若不验证则转化出的pdf文档会有水印产生
     * @return
     */
    private static boolean getLicense() {
        boolean result = false;
        try {
            InputStream is = AsposeUtil.class.getClassLoader().getResourceAsStream("license.xml");
            log.info("is =** ", is);
            License aposeLic = new License();
            aposeLic.setLicense(is);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        AsposeUtil bean = new AsposeUtil();
        AsposeUtil.wordToPdf("D:\\res\\test.docx","D:\\res\\test-001.pdf");
    }

    /**
     * word转pdf 保存本地
     * inpath: 输入word的路径
     * outpath: 输出pdf的路径
     */
    public static void wordToPdf(String inpath,String outpath) throws Exception {
        if (!getLicense()) {
            log.info("非法------------");
            return;
        }

        log.info("开始使用Aspose.words进行转换");

        long old = System.currentTimeMillis();
        File file = new File(outpath);

        FileOutputStream os = new FileOutputStream(file);

        //解决乱码
        //如果是windows执行，不需要加这个
        String osName = System.getProperty("os.name", "");
        if (osName.startsWith("Mac OS")) {
        } else if (osName.startsWith("Windows")) {
        } else { // assume Unix or Linux
            //如果是linux执行，需要添加这个 ，如果还有乱码，可以把/usr/share/fonts/chinese路径下的所有文件拷贝到有问题的环境。并且再执行：source /etc/profile
            new FontSettings().setFontsFolder("/usr/share/fonts/chinese",true);
        }

        Document doc = new Document(inpath);

        //全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF, EPUB, XPS, SWF 相互转换
        doc.save(os, SaveFormat.PDF);
        long now = System.currentTimeMillis();
        os.close();
        log.info("Aspose.words转换结束，共耗时：" + ((now - old) / 1000.0) + "秒");
    }


    /**
     * word转pdf 保存本地
     * @param path      pdf输出路径
     * @param wordInput word输入流
     * @param wordName  word文档的名称
     */
    public static void wordToPdf(String path, InputStream wordInput, String wordName) throws Exception {
        if (!getLicense()) {
            log.info("非法");
            return;
        }
        //新建一个空白pdf文档
        long old = System.currentTimeMillis();
        File file = new File(path + wordName + ".pdf");
        FileOutputStream os = new FileOutputStream(file);

        //Address是将要被转化的word文档
        Document doc = null;
        try {
            doc = new Document(wordInput);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            //全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF, EPUB, XPS, SWF 相互转换
            doc.save(os, SaveFormat.PDF);
        } catch (Exception e) {
            e.printStackTrace();
        }
        long now = System.currentTimeMillis();
        //转化用时
        os.close();
        log.info("共耗时：" + ((now - old) / 1000.0) + "秒");
    }

    /**
     * word转pdf 输出流（预览）
     * @param wordInput     word输入流
     * @param wordName      word文档的名称
     * @param response      Servlet响应
     */
    public static void wordToPdf(InputStream wordInput, String wordName, HttpServletResponse response) {
        if (!getLicense()) {
            log.info("水印校验非法");
            return;
        }
        log.info("开始对{}进行pdf转换", wordName);
        long old = System.currentTimeMillis();
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            //Address是将要被转化的word文档
            Document doc = null;
            doc = new Document(wordInput);
            //全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF, EPUB, XPS, SWF 相互转换
            doc.save(outputStream, SaveFormat.PDF);
        } catch (Exception e) {
            log.error("word转换异常{}", e.getMessage(), e);
            e.printStackTrace();
        }finally {
            long now = System.currentTimeMillis();
            log.info("共耗时：" + ((now - old) / 1000.0) + "秒");
        }
    }
}