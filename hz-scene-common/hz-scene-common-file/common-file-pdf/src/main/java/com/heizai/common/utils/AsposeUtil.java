package com.heizai.common.utils;

import com.aspose.words.*;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


/**
 * @author zhulx
 * @version 1.2.0
 * @ClassName AsposeUtil
 * @Description TODO Aspose 工具类
 * @Date 2023/4/3 14:48
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
            InputStream is = AsposeUtil.class.getResourceAsStream("/License.xml");
            log.info("水印文件路径{}", AsposeUtil.class.getResource("/License.xml").getFile());
            License aposeLic = new License();
            aposeLic.setLicense(is);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 检查环境（解决乱码）
     */
    private static void checkSetting() {
        //解决乱码
        String osName = System.getProperty("os.name", "");
        if (osName.startsWith("Mac OS")) {
            log.info("当前操作系统环境：Mac OS");
        } else if (osName.startsWith("Windows") || osName.startsWith("win")) {
            /*FontSettings.getDefaultInstance().setFontsFolder(AsposeUtil.class.getResource("/font").getFile(), true);
            log.info("Windows字体包路径{}", AsposeUtil.class.getResource("/font").getFile());*/
            log.info("当前操作系统环境：Windows");
        } else {
            // Linux环境(固定放在此目录)
            FontSettings.getDefaultInstance().setFontsFolder("/usr/share/fonts/chinese", true);
            log.info("当前操作系统环境：Linux");
            log.info("Linux字体包路径{}", "/usr/share/fonts/chinese");
        }
    }

    /**
     * 测试方法
     * @param args
     * @throws Exception
     */
    /*public static void main(String[] args) throws Exception {
        AsposeUtil bean = new AsposeUtil();
        AsposeUtil.wordToPdf("D:\\res\\test.docx","D:\\res\\test-001.pdf");
    }*/

    /**
     * word转pdf 保存本地
     * inpath: 输入word的路径
     * outpath: 输出pdf的路径
     */
    public static void wordToPdf(String inpath,String outpath) {
        if (!getLicense()) {
            log.info("水印校验非法");
            return;
        }
        log.info("开始对文档进行pdf转换");
        long old = System.currentTimeMillis();
        File file = new File(outpath);
        try {
            FileOutputStream os = new FileOutputStream(file);
            Document doc = new Document(inpath);
            //全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF, EPUB, XPS, SWF 相互转换
            doc.save(os, SaveFormat.PDF);
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            long now = System.currentTimeMillis();
            log.info("Aspose.words转换结束，共耗时：" + ((now - old) / 1000.0) + "秒");
        }
    }


    /**
     * word转pdf 保存本地
     * @param path      pdf输出路径
     * @param wordInput word输入流
     * @param wordName  word文档的名称
     */
    public static void wordToPdf(String path, InputStream wordInput, String wordName) {
        if (!getLicense()) {
            log.info("水印校验非法");
            return;
        }
        log.info("开始对文档[{}]进行pdf转换", wordName);
        long old = System.currentTimeMillis();
        //新建一个空白pdf文档
        File file = new File(path + wordName + ".pdf");
        FileOutputStream os = null;
        try {
            os = new FileOutputStream(file);
            //Address是将要被转化的word文档
            Document doc = null;
            doc = new Document(wordInput);
            //全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF, EPUB, XPS, SWF 相互转换
            doc.save(os, SaveFormat.PDF);
            os.close();
        } catch (Exception e) {
            log.error("word转换异常{}", e.getMessage(), e);
            e.printStackTrace();
        }finally {
            //转化用时
            long now = System.currentTimeMillis();
            log.info("共耗时：" + ((now - old) / 1000.0) + "秒");
        }
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
        log.info("开始对文档[{}]进行pdf转换", wordName);
        long old = System.currentTimeMillis();
        try {
            //校验环境
            AsposeUtil.checkSetting();
            //Servlet响应 输出流 方便输出预览
            ServletOutputStream outputStream = response.getOutputStream();
            //获取传入word输入流
            Document doc = new Document(wordInput);
            //保存PDF
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