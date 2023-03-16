package com.heizai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhulx
 * @version 1.0
 * @ClassName com.heizai.FileExcelApplication
 * @Description TODO 主启动类
 * @Date 2023/3/14 11:22
 * @since JDK1.8
 */
@SpringBootApplication
@MapperScan(basePackages = "com.heizai.easypoi.mapper")
public class FileExcelApplication {

    /**
     * 主启动类
     * 遇到的问题记录：https://blog.csdn.net/M_Love_U/article/details/123469003
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(FileExcelApplication.class);
    }

}
