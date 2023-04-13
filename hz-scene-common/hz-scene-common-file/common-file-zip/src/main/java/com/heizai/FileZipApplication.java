package com.heizai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhulx
 * @version 1.0
 * @ClassName com.heizai.FilePdfApplication
 * @Description TODO 主启动类
 * @Date 2023/3/14 11:22
 * @since JDK1.8
 */
@SpringBootApplication
@MapperScan({"com.heizai.**.mapper*"})
public class FileZipApplication {

    /**
     * 主启动类
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(FileZipApplication.class);
    }

}
