package com.heizai.log;

import com.heizai.log.event.SysLogListener;
import com.heizai.log.service.ISysLogService;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 类名称：LogAutoConfiguration
 * 类描述：TODO 日志自动配置
 * 创建人：
 * 创建时间：2020-08-05 17:25
 */
@EnableAsync
@ConditionalOnWebApplication
@MapperScan(basePackages = "com.heizai.log.mapper")
@Configuration(proxyBeanMethods = false)
@RequiredArgsConstructor
public class LogAutoConfiguration {

    private final ISysLogService wiseLogService;

    @Bean
    public SysLogListener sysLogListener() {
        return new SysLogListener(wiseLogService);
    }
}
