package com.heizai.log.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author zhulx
 * @version 1.0
 * @ClassName Global
 * @Description TODO 全局配置类
 * @Date 2023/4/13 11:17
 * @since JDK1.8
 */
@Component
public class Global {

    /** 模块ID */
    public static Long moduleId;

    /** 模块名称 */
    public static String moduleName;

    @Value("${hz-sys.global.moduleId}")
    private void setModuleId(Long moduleId) {
        Global.moduleId = moduleId;
    }

    @Value("${hz-sys.global.moduleName}")
    private void setModuleName(String moduleName) {
        Global.moduleName = moduleName;
    }
}
