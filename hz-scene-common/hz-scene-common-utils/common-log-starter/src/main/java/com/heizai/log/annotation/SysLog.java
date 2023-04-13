package com.heizai.log.annotation;

import com.heizai.log.enums.BusinessTypeEnum;
import com.heizai.log.enums.OperatorTypeEnum;

import java.lang.annotation.*;

/**
 * @author zhulx
 * @version 1.0
 * @ClassName SysLog
 * @Description TODO 系统日志注解
 * @Date 2023/4/7 17:05
 * @since JDK1.8
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

    /**
     * 日志备注
     */
    String remark() default "日志备注";

    /**
     * 功能
     * @return
     */
    BusinessTypeEnum businessType() default BusinessTypeEnum.SEARCH;

    /**
     * 操作人类别
     * @return
     */
    OperatorTypeEnum operatorType() default OperatorTypeEnum.MANAGE;

    /**
     * 是否保存请求的参数
     */
    boolean isSaveRequestData() default true;

}
