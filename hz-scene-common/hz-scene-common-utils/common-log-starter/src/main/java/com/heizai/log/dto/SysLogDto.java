package com.heizai.log.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @author zhulx
 * @version 1.0
 * @ClassName SysLog
 * @Description TODO 系统日志 Dto
 * @Date 2023/4/10 15:23
 * @since JDK1.8
 */
@Data
public class SysLogDto {

    /**
     * 编号
     */
    private Long id;

    /**
     * 模块Id
     */
    private Long moduleId;

    /**
     * 模块名称
     */
    private String moduleName;

    /**
     * 日志类型
     */
    @NotBlank(message = "业务类型不能为空")
    private Integer businessType;

    /**
     * 业务类型名称
     */
    private String businessTypeName;
    /**
     * 日志标题
     */
    @NotBlank(message = "业务描述不能为空")
    private String remark;

    /**
     * 请求方法
     */
    private String methodName;

    /**
     * 请求方式
     */
    private String requestMethod;

    /**
     * 操作类别（0其它 1后台用户 2手机端用户）
     */
    private Integer operatorType;

    /**
     * 操作类别名称
     */
    private String operatorTypeName;

    /**
     * 请求URI
     */
    private String requestUrl;

    /**
     * 请求参数
     */
    private String params;

    /**
     * 返回参数
     */
    private String jsonResult;

    /**
     * 操作状态 1 正常  0 异常
     */
    private Integer businessStatus;

    /**
     * 错误消息
     */
    private String errorMsg;

    /**
     * 操作人员
     */
    private String operName;

    /**
     * 操作IP地址
     */
    private String operIp;

    /**
     * 操作地址
     */
    private String operLocation;

    /**
     * 操作时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private LocalDateTime operTime;

    /**
     * 用户浏览器
     */
    private String userAgent;

    /**
     * 执行时长
     */
    private Long duration;
}
