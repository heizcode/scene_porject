package com.heizai.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author zhulx
 * @version 1.0
 * @ClassName WiseLog
 * @Description TODO 日志实体类
 * @Date 2023/3/14 16:35
 * @since JDK1.8
 */
@Data
@TableName("sys_log")
public class SysLog implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 模块Id
     */
    @TableField("module_id")
    private Long moduleId;

    /**
     * 模块名称
     */
    @TableField("module_name")
    private String moduleName;

    /**
     * 日志类型
     */
    @TableField("business_type")
    private Integer businessType;

    /**
     * 业务类型名称
     */
    @TableField("business_type_name")
    private String businessTypeName;

    /**
     * 日志标题
     */
    @TableField("remark")
    private String remark;

    /**
     * 请求方法
     */
    @TableField("method_name")
    private String methodName;

    /**
     * 请求方式
     */
    @TableField("request_method")
    private String requestMethod;

    /**
     * 请求URI
     */
    @TableField("request_url")
    private String requestUrl;

    /**
     * 操作类别（0其它 1后台用户 2手机端用户）
     */
    @TableField("operator_type")
    private Integer operatorType;

    /**
     * 操作类别名称
     */
    @TableField("operator_type_name")
    private String operatorTypeName;

    /**
     * 请求参数
     */
    @TableField("params")
    private String params;

    /**
     * 返回参数
     */
    @TableField("json_result")
    private String jsonResult;

    /**
     * 操作人员
     */
    @TableField("oper_name")
    private String operName;

    /**
     * 操作IP地址
     */
    @TableField("oper_ip")
    private String operIp;

    /**
     * 操作地址
     */
    @TableField("oper_location")
    private String operLocation;

    /**
     * 操作时间
     */
    @TableField("oper_time")
    private LocalDateTime operTime;

    /**
     * 用户浏览器
     */
    @TableField("user_agent")
    private String userAgent;

    /**
     * 执行时长
     */
    @TableField("duration")
    private Long duration;

    /**
     * 错误消息
     */
    @TableField("error_msg")
    private String errorMsg;

    /**
     * 操作状态 1 正常  0 异常
     */
    @TableField("business_status")
    private Integer businessStatus;
}
