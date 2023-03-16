package com.heizai.common.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author zhulx
 * @version 1.0
 * @ClassName WiseLogVo
 * @Description TODO
 * @Date 2023/3/15 17:26
 * @since JDK1.8
 */
@Data
public class WiseLogVo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 导出下标序号
     * name：列名
     * orderNum：开始序号
     * format：格式
     */
    @Excel(name = "序号",orderNum = "0",format = "isAddIndex")
    private Long indexNo;

    /**
     * 模块名称
     */
    @Excel(name = "模块名称")
    private String moduleName;

    /**
     * 日志标题
     */
    @Excel(name = "日志标题")
    private String remark;

    /**
     * 请求方式
     */
    @Excel(name = "请求方式")
    private String requestMethod;

    /**
     * 请求URI
     */
    @Excel(name = "请求URI")
    private String requestUrl;

    /**
     * 操作人员
     */
    @Excel(name = "操作人员")
    private String operName;

    /**
     * 操作IP地址
     */
    @Excel(name = "操作IP地址")
    private String operIp;

    /**
     * 操作地址
     */
    @Excel(name = "操作地址")
    private String operLocation;

    /**
     * 操作时间
     */
    @Excel(name = "操作时间", databaseFormat = "yyyy-MM-dd")
    private LocalDateTime operTime;

    /**
     * 用户浏览器
     */
    @Excel(name = "用户浏览器")
    private String userAgent;

    /**
     * 操作状态 1 正常  0 异常
     */
    @Excel(name = "用户浏览器",replace = {"正常_1", "异常_0"})
    private Integer businessStatus;
}
