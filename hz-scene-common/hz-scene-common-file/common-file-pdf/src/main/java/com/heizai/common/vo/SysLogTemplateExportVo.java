package com.heizai.common.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhulx
 * @version 1.0
 * @ClassName SysLogTemplateExportVo
 * @Description TODO
 * @Date 2023/3/23 14:40
 * @since JDK1.8
 */
@Data
public class SysLogTemplateExportVo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 操作内容
     */
    private String remark;

    /**
     * 请求路径
     */
    private String requestUrl;

    /**
     * 操作时间
     */
    private String operTime;

    /**
     * 操作ip
     */
    private String operIp;

}
