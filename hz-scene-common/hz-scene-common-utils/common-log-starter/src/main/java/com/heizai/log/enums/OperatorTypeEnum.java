package com.heizai.log.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhulx
 * @version 1.0
 * @ClassName OperatorTypeEnum
 * @Description TODO 操作人类别 枚举
 * @Date 2023/4/10 15:15
 * @since JDK1.8
 */
@Getter
@AllArgsConstructor
public enum OperatorTypeEnum {

    /**
     * 后台用户
     */
    MANAGE(1, "后台用户"),

    /**
     * 手机端用户
     */
    MOBILE(2, "移动端用户"),

    /**
     * 其他
     */
    OTHER(3,"其他");

    private final int code;
    private final String remark;
}
