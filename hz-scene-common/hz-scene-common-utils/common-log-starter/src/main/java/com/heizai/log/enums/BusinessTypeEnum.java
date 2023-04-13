package com.heizai.log.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhulx
 * @version 1.0
 * @ClassName BusinessTypeEnum
 * @Description TODO 业务操作类型 枚举
 * @Date 2023/4/10 15:11
 * @since JDK1.8
 */
@Getter
@AllArgsConstructor
public enum BusinessTypeEnum {

    /**
     * 查询
     */
    SEARCH(1,"查询"),

    /**
     * 新增
     */
    INSERT(2,"新增"),

    /**
     * 修改
     */
    UPDATE(3,"编辑"),

    /**
     * 删除
     */
    DELETE(4,"删除"),

    /**
     * 启用
     */
    ENABLE(5,"启用"),

    /**
     * 导入
     */
    IMPORT(6,"导入"),

    /**
     * 导出
     */
    EXPORT(7,"导出"),

    /**
     * 强退
     */
    FORCE(8,"退出"),

    /**
     * 其他
     */
    OTHER(9,"其他"),

    /**
     * 登录
     */
    LOGIN(10,"登录");

    private final int code;
    private final String remark;
}
