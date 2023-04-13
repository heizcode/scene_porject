package com.heizai.log.utils;

/**
 * @author zhulx
 * @version 1.0
 * @ClassName CommonConstantEnum
 * @Description TODO
 * @Date 2023/4/11 11:18
 * @since JDK1.8
 */
public interface CommonConstant {

    String TIME_ZONE_GMT8 = "GMT+8";

    /**
     * 用于IP定位转换
     */
    String REGION = "内网IP|内网IP";

    /**
     * 常用接口
     */
    interface Url {
        // IP归属地查询
        String IP_URL = "http://whois.pconline.com.cn/ipJson.jsp?ip=%s&json=true";
    }
}
