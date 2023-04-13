package com.heizai.log.event;

import org.springframework.context.ApplicationEvent;

/**
 * 类名称：SysLogEvent
 * 类描述：TODO 系统日志事件
 * 创建人：Sun XueYong
 * 创建时间：2020-08-05 17:18
 */
public class SysLogEvent  extends ApplicationEvent {

    public SysLogEvent(Object source) {
        super(source);
    }
}
