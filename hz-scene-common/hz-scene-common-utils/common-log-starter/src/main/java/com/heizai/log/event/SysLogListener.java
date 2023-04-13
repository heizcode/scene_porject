package com.heizai.log.event;

import com.heizai.log.dto.SysLogDto;
import com.heizai.log.service.ISysLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

/**
 * 类名称：SysLogListener
 * 类描述：TODO 异步监听日志事件
 * 创建人：Sun XueYong
 * 创建时间：2020-08-05 17:19
 */
@Slf4j
@RequiredArgsConstructor
public class SysLogListener {

   private final ISysLogService wiseLogService;

    @Async
    @Order
    @EventListener(SysLogEvent.class)
    public void saveSysLog(SysLogEvent event) {
        SysLogDto wiseLog = (SysLogDto) event.getSource();
        wiseLogService.save(wiseLog);
    }

}
