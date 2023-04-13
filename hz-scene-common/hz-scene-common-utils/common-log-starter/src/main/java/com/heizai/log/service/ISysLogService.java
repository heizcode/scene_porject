package com.heizai.log.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heizai.log.dto.SysLogDto;
import com.heizai.log.entity.SysLog;

/**
 * 类名称：WiseLog
 * 类描述：
 * 创建人：
 * 创建时间：2021-01-24 19:38
 */
public interface ISysLogService extends IService<SysLog> {
    void save(SysLogDto wiseLog);
}
