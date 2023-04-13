package com.heizai.log.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heizai.log.dto.SysLogDto;
import com.heizai.log.entity.SysLog;
import com.heizai.log.mapper.SysLogMapper;
import com.heizai.log.service.ISysLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * 类名称：WiseLog
 * 类描述：
 * 创建人：
 * 创建时间：2021-01-24 19:38
 */
@Slf4j
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements ISysLogService {

    @Resource
    private SysLogMapper mapper;

    @Async
    @Override
    public void save(SysLogDto wiseLog) {
        SysLog sysLog = new SysLog();
        BeanUtil.copyProperties(wiseLog,sysLog);
        mapper.insert(sysLog);

    }
}
