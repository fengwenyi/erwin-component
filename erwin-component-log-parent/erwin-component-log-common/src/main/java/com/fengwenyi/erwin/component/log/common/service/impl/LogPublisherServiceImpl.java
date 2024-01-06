package com.fengwenyi.erwin.component.log.common.service.impl;

import info.hxgy.component.common.util.JacksonUtils;
import com.fengwenyi.erwin.component.log.common.event.LogEvent;
import com.fengwenyi.erwin.component.log.common.service.ILogPublisherService;
import com.fengwenyi.erwin.component.log.model.dto.LogDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-12-14
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class LogPublisherServiceImpl implements ILogPublisherService {

    private final ApplicationContext applicationContext;

    @Override
    public void logEvent(LogDto dto) {
        try {
            applicationContext.publishEvent(new LogEvent(dto));
        } catch (Exception e) {
            log.info("logEvent publish failed, logDto: [{}], exception: {}", JacksonUtils.json(dto),
                    e.getLocalizedMessage(), e);
        }
    }

}
