package com.fengwenyi.erwin.component.log.lister;

import info.hxgy.component.common.util.JacksonUtils;
import com.fengwenyi.erwin.component.log.common.event.LogEvent;
import com.fengwenyi.erwin.component.log.model.dto.LogDto;
import com.fengwenyi.erwin.component.log.service.ILogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-12-14
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class LogLister {

    private final ILogService logService;

    @EventListener
    @Async("logComponentPool")
    public void logEvent(LogEvent event) {

        Object source = event.getSource();

        if (Objects.nonNull(source)) {
            try {
                LogDto dto = (LogDto) source;
                logService.saveLog(dto);
                log.info("logEvent, saved, source: [{}]", JacksonUtils.json(source));
            } catch (Exception e) {
                log.error("logEvent, save failed, source: [{}], exception: {}",
                        JacksonUtils.json(source), e.getLocalizedMessage(), e);
            }

        }

    }

}