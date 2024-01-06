package com.fengwenyi.erwin.component.log.common.service;

import com.fengwenyi.erwin.component.log.model.dto.LogDto;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-12-14
 */
public interface ILogPublisherService {

    void logEvent(LogDto dto);

}
