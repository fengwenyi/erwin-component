package com.fengwenyi.erwin.component.log.client.jk;

import com.fengwenyi.erwin.component.log.model.dto.LogDto;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2024-01-06
 */
public interface ILogClient {

    void save(LogDto dto);

}
