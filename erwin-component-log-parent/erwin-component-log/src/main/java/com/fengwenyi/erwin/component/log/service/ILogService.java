package com.fengwenyi.erwin.component.log.service;

import info.hxgy.component.common.dto.BaseDto;
import com.fengwenyi.erwin.component.log.model.dto.LogDto;
import com.fengwenyi.erwin.component.log.model.dto.LogQueryDto;
import com.fengwenyi.erwin.component.log.model.vo.LogVo;
import com.fengwenyi.erwin.component.log.model.vo.OptionVo;
import info.hxgy.platform.commons.core.page.HxPage;
import info.hxgy.platform.commons.core.page.HxPageRequest;

import java.util.List;
import java.util.Map;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-12-14
 */
public interface ILogService {

    void saveLog(LogDto dto);

    HxPage<LogVo> getPage(HxPageRequest<LogQueryDto> hxPageRequest);

    Map<String, List<OptionVo>> getOption(BaseDto dto);

}
