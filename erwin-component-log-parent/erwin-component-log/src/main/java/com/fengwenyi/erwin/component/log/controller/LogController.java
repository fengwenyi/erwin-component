package com.fengwenyi.erwin.component.log.controller;

import info.hxgy.component.common.dto.BaseDto;
import com.fengwenyi.erwin.component.log.model.dto.LogDto;
import com.fengwenyi.erwin.component.log.model.dto.LogQueryDto;
import com.fengwenyi.erwin.component.log.model.vo.LogVo;
import com.fengwenyi.erwin.component.log.model.vo.OptionVo;
import com.fengwenyi.erwin.component.log.service.ILogService;
import info.hxgy.platform.commons.core.page.HxPage;
import info.hxgy.platform.commons.core.page.HxPageRequest;
import info.hxgy.platform.commons.core.response.BaseResponse;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-12-14
 */
@Api(tags = "日志-API")
@RestController
@RequestMapping("/log")
@RequiredArgsConstructor
public class LogController {

    private final ILogService logService;

    @PostMapping("/save")
    public BaseResponse<Void> save(@Validated @RequestBody LogDto dto) {

        logService.saveLog(dto);

        return BaseResponse.success();
    }

    @ApiOperation("日志分页查询接口")
    @PostMapping("/getPage")
    public BaseResponse<HxPage<LogVo>> getPage(@Validated @RequestBody HxPageRequest<LogQueryDto> hxPageRequest) {

        HxPage<LogVo> hxPage = logService.getPage(hxPageRequest);

        return BaseResponse.success(hxPage);

    }

    /**
     * key如下：
     *
     * <ul>
     *     <li>organ-机构</li>
     *     <li>bizType-业务类型</li>
     *     <li>logKey-接口</li>
     *     <li>application-服务</li>
     *     <li>logLevel-日志级别</li>
     *     <li>channel-渠道</li>
     *     <li>action-操作</li>
     * </ul>
     */
    @ApiOperation("日志选项查询接口")
    @PostMapping("/getOption")
    public BaseResponse<Map<String, List<OptionVo>>> getOption(@Validated @RequestBody BaseDto dto) {

        Map<String, List<OptionVo>> optionMap = logService.getOption(dto);

        return BaseResponse.success(optionMap);

    }

}
