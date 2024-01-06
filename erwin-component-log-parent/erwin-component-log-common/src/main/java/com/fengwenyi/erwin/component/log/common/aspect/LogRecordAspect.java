package com.fengwenyi.erwin.component.log.common.aspect;

import info.hxgy.component.common.aop.BaseAspect;
import info.hxgy.component.common.dto.BaseDto;
import info.hxgy.component.common.util.JacksonUtils;
import com.fengwenyi.erwin.component.log.common.annotation.LogRecord;
import com.fengwenyi.erwin.component.log.common.service.ILogPublisherService;
import com.fengwenyi.erwin.component.log.common.util.LogUtils;
import com.fengwenyi.erwin.component.log.model.constant.SampleAction;
import com.fengwenyi.erwin.component.log.model.dto.LogDto;
import com.fengwenyi.erwin.component.log.model.enums.LogLevelEnum;
import com.fengwenyi.erwin.component.log.model.enums.LogTypeEnum;
import com.fengwenyi.erwin.component.log.model.enums.LogWhoTypeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.util.Objects;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-12-25
 */
@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class LogRecordAspect extends BaseAspect {

    private final ILogPublisherService logPublisherService;

    @Around("@annotation(logRecord)")
    public Object around(ProceedingJoinPoint joinPoint, LogRecord logRecord) throws Throwable {

        String logKey = logRecord.logKey();
        String bizType = logRecord.bizType();
        String app = logRecord.app();

        String organCode = getKey(logRecord.organCode(), joinPoint);
        String cardNo = getKey(logRecord.cardNo(), joinPoint);
        String pmiNo = getKey(logRecord.pmiNo(), joinPoint);
        String bizId = getKey(logRecord.bizId(), joinPoint);
        String logBizId = getKey(logRecord.logBizId(), joinPoint);

        String channelCode = null;
        String userId = null;
        String accountId = null;
        String accountNo = null;
        String userName = null;
        String requestIp = null;

        String param = null;
        Object data = null;

        Object[] args = joinPoint.getArgs();
        if (null != args && args.length > 0) {

            param = JacksonUtils.json(args[0]);

            BaseDto baseDto = JacksonUtils.jsonObject(param, BaseDto.class);
            if (Objects.nonNull(baseDto)) {

                accountId = baseDto.getUserId();
                accountNo = baseDto.getUserId();
                userId = baseDto.getUserId();
                logBizId = baseDto.getLogBizId();
                channelCode = baseDto.getChannelCode();
                userName = baseDto.getUserName();
                cardNo = baseDto.getCardNo();
                pmiNo = baseDto.getPmiNo();
                requestIp = baseDto.getRequestIp();

                if (!StringUtils.hasText(organCode)) {
                    organCode = baseDto.getOrganCode();
                }

                if (!StringUtils.hasText(cardNo)) {
                    cardNo = baseDto.getCardNo();
                }

                if (!StringUtils.hasText(pmiNo)) {
                    pmiNo = baseDto.getPmiNo();
                }

                if (!StringUtils.hasText(bizId)) {
                    bizId = baseDto.getBizId();
                }

                if (!StringUtils.hasText(logBizId)) {
                    logBizId = baseDto.getLogBizId();
                }

                if (!StringUtils.hasText(logKey)) {
                    logKey = baseDto.getLogKey();
                }

                if (!StringUtils.hasText(bizType)) {
                    bizType = baseDto.getBizType();
                }
            }
        }

        HttpServletRequest request = getHttpServletRequest();

        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();

        long startTime = System.nanoTime();

        try {
            data = joinPoint.proceed();
            return data;
        } finally {
            Duration duration = Duration.ofNanos(System.nanoTime() - startTime);

            LogDto logDto = new LogDto();
            logDto.setOrganCode(organCode);
            logDto.setBizId(bizId);
            logDto.setBizType(bizType);
            logDto.setApplicationCode(app);
            logDto.setLogKey(logKey);
            logDto.setLogBizId(logBizId);
            logDto.setLogLevel(LogLevelEnum.info);
            logDto.setWhoType(LogWhoTypeEnum.user);
            logDto.setUserId(userId);
            logDto.setAccountId(accountId);
            logDto.setAccountNo(accountNo);
            logDto.setCardNo(cardNo);
            logDto.setPmiNo(pmiNo);
            logDto.setUserName(userName);
            logDto.setLogType(LogTypeEnum.operation);
            logDto.setAction(SampleAction.request);
            logDto.setMethod(LogUtils.getMethod());
            logDto.setRequestIp(requestIp);
            logDto.setChannelCode(channelCode);
            logDto.setMethod(className + "." + methodName + "()");
            logDto.setUrl(request.getRequestURL().toString());
            logDto.setParam(param);
            logDto.setData(data);
            logDto.setSpentTime(duration);
            logPublisherService.logEvent(logDto);
        }

    }

}
