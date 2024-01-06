package com.fengwenyi.erwin.component.log.service.impl;

import info.hxgy.component.common.constant.ComponentConstant;
import info.hxgy.component.common.dto.BaseDto;
import info.hxgy.component.common.incrementer.IdGenerator;
import info.hxgy.component.common.ip2region.util.IpRegionUtils;
import info.hxgy.component.common.util.*;
import info.hxgy.component.config.service.IConfigService;
import com.fengwenyi.erwin.component.log.constant.LogOptionConstant;
import com.fengwenyi.erwin.component.log.entity.LogEntity;
import com.fengwenyi.erwin.component.log.model.dto.LogDto;
import com.fengwenyi.erwin.component.log.model.dto.LogQueryDto;
import com.fengwenyi.erwin.component.log.model.vo.LogVo;
import com.fengwenyi.erwin.component.log.model.vo.OptionVo;
import com.fengwenyi.erwin.component.log.repository.ILogRepository;
import com.fengwenyi.erwin.component.log.service.ILogService;
import info.hxgy.platform.commons.core.page.HxPage;
import info.hxgy.platform.commons.core.page.HxPageRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-12-14
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class LogServiceImpl implements ILogService {

    private final ILogRepository logRepository;
    private final IConfigService configService;
    private final IdGenerator idGenerator;

    @Override
    public void saveLog(LogDto dto) {
        LogEntity entity = new LogEntity();
        entity.setId(idGenerator.nextId().longValue());
        entity.setOrganCode(dto.getOrganCode());
        entity.setBizId(dto.getBizId());
        entity.setBizType(dto.getBizType());
        entity.setApplicationCode(dto.getApplicationCode());
        entity.setLogKey(dto.getLogKey());
        entity.setMethod(dto.getMethod());
        entity.setAction(dto.getAction());
        entity.setLogBizId(dto.getLogBizId());
        entity.setCardNo(dto.getCardNo());
        entity.setPmiNo(dto.getPmiNo());
        entity.setUserName(dto.getUserName());
        entity.setWhoType(dto.getWhoType());
        entity.setUserId(dto.getUserId());
        entity.setAccountId(dto.getAccountId());
        entity.setAccountNo(dto.getAccountNo());
        entity.setLogType(dto.getLogType());
        entity.setLogLevel(dto.getLogLevel());
        entity.setRequestIp(dto.getRequestIp());
        entity.setChannelCode(dto.getChannelCode());
        entity.setUrl(dto.getUrl());
        entity.setParam(convertString(dto.getParam()));
        entity.setData(convertString(dto.getData()));
        entity.setMessage(dto.getMessage());
        entity.setLogDateTime(dto.getLogDateTime());

        if (Objects.nonNull(dto.getSpentTime())) {
            entity.setSpentSeconds(dto.getSpentTime().toMillis() / 1000.0);
        }

        logRepository.save(entity);
    }

    @Override
    public HxPage<LogVo> getPage(HxPageRequest<LogQueryDto> hxPageRequest) {

        String sortField = "logDateTime";

        PageRequest pageRequest = PageRequest.of(
                hxPageRequest.getPageNum() - 1,
                hxPageRequest.getPageSize(),
                Sort.by(Sort.Direction.DESC, sortField)
        );

        Page<LogEntity> page = logRepository.findAll(
                (root, query, builder)
                        -> buildQueryPredicate(root, query, builder, hxPageRequest.getQuery()),
                pageRequest
        );

        HxPage<LogVo> hxPage = HxPageUtils.convert(page, false, sortField);

        if (!CollectionUtils.isEmpty(page.getContent())) {
            hxPage.setContent(
                    page.getContent()
                            .stream()
                            .map(this::buildLogVo)
                            .collect(Collectors.toList())
            );
        }

        return hxPage;
    }

    @Override
    public Map<String, List<OptionVo>> getOption(BaseDto dto) {
        return configService.queryMap(
                ComponentConstant.KEY_ORGAN_COMMON,
                ComponentConstant.KEY_CONFIG_LOG_OPTION
        );
    }

    private String convertString(Object data) {

        if (Objects.isNull(data)) {
            return "";
        }

        if (data.getClass().isPrimitive()) {
            return data + "";
        }

        if (data instanceof String) {
            return data + "";
        }

        if (data instanceof Integer) {
            return data + "";
        }

        return JacksonUtils.json(data);
    }

    private LocalDateTime parseLocalDateTime(String dateTime) {
        if (StringUtils.hasText(dateTime)) {
            return DateTimeUtils.parseLocalDateTime(dateTime, DateTimeUtils.DATE_TIME);
        }
        return null;
    }

    private <T> Predicate buildQueryPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder, LogQueryDto queryDto) {

        if (Objects.isNull(queryDto)) {
            return query.getRestriction();
        }

        List<Predicate> predicateList = new ArrayList<>();

        Path<LocalDateTime> logDateTimePath = root.get("logDateTime");

        String logStartTime = queryDto.getLogStartTime();
        if (StringUtils.hasText(logStartTime)) {
            predicateList.add(builder.greaterThanOrEqualTo(logDateTimePath, parseLocalDateTime(logStartTime)));
        }

        String logEndTime = queryDto.getLogEndTime();
        if (StringUtils.hasText(logEndTime)) {
            predicateList.add(builder.lessThanOrEqualTo(logDateTimePath, parseLocalDateTime(logEndTime)));
        }

        String bizType = queryDto.getBizType();
        if (StringUtils.hasText(bizType)) {
            Path<String> bizTypePath = root.get("bizType");
            predicateList.add(builder.equal(bizTypePath, bizType));
        }

        String organCode = queryDto.getOrganCode();
        if (StringUtils.hasText(organCode)) {
            Path<String> organCodePath = root.get("organCode");
            predicateList.add(builder.equal(organCodePath, organCode));
        }

        String bizId = queryDto.getBizId();
        if (StringUtils.hasText(bizId)) {
            Path<String> bizIdPath = root.get("bizId");
            predicateList.add(builder.equal(bizIdPath, bizId));
        }

        String logBizId = queryDto.getLogBizId();
        if (StringUtils.hasText(logBizId)) {
            Path<String> logBizIdPath = root.get("logBizId");
            predicateList.add(builder.equal(logBizIdPath, logBizId));
        }

        String logKey = queryDto.getLogKey();
        if (StringUtils.hasText(logKey)) {
            Path<String> logKeyPath = root.get("logKey");
            predicateList.add(builder.equal(logKeyPath, logKey));
        }

        String applicationCode = queryDto.getApplicationCode();
        if (StringUtils.hasText(applicationCode)) {
            Path<String> applicationCodePath = root.get("applicationCode");
            predicateList.add(builder.equal(applicationCodePath, applicationCode));
        }

        String logLevel = queryDto.getLogLevel();
        if (StringUtils.hasText(logLevel)) {
            Path<String> logLevelPath = root.get("logLevel");
            predicateList.add(builder.equal(logLevelPath, logLevel));
        }

        String cardNo = queryDto.getCardNo();
        if (StringUtils.hasText(cardNo)) {
            Path<String> cardNoPath = root.get("cardNo");
            predicateList.add(builder.equal(cardNoPath, cardNo));
        }

        String pmiNo = queryDto.getPmiNo();
        if (StringUtils.hasText(pmiNo)) {
            Path<String> pmiNoPath = root.get("pmiNo");
            predicateList.add(builder.equal(pmiNoPath, pmiNo));
        }

        return builder.and(predicateList.toArray(new Predicate[0]));
    }

    private LogVo buildLogVo(LogEntity t) {

        Map<String, List<OptionVo>> optionMap = getOption(null);

        LogVo logVo = new LogVo();
        logVo.setId(t.getId() + "");
        logVo.setLogDateTime(DateTimeUtils.format(t.getLogDateTime(), DateTimeUtils.DATE_TIME));
        logVo.setBizType(t.getBizType());
        logVo.setBizTypeMsg(getOptionMsgByTypeAndCode(optionMap, LogOptionConstant.BIZ_TYPE, t.getBizType()));
        logVo.setOrganCode(t.getOrganCode());
        logVo.setOrganName(getOptionMsgByTypeAndCode(optionMap, LogOptionConstant.ORGAN, t.getOrganCode()));
        logVo.setBizId(t.getBizId());
        logVo.setLogBizId(t.getLogBizId());
        logVo.setLogKey(t.getLogKey());
        logVo.setLogKeyMsg(getOptionMsgByTypeAndCode(optionMap, LogOptionConstant.LOG_KEY, t.getLogKey()));
        logVo.setMethod(t.getMethod());
        logVo.setActionMsg(getOptionMsgByTypeAndCode(optionMap, LogOptionConstant.ACTION, t.getAction()));
        logVo.setApplicationCode(t.getApplicationCode());
        logVo.setApplicationMsg(getOptionMsgByTypeAndCode(optionMap, LogOptionConstant.APPLICATION, t.getApplicationCode()));
        logVo.setWhoType(toUpperCase(getEnumName(t.getWhoType())));
        logVo.setLogLevel(toUpperCase(getEnumName(t.getLogLevel())));
        logVo.setUserId(t.getUserId());
        logVo.setAccountId(t.getAccountId());
        logVo.setAccountNo(t.getAccountNo());
        logVo.setCardNo(t.getCardNo());
        logVo.setPmiNo(t.getPmiNo());
        logVo.setUserName(t.getUserName());
        logVo.setLogType(t.getLogType().name());
        logVo.setLogTypeMsg(getOptionMsgByTypeAndCode(optionMap, LogOptionConstant.LOG_TYPE, getEnumName(t.getLogType())));
        logVo.setRequestIp(t.getRequestIp());
        logVo.setPosition(IpRegionUtils.getCity(t.getRequestIp()));
        logVo.setChannelName(getOptionMsgByTypeAndCode(optionMap, LogOptionConstant.CHANNEL, t.getChannelCode()));
        logVo.setUrl(t.getUrl());
        logVo.setParam(t.getParam());
        logVo.setData(t.getData());
        logVo.setMessage(t.getMessage());
        logVo.setSpentSeconds(t.getSpentSeconds());
        return logVo;
    }

    private String getOptionMsgByTypeAndCode(Map<String, List<OptionVo>> optionMap, String key, String code) {

        if (CollectionUtils.isEmpty(optionMap)
                || !StringUtils.hasText(key)
                || !StringUtils.hasText(code)) {
            return "";
        }

        List<OptionVo> optionVoList = JacksonUtils.jsonList(JacksonUtils.json(optionMap.get(key)), OptionVo.class);

        if (CollectionUtils.isEmpty(optionVoList)) {
            return code;
        }

        for (OptionVo optionVo : optionVoList) {

            if (code.equals(optionVo.getCode())) {
                return optionVo.getMsg();
            }

        }

        return code;

    }

    private String toUpperCase(String name) {
        if (StringUtils.hasText(name)) {
            return name.toUpperCase();
        }
        return "";
    }

    private String getEnumName(Enum e) {
        return Optional.ofNullable(e).map(Enum::name).orElse(null);
    }

}
