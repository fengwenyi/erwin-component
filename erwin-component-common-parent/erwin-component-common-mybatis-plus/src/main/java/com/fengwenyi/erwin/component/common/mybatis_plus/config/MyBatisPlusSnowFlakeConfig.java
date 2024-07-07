package com.fengwenyi.erwin.component.common.mybatis_plus.config;

import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.fengwenyi.erwin.component.common.util.SnowFlakeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2024-01-03
 */
@Configuration
@Slf4j
public class MyBatisPlusSnowFlakeConfig {

    @Bean
    @ConditionalOnMissingBean
    public IdentifierGenerator identifierGenerator()  {
        long workId = SnowFlakeUtils.getWorkId();
        long dataCenterId = SnowFlakeUtils.getDataCenterId();
        log.info("MyBatisPlusSnowFlakeConfig, identifierGenerator, workId: [{}], dataCenterId: [{}]", workId, dataCenterId);
        return new DefaultIdentifierGenerator(workId, dataCenterId);
    }

}
