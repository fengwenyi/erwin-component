package com.fengwenyi.erwin.component.common.mybatis_plus.config;

import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import info.hxgy.component.common.util.SnowFlakeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2024-01-03
 */
@Configuration
@Slf4j
public class SnowFlakeConfig {

    @Bean
    public IdentifierGenerator identifierGenerator()  {
        long workId = SnowFlakeUtils.getWorkId();
        long dataCenterId = SnowFlakeUtils.getDataCenterId();
        log.info("MyBatisPlusSnowFlakeConfig, workId: [{}], dataCenterId: [{}]", workId, dataCenterId);
        return new DefaultIdentifierGenerator(workId, dataCenterId);
    }

}
