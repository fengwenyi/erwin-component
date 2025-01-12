package com.fengwenyi.erwin.component.common.mybatis_plus.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.fengwenyi.erwin.component.common.util.SnowFlakeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-07-25
 */
@Slf4j
@Configuration
public class MyBatisPlusComponentConfig {

    @Bean
    @ConditionalOnMissingBean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        final MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL)); // 分页插件
        return interceptor;
    }

    @Bean
    @ConditionalOnMissingBean
    public IdentifierGenerator identifierGenerator()  {
        long workId = SnowFlakeUtils.getWorkId();
        long dataCenterId = SnowFlakeUtils.getDataCenterId();
        log.info("MyBatisPlusComponentConfig, identifierGenerator, workId: [{}], dataCenterId: [{}]", workId, dataCenterId);
        return new DefaultIdentifierGenerator(workId, dataCenterId);
    }

}
