package com.fengwenyi.erwin.component.log.config;

import info.hxgy.component.common.config.AbstractThreadPoolConfig;
import info.hxgy.component.common.constant.ComponentConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-12-15
 */
@Configuration
@EnableAsync
public class LogComponentThreadPoolConfig extends AbstractThreadPoolConfig {

    private static final String PREFIX = ComponentConstant.THREAD_POOL_PREFIX;

    private static final int THREAD_POOL_SIZE = 5;
    private static final int MAX_POOL_SIZE = 30;
    private static final int QUEUE_CAPACITY = 2048;
    private static final int KEEP_ALIVE_SECONDS = 60;

    protected ThreadPoolTaskExecutor genExecutor(String threadNamePrefix) {
        return genExecutor(
                THREAD_POOL_SIZE,
                MAX_POOL_SIZE,
                QUEUE_CAPACITY,
                KEEP_ALIVE_SECONDS,
                threadNamePrefix
        );
    }

    @Bean
    public ThreadPoolTaskExecutor logComponentPool() {
        return genExecutor(PREFIX + "Log-");
    }

}
