package com.fengwenyi.erwin.component.common.redis;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-12-13
 */
@Configuration
@EnableCaching
@ComponentScan(basePackageClasses = CommonRedisComponentAutoConfiguration.class)
public class CommonRedisComponentAutoConfiguration {
}
