package com.fengwenyi.erwin.component.log.client;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-12-14
 */
@Configuration
@ComponentScan(basePackageClasses = LogClientComponentAutoConfiguration.class)
@EnableFeignClients("info.hxgy.component.log.client.feign")
public class LogClientComponentAutoConfiguration {
}
