package com.fengwenyi.erwin.component.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-12-12
 */
@Configuration
@MapperScan("com.fengwenyi.erwin.component.config.mapper")
@ComponentScan(basePackageClasses = ConfigComponentAutoConfiguration.class)
public class ConfigComponentAutoConfiguration {
}
