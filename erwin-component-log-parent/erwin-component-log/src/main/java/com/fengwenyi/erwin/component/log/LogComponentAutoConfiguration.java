package com.fengwenyi.erwin.component.log;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-12-07
 */
@Configuration
@ComponentScan(basePackageClasses = LogComponentAutoConfiguration.class)
@EnableJpaRepositories(basePackages = "com.fengwenyi.erwin.component.log.repository")
@EntityScan(basePackages = "info.hxgy.component.log.entity")
public class LogComponentAutoConfiguration {
}
