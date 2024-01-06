package com.fengwenyi.erwin.component.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-11-29
 */
@Configuration
@ComponentScan(basePackageClasses = ConfigComponentAutoConfiguration.class)
@EnableJpaRepositories(basePackages = "info.hxgy.component.config.repository")
@EntityScan(basePackages = "info.hxgy.component.config.entity")
public class ConfigComponentAutoConfiguration {
}
