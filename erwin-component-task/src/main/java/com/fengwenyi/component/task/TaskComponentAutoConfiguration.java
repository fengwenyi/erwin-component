package com.fengwenyi.component.task;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-11-29
 */
@Configuration
@ComponentScan(basePackageClasses = TaskComponentAutoConfiguration.class)
@EnableJpaRepositories(basePackages = "com.fengwenyi.component.task.repository")
@EntityScan(basePackages = "com.fengwenyi.component.task.entity")
@EnableAsync
public class TaskComponentAutoConfiguration {
}
