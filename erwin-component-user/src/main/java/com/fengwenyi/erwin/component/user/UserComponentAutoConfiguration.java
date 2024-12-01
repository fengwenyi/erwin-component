package com.fengwenyi.erwin.component.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2024-12-01
 */
@Configuration
@MapperScan("com.fengwenyi.erwin.component.user.mapper")
@ComponentScan(basePackageClasses = UserComponentAutoConfiguration.class)
public class UserComponentAutoConfiguration {
}
