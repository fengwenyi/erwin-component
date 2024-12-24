package com.fengwenyi.component.shardingsphere.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.Properties;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2024-12-24
 */
@Data
@ConfigurationProperties(prefix = "erwin")
@Configuration
public class ErwinProperties {

    private Map<String, DataSource> dataSource;

    private Map<String, String> props;

    private Sharding sharding;

    private Map<String, Map<String, Properties>> modeConfig;

    @Data
    public static class DataSource {

        private String type;

        private String driverClassName;

        private String url;

        private String username;

        private String password;

    }

    @Data
    public static class Sharding {
        private Map<String, Table> tables;
        private ShardingStrategyConfig defaultTableStrategy;
        private Map<String, Algorithm> algorithm;
    }

    @Data
    public static class Table {
        private String actualDataNodes;
        private String column;
        private String algorithmName;
    }

    @Data
    public static class Algorithm {
        private String type;
        private Properties props;
    }

    @Data
    public static class ShardingStrategyConfig {
        private String type;
        private String name;
    }

}
