package com.fengwenyi.component.shardingsphere.config;

import com.fengwenyi.javalib.exception.ExceptionUtils;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.infra.algorithm.core.config.AlgorithmConfiguration;
import org.apache.shardingsphere.infra.config.rule.RuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.ShardingRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.rule.ShardingTableRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.sharding.NoneShardingStrategyConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.sharding.ShardingStrategyConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.sharding.StandardShardingStrategyConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2024-12-24
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class ShardingConfig {

    private final ErwinProperties erwinProperties;

    @Bean
    public DataSource shardingDataSource() {
        try {
            log.info("shardingConfig: {}", erwinProperties.toString());
            Properties properties = new Properties();
            Map<String, String> props = erwinProperties.getProps();
            if (!CollectionUtils.isEmpty(props)) {
                properties.putAll(props);
            }
            ShardingRuleConfiguration shardingRuleConfig = buildShardingRuleConfig(erwinProperties.getSharding());
            List<RuleConfiguration> ruleConfigList = List.of(shardingRuleConfig);
            return ShardingSphereDataSourceFactory.createDataSource(
                    createDataSourceMap(),
                    ruleConfigList,
                    properties
            );
        } catch (SQLException e) {
            log.error("shardingConfig, exception: {}", ExceptionUtils.getStackTrace(e));
            throw new RuntimeException(e);
        }
    }

    private ShardingRuleConfiguration buildShardingRuleConfig(ErwinProperties.Sharding sharding) {
        ShardingRuleConfiguration shardingRuleConfiguration = new ShardingRuleConfiguration();
        shardingRuleConfiguration.setDefaultTableShardingStrategy(buildDefaultShardingStrategy(sharding.getDefaultTableStrategy()));
        shardingRuleConfiguration.setTables(buildShardingTableRuleConfig(sharding.getTables()));
        shardingRuleConfiguration.setShardingAlgorithms(buildAlgorithmConfigurationMap(sharding.getAlgorithm()));
        return shardingRuleConfiguration;
    }

    private Map<String, AlgorithmConfiguration> buildAlgorithmConfigurationMap(Map<String, ErwinProperties.Algorithm> algorithmMap) {
        if (CollectionUtils.isEmpty(algorithmMap)) {
            return Collections.emptyMap();
        }
        Map<String, AlgorithmConfiguration> algorithmConfigMap = new HashMap<>();
        for (Map.Entry<String, ErwinProperties.Algorithm> algorithmEntry : algorithmMap.entrySet()) {
            ErwinProperties.Algorithm algorithmEntryValue = algorithmEntry.getValue();
            AlgorithmConfiguration algorithmConfiguration = new AlgorithmConfiguration(algorithmEntryValue.getType(), algorithmEntryValue.getProps());
            algorithmConfigMap.put(algorithmEntry.getKey(), algorithmConfiguration);
        }
        return algorithmConfigMap;
    }

    private ShardingStrategyConfiguration buildDefaultShardingStrategy(ErwinProperties.ShardingStrategyConfig defaultTableStrategyConfig) {
        ShardingStrategyConfiguration shardingStrategyConfiguration = null;
        if ("none".equalsIgnoreCase(defaultTableStrategyConfig.getType())) {
            shardingStrategyConfiguration = new NoneShardingStrategyConfiguration();
        }
        return shardingStrategyConfiguration;
    }

    private List<ShardingTableRuleConfiguration> buildShardingTableRuleConfig(Map<String, ErwinProperties.Table> tableMap) {

        List<ShardingTableRuleConfiguration> shardingTableRuleConfigurationList = new ArrayList<>();

        for (Map.Entry<String, ErwinProperties.Table> entry : tableMap.entrySet()) {
            ErwinProperties.Table value = entry.getValue();
            ShardingTableRuleConfiguration table = new ShardingTableRuleConfiguration(entry.getKey(), value.getActualDataNodes());
            if (StringUtils.hasText(value.getColumn()) && StringUtils.hasText(value.getAlgorithmName())) {
                table.setTableShardingStrategy(new StandardShardingStrategyConfiguration(value.getColumn(), value.getAlgorithmName()));
            }
            shardingTableRuleConfigurationList.add(table);
        }
        return shardingTableRuleConfigurationList;
    }

    private Map<String, DataSource> createDataSourceMap() {

        Map<String, ErwinProperties.DataSource> dataSourceConfigMap = erwinProperties.getDataSource();
        if (CollectionUtils.isEmpty(dataSourceConfigMap)) {
            return null;
        }

        Map<String, DataSource> dataSourceMap = new HashMap<>();

        for (Map.Entry<String, ErwinProperties.DataSource> entry : dataSourceConfigMap.entrySet()) {
            HikariDataSource dataSource = new HikariDataSource();
//            dataSource.setDriverClassName(entry.getValue().getDriverClassName());
            dataSource.setJdbcUrl(entry.getValue().getUrl());
            dataSource.setUsername(entry.getValue().getUsername());
            dataSource.setPassword(entry.getValue().getPassword());

            dataSourceMap.put(entry.getKey(), dataSource);
        }

        return dataSourceMap;
    }

}
