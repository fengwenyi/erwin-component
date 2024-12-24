# erwin-component
组件

## shardingsphere

```yaml
erwin:
  props:
    sql-show: true
  data-source:
    ds_0:
      driver-class-name: com.mysql.cj.jdbc.Driver
      url: jdbc:mysql://localhost:3306/hc?serverTimezone=Asia/Shanghai&useSSL=false&useUnicode=true&characterEncoding=UTF-8
      username: log
      password: 123456
  sharding:
    tables:
      t_operation_log:
        actual-data-nodes: "ds_0.t_operation_log_${2024..2025}"
        column: log_date_time
        algorithm-name: sharding_by_year
      t_log:
        actual-data-nodes: "ds_0.t_log_${202411..202512}"
        column: log_date_time
        algorithm-name: sharding_by_month
      hc_config:
        actual-data-nodes: "ds_0.ec_config"
    default-table-strategy:
      type: none 
    algorithm:
      sharding_by_month:
        type: INTERVAL
        props:
          "datetime-pattern": "yyyy-MM-dd HH:mm:ss"
          "datetime-lower": "2024-11-01 00:00:00"
          "datetime-upper": "2025-12-31 23:59:59"
          "sharding-suffix-pattern": "yyyyMM"
          "datetime-interval-amount": "1"
          "datetime-interval-unit": "MONTHS"
      sharding_by_year:
        type: INTERVAL
        props:
          datetime-pattern: "yyyy"
          datetime-lower: "2024"
          datetime-upper: "2025"
          sharding-suffix-pattern: "yyyy"
          datetime-interval-amount: 1
          datetime-interval-unit: "YEARS"
```
