-- 配置表
create table if not exists `ec_config`
(
    id               bigint               not null comment '主键ID' primary key auto_increment,
    create_date_time datetime             not null comment '创建时间',
    update_date_time datetime             comment '更新时间',
    `status`         tinyint(1) default 1 not null comment '状态: 0-停用; 1-启用',

    organ_code       varchar(32)          not null comment '机构编码',
    config_name      varchar(64)          not null comment '配置名称',
    config_code      varchar(64)          not null comment '配置编码',
    config_json      varchar(255)         comment '配置JSON',

    unique uni_organCode_configCode (organ_code, config_code)

) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin comment '配置表';