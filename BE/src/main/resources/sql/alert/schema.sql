DROP TABLE IF EXISTS ;
DROP TABLE IF EXISTS ;

create table ALERT_LOG_TABLE (
    alert_log_seq bigint not null auto_increment,
    created_at datetime not null,
    updated_at datetime not null,
    content varchar(255),
    is_end bit,
    is_read bit,
    task_code varchar(255),
    user_seq bigint,
    primary key (alert_log_seq)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

create table alert_settings_entity (
    alert_settings_seq bigint not null auto_increment,
    created_at datetime not null,
    updated_at datetime not null,
    is_all bit,
    is_chat bit,
    is_community bit,
    is_tonny_bunny bit,
    user_seq bigint,
    primary key (alert_settings_seq)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;