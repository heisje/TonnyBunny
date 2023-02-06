create table auth_entity (
    id bigint not null auto_increment,
    created_at datetime not null,
    updated_at datetime not null,
    refresh_token varchar(255),
    user_seq bigint,
    primary key (id)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;