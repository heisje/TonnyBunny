DROP TABLE IF EXISTS BLOCK_TABLE;

create table BLOCK_TABLE (
    block_seq bigint not null auto_increment,
    created_at datetime not null,
    updated_at datetime not null,
    blocked_user_seq bigint,
    user_seq bigint,
    primary key (block_seq)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;