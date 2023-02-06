create table board_entity (
    board_seq bigint not null auto_increment,
    created_at datetime not null,
    updated_at datetime not null,
    content varchar(255),
    is_delete varchar(255),
    title varchar(255),
    user_seq bigint,
    primary key (board_seq)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

create table board_comment_entity (
    board_comment_seq bigint not null auto_increment,
    created_at datetime not null,
    updated_at datetime not null,
    content varchar(255),
    board_seq bigint,
    user_seq bigint,
    primary key (board_comment_seq)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;