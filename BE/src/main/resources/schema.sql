DROP TABLE IF EXISTS ;

CREATE TABLE  (

) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ALERT table
DROP TABLE IF EXISTS alert_log_table;
CREATE TABLE `alert_log_table` (
    `alert_log_seq` BIGINT NO NULL auto_increment,
    `user_seq` BIGINT,
    `task_code` CHAR(7),
    `content` VARCHAR(255),
    `is_end` BIGINT,
    `is_read` BOOLEAN,
    `created_at` TIMESTAMP NO NULL,
    `updated_at` TIMESTAMP NO NULL,
    PRIMARY KEY (`alert_log_seq`)
    FOREIGN KEY (`user_seq`) 
    REFERENCES `user_table` (`user_seq`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

DROP TABLE IF EXISTS alert_settings_table;
CREATE TABLE `alert_settings_table` (
    `alert_settings_seq` BIGINT NO NULL auto_increment,
    `user_seq` BIGINT,
    `is_all` BOOLEAN,
    `is_chat` BOOLEAN,
    `is_community` BOOLEAN,
    `is_tonny_bunny` BOOLEAN,
    `created_at` TIMESTAMP NO NULL,
    `updated_at` TIMESTAMP NO NULL,
    PRIMARY KEY (`alert_settings_seq`)
    FOREIGN KEY (`user_seq`) 
    REFERENCES user_table (`user_seq`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;


-- 인증
DROP TABLE IF EXISTS auth_table;
CREATE TABLE `auth_table` (
    `id` BIGINT NO NULL auto_increment,
    `user_seq` BIGINT,
    `refresh_token` VARCHAR(255),
    `created_at` TIMESTAMP NO NULL,
    `updated_at` TIMESTAMP NO NULL,
    PRIMARY KEY (`id`)
    FOREIGN KEY (`user_seq`) 
    REFERENCES `user_table` (`user_seq`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;


-- 차단
DROP TABLE IF EXISTS block_table;
CREATE TABLE `block_table` (
    `block_seq` BIGINT NO NULL auto_increment,
    `user_seq` BIGINT,
    `blocked_user_seq` BIGINT,
    `created_at` TIMESTAMP NO NULL,
    `updated_at` TIMESTAMP NO NULL,
    PRIMARY KEY (`block_seq`)
    FOREIGN KEY (`user_seq`) 
    REFERENCES `user_table` (`user_seq`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;


-- 게시글
DROP TABLE IF EXISTS board_image_table;
DROP TABLE IF EXISTS board_comment_table;
DROP TABLE IF EXISTS board_table;
CREATE TABLE `board_table` (
    `board_seq` BIGINT NO NULL auto_increment,
    `user_seq` BIGINT,
    `title` VARCHAR(255),
    `content` VARCHAR(255),
    `is_delete` BOOLEAN,
    `created_at` TIMESTAMP NO NULL,
    `updated_at` TIMESTAMP NO NULL,
    PRIMARY KEY (`board_seq`)
    FOREIGN KEY (`user_seq`) 
    REFERENCES `user_table` (`user_seq`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `board_comment_table` (
    `board_comment_seq` BIGINT NO NULL auto_increment,
    `user_seq` BIGINT,
    `board_seq` BIGINT,
    `content` VARCHAR(255),
    `created_at` TIMESTAMP NO NULL,
    `updated_at` TIMESTAMP NO NULL,
    PRIMARY KEY (`board_comment_seq`)
    FOREIGN KEY (`board_seq`) 
    REFERENCES `board_table` (`board_seq`)
    FOREIGN KEY (`user_seq`) 
    REFERENCES `user_table` (`user_seq`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `board_image_table` (
    `board_image_seq` BIGINT NO NULL auto_increment,
    `board_seq` BIGINT,
    `image_path` VARCHAR(255),
    `created_at` TIMESTAMP NO NULL,
    `updated_at` TIMESTAMP NO NULL,
    PRIMARY KEY (`board_image_seq`)
    FOREIGN KEY (`board_seq`) 
    REFERENCES `board_table` (`board_seq`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- 번역
DROP TABLE IF EXISTS bunny_apply_table;
DROP TABLE IF EXISTS bunny_table;
DROP TABLE IF EXISTS bunny_history_table;
DROP TABLE IF EXISTS bunny_image_table;
DROP TABLE IF EXISTS bunny_quotation_table;
DROP TABLE IF EXISTS bunny_quotation_image_table;
DROP TABLE IF EXISTS bunny_apply_table;
CREATE TABLE `bunny_apply_table` (
    `bunny_apply_seq` BIGINT NO NULL auto_increment,
    `user_seq` BIGINT,
    `bunny_seq` BIGINT,
    `estimate_price` INTEGER,
    `updated_at` TIMESTAMP NO NULL,
    `created_at` TIMESTAMP NO NULL,
    PRIMARY KEY (`bunny_apply_seq`)
    FOREIGN KEY (`bunny_seq`) 
    REFERENCES `bunny_table` (`bunny_seq`)
    FOREIGN KEY (`user_seq`) 
    REFERENCES `user_table` (`user_seq`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `bunny_table` (
    `bunny_seq` BIGINT NO NULL auto_increment,
    `user_seq` BIGINT,
    `helper_seq` BIGINT,
    `task_code` CHAR(7),
    `start_lang_code` CHAR(7),
    `end_lang_code` CHAR(7),
    `title` VARCHAR(255),
    `content` VARCHAR(255),
    `bunny_situ_code` CHAR(7),
    `bunny_state_code` CHAR(7),
    `start_date` TIMESTAMP,
    `end_date` TIMESTAMP,
    `estimate_price` INTEGER,
    `is_deleted` BOOLEAN,
    `created_at` TIMESTAMP NO NULL,
    `updated_at` TIMESTAMP NO NULL,
    PRIMARY KEY (`bunny_seq`)
    FOREIGN KEY (`user_seq`) 
    REFERENCES `user_table` (`user_seq`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `bunny_history_table` (
    `history_seq` BIGINT NO NULL,
    `title` VARCHAR(255),
    `total_price` INTEGER,
    PRIMARY KEY (`history_seq`)
    FOREIGN KEY (`history_seq`) 
    REFERENCES `history_table` (`history_seq`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `bunny_image_table` (
    `bunny_image_seq` BIGINT NO NULL auto_increment,
    `bunny_seq` BIGINT,
    `image_path` VARCHAR(255),
    `created_at` TIMESTAMP NO NULL,
    `updated_at` TIMESTAMP NO NULL,
    PRIMARY KEY (`bunny_image_seq`)
    FOREIGN KEY (`bunny_seq`) 
    REFERENCES `bunny_table` (`bunny_seq`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `bunny_quotation_table` (
    `bunny_quotation_seq` BIGINT NO NULL auto_increment,
    `bunny_seq` BIGINT,
    `client_seq` BIGINT,
    `helper_seq` BIGINT,
    `start_lang_code` CHAR(7),
    `end_lang_code` CHAR(7),
    `title` VARCHAR(255),
    `content` VARCHAR(255),
    `start_date_time` TIMESTAMP,
    `end_date_time` TIMESTAMP,
    `bunny_quotation_state_code` CHAR(7),
    `total_price` INTEGER,
    `created_at` TIMESTAMP NO NULL,
    `updated_at` TIMESTAMP NO NULL,
    PRIMARY KEY (`bunny_quotation_seq`)
    FOREIGN KEY (`bunny_seq`) 
    REFERENCES `bunny_table` (`bunny_seq`)
    FOREIGN KEY (`client_seq`) 
    REFERENCES `user_table` (`user_seq`)
    FOREIGN KEY (`helper_seq`) 
    REFERENCES `user_table` (`user_seq`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `bunny_quotation_image_table` (
    `bunny_quotation_image_seq` BIGINT NO NULL auto_increment,
    `bunny_quotation_seq` BIGINT,
    `image_path` VARCHAR(255),
    `updated_at` TIMESTAMP NO NULL,
    `created_at` TIMESTAMP NO NULL,
    PRIMARY KEY (`bunny_quotation_image_seq`)
    FOREIGN KEY (`bunny_quotation_seq`) 
    REFERENCES `bunny_quotation_table` (`bunny_quotation_seq`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;


--
CREATE TABLE `certificate_table` (
    `certificate_seq` BIGINT NO NULL auto_increment,
    `helper_info_seq` BIGINT,
    `lang_code` CHAR(7),
    `cert_name` VARCHAR(255),
    `content` VARCHAR(255),
    `is_deleted` BOOLEAN,
    `created_at` TIMESTAMP NO NULL,
    `updated_at` TIMESTAMP NO NULL,
    PRIMARY KEY (`certificate_seq`)
    FOREIGN KEY (`helper_info_seq`) 
    REFERENCES `helper_info_table` (`helper_info_seq`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;


--
CREATE TABLE `chat_log_table` (
    `chat_log_seq` BIGINT NO NULL auto_increment,
    `user_seq` BIGINT,
    `room_seq` VARCHAR(255),
    `data_type_code` CHAR(7),
    `message` VARCHAR(255),
    `date` TIMESTAMP,
    `created_at` TIMESTAMP NO NULL,
    `updated_at` TIMESTAMP NO NULL,
    PRIMARY KEY (`chat_log_seq`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `chat_room_table` (
    `chat_room_seq` VARCHAR(255) NO NULL,
    `client_seq` BIGINT,
    `helper_seq` BIGINT,
    `created_at` TIMESTAMP NO NULL,
    `updated_at` TIMESTAMP NO NULL,
    PRIMARY KEY (`chat_room_seq`)
    FOREIGN KEY (`client_seq`) 
    REFERENCES `user_table` (`user_seq`)
    FOREIGN KEY (`helper_seq`) 
    REFERENCES `user_table` (`user_seq`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;


--
CREATE TABLE `common_code_table` (
    `code_seq` BIGINT NO NULL auto_increment,
    `group_code` CHAR(3),
    `code` CHAR(4),
    `code_name` VARCHAR(255),
    PRIMARY KEY (`code_seq`)
    FOREIGN KEY (`group_code`) 
    REFERENCES `common_group_code_table` (`group_code`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `common_group_code_table` (
    `group_code` CHAR(3) NO NULL,
    `group_code_name` VARCHAR(255),
    PRIMARY KEY (`group_code`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;


--
CREATE TABLE `follow_table` (
    `follow_seq` BIGINT NO NULL auto_increment,
    `user_seq` BIGINT,
    `followed_user_seq` BIGINT,
    `created_at` TIMESTAMP NO NULL,
    `updated_at` TIMESTAMP NO NULL,
    PRIMARY KEY (`follow_seq`)
    FOREIGN KEY (`user_seq`) 
    REFERENCES `user_table` (`user_seq`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `helper_info_table` (
    `helper_info_seq` BIGINT NO NULL auto_increment,
    `user_seq` BIGINT,
    `one_line_introduction` VARCHAR(255),
    `introduction` VARCHAR(255),
    `unit_price` INTEGER,
    `avg_score` FLOAT,
    `review_count` INTEGER,
    `is_active` BOOLEAN,
    `created_at` TIMESTAMP NO NULL,
    `updated_at` TIMESTAMP NO NULL,
    PRIMARY KEY (`helper_info_seq`)
    FOREIGN KEY (`user_seq`) 
    REFERENCES `user_table` (`user_seq`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `helper_info_image_table` (
    `helper_info_image_seq` BIGINT NO NULL auto_increment,
    `helper_info_seq` BIGINT,
    `image_path` VARCHAR(255),
    `is_deleted` BOOLEAN,
    `created_at` TIMESTAMP NO NULL,
    `updated_at` TIMESTAMP NO NULL,
    PRIMARY KEY (`helper_info_image_seq`)
    FOREIGN KEY (`helper_info_seq`) 
    REFERENCES `helper_info_table` (`helper_info_seq`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;


--
CREATE TABLE `history_table` (
    `history_seq` BIGINT NO NULL auto_increment,
    `client_seq` BIGINT,
    `helper_seq` BIGINT,
    `task_code` CHAR(7) NO NULL,
    `noti_seq` BIGINT,
    `start_lang_code` CHAR(7),
    `end_lang_code` CHAR(7),
    `content` VARCHAR(255),
    `start_date_time` TIMESTAMP,
    `end_date_time` TIMESTAMP,
    `created_at` TIMESTAMP NO NULL,
    `updated_at` TIMESTAMP NO NULL,
    PRIMARY KEY (`history_seq`)
    FOREIGN KEY (`client_seq`) 
    REFERENCES `user_table` (`user_seq`)
    FOREIGN KEY (`helper_seq`) 
    REFERENCES `user_table` (`user_seq`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

create table `jtonny_table` (
    `j_tonny_seq` BIGINT NOT NULL auto_increment,
    PRIMARY KEY (`j_tonny_seq`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

create table `jtonny_history_table` (
    `history_seq` BIGINT NOT NULL,
    `tonny_situ_code` CHAR(7),
    `total_time` TIME,
    `unit_price` INTEGER,
    `record_video_path` VARCHAR(255),
    PRIMARY KEY (`history_seq`)
    FOREIGN KEY (`history_seq`) 
    REFERENCES `history_table` (`history_seq`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

create table `point_log_table` (
    `point_log_seq` BIGINT NOT NULL auto_increment,
    `user_seq` BIGINT,
    `type_code` CHAR(7),
    `amount` INTEGER NOT NULL,
    `created_at` TIMESTAMP NO NULL,
    `updated_at` TIMESTAMP NO NULL,
    PRIMARY KEY (`point_log_seq`)
    FOREIGN KEY (`user_seq`) 
    REFERENCES `user_table` (`user_seq`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

create table `possible_language_table` (
    `possible_language_seq` BIGINT NOT NULL auto_increment,
    `helper_info_seq` BIGINT,
    `lang_code` CHAR(7),
    `created_at` TIMESTAMP NO NULL,
    `updated_at` TIMESTAMP NO NULL,
    PRIMARY KEY (`possible_language_seq`)
    FOREIGN KEY (`helper_info_seq`) 
    REFERENCES `helper_info_table` (`helper_info_seq`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

create table `review_table` (
    `review_seq` BIGINT NOT NULL auto_increment,
    `user_seq` BIGINT,
    `history_seq` BIGINT,
    `score` FLOAT,
    `comment` VARCHAR(255),
    `created_at` TIMESTAMP NO NULL,
    `updated_at` TIMESTAMP NO NULL,
    PRIMARY KEY (`review_seq`)
    FOREIGN KEY (`history_seq`) 
    REFERENCES `history_table` (`history_seq`)
    FOREIGN KEY (`user_seq`) 
    REFERENCES `user_table` (`user_seq`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

create table `schedule_table` (
    `schedule_seq` BIGINT NOT NULL auto_increment,
    `user_seq` BIGINT,
    `task_code` CHAR(7),
    `title` VARCHAR(255),
    `content` VARCHAR(255),
    `start_date_time` DATETIME,
    `end_date_time` DATETIME,
    `is_complete` BOOLEAN,
    `created_at` TIMESTAMP NO NULL,
    `updated_at` TIMESTAMP NO NULL,
    PRIMARY KEY (`schedule_seq`)
    FOREIGN KEY (`user_seq`) 
    REFERENCES `user_table` (`user_seq`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

create table `user_table` (
    `user_seq` BIGINT NOT NULL auto_increment,
    `user_code` CHAR(7),
    `email` VARCHAR(255),
    `nick_name` VARCHAR(255),
    `password` VARCHAR(255),
    `phone_number` VARCHAR(255),
    `point` BIGINT,
    `profile_image_path` VARCHAR(255),
    `report_count` INTEGER,
    `is_agreement` BOOLEAN,
    `is_deleted` BOOLEAN,
    `created_at` TIMESTAMP NO NULL,
    `updated_at` TIMESTAMP NO NULL,
    PRIMARY KEY (`user_seq`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

create table `ytonny_apply_table` (
    `y_tonny_apply_seq` BIGINT NOT NULL auto_increment,
    `y_tonny_seq` BIGINT,
    `helper_seq` BIGINT,
    `total_price` INTEGER,
    `created_at` TIMESTAMP NO NULL,
    `updated_at` TIMESTAMP NO NULL,
    PRIMARY KEY (`y_tonny_apply_seq`)
    FOREIGN KEY (`helper_seq`) 
    REFERENCES `user_table` (`user_seq`)
    FOREIGN KEY (`y_tonny_seq`) 
    REFERENCES `ytonny_table` (`y_tonny_seq`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

create table `ytonny_table` (
    `y_tonny_seq` BIGINT NOT NULL auto_increment,
    `user_seq` BIGINT,
    `helper_seq` BIGINT,
    `task_code` CHAR(7),
    `start_lang_code` CHAR(7),
    `end_lang_code` CHAR(7),
    `tonny_situ_code` CHAR(7),
    `title` VARCHAR(255),
    `content` VARCHAR(255),
    `estimate_price` INTEGER,
    `estimate_time` TIME,
    `task_state_code` CHAR(7),
    `start_date_time` datetime,
    `is_deleted` BOOLEAN,
    `created_at` TIMESTAMP NO NULL,
    `updated_at` TIMESTAMP NO NULL,
    PRIMARY KEY (`y_tonny_seq`)
    FOREIGN KEY (`user_seq`) 
    REFERENCES `user_table` (`user_seq`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

create table `ytonny_history_table` (
    `history_seq` BIGINT NOT NULL,
    `tonny_situ_code` CHAR(7),
    `title` VARCHAR(255),
    `unit_price` INTEGER,
    `total_time` TIME,
    `record_video_path` VARCHAR(255),
    PRIMARY KEY (`history_seq`)
    FOREIGN KEY (`history_seq`) 
    REFERENCES `history_table` (`history_seq`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

create table `ytonny_quotation_table` (
    `y_tonny_quotation_seq` BIGINT NOT NULL auto_increment,
    `y_tonny_seq` BIGINT,
    `client_seq` BIGINT,
    `helper_seq` BIGINT,
    `start_lang_code` CHAR(7),
    `end_lang_code` CHAR(7),
    `title` VARCHAR(255),
    `content` VARCHAR(255),
    `unit_price` INTEGER,
    `estimate_time` TIME,
    `quotation_state_code` CHAR(7),
    `start_date_time` DATETIME,
    `created_at` TIMESTAMP NO NULL,
    `updated_at` TIMESTAMP NO NULL,
    PRIMARY KEY (`y_tonny_quotation_seq`)
    FOREIGN KEY (`client_seq`) 
    REFERENCES `user_table` (`user_seq`)
    FOREIGN KEY (`helper_seq`) 
    REFERENCES `user_table` (`user_seq`)
    FOREIGN KEY (`y_tonny_seq`) 
    REFERENCES `ytonny_table` (`y_tonny_seq`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

create table `ytonny_quotation_image_table` (
    `y_tonny_quotation_image_seq` BIGINT NOT NULL auto_increment,
    `y_tonny_quotation_seq` BIGINT,
    `image_content_type` VARCHAR(255),
    `image_name` VARCHAR(255),
    `image_path` VARCHAR(255),
    `image_file_size` BIGINT,
    PRIMARY KEY (`y_tonny_quotation_image_seq`)
    FOREIGN KEY (`y_tonny_quotation_seq`) 
    REFERENCES `ytonny_quotation_table` (`y_tonny_quotation_seq`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;