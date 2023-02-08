DROP TABLE IF EXISTS `board_comment_table`;
DROP TABLE IF EXISTS `board_image_table`;
DROP TABLE IF EXISTS `board_table`;

DROP TABLE IF EXISTS `chat_log_table`;
DROP TABLE IF EXISTS `chat_room_table`;

DROP TABLE IF EXISTS `bunny_history_table`;
DROP TABLE IF EXISTS `bunny_quotation_image_table`;
DROP TABLE IF EXISTS `bunny_quotation_table`;
DROP TABLE IF EXISTS `bunny_apply_table`;
DROP TABLE IF EXISTS `bunny_image_table`;
DROP TABLE IF EXISTS `bunny_table`;

DROP TABLE IF EXISTS `ytonny_history_table`;
DROP TABLE IF EXISTS `ytonny_quotation_image_table`;
DROP TABLE IF EXISTS `ytonny_quotation_table`;
DROP TABLE IF EXISTS `ytonny_apply_table`;
DROP TABLE IF EXISTS `ytonny_table`;

DROP TABLE IF EXISTS `jtonny_history_table`;
DROP TABLE IF EXISTS `jtonny_table`;

DROP TABLE IF EXISTS `review_table`;
DROP TABLE IF EXISTS `history_table`;

DROP TABLE IF EXISTS `auth_table`;

DROP TABLE IF EXISTS `alert_log_table`;
DROP TABLE IF EXISTS `alert_settings_table`;
DROP TABLE IF EXISTS `point_log_table`;
DROP TABLE IF EXISTS `schedule_table`;
DROP TABLE IF EXISTS `block_table`;
DROP TABLE IF EXISTS `follow_table`;
DROP TABLE IF EXISTS `certificate_table`;
DROP TABLE IF EXISTS `possible_language_table`;

DROP TABLE IF EXISTS `helper_info_image_table`;
DROP TABLE IF EXISTS `helper_info_table`;
DROP TABLE IF EXISTS `user_table`;

DROP TABLE IF EXISTS `common_code_table`;
DROP TABLE IF EXISTS `common_group_code_table`;

-- COMMON GROUP CODE
CREATE TABLE `common_group_code_table`
(
    `group_code`      CHAR(3) NOT NULL,
    `group_code_name` VARCHAR(255),
    PRIMARY KEY (`group_code`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- COMMON CODE
CREATE TABLE `common_code_table`
(
    `code_seq`   BIGINT NOT NULL auto_increment,
    `group_code` CHAR(3),
    `code`       CHAR(4),
    `code_name`  VARCHAR(255),
    PRIMARY KEY (`code_seq`),
    FOREIGN KEY (`group_code`)
        REFERENCES `common_group_code_table` (`group_code`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- USER
CREATE TABLE `user_table`
(
    `user_seq`           BIGINT    NOT NULL auto_increment,
    `user_code`          CHAR(7),
    `email`              VARCHAR(255),
    `nick_name`          VARCHAR(255),
    `password`           VARCHAR(255),
    `phone_number`       VARCHAR(255),
    `point`              BIGINT,
    `profile_image_path` VARCHAR(255),
    `report_count`       INTEGER,
    `is_agreement`       BOOLEAN,
    `is_deleted`         BOOLEAN,
    `created_at`         TIMESTAMP NOT NULL DEFAULT now(),
    `updated_at`         TIMESTAMP NOT NULL DEFAULT now(),
    PRIMARY KEY (`user_seq`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- HELPER INFO
CREATE TABLE `helper_info_table`
(
    `helper_info_seq`       BIGINT    NOT NULL auto_increment,
    `user_seq`              BIGINT,
    `one_line_introduction` VARCHAR(255),
    `introduction`          VARCHAR(255),
    `unit_price`            INTEGER,
    `avg_score`             FLOAT,
    `like_count`            INTEGER,
    `review_count`          INTEGER,
    `is_active`             BOOLEAN,
    `created_at`            TIMESTAMP NOT NULL DEFAULT now(),
    `updated_at`            TIMESTAMP NOT NULL DEFAULT now(),
    PRIMARY KEY (`helper_info_seq`),
    FOREIGN KEY (`user_seq`)
        REFERENCES `user_table` (`user_seq`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- HELPER INFO IMAGE
CREATE TABLE `helper_info_image_table`
(
    `helper_info_image_seq` BIGINT    NOT NULL auto_increment,
    `helper_info_seq`       BIGINT,
    `image_path`            VARCHAR(255),
    `is_deleted`            BOOLEAN,
    `created_at`            TIMESTAMP NOT NULL DEFAULT now(),
    `updated_at`            TIMESTAMP NOT NULL DEFAULT now(),
    PRIMARY KEY (`helper_info_image_seq`),
    FOREIGN KEY (`helper_info_seq`)
        REFERENCES `helper_info_table` (`helper_info_seq`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- POSSIBLE LANGUAGE
CREATE TABLE `possible_language_table`
(
    `possible_language_seq` BIGINT    NOT NULL auto_increment,
    `helper_info_seq`       BIGINT,
    `lang_code`             CHAR(7),
    `lang_name`             VARCHAR(10),
    `created_at`            TIMESTAMP NOT NULL DEFAULT now(),
    `updated_at`            TIMESTAMP NOT NULL DEFAULT now(),
    PRIMARY KEY (`possible_language_seq`),
    FOREIGN KEY (`helper_info_seq`)
        REFERENCES `helper_info_table` (`helper_info_seq`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- CERTIFICATE
CREATE TABLE `certificate_table`
(
    `certificate_seq` BIGINT    NOT NULL auto_increment,
    `helper_info_seq` BIGINT,
    `lang_code`       CHAR(7),
    `cert_name`       VARCHAR(255),
    `content`         VARCHAR(255),
    `is_deleted`      BOOLEAN,
    `created_at`      TIMESTAMP NOT NULL DEFAULT now(),
    `updated_at`      TIMESTAMP NOT NULL DEFAULT now(),
    PRIMARY KEY (`certificate_seq`),
    FOREIGN KEY (`helper_info_seq`)
        REFERENCES `helper_info_table` (`helper_info_seq`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- FOLLOW
CREATE TABLE `follow_table`
(
    `follow_seq`        BIGINT    NOT NULL auto_increment,
    `user_seq`          BIGINT,
    `followed_user_seq` BIGINT,
    `created_at`        TIMESTAMP NOT NULL DEFAULT now(),
    `updated_at`        TIMESTAMP NOT NULL DEFAULT now(),
    PRIMARY KEY (`follow_seq`),
    FOREIGN KEY (`user_seq`)
        REFERENCES `user_table` (`user_seq`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- BLOCK
CREATE TABLE `block_table`
(
    `block_seq`        BIGINT    NOT NULL auto_increment,
    `user_seq`         BIGINT,
    `blocked_user_seq` BIGINT,
    `created_at`       TIMESTAMP NOT NULL DEFAULT now(),
    `updated_at`       TIMESTAMP NOT NULL DEFAULT now(),
    PRIMARY KEY (`block_seq`),
    FOREIGN KEY (`user_seq`)
        REFERENCES `user_table` (`user_seq`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- SCHEDULE
CREATE TABLE `schedule_table`
(
    `schedule_seq`    BIGINT    NOT NULL auto_increment,
    `user_seq`        BIGINT,
    `task_code`       CHAR(7),
    `title`           VARCHAR(255),
    `content`         VARCHAR(255),
    `start_date_time` TIMESTAMP NOT NULL DEFAULT now(),
    `end_date_time`   TIMESTAMP NOT NULL DEFAULT now(),
    `is_complete`     BOOLEAN,
    `created_at`      TIMESTAMP NOT NULL DEFAULT now(),
    `updated_at`      TIMESTAMP NOT NULL DEFAULT now(),
    PRIMARY KEY (`schedule_seq`),
    FOREIGN KEY (`user_seq`)
        REFERENCES `user_table` (`user_seq`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- POINT LOG
CREATE TABLE `point_log_table`
(
    `point_log_seq` BIGINT    NOT NULL auto_increment,
    `user_seq`      BIGINT,
    `type_code`     CHAR(7),
    `amount`        INTEGER   NOT NULL,
    `created_at`    TIMESTAMP NOT NULL DEFAULT now(),
    `updated_at`    TIMESTAMP NOT NULL DEFAULT now(),
    PRIMARY KEY (`point_log_seq`),
    FOREIGN KEY (`user_seq`)
        REFERENCES `user_table` (`user_seq`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ALERT SETTING
CREATE TABLE `alert_settings_table`
(
    `alert_settings_seq` BIGINT    NOT NULL auto_increment,
    `user_seq`           BIGINT,
    `is_all`             BOOLEAN,
    `is_chat`            BOOLEAN,
    `is_community`       BOOLEAN,
    `is_tonny_bunny`     BOOLEAN,
    `created_at`         TIMESTAMP NOT NULL DEFAULT now(),
    `updated_at`         TIMESTAMP NOT NULL DEFAULT now(),
    PRIMARY KEY (`alert_settings_seq`),
    FOREIGN KEY (`user_seq`)
        REFERENCES `user_table` (`user_seq`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ALERT LOG
CREATE TABLE `alert_log_table`
(
    `alert_log_seq` BIGINT    NOT NULL auto_increment,
    `user_seq`      BIGINT,
    `task_code`     CHAR(7),
    `content`       VARCHAR(255),
    `is_end`        BIGINT,
    `is_read`       BOOLEAN,
    `created_at`    TIMESTAMP NOT NULL DEFAULT now(),
    `updated_at`    TIMESTAMP NOT NULL DEFAULT now(),
    PRIMARY KEY (`alert_log_seq`),
    FOREIGN KEY (`user_seq`)
        REFERENCES `user_table` (`user_seq`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- AUTH
CREATE TABLE `auth_table`
(
    `id`            BIGINT    NOT NULL auto_increment,
    `user_seq`      BIGINT,
    `refresh_token` VARCHAR(255),
    `created_at`    TIMESTAMP NOT NULL DEFAULT now(),
    `updated_at`    TIMESTAMP NOT NULL DEFAULT now(),
    PRIMARY KEY (`id`),
    FOREIGN KEY (`user_seq`)
        REFERENCES `user_table` (`user_seq`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- HISTORY
CREATE TABLE `history_table`
(
    `history_seq`     BIGINT    NOT NULL auto_increment,
    `client_seq`      BIGINT,
    `helper_seq`      BIGINT,
    `task_code`       CHAR(7)   NOT NULL,
    `noti_seq`        BIGINT,
    `start_lang_code` CHAR(7),
    `end_lang_code`   CHAR(7),
    `content`         VARCHAR(255),
    `start_date_time` TIMESTAMP NOT NULL DEFAULT now(),
    `end_date_time`   TIMESTAMP NOT NULL DEFAULT now(),
    `created_at`      TIMESTAMP NOT NULL DEFAULT now(),
    `updated_at`      TIMESTAMP NOT NULL DEFAULT now(),
    PRIMARY KEY (`history_seq`),
    FOREIGN KEY (`client_seq`)
        REFERENCES `user_table` (`user_seq`),
    FOREIGN KEY (`helper_seq`)
        REFERENCES `user_table` (`user_seq`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- REVIEW
CREATE TABLE `review_table`
(
    `review_seq`  BIGINT    NOT NULL auto_increment,
    `user_seq`    BIGINT,
    `history_seq` BIGINT,
    `score`       FLOAT,
    `comment`     VARCHAR(255),
    `is_deleted`  BOOLEAN,
    `created_at`  TIMESTAMP NOT NULL DEFAULT now(),
    `updated_at`  TIMESTAMP NOT NULL DEFAULT now(),
    PRIMARY KEY (`review_seq`),
    FOREIGN KEY (`history_seq`)
        REFERENCES `history_table` (`history_seq`),
    FOREIGN KEY (`user_seq`)
        REFERENCES `user_table` (`user_seq`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- JTONNY
CREATE TABLE `jtonny_table`
(
    `j_tonny_seq`     BIGINT NOT NULL auto_increment,
    `client_seq`      BIGINT,
    `helper_seq`      BIGINT,
    `task_code`       CHAR(7),
    `task_state_code` CHAR(7),
    `start_lang_code` CHAR(7),
    `end_lang_code`   CHAR(7),
    `tonny_situ_code` CHAR(7),
    `content`         VARCHAR(255),
    `estimate_time`   CHAR(10),
    `unit_price`      INTEGER,
    PRIMARY KEY (`j_tonny_seq`),
    FOREIGN KEY (`client_seq`)
        REFERENCES `user_table` (`user_seq`),
    FOREIGN KEY (`helper_seq`)
        REFERENCES `user_table` (`user_seq`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- JTONNY HISTORY
CREATE TABLE `jtonny_history_table`
(
    `history_seq`       BIGINT NOT NULL,
    `tonny_situ_code`   CHAR(7),
    `total_time`        TIME,
    `unit_price`        INTEGER,
    `record_video_path` VARCHAR(255),
    PRIMARY KEY (`history_seq`),
    FOREIGN KEY (`history_seq`)
        REFERENCES `history_table` (`history_seq`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- YTONNY
CREATE TABLE `ytonny_table`
(
    `y_tonny_seq`     BIGINT    NOT NULL auto_increment,
    `user_seq`        BIGINT,
    `helper_seq`      BIGINT,
    `task_code`       CHAR(7),
    `start_lang_code` CHAR(7),
    `end_lang_code`   CHAR(7),
    `tonny_situ_code` CHAR(7),
    `title`           VARCHAR(255),
    `content`         VARCHAR(255),
    `estimate_price`  INTEGER,
    `estimate_time`   CHAR(10),
    `task_state_code` CHAR(7),
    `start_date_time` TIMESTAMP NOT NULL DEFAULT now(),
    `is_deleted`      BOOLEAN,
    `created_at`      TIMESTAMP NOT NULL DEFAULT now(),
    `updated_at`      TIMESTAMP NOT NULL DEFAULT now(),
    PRIMARY KEY (`y_tonny_seq`),
    FOREIGN KEY (`user_seq`)
        REFERENCES `user_table` (`user_seq`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- YTONNY APPLY
CREATE TABLE `ytonny_apply_table`
(
    `y_tonny_apply_seq` BIGINT    NOT NULL auto_increment,
    `y_tonny_seq`       BIGINT,
    `helper_seq`        BIGINT,
    `total_price`       INTEGER,
    `created_at`        TIMESTAMP NOT NULL DEFAULT now(),
    `updated_at`        TIMESTAMP NOT NULL DEFAULT now(),
    PRIMARY KEY (`y_tonny_apply_seq`),
    FOREIGN KEY (`helper_seq`)
        REFERENCES `user_table` (`user_seq`),
    FOREIGN KEY (`y_tonny_seq`)
        REFERENCES `ytonny_table` (`y_tonny_seq`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- YTONNY QUOTATION
CREATE TABLE `ytonny_quotation_table`
(
    `y_tonny_quotation_seq` BIGINT    NOT NULL auto_increment,
    `y_tonny_seq`           BIGINT,
    `client_seq`            BIGINT,
    `helper_seq`            BIGINT,
    `start_lang_code`       CHAR(7),
    `end_lang_code`         CHAR(7),
    `title`                 VARCHAR(255),
    `content`               VARCHAR(255),
    `unit_price`            INTEGER,
    `estimate_time`         CHAR(10),
    `quotation_state_code`  CHAR(7),
    `start_date_time`       DATETIME,
    `created_at`            TIMESTAMP NOT NULL DEFAULT now(),
    `updated_at`            TIMESTAMP NOT NULL DEFAULT now(),
    PRIMARY KEY (`y_tonny_quotation_seq`),
    FOREIGN KEY (`client_seq`)
        REFERENCES `user_table` (`user_seq`),
    FOREIGN KEY (`helper_seq`)
        REFERENCES `user_table` (`user_seq`),
    FOREIGN KEY (`y_tonny_seq`)
        REFERENCES `ytonny_table` (`y_tonny_seq`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- YTONNY QUOTATION IMAGE
CREATE TABLE `ytonny_quotation_image_table`
(
    `y_tonny_quotation_image_seq` BIGINT NOT NULL auto_increment,
    `y_tonny_quotation_seq`       BIGINT,
    `image_content_type`          VARCHAR(255),
    `image_name`                  VARCHAR(255),
    `image_path`                  VARCHAR(255),
    `image_file_size`             BIGINT,
    PRIMARY KEY (`y_tonny_quotation_image_seq`),
    FOREIGN KEY (`y_tonny_quotation_seq`)
        REFERENCES `ytonny_quotation_table` (`y_tonny_quotation_seq`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- YTONNY HISTORY
CREATE TABLE `ytonny_history_table`
(
    `history_seq`       BIGINT NOT NULL,
    `tonny_situ_code`   CHAR(7),
    `title`             VARCHAR(255),
    `unit_price`        INTEGER,
    `total_time`        TIME,
    `record_video_path` VARCHAR(255),
    PRIMARY KEY (`history_seq`),
    FOREIGN KEY (`history_seq`)
        REFERENCES `history_table` (`history_seq`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- BUNNY
CREATE TABLE `bunny_table`
(
    `bunny_seq`        BIGINT    NOT NULL auto_increment,
    `user_seq`         BIGINT,
    `helper_seq`       BIGINT,
    `task_code`        CHAR(7),
    `start_lang_code`  CHAR(7),
    `end_lang_code`    CHAR(7),
    `title`            VARCHAR(255),
    `content`          VARCHAR(255),
    `bunny_situ_code`  CHAR(7),
    `bunny_state_code` CHAR(7),
    `start_date_time`  TIMESTAMP NOT NULL DEFAULT now(),
    `end_date_time`    TIMESTAMP NOT NULL DEFAULT now(),
    `estimate_price`   INTEGER,
    `is_deleted`       BOOLEAN,
    `created_at`       TIMESTAMP NOT NULL DEFAULT now(),
    `updated_at`       TIMESTAMP NOT NULL DEFAULT now(),
    PRIMARY KEY (`bunny_seq`),
    FOREIGN KEY (`user_seq`)
        REFERENCES `user_table` (`user_seq`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- BUNNY IMAGE
CREATE TABLE `bunny_image_table`
(
    `bunny_image_seq` BIGINT    NOT NULL auto_increment,
    `bunny_seq`       BIGINT,
    `image_path`      VARCHAR(255),
    `created_at`      TIMESTAMP NOT NULL DEFAULT now(),
    `updated_at`      TIMESTAMP NOT NULL DEFAULT now(),
    PRIMARY KEY (`bunny_image_seq`),
    FOREIGN KEY (`bunny_seq`)
        REFERENCES `bunny_table` (`bunny_seq`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- BUNNY APPLY
CREATE TABLE `bunny_apply_table`
(
    `bunny_apply_seq` BIGINT    NOT NULL auto_increment,
    `user_seq`        BIGINT,
    `bunny_seq`       BIGINT,
    `estimate_price`  INTEGER,
    `updated_at`      TIMESTAMP NOT NULL DEFAULT now(),
    `created_at`      TIMESTAMP NOT NULL DEFAULT now(),
    PRIMARY KEY (`bunny_apply_seq`),
    FOREIGN KEY (`bunny_seq`)
        REFERENCES `bunny_table` (`bunny_seq`),
    FOREIGN KEY (`user_seq`)
        REFERENCES `user_table` (`user_seq`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- BUNNY QUOTATION
CREATE TABLE `bunny_quotation_table`
(
    `bunny_quotation_seq`        BIGINT    NOT NULL auto_increment,
    `bunny_seq`                  BIGINT,
    `client_seq`                 BIGINT,
    `helper_seq`                 BIGINT,
    `start_lang_code`            CHAR(7),
    `end_lang_code`              CHAR(7),
    `title`                      VARCHAR(255),
    `content`                    VARCHAR(255),
    `start_date_time`            TIMESTAMP NOT NULL DEFAULT now(),
    `end_date_time`              TIMESTAMP NOT NULL DEFAULT now(),
    `bunny_quotation_state_code` CHAR(7),
    `total_price`                INTEGER,
    `created_at`                 TIMESTAMP NOT NULL DEFAULT now(),
    `updated_at`                 TIMESTAMP NOT NULL DEFAULT now(),
    PRIMARY KEY (`bunny_quotation_seq`),
    FOREIGN KEY (`bunny_seq`)
        REFERENCES `bunny_table` (`bunny_seq`),
    FOREIGN KEY (`client_seq`)
        REFERENCES `user_table` (`user_seq`),
    FOREIGN KEY (`helper_seq`)
        REFERENCES `user_table` (`user_seq`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- BUNNY QUOTATION IMAGE
CREATE TABLE `bunny_quotation_image_table`
(
    `bunny_quotation_image_seq` BIGINT    NOT NULL auto_increment,
    `bunny_quotation_seq`       BIGINT,
    `image_path`                VARCHAR(255),
    `updated_at`                TIMESTAMP NOT NULL DEFAULT now(),
    `created_at`                TIMESTAMP NOT NULL DEFAULT now(),
    PRIMARY KEY (`bunny_quotation_image_seq`),
    FOREIGN KEY (`bunny_quotation_seq`)
        REFERENCES `bunny_quotation_table` (`bunny_quotation_seq`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- BUNNY HISTORY
CREATE TABLE `bunny_history_table`
(
    `history_seq` BIGINT NOT NULL,
    `title`       VARCHAR(255),
    `total_price` INTEGER,
    PRIMARY KEY (`history_seq`),
    FOREIGN KEY (`history_seq`)
        REFERENCES `history_table` (`history_seq`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- CHAT ROOM
CREATE TABLE `chat_room_table`
(
    `chat_room_seq`     VARCHAR(255) NOT NULL,
    `user_less_seq`     BIGINT,
    `user_larger_seq`   BIGINT,
    `created_at`        TIMESTAMP    NOT NULL DEFAULT now(),
    `updated_at`        TIMESTAMP    NOT NULL DEFAULT now(),
    PRIMARY KEY (`chat_room_seq`),
    FOREIGN KEY (`user_less_seq`)
        REFERENCES `user_table` (`user_seq`),
    FOREIGN KEY (`user_larger_seq`)
        REFERENCES `user_table` (`user_seq`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- CHAT LOG
CREATE TABLE `chat_log_table`
(
    `chat_log_seq`   BIGINT    NOT NULL auto_increment,
    `user_seq`       BIGINT,
    `room_seq`       VARCHAR(255),
    `data_type_code` CHAR(7),
    `message`        VARCHAR(255),
    `date`           TIMESTAMP NOT NULL DEFAULT now(),
    `created_at`     TIMESTAMP NOT NULL DEFAULT now(),
    `updated_at`     TIMESTAMP NOT NULL DEFAULT now(),
    PRIMARY KEY (`chat_log_seq`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- BOARD
CREATE TABLE `board_table`
(
    `board_seq`  BIGINT    NOT NULL auto_increment,
    `user_seq`   BIGINT,
    `title`      VARCHAR(255),
    `content`    VARCHAR(255),
    `is_delete`  BOOLEAN,
    `created_at` TIMESTAMP NOT NULL DEFAULT now(),
    `updated_at` TIMESTAMP NOT NULL DEFAULT now(),
    PRIMARY KEY (`board_seq`),
    FOREIGN KEY (`user_seq`)
        REFERENCES `user_table` (`user_seq`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- BOARD IMAGE
CREATE TABLE `board_image_table`
(
    `board_image_seq` BIGINT    NOT NULL auto_increment,
    `board_seq`       BIGINT,
    `image_path`      VARCHAR(255),
    `created_at`      TIMESTAMP NOT NULL DEFAULT now(),
    `updated_at`      TIMESTAMP NOT NULL DEFAULT now(),
    PRIMARY KEY (`board_image_seq`),
    FOREIGN KEY (`board_seq`)
        REFERENCES `board_table` (`board_seq`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- BOARD COMMENT
CREATE TABLE `board_comment_table`
(
    `board_comment_seq` BIGINT    NOT NULL auto_increment,
    `user_seq`          BIGINT,
    `board_seq`         BIGINT,
    `content`           VARCHAR(255),
    `created_at`        TIMESTAMP NOT NULL DEFAULT now(),
    `updated_at`        TIMESTAMP NOT NULL DEFAULT now(),
    PRIMARY KEY (`board_comment_seq`),
    FOREIGN KEY (`board_seq`)
        REFERENCES `board_table` (`board_seq`),
    FOREIGN KEY (`user_seq`)
        REFERENCES `user_table` (`user_seq`)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;



































