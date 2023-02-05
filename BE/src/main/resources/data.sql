-- 공통그룹코드
INSERT INTO common_group_code_entity(group_code, group_code_name)
VALUES ('001', "userCode"),
       ('002', "langCode"),
       ('003', "taskCode"),
       ('004', "tonnySituCode"),
       ('005', "bunnySituCode"),
       ('007', "quotationStateCode"),
       ('009', "taskStateCode"),
       ('011', "pointLogStateCode");

-- 공통코드
INSERT INTO common_code_entity(code_seq, group_code, code_name, code)
VALUES (1, '001', "고객", '0001'),
       (2, '001', "헬퍼", '0002'),
       (3, '001', "관리자", '0003'),
       (4, '002', "기타", '0000'),
       (5, '002', "한국어", '0001'),
       (6, '002', "영어", '0002'),
       (7, '002', "일본어", '0003'),
       (8, '003', "즉시통역", '0001'),
       (9, '003', "예약통역", '0002'),
       (10, '003', "번역", '0003'),
       (11, '004', "기타", '0000'),
       (12, '004', "일상", '0001'),
       (13, '004', "의료", '0002'),
       (14, '004', "법", '0003'),
       (15, '005', "인쇄물", '0001'),
       (16, '005', "영상", '0002'),
       (17, '005', "기타", '0003'),
       (18, '007', "미선택", '0001'),
       (19, '007', "선택", '0002'),
       (20, '007', "작업완료", '0003'),
       (21, '007', "거절", '0004'),
       (22, '009', "모집중", '0001'),
       (23, '009', "진행중", '0002'),
       (24, '009', "완료됨", '0003'),
       (25, '011', "충전", '0001'),
       (26, '011', "출금", '0002'),
       (27, '011', "거래", '0003');

-- 유저 정보
INSERT INTO user_entity(user_seq, created_at, updated_at, email, is_agreement, is_deleted, nick_name, password,
                        phone_number, point,
                        profile_image_path, report_count, user_code)
VALUES (1, now(), now(), "client@ssafy.com", 0, 0, "nickname1", "pw1", "010", 0, "image", 0, "0010001"),
       (2, now(), now(), "helper@ssafy.com", 1, 0, "nickname2", "pw2", "020", 0, "image2", 0, "0010002");

-- 알림 로그
INSERT INTO alert_log_entity(alert_log_seq, created_at, updated_at, content, is_end, is_read, task_code, user_seq)
VALUES (1, now(), now(), 'content1', false, false, "0030001", 1),
       (2, now(), now(), 'content2', true, false, "0030002", 2),
       (3, now(), now(), 'content3', false, true, "0030003", 1),
       (4, now(), now(), 'content4', true, true, "0030003", 2);

-- 알림 설정
INSERT INTO alert_settings_entity(alert_settings_seq, created_at, updated_at, is_all, is_chat, is_community,
                                  is_tonny_bunny, user_seq)
VALUES (1, now(), now(), true, true, true, true, 1),
       (2, now(), now(), true, false, false, true, 2);