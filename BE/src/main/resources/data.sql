-- 공통그룹코드
INSERT INTO common_group_code_table(group_code, group_code_name)
VALUES ('001', "userCode"),
       ('002', "langCode"),
       ('003', "taskCode"),
       ('004', "tonnySituCode"),
       ('005', "bunnySituCode"),
       ('007', "quotationStateCode"),
       ('009', "taskStateCode"),
       ('011', "pointLogStateCode");


-- 공통코드
INSERT INTO common_code_table(code_seq, group_code, code_name, code)
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
       (15, '004', "비즈니스", '0004'),
       (16, '005', "인쇄물", '0001'),
       (17, '005', "영상", '0002'),
       (18, '005', "기타", '0003'),
       (19, '007', "미선택", '0001'),
       (20, '007', "선택", '0002'),
       (21, '007', "작업완료", '0003'),
       (22, '007', "거절", '0004'),
       (23, '009', "모집중", '0001'),
       (24, '009', "진행중", '0002'),
       (25, '009', "완료됨", '0003'),
       (26, '011', "충전", '0001'),
       (27, '011', "출금", '0002'),
       (28, '011', "거래", '0003');


-- 유저 정보
INSERT INTO user_table(user_seq, created_at, updated_at, email, is_agreement, is_deleted, nick_name, password,
                       phone_number, point,
                       profile_image_path, report_count, user_code)
VALUES (1, now(), now(), "client1@ssafy.com", true, false, "최유저",
        "$2a$10$IGu.pRd5Kn50eRFxcsZ1/O1i3guAooijPw9/SXurzZDuta2VN9ixK", "010", 0, "image", 0, "0010001"),
       (2, now(), now(), "clietn2@ssafy.com", true, false, "안유저",
        "$2a$10$IGu.pRd5Kn50eRFxcsZ1/O1i3guAooijPw9/SXurzZDuta2VN9ixK", "020", 0, "image2", 0, "0010001"),
       (3, now(), now(), "client3@ssafy.com", true, false, "강유저",
        "$2a$10$IGu.pRd5Kn50eRFxcsZ1/O1i3guAooijPw9/SXurzZDuta2VN9ixK", "010", 0, "image", 0, "0010001"),
       (4, now(), now(), "helper1@ssafy.com", true, false, "김헬퍼",
        "$2a$10$IGu.pRd5Kn50eRFxcsZ1/O1i3guAooijPw9/SXurzZDuta2VN9ixK", "010", 0, "image", 0, "0010002"),
       (5, now(), now(), "helper2@ssafy.com", true, false, "나헬퍼",
        "$2a$10$IGu.pRd5Kn50eRFxcsZ1/O1i3guAooijPw9/SXurzZDuta2VN9ixK", "010", 0, "image", 0, "0010002"),
       (6, now(), now(), "helper3@ssafy.com", true, false, "박헬퍼",
        "$2a$10$IGu.pRd5Kn50eRFxcsZ1/O1i3guAooijPw9/SXurzZDuta2VN9ixK", "010", 0, "image", 0, "0010002"),
       (7, now(), now(), "helper4@ssafy.com", true, false, "이헬퍼",
        "$2a$10$IGu.pRd5Kn50eRFxcsZ1/O1i3guAooijPw9/SXurzZDuta2VN9ixK", "010", 0, "image", 0, "0010002");


-- 헬퍼 정보
/*
helper_info_seq
user_seq
unit_price
one_line_introduction
introduction
review_count
avg_score
is_active
created_at
updated_at
*/
INSERT INTO helper_info_table(helper_info_seq, user_seq, unit_price, one_line_introduction, introduction, review_count,
                              total_score, is_active, created_at, updated_at)
VALUES (1, 4, 5000, "안녕하세요. 김헬퍼입니다.", "안녕하세요.안녕하세요.안녕하세요.안녕하세요.안녕하세요.안녕하세요.안녕하세요.", 2, 6, false, now(), now()),
       (2, 5, 4000, "안녕하세요. 나헬퍼입니다.", "안녕하세요.안녕하세요.안녕하세요.안녕하세요.안녕하세요.안녕하세요.안녕하세요.", 0, 0, false, now(), now()),
       (3, 6, 1000, "안녕하세요. 박헬퍼입니다.", "안녕하세요.안녕하세요.안녕하세요.안녕하세요.안녕하세요.안녕하세요.안녕하세요.", 0, 0, false, now(), now()),
       (4, 7, 20000, "안녕하세요. 이헬퍼입니다.", "안녕하세요.안녕하세요.안녕하세요.안녕하세요.안녕하세요.안녕하세요.안녕하세요.", 0, 0, false, now(), now());


-- 가능 언어 정보
/*
possible_language_seq
helper_info_seq
lang_code
created_at
updated_at
*/
-- 영어는 전부 가능, 일본어는 김헬퍼, 이헬퍼만 가능
INSERT INTO possible_language_table(possible_language_seq, helper_info_seq, lang_code, created_at, updated_at)
VALUES (1, 1, "0020002", now(), now()),
       (2, 2, "0020002", now(), now()),
       (3, 3, "0020002", now(), now()),
       (4, 4, "0020002", now(), now()),
       (5, 1, "0020003", now(), now()),
       (6, 4, "0020003", now(), now());


-- 보유 자격증 정보
/*
certificate_seq
helper_info_seq
lang_code
cert_name
content
is_deleted
created_at
updated_at
*/
INSERT INTO certificate_table(certificate_seq, helper_info_seq, lang_code, cert_name, content, is_deleted, created_at,
                              updated_at)
VALUES (1, 1, "0020002", "토익", "무려 535점", false, now(), now()),
       (2, 1, "0020003", "JPT", "1급", false, now(), now()),
       (3, 4, "0020003", "JPT", "1급", false, now(), now()),
       (4, 2, "0020003", "토익", "900점", false, now(), now()),
       (5, 3, "0020003", "토익", "950점", false, now(), now()),
       (6, 4, "0020003", "토익", "1000점", false, now(), now());


-- 알림 로그
INSERT INTO alert_log_table(alert_log_seq, created_at, updated_at, content, is_end, is_read, task_code, user_seq)
VALUES (1, now(), now(), 'content1', false, false, "0030001", 1),
       (2, now(), now(), 'content2', true, false, "0030002", 2),
       (3, now(), now(), 'content3', false, true, "0030003", 1),
       (4, now(), now(), 'content4', true, true, "0030003", 2);


-- 알림 설정
INSERT INTO alert_settings_table(alert_settings_seq, created_at, updated_at, is_all, is_chat, is_community,
                                 is_tonny_bunny, user_seq)
VALUES (1, now(), now(), true, true, true, true, 1),
       (2, now(), now(), true, false, false, true, 2);


-- 게시글
INSERT INTO board_table(board_seq, user_seq, title, content, is_delete, created_at, updated_at)
VALUES (1, 1, "영어숙제 좀 도와주세요!", "영어 어렵다 내용내용내용내용", false, now(), now()),
       (2, 2, "저도 영어숙제 좀 도와주세요!", "영어 어렵다 내용내용내용내용", false, now(), now()),
       (3, 3, "일본어 메뉴판 읽어주실분?", "일본어 어렵다 내용내용내용내용", false, now(), now()),
       (4, 7, "일본어 공부법", "일본어 쉽다 내용내용내용내용", false, now(), now()),
       (5, 2, "외국 거주자분들, 이런 상황에서는 어떻게 하시나요?", "영어 어렵다 내용내용내용내용", false, now(), now()),
       (6, 5, "생생한 미국 현지인 일상 공개", "영어 어렵다 내용내용내용내용", false, now(), now()),
       (7, 3, "유학을 준비중입니다. 도와주세요", "영어 어렵다 내용내용내용내용", false, now(), now());


-- 댓글
INSERT INTO board_comment_table(board_comment_seq, user_seq, board_seq, content, created_at, updated_at)
VALUES (1, 3, 1, "흠.. 이건 저도 잘 모르겠네요.", now(), now()),
       (2, 1, 1, "헉! 도움이 전혀 안되었지만 감사합니다!!", now(), now()),
       (3, 7, 3, "맛있겠네요.", now(), now()),
       (4, 3, 3, "?????", now(), now()),
       (5, 5, 3, "ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ", now(), now()),
       (6, 2, 6, "재밌게 잘 읽었습니다.", now(), now()),
       (7, 1, 6, "앞으로도 자주 올려주세요!", now(), now());


-- 예약통역 공고
INSERT INTO ytonny_table(y_tonny_seq, user_seq, helper_seq, session_name, task_code, start_lang_code, end_lang_code,
                         tonny_situ_code, title, content, estimate_price, estimate_time, task_state_code,
                         start_date_time, is_deleted, created_at, updated_at)
VALUES (1, 1, 0, "sdoiefsigxdsgssdfjq", "0030002", "0020001", "0020002", "0040004", "철강회사 바이어 미팅건",
        "어쩌고 저쩌고 작업 가능하신 분을 찾습니다.", 300000, "02:30", "0090001", "2023-02-11 00:00:00", false, now(), now()),
       (2, 2, 7, "sdoiefelsjdsigxdsgs", "0030002", "0020001", "0020003", "0040001", "일본 관광 통역요청",
        "어쩌고 저쩌고 작업 가능하신 분을 찾습니다.", 300000, "00:45", "0090002", "2023-02-13 00:00:00", false, now(), now()),
       (3, 3, 0, "sdoiefsigxdgdfgljaesgs", "0030002", "0020001", "0020000", "0040001", "관공서 서류 작업 예정",
        "어쩌고 저쩌고 작업 가능하신 분을 찾습니다.", 300000, "01:00", "0090001", "2023-02-14 00:00:00", false, now(), now());


-- 예약통역 신청
INSERT INTO ytonny_apply_table(y_tonny_apply_seq, y_tonny_seq, helper_seq, unit_price, created_at, updated_at)
VALUES (1, 1, 4, 5000, now(), now()),
       (2, 1, 5, 4000, now(), now()),
       (3, 1, 6, 3500, now(), now()),
       (4, 2, 4, 5000, now(), now()),
       (5, 2, 7, 10000, now(), now()),
       (6, 3, 4, 5000, now(), now()),
       (7, 3, 7, 7000, now(), now());


-- 번역 공고
INSERT INTO bunny_table(bunny_seq, user_seq, helper_seq, task_code, start_lang_code, end_lang_code, title, content,
                        bunny_situ_code, task_state_code, start_date_time, end_date_time, estimate_price, is_deleted,
                        created_at, updated_at)
VALUES (1, 1, 0, "0030003", "0020001", "0020002", "해리포터 에필로그 번역", "내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용", "0050001",
        "0090001", "2023-02-07", "2023-03-01", 200000, false, now(), now()),
       (2, 2, 0, "0030003", "0020001", "0020003", "반지의제왕 에필로그 번역", "내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용", "0050002",
        "0090001", "2023-02-07", "2023-02-21", 100000, false, now(), now()),
       (3, 3, 5, "0030003", "0020001", "0020002", "해리포터 프롤로그 번역", "내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용", "0050003",
        "0090002", "2023-02-07", "2023-02-26", 100000, false, now(), now()),
       (4, 3, 0, "0030003", "0020001", "0020000", "반지의제왕 프롤로그 번역", "내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용", "0050001",
        "0090001", "2023-02-07", "2023-03-10", 70000, false, now(), now());


-- 번역 신청
INSERT INTO bunny_apply_table(bunny_apply_seq, bunny_seq, user_seq, estimate_price, created_at, updated_at)
VALUES (1, 1, 4, 150000, now(), now()),
       (2, 1, 5, 80000, now(), now()),
       (3, 1, 6, 120000, now(), now()),
       (4, 2, 4, 150000, now(), now()),
       (5, 3, 7, 100000, now(), now()),
       (6, 4, 4, 105000, now(), now()),
       (7, 4, 7, 170000, now(), now());


-- 히스토리
INSERT INTO history_table(history_seq, client_seq, helper_seq, task_code, noti_seq, start_lang_code, end_lang_code,
                          content, start_date_time, end_date_time, created_at, updated_at)
VALUES (1, 1, 4, "0030003", 1, "0020001", "0020002", "해리포터 에필로그 번역", "2023-02-07", "2023-03-01", now(), now()),
       (2, 1, 4, "0030002", 1, "0020001", "0020002", "철강회사 바이어 미팅건", "2023-02-11 09:00:00", "2023-02-11 11:30:00",
        now(), now());

INSERT INTO ytonny_history_table(history_seq, tonny_situ_code, title, unit_price, total_time, record_video_path)
VALUES (2, "0040004", "철강회사 바이어 미팅건", 10000, "02:30:00", "iljawefposdnbdsfksldff.jpg");

INSERT INTO bunny_history_table(history_seq, title, total_price)
VALUES (1, "해리포터 에필로그 번역", 200000);


-- 리뷰
INSERT INTO review_table(review_seq, user_seq, history_seq, score, comment, is_deleted, created_at, updated_at)
VALUES (1, 1, 1, 5, "덕분에 잘 읽었습니다!!", false, now(), now()),
       (2, 1, 2, 1, "이사람때매 사업 망했습니다.", false, now(), now());