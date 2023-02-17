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
       (8, '002', "중국어", '0004'),
       (9, '002', "프랑스어", '0005'),
       (10, '002', "독일어", '0006'),
       (11, '002', "힌두어", '0007'),
       (12, '002', "스페인어", '0008'),
       (13, '002', "러시아어", '0009'),
       (14, '002', "아랍어", '0010'),
       (15, '003', "즉시통역", '0001'),
       (16, '003', "예약통역", '0002'),
       (17, '003', "번역", '0003'),
       (18, '004', "기타", '0000'),
       (19, '004', "일상", '0001'),
       (20, '004', "의료", '0002'),
       (21, '004', "법", '0003'),
       (22, '004', "비즈니스", '0004'),
       (23, '005', "인쇄물", '0001'),
       (24, '005', "영상", '0002'),
       (25, '005', "기타", '0003'),
       (26, '007', "미선택", '0001'),
       (27, '007', "선택", '0002'),
       (28, '007', "작업완료", '0003'),
       (29, '007', "거절", '0004'),
       (30, '009', "모집중", '0001'),
       (31, '009', "진행중", '0002'),
       (32, '009', "완료됨", '0003'),
       (33, '011', "충전", '0001'),
       (34, '011', "출금", '0002'),
       (35, '011', "거래", '0003');


-- 유저 정보
INSERT INTO user_table(user_seq, created_at, updated_at, email, is_agreement, is_deleted, nick_name, password,
                       phone_number, point,
                       profile_image_path, report_count, user_code)
VALUES (1, now(), now(), "client1@ssafy.com", true, false, "최유저",
        "$2a$10$IGu.pRd5Kn50eRFxcsZ1/O1i3guAooijPw9/SXurzZDuta2VN9ixK", "010", 100000, "/noProfile.png", 0, "0010001"),
       (2, now(), now(), "client2@ssafy.com", true, false, "안유저",
        "$2a$10$IGu.pRd5Kn50eRFxcsZ1/O1i3guAooijPw9/SXurzZDuta2VN9ixK", "010", 100000, "/noProfile.png", 0, "0010001"),
       (3, now(), now(), "client3@ssafy.com", true, false, "강유저",
        "$2a$10$IGu.pRd5Kn50eRFxcsZ1/O1i3guAooijPw9/SXurzZDuta2VN9ixK", "010", 100000, "/noProfile.png", 0, "0010001"),
       (4, now(), now(), "helper1@ssafy.com", true, false, "김헬퍼",
        "$2a$10$IGu.pRd5Kn50eRFxcsZ1/O1i3guAooijPw9/SXurzZDuta2VN9ixK", "010", 100000, "/noProfile.png", 0, "0010002"),
       (5, now(), now(), "helper2@ssafy.com", true, false, "나헬퍼",
        "$2a$10$IGu.pRd5Kn50eRFxcsZ1/O1i3guAooijPw9/SXurzZDuta2VN9ixK", "010", 100000, "/noProfile.png", 0, "0010002"),
       (6, now(), now(), "helper3@ssafy.com", true, false, "박헬퍼",
        "$2a$10$IGu.pRd5Kn50eRFxcsZ1/O1i3guAooijPw9/SXurzZDuta2VN9ixK", "010", 100000, "/noProfile.png", 0, "0010002"),
       (7, now(), now(), "helper4@ssafy.com", true, false, "이헬퍼",
        "$2a$10$IGu.pRd5Kn50eRFxcsZ1/O1i3guAooijPw9/SXurzZDuta2VN9ixK", "010", 100000, "/noProfile.png", 0, "0010002"),
       (8, now(), now(), "client@ssafy.coach", true, false, "동준갓",
        "$2a$10$7.RTTHBcOgE/8KVNyRNfk.OEMuDu8VhlimMhk.7H6Sel2UsryCmau", "010", 1000000, "/noProfile.png", 0, "0010001"),
       (9, now(), now(), "helper@ssafy.coach", true, false, "효은갓",
        "$2a$10$7.RTTHBcOgE/8KVNyRNfk.OEMuDu8VhlimMhk.7H6Sel2UsryCmau", "010", 1000000, "/noProfile.png", 0, "0010002"),
       (10, now(), now(), "client@ssafy.con", true, false, "서고객",
        "$2a$10$7.RTTHBcOgE/8KVNyRNfk.OEMuDu8VhlimMhk.7H6Sel2UsryCmau", "010", 1000000, "/noProfile.png", 0, "0010001"),
       (11, now(), now(), "helper@ssafy.con", true, false, "서헬퍼",
        "$2a$10$7.RTTHBcOgE/8KVNyRNfk.OEMuDu8VhlimMhk.7H6Sel2UsryCmau", "010", 1000000, "/noProfile.png", 0, "0010002");


-- 헬퍼 정보
/*
helper_info_seq
user_seq
unit_price
one_line_introduction
introduction
review_count
avg_score
created_at
updated_at
*/
INSERT INTO helper_info_table(helper_info_seq, user_seq, unit_price, one_line_introduction, introduction, review_count,
                              total_score, created_at, updated_at)
VALUES (1, 4, 500, "안녕하세요. 김헬퍼입니다.", "미국 11년째 거주중인 평범한 직장인입니다.", 17, 77, now(), now()),
       (2, 5, 400, "안녕하세요. 나헬퍼입니다.", "안녕하세요. 영문학을 전공하고 번역가로 활동중입니다.", 7, 32, now(), now()),
       (3, 6, 100, "안녕하세요. 박헬퍼입니다.", "안녕하세요. 미국에서 대학을 다니고 있습니다.", 3, 12, now(), now()),
       (4, 7, 1000, "안녕하세요. 이헬퍼입니다.", "안녕하세요. 일본을 사랑하는 학생입니다! 잘부탁드립니다.", 3, 6, now(), now()),
       (5, 9, 2000, "안녕하세요. 부울경 1반 실습코치입니다.", "믿고 맡겨주세요!", 2, 8, now(), now()),
       (6, 11, 5000, "안녕하세요. 부울경 1반 컨설턴트입니다.", "저 꽤 비싼 사람입니다.", 2, 10, now(), now());


-- 가능 언어 정보
/*
possible_language_seq
helper_info_seq
lang_code
created_at
updated_at
*/
-- 영어는 전부 가능, 일본어는 김헬퍼, 이헬퍼만 가능
INSERT INTO possible_language_table(possible_language_seq, helper_info_seq, lang_code, lang_name, created_at,
                                    updated_at)
VALUES (1, 1, "0020002", "영어", now(), now()),
       (2, 1, "0020003", "일본어", now(), now()),
       (3, 2, "0020002", "영어", now(), now()),
       (4, 3, "0020002", "영어", now(), now()),
       (5, 4, "0020002", "영어", now(), now()),
       (6, 4, "0020003", "일본어", now(), now()),
       (7, 5, "0020002", "영어", now(), now()),
       (8, 5, "0020003", "일본어", now(), now()),
       (9, 5, "0020004", "중국어", now(), now()),
       (10, 5, "0020005", "프랑스어", now(), now()),
       (11, 5, "0020006", "독일어", now(), now()),
       (12, 5, "0020000", "기타", now(), now()),
       (13, 6, "0020002", "영어", now(), now()),
       (14, 6, "0020003", "일본어", now(), now()),
       (15, 6, "0020004", "중국어", now(), now()),
       (16, 6, "0020005", "프랑스어", now(), now()),
       (17, 6, "0020006", "독일어", now(), now()),
       (18, 6, "0020000", "기타", now(), now());


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
       (4, 2, "0020002", "토익", "900점", false, now(), now()),
       (5, 3, "0020002", "토익", "950점", false, now(), now()),
       (6, 4, "0020002", "토익", "1000점", false, now(), now()),
       (7, 5, "0020002", "토익", "10000점", false, now(), now()),
       (8, 6, "0020002", "토익", "20000점", false, now(), now());


-- 알림 로그
INSERT INTO alert_log_table(alert_log_seq, user_seq, task_code, content, is_read, created_at,
                            updated_at)
VALUES (1, 1, "0030001", 'content1', false, now(), now()),
       (2, 2, "0030002", 'content2', false, now(), now()),
       (3, 1, "0030003", 'content3', true, now(), now()),
       (4, 2, "0030003", 'content4', true, now(), now());


-- 알림 설정
INSERT INTO alert_settings_table(alert_settings_seq, created_at, updated_at, is_all, is_chat, is_community,
                                 is_tonny_bunny, user_seq)
VALUES (1, now(), now(), true, true, true, true, 1),
       (2, now(), now(), true, false, false, true, 2);


-- 게시글
INSERT INTO board_table(board_seq, user_seq, title, content, is_delete, created_at, updated_at)
VALUES (1, 1, "영어숙제 좀 도와주세요!", "영어가 너무 어렵네요", false, now(), now()),
       (2, 2, "저도 영어숙제 좀 도와주세요!", "영어가 어렵다 ㅠㅠ", false, now(), now()),
       (3, 3, "일본어 메뉴판 읽어주실분?", "배고파요..", false, now(), now()),
       (4, 7, "일본어 공부법", "일본어 이렇게 하면 짱쉽습니다!", false, now(), now()),
       (5, 2, "외국 거주자분들, 이런 상황에서는 어떻게 하시나요?", "영어 어렵다 내용내용내용내용", false, now(), now()),
       (6, 5, "생생한 미국 현지인 일상 공개", "해주세요", false, now(), now()),
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


-- 예약통역
INSERT INTO ytonny_table(y_tonny_seq, user_seq, y_tonny_apply_seq, session_name, task_code, start_lang_code,
                         end_lang_code,
                         tonny_situ_code, title, content, estimate_price, estimate_time, task_state_code,
                         start_date_time, is_deleted, created_at, updated_at)
VALUES (1, 1, 0, "sdoiefsigxdsgssdfjq", "0030002", "0020001", "0020002", "0040004", "철강회사 바이어 미팅건",
        "어쩌고 저쩌고 작업 가능하신 분을 찾습니다.", 30000, "02:30", "0090001", "2023-02-11 00:00:00", false, now(), now()),
       (2, 2, 7, "sdoiefelsjdsigxdsgs", "0030002", "0020001", "0020003", "0040001", "일본 관광 통역요청",
        "어쩌고 저쩌고 작업 가능하신 분을 찾습니다.", 30000, "00:45", "0090002", "2023-02-13 00:00:00", false, now(), now()),
       (3, 3, 0, "sdoiefsigxdgdfgljaesgs", "0030002", "0020001", "0020000", "0040001", "관공서 서류 작업 예정",
        "어쩌고 저쩌고 작업 가능하신 분을 찾습니다.", 30000, "01:00", "0090001", "2023-02-17 00:00:00", false, now(), now()),
       (4, 1, 0, "sdoiefdsigxdgdfgljaesgs", "0030002", "0020001", "0020002", "0040004", "식품회사 바이어 미팅",
        "어쩌고 저쩌고 작업 가능하신 분을 찾습니다.", 30000, "01:00", "0090001", "2023-02-17 00:00:00", false, now(), now()),
       (5, 1, 0, "sdoiefsigxdgddfgljaesgs", "0030002", "0020001", "0020002", "0040001", "프랑스 관광 통역 요청",
        "어쩌고 저쩌고 작업 가능하신 분을 찾습니다.", 30000, "01:00", "0090001", "2023-02-17 00:00:00", false, now(), now()),
       (6, 1, 0, "sdoiefsigsdxdgdfgljaesgs", "0030002", "0020001", "0020000", "0040001", "나이지리아 관공서 방문",
        "어쩌고 저쩌고 작업 가능하신 분을 찾습니다.", 30000, "01:00", "0090001", "2023-02-17 00:00:00", false, now(), now()),
       (7, 1, 0, "sdoiefsiagxdgdfgljaesgs", "0030002", "0020001", "0020002", "0040004", "신발회사 바이어 미팅",
        "어쩌고 저쩌고 작업 가능하신 분을 찾습니다.", 30000, "01:00", "0090001", "2023-02-17 00:00:00", false, now(), now()),
       (8, 2, 0, "sdaoiefsigxdgdfgljaesgs", "0030002", "0020001", "0020002", "0040001", "중국 관광 통역 요청",
        "어쩌고 저쩌고 작업 가능하신 분을 찾습니다.", 30000, "01:00", "0090001", "2023-02-17 00:00:00", false, now(), now()),
       (9, 2, 0, "sdoiefsigxdgdffgljaesgs", "0030002", "0020001", "0020002", "0040004", "장난감 회사 거래처 미팅",
        "어쩌고 저쩌고 작업 가능하신 분을 찾습니다.", 30000, "01:00", "0090001", "2023-02-17 00:00:00", false, now(), now()),
       (10, 2, 0, "sdoiefsigxdwgdfgljaesgs", "0030002", "0020001", "0020002", "0040001", "관공서 서류 작업 예정",
        "어쩌고 저쩌고 작업 가능하신 분을 찾습니다.", 30000, "01:00", "0090001", "2023-02-18 00:00:00", false, now(), now()),
       (11, 2, 0, "sdoiefgsigxdgdfgljaesgs", "0030002", "0020001", "0020002", "0040001", "이탈리아 현지 통역",
        "어쩌고 저쩌고 작업 가능하신 분을 찾습니다.", 30000, "01:00", "0090001", "2023-02-18 00:00:00", false, now(), now()),
       (12, 3, 0, "sdoiefsigxdgedfgljaesgs", "0030002", "0020001", "0020002", "0040004", "중국 현지 거래처 방문",
        "어쩌고 저쩌고 작업 가능하신 분을 찾습니다.", 30000, "01:00", "0090001", "2023-02-18 00:00:00", false, now(), now()),
       (13, 3, 0, "sdoiefsigxhdgdfgljaesgs", "0030002", "0020001", "0020002", "0040004", "장갑 회사 바이어 미팅",
        "어쩌고 저쩌고 작업 가능하신 분을 찾습니다.", 30000, "01:00", "0090001", "2023-02-18 00:00:00", false, now(), now()),
       (14, 3, 0, "sdoiefsigxdgcddfgljaesgs", "0030002", "0020001", "0020002", "0040001", "미얀마 관공서 방문",
        "어쩌고 저쩌고 작업 가능하신 분을 찾습니다.", 30000, "01:00", "0090001", "2023-02-18 00:00:00", false, now(), now()),
       (15, 3, 0, "sdoiefsasqigxdgdfgljaesgs", "0030002", "0020001", "0020002", "0040004", "무역회사 거래처 방문",
        "어쩌고 저쩌고 작업 가능하신 분을 찾습니다.", 30000, "01:00", "0090001", "2023-02-18 00:00:00", false, now(), now()),
       (16, 3, 0, "sdoiefsigxqtdgdfgljaesgs", "0030002", "0020001", "0020002", "0040001", "인도 관광 통역 요청",
        "어쩌고 저쩌고 작업 가능하신 분을 찾습니다.", 30000, "01:00", "0090001", "2023-02-18 00:00:00", false, now(), now());


-- 예약통역 신청
INSERT INTO ytonny_apply_table(y_tonny_apply_seq, y_tonny_seq, helper_seq, unit_price, created_at, updated_at)
VALUES (1, 1, 4, 500, now(), now()),
       (2, 1, 5, 400, now(), now()),
       (3, 1, 6, 350, now(), now()),
       (4, 2, 4, 500, now(), now()),
       (5, 2, 7, 1000, now(), now()),
       (6, 3, 4, 500, now(), now()),
       (7, 2, 7, 700, now(), now());


-- 번역 공고
INSERT INTO bunny_table(bunny_seq, user_seq, helper_seq, task_code, start_lang_code, end_lang_code, title, content,
                        bunny_situ_code, task_state_code, start_date_time, end_date_time, estimate_price, is_deleted,
                        created_at, updated_at)
VALUES (1, 1, 0, "0030003", "0020001", "0020002", "해리포터 에필로그 번역", "해리포터 에필로그 번역해주실 분을 구합니다. 원서로 읽어보려했는데 쉽지않네요.",
        "0050001",
        "0090001", "2023-02-07", "2023-03-01", 20000, false, now(), now()),
       (2, 2, 0, "0030003", "0020001", "0020003", "반지의제왕 에필로그 번역", "반지의제왕 번역가 구합니다. 세계관에 대한 이해도가 있었으면 좋겠습니다!",
        "0050002",
        "0090001", "2023-02-07", "2023-02-21", 10000, false, now(), now()),
       (3, 3, 5, "0030003", "0020001", "0020002", "해리포터 프롤로그 번역", "해리포터 프롤로그 번역해주실 분을 구합니다. 원서로 읽어보려했는데 쉽지않네요.",
        "0050003",
        "0090002", "2023-02-07", "2023-02-26", 10000, false, now(), now()),
       (4, 3, 0, "0030003", "0020001", "0020000", "반지의제왕 프롤로그 번역", "반지의제왕 번역가 구합니다. 세계관에 대한 이해도가 있었으면 좋겠습니다!",
        "0050001",
        "0090001", "2023-02-07", "2023-03-10", 7000, false, now(), now());


-- 번역 신청
INSERT INTO bunny_apply_table(bunny_apply_seq, bunny_seq, user_seq, estimate_price, created_at, updated_at)
VALUES (1, 1, 4, 15000, now(), now()),
       (2, 1, 5, 8000, now(), now()),
       (3, 1, 6, 12000, now(), now()),
       (4, 2, 4, 15000, now(), now()),
       (5, 3, 7, 10000, now(), now()),
       (6, 4, 4, 10000, now(), now()),
       (7, 4, 7, 17000, now(), now());


-- 히스토리
INSERT INTO history_table(history_seq, client_seq, helper_seq, task_code, noti_seq, start_lang_code, end_lang_code,
                          content, start_date_time, end_date_time, created_at, updated_at)
VALUES (1, 1, 4, "0030003", 1, "0020001", "0020002", "해리포터 에필로그 번역", "2023-02-07", "2023-03-01", now(), now()),
       (2, 1, 4, "0030002", 1, "0020001", "0020002", "철강회사 바이어 미팅건", "2023-02-11 09:00:00", "2023-02-11 11:30:00",
        now(), now());

INSERT INTO ytonny_history_table(history_seq, tonny_situ_code, title, unit_price, total_time, record_video_path)
VALUES (2, "0040004", "철강회사 바이어 미팅건", 10000, "02:30:00", "iljawefposdnbdsfksldff.jpg");

INSERT INTO bunny_history_table(history_seq, title, total_price)
VALUES (1, "해리포터 에필로그 번역", 20000);


-- 리뷰
INSERT INTO review_table(review_seq, user_seq, history_seq, score, comment, is_deleted, created_at, updated_at)
VALUES (1, 1, 1, 5, "덕분에 잘 읽었습니다!!", false, now(), now()),
       (2, 1, 2, 1, "이사람때매 사업 망했습니다.", false, now(), now());