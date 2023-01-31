
select * from user_entity;

insert into user_entity (created_at, updated_at, email, nick_name, password, phone_number, point)
values (now(), now(), "email", "nickname", "pw", "010", 0);
insert into user_entity (created_at, updated_at, email, nick_name, password, phone_number, point)
values (now(), now(), "email2", "nickname2", "pw2", "010", 0);

select * from alert_log_entity;

insert into alert_log_entity (created_at, updated_at, content, is_read, task_code, user_seq)
values (now(), now(), '하이루', false, '1234', 1);
insert into alert_log_entity (created_at, updated_at, content, is_read, task_code, user_seq)
values (now(), now(), '하이루22', true, '1234', 1);