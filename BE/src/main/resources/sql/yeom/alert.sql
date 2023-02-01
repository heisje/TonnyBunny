
select * from user_entity;

insert into user_entity 
values (1, now(), now(), "email1", "nickname1", "pw1", "010", 0, "image", 0, "usercode");
insert into user_entity 
values (2, now(), now(), "email2", "nickname2", "pw2", "020", 0, "image2", 0, "usercode2");

select * from alert_log_entity;

insert into alert_log_entity (created_at, updated_at, content, is_read, task_code, user_seq)
values (now(), now(), '하이루', false, '1234', 1);
insert into alert_log_entity (created_at, updated_at, content, is_read, task_code, user_seq)
values (now(), now(), '하이루22', true, '1234', 2);

select * from alert_settings_entity;

insert into alert_settings_entity
values (now(), now(), true, true, true, true, 1);