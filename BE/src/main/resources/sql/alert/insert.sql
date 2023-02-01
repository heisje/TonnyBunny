
select * from user_entity;

insert into user_entity 
values (1, now(), now(), "email1", "nickname1", "pw1", "010", 0, "image", 0, "usercode");
insert into user_entity 
values (2, now(), now(), "email2", "nickname2", "pw2", "020", 0, "image2", 0, "usercode2");

select * from alert_log_entity;

insert into alert_log_entity
values (null, now(), now(), 'content1', false, false, 'taskcode1', 1);
insert into alert_log_entity
values (null, now(), now(),'content2', true, false, 'taskcode2', 2);
insert into alert_log_entity
values (null, now(), now(), 'content3', false, true, 'taskcode3', 1);
insert into alert_log_entity
values (null, now(), now(), 'content4', true, true, 'taskcode4', 2);

select * from alert_settings_entity;

insert into alert_settings_entity 
values (null, now(), now(), true, true, true, true, 1);

insert into alert_settings_entity 
values (null, now(), now(), true, false, false, true, 2);