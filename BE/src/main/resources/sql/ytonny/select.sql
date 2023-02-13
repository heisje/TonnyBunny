use tonny_bunny;

select * from user_table;

-- YTonny
select * from ytonny_history_table;
select * from ytonny_table;
select * from ytonny_apply_table;
select * from ytonny_quotation_table;
select * from ytonny_quotation_image_table;

select * from common_code_table;
select * from common_group_code_table;

select * from alert_log_table;
select * from history_table;
select * from schedule_table;

select scheduleen0_.schedule_seq as schedule1_27_, scheduleen0_.created_at as created_2_27_, scheduleen0_.updated_at as updated_3_27_, scheduleen0_.content as content4_27_, scheduleen0_.end_date_time as end_date5_27_, scheduleen0_.is_complete as is_compl6_27_, scheduleen0_.start_date_time as start_da7_27_, scheduleen0_.task_code as task_cod8_27_, scheduleen0_.title as title9_27_, scheduleen0_.user_seq as user_se10_27_ from schedule_table scheduleen0_ left outer join user_table userentity1_ on scheduleen0_.user_seq=userentity1_.user_seq where userentity1_.user_seq=8 and (scheduleen0_.start_date_time between '2023-02-13T00:00:00.000+0900' and '2023-02-13T00:00:00.000+0900');

select * from schedule_table where user_seq = 8;
select * from schedule_table where start_date_time between '2023-02-13' and '2023-02-13 23:00';
select * from schedule_table where start_date_time between '2023-02-13' and '2023-02-13 23:00';