-- Project Name : AttendanceManage
-- Date/Time    : 2023/11/13 12:23:35
-- Author       : 81808
-- RDBMS Type   : PostgreSQL
-- Application  : A5:SQL Mk-2

--/*
--  << 注意！！ >>
--  BackupToTempTable, RestoreFromTempTable疑似命令が付加されています。
--  これにより、drop table, create table 後もデータが残ります。
--  この機能は一時的に $$TableName のような一時テーブルを作成します。
--  この機能は A5:SQL Mk-2でのみ有効であることに注意してください。
--*/

-- attendances
-- * RestoreFromTempTable
create table attendances (
  attendance_id serial not null
  , user_id character varying(5) not null
  , begin_time time without time zone
  , end_time time without time zone
  , rest_start time without time zone
  , rest_end time without time zone
  , place character varying(40)
  , date date
  , status smallint
  , constraint attendances_PKC primary key (attendance_id)
) ;

comment on table attendances is 'attendances';
comment on column attendances.attendance_id is 'attendance_id';
comment on column attendances.user_id is 'user_id';
comment on column attendances.begin_time is 'begin_time';
comment on column attendances.end_time is 'end_time';
comment on column attendances.rest_start is 'rest_start';
comment on column attendances.rest_end is 'rest_end';
comment on column attendances.place is 'place';
comment on column attendances.date is 'date';
comment on column attendances.status is 'status';

