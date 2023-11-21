-- Project Name : AttendanceManage
-- Date/Time    : 2023/11/13 12:23:06
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

-- users
-- * RestoreFromTempTable
-- テーブル作成
create table users (
  id serial not null
  , user_id character varying(5) not null
  , name character varying(40)
  , email character varying(256)
  , password character varying(256)
  , tel character varying(256)
  , remarks text
  , role character varying(255)
  , primary key (id)
) ;

comment on table users is 'users';
comment on column users.id is 'id';
comment on column users.user_id is 'user_id';
comment on column users.name is 'name';
comment on column users.email is 'email';
comment on column users.password is 'password';
comment on column users.tel is 'tel';
comment on column users.remarks is 'remarks';
comment on column users.role is 'role:USER:一般 ADMIN:管理者';

