create table UserDB (
  user_id raw(16) default sys_guid() constraint userdb_userid_pk primary key,
  email    varchar2(100) not null unique,
  hashed_password varchar2(64) not null,
  is_admin number(1) not null)

create table user_full_name(
  first_name varchar2(40),
  last_name  varchar2(40)
)
