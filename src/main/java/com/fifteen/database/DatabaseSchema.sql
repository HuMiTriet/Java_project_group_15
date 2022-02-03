 drop table userdb;
 
 create table UserDB (
    email    varchar2(100) not null unique,
    username varchar2(100) not null,
    hashed_password varchar2(64) not null,
    user_id raw(16) default sys_guid() constraint userdb_userid_pk primary key,
    is_admin number(1) not null,
    local_data BLOB 
)
