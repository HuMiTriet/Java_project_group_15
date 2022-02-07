 drop table userdb;
 
/*
Database schema for the remote database that stores all of the user's information and
data
Author: Triet Huynh
*/
 create table UserDB (
    email    varchar2(100) not null unique,
    username varchar2(100) not null,
    hashed_password varchar2(64) not null,
    user_id raw(16) default sys_guid() constraint userdb_userid_pk primary key,
    is_admin number(1) not null,
    -- This BLOB variable is used to store the local data
    local_data BLOB 
)
