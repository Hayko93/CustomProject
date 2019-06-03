create database users_chat;

use users_chat;

create table user_client
(
    id        int primary key not null unique auto_increment,
    name      varchar(255)    not null,
    surname   varchar(255)    not null,
    email     varchar(255)    not null unique ,
    password  varchar(255)    not null,
    country   varchar(255)    not null,
    image_url varchar(255)    not null
);