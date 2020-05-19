create database if not exists bookLib;

use bookLib;

create table if not exists user
(
    `userName` varchar(10)  not null primary key,
    `userPwd`  varchar(10)  not null,
    `sex`      char(2),
    `birthday` date         not null,
    `mail`     varchar(30),
    `work`     varchar(20),
    `like`     varchar(50),
    `intro`    varchar(200) not null
);

insert into user (userName, userPwd, sex, birthday, mail, work, `like`, intro)
values ('123',
        '123',
        '0',
        now(),
        '123@qq.com',
        'work',
        'like',
        '123123');
