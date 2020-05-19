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

create table if not exists book
(
    `bookNo`    varchar(10) not null primary key,
    `bookCover` varchar(50),
    `bookName`  varchar(50),
    `nowPrice`  float,
    `orgPrice`  float,
    `comment`   int,
    `author`    varchar(50),
    `press`     varchar(50),
    `pressDate` date,
    `bookNum`   int         not null
);

INSERT INTO bookLib.book
(bookNo, bookCover, bookName, nowPrice, orgPrice,
 comment, author, press, pressDate, bookNum)
VALUES ('1001', 'book01.jpg', '零基础设计',
        60, 52, 99, '李凯',
        '人民邮电', '2020-05-23', 0);

INSERT INTO bookLib.book
(bookNo, bookCover, bookName, nowPrice, orgPrice,
 comment, author, press, pressDate, bookNum)
VALUES ('1002', 'book02.jpg', 'JSP&Servlet学习笔记',
        59, 40, 23, '林信',
        '清华大学', '2020-03-10', 0);

INSERT INTO bookLib.book
(bookNo, bookCover, bookName, nowPrice, orgPrice,
 comment, author, press, pressDate, bookNum)
VALUES ('1003', 'book03.jpg', '零基础设计',
        90, 78, 2, 'Nicholas',
        '南京大学', '2020-02-23', 0);

INSERT INTO bookLib.book
(bookNo, bookCover, bookName, nowPrice, orgPrice,
 comment, author, press, pressDate, bookNum)
VALUES ('1004', 'book04.jpg', '零基础设计',
        99, 89, 45, 'Len',
        '重庆大学', '2020-04-23', 0);

create table if not exists `order`
(
    `orderId`    varchar(16) not null primary key,
    `userId`     varchar(10),
    `orderTime`  datetime,
    `totalPrice` float
);

create table if not exists orderDetail
(
    `orderId`  varchar(16) not null,
    `bookNo`   varchar(10) not null references book (bookNo),
    `nowPrice` float,
    `buyNum`   int
);

create trigger orderBookTri
    before insert
    on orderDetail
    for each row
begin
    update book
    set bookNum = bookNum - buyNum
    where bookNo = bookNo;
end;
