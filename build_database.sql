-- I'll let Max set up flyway for us, it's far too complicated for me.  Until then, we'll just run this fancy sql script to keep our database up-to-date!

drop database if exists mixr;
create database mixr;

use mixr;


create table users(
	username varchar(32),
	password char(60) not null,
    email varchar(32),
	enabled boolean not null,
	admin boolean default false,
	created_on datetime not null,
	updated_on datetime,
	version bigint not null default 0,
	primary key(username)
);

insert into users (username, password, admin, enabled, created_on) values ("joe", "$2a$06$r1jxFWhkm8XoFwiQomqATuOjWsXYSNCJkbusiKgOOdTmaWaBeM5qK", true, true, now());
insert into users (username, password, admin, enabled, created_on) values ("cameron", "$2a$06$r1jxFWhkm8XoFwiQomqATuOjWsXYSNCJkbusiKgOOdTmaWaBeM5qK", true, true, now());
insert into users (username, password, admin, enabled, created_on) values ("max", "$2a$06$r1jxFWhkm8XoFwiQomqATuOjWsXYSNCJkbusiKgOOdTmaWaBeM5qK", true, true, now());
insert into users (username, password, admin, enabled, created_on) values ("welby", "$2a$06$r1jxFWhkm8XoFwiQomqATuOjWsXYSNCJkbusiKgOOdTmaWaBeM5qK", true, true, now());
insert into users (username, password, admin, enabled, created_on) values ("user", "$2a$06$r1jxFWhkm8XoFwiQomqATuOjWsXYSNCJkbusiKgOOdTmaWaBeM5qK", false, true, now());
select * from users;