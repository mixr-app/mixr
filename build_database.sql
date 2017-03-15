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

CREATE TABLE ingredients(
   id serial,
   name varchar(100),
   description varchar(500),
   type enum('liquor', 'mixer', 'other', 'tool'),
   
   created_on datetime not null,
   updated_on datetime,
   created_by varchar(32) not null,
   updated_by varchar(32),
   version bigint not null default 0,
   PRIMARY KEY( id ),
   foreign key( created_by ) references users( username ),
   foreign key( updated_by ) references users( username )
);

insert into ingredients (name, description, created_on, created_by) values ("null test", "inserted directly into db", now(), "joe");
select * from ingredients;

CREATE TABLE sources(
   id serial,
   name varchar(100),
   description varchar(500),
   
   created_on datetime not null,
   updated_on datetime,
   created_by varchar(32) not null,
   updated_by varchar(32),
   version bigint not null default 0,
   PRIMARY KEY( id ),
   foreign key( created_by ) references users( username ),
   foreign key( updated_by ) references users( username )
);

CREATE TABLE recipes(
   id serial,
   name varchar(100),
   description varchar(500),
   instructions varchar(500),
   image_location varchar(100),
   source bigint unsigned,
   
   created_on datetime not null,
   updated_on datetime,
   created_by varchar(32) not null,
   updated_by varchar(32),
   version bigint not null default 0,
   PRIMARY KEY( id ),
   foreign key( source ) references sources( id ),
   foreign key( created_by ) references users( username ),
   foreign key( updated_by ) references users( username )
);

CREATE TABLE recipe_ingredients(
   id serial,
   recipe_id bigint unsigned,
   ingredient_id bigint unsigned,
   amount float,
   unit enum('oz', 'mL', 'tsp', 'dash', 'other'),
   unit_other varchar(32),
   
   created_on datetime not null,
   updated_on datetime,
   created_by varchar(32) not null,
   updated_by varchar(32),
   version bigint not null default 0,
   PRIMARY KEY( id ),
   foreign key( recipe_id ) references recipes( id ),
   foreign key( ingredient_id ) references ingredients( id ),
   foreign key( created_by ) references users( username ),
   foreign key( updated_by ) references users( username )
);


CREATE TABLE recipe_ratings(
   id serial,
   recipe_id bigint unsigned,
   rating enum('never drink it again', 'good enough if it\'s free', 'pretty good, no complaints', 'my go-to drink', 'better than sex'),
   comment varchar(500),
   
   created_on datetime not null,
   updated_on datetime,
   created_by varchar(32) not null,
   updated_by varchar(32),
   version bigint not null default 0,
   PRIMARY KEY( id ),
   foreign key( recipe_id ) references recipes( id ),
   foreign key( created_by ) references users( username ),
   foreign key( updated_by ) references users( username )
);

CREATE TABLE pantry(
   id serial,
   ingredient_id bigint unsigned,
   
   created_on datetime not null,
   updated_on datetime,
   created_by varchar(32) not null,
   updated_by varchar(32),
   version bigint not null default 0,
   PRIMARY KEY( id ),
   foreign key( ingredient_id ) references ingredients( id ),
   foreign key( created_by ) references users( username ),
   foreign key( updated_by ) references users( username )
);