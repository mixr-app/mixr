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
   name varchar(100) unique,
   description varchar(500),
   type varchar(255),

   created_on datetime not null,
   updated_on datetime,
   created_by varchar(32) not null,
   updated_by varchar(32),
   version bigint not null default 0,
   PRIMARY KEY( id ),
   foreign key( created_by ) references users( username ),
   foreign key( updated_by ) references users( username )
);

insert into ingredients (name, description, type, created_on, created_by) values ("Rum (clear)", "Rum that's clear", 'LIQUOR', now(), "joe");
insert into ingredients (name, description, type, created_on, created_by) values ("Vodka", "Vodka is pretty interchangable", 'LIQUOR', now(), "joe");
insert into ingredients (name, description, type, created_on, created_by) values ("Tequila", "Need to break this into different types", 'LIQUOR', now(), "joe");
insert into ingredients (name, description, type, created_on, created_by) values ("Gin", "Tastes like Christmas", 'LIQUOR', now(), "joe");
insert into ingredients (name, description, type, created_on, created_by) values ("Triple Sec", "An orange-flavored liqueur", 'LIQUOR', now(), "joe");


insert into ingredients (name, description, type, created_on, created_by) values ("Cola", "Any brand will do.  Choose diet for a lower calorie drink", 'MIXER', now(), "joe");
insert into ingredients (name, description, type, created_on, created_by) values ("Lemon Juice", "Fresh-squeezed is much better but a lot of work", 'MIXER', now(), "joe");
insert into ingredients (name, description, type, created_on, created_by) values ("Simple Syrup", "You can make this by desolving sugar into hot water on the stovetop in a 1:1 ratio", 'MIXER', now(), "joe");

select * from ingredients;

CREATE TABLE sources(
   id serial,
   name varchar(100) unique,
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

insert into sources (id, name, description, created_on, created_by) values (1, "IBA Official Cocktail", "The International Bartender's Association recognizes these cocktails and uses them to judge some competition or something.", now(), "joe");

CREATE TABLE recipes(
   id serial,
   name varchar(100) unique,
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

insert into recipes (name, description, instructions, source, created_on, created_by) values (
	"Rum and Coke",
    "One of the easiest things to make.",
    "Mix the rum and the coke, serve on ice",
    1,
    now(),
    "joe");

insert into recipes (name, description, instructions, source, created_on, created_by) values (
	"What I Drank in College",
    "Cheers!",
    "It's just vodka, serve chilled if feeling adventerous.",
    1,
    now(),
    "joe");

insert into recipes (name, description, instructions, source, created_on, created_by) values (
	"White Lady",
    "Strong and refreshing",
    "Mix the rum and the coke, serve on ice",
    1,
    now(),
    "joe");

select * from recipes;

CREATE TABLE recipe_ingredients(
   id serial,
   recipe_id bigint unsigned,
   ingredient_id bigint unsigned,
   amount float,
   unit varchar(256),

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

insert into recipe_ingredients (recipe_id, ingredient_id, amount, unit, created_on, created_by) values (
	1,
    1,
    1.5,
    "oz",
    now(),
    "joe");

insert into recipe_ingredients (recipe_id, ingredient_id, amount, unit, created_on, created_by) values (
	1,
    6,
    4.5,
    "oz",
    now(),
    "joe");

insert into recipe_ingredients (recipe_id, ingredient_id, amount, unit, created_on, created_by) values (
	2,
    2,
    8,
    "oz",
    now(),
    "joe");

insert into recipe_ingredients (recipe_id, ingredient_id, amount, unit, created_on, created_by) values (
	3,
    4,
    4,
    "cl",
    now(),
    "joe");

insert into recipe_ingredients (recipe_id, ingredient_id, amount, unit, created_on, created_by) values (
	3,
    5,
    3,
    "cl",
    now(),
    "joe");

insert into recipe_ingredients (recipe_id, ingredient_id, amount, unit, created_on, created_by) values (
	3,
    7,
    2,
    "cl",
    now(),
    "joe");

select * from recipe_ingredients;


CREATE TABLE recipe_ratings(
   id serial,
   recipe_id bigint unsigned,
   rating varchar(255),
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
