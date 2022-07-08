create table "users"
(
    "username" varchar(255)                 not null,
    "password" varchar(255)                 not null,
    "status"   varchar(15) default "ACTIVE" not null,
    unique ("username"),
    primary key ("username")
);

create table "animals"
(
    "name"     varchar(255) not null,
    "birthday" varchar(30),
    "sex"      varchar(10),
    "type"     varchar(150),
    unique ("name"),
    primary key ("name")
);

create table "roles"
(
    "id"   uuid,
    "name" varchar(50) not null,
    unique ("name"),
    primary key ("id")
);

insert into "roles" ("id", "name")
values ('1871dbe4-ffda-36ba-92db-a845efaa6fa7', 'ROLE_USER'),
       ('73acd9a5-9721-30b7-9066-c82595a1fae3', 'ROLE_ADMIN');
