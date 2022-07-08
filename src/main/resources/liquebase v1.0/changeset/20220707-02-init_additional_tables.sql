create table "user_role"
(
    "user_name" varchar(255),
    "role_id"   uuid,
    primary key ("user_name", "role_id"),
    foreign key ("user_name") references "users" ("username"),
    foreign key ("role_id") references "roles" ("id")
);

create table "owned_animals"
(
    "username" varchar(255) not null,
    "name"     varchar(255),
    primary key ("username", "name"),
    foreign key ("username") references "users" ("username"),
    foreign key ("name") references "animals" ("name")
);