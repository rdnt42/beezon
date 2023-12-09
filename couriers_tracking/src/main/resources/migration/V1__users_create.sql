create table if not exists users
(
    username text not null unique,
    password text not null,
);

create index on users (username, password);