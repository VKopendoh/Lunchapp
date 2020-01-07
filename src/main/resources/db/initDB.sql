DROP TABLE IF EXISTS dish;
DROP TABLE IF EXISTS menu;
DROP TABLE IF EXISTS restaurant;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000 INCREMENT BY 1;

create table dish
(
    id      integer      not null,
    name    varchar(100) not null,
    price   integer      not null check (price <= 9223372036854775807 AND price >= 0),
    menu_id integer,
    primary key (id)
);

create table menu
(
    id          integer                 not null,
    create_date timestamp default now() not null,
    primary key (id)
);

create table restaurant
(
    id      integer      not null,
    name    varchar(100) not null,
    menu_id integer,
    primary key (id)
);

create table user_roles
(
    user_id integer not null,
    role    varchar(255)
);

create table users
(
    id            integer                 not null,
    name          varchar(100)            not null,
    enabled       bool      default true  not null,
    password      varchar(100)            not null,
    registered    timestamp default now() not null,
    restaurant_id integer,
    primary key (id)
);

alter table dish
    add constraint FKljuksxg35var0r9a3y09l148h foreign key (menu_id) references menu;

alter table restaurant
    add constraint FKooiid4751i6wv4ebwbi6gjfr3 foreign key (menu_id) references menu;

alter table user_roles
    add constraint FKhfh9dx7w3ubf1co1vdev94g3f foreign key (user_id) references users;

alter table users
    add constraint FKcn7awwo916vg0my6knhmkn03j foreign key (restaurant_id) references restaurant;