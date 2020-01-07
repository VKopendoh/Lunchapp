DELETE
FROM dish;
DELETE
FROM menu;
DELETE
FROM restaurant;
DELETE
FROM user_roles;
DELETE
FROM users;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('ROLE_USER', 100000),
       ('ROLE_ADMIN', 100001);

INSERT INTO menu(create_date)
VALUES (now()),
       (now());


INSERT INTO dish (name, price, menu_id)
VALUES ('pizza', 700, 100002),
       ('cola', 100, 100002),
       ('burger', 150, 100002),
       ('steak', 4000, 100003),
       ('latte', 300, 100003),
       ('Fleur Burger', 70000, 100003);

INSERT INTO restaurant(name, menu_id)
VALUES ('Rostov burgers', 100002),
       ('NY Burgers', 100003);

