INSERT INTO users (name, password)
VALUES ('user', 'pass'),
       ('user2', 'pass'),
       ('admin', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('ROLE_USER', 1),
       ('ROLE_USER', 2),
       ('ROLE_USER', 3),
       ('ROLE_ADMIN', 3);

INSERT INTO menu(create_date)
VALUES (TIMESTAMP '2020-01-10 10:00:00'),
       (TIMESTAMP '2020-01-11 10:00:00');


INSERT INTO dish (name, price, menu_id)
VALUES ('pizza', 700, 1),
       ('cola', 100, 1),
       ('burger', 150, 1),
       ('steak', 4000, 2),
       ('latte', 300, 2),
       ('Fleur Burger', 70000, 2);

INSERT INTO restaurant(name, menu_id)
VALUES ('Rostov burgers', 1),
       ('NY Burgers', 2);