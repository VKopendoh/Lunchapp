INSERT INTO users (id, name, password)
VALUES (100000, 'user', 'pass'),
       (100001, 'user2', 'pass'),
       (100002, 'admin', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('ROLE_USER', 100000),
       ('ROLE_USER', 100001),
       ('ROLE_ADMIN', 100002);

INSERT INTO menu(id, create_date)
VALUES (100003, now()),
       (100004, now());


INSERT INTO dish (id, name, price, menu_id)
VALUES (100005, 'pizza', 700, 100003),
       (100006,'cola', 100, 100003),
       (100007,'burger', 150, 100003),
       (100008,'steak', 4000, 100004),
       (100009,'latte', 300, 100004),
       (100010,'Fleur Burger', 70000, 100004);

INSERT INTO restaurant(id, name, menu_id)
VALUES (100011,'Rostov burgers', 100003),
       (100012,'NY Burgers', 100004);