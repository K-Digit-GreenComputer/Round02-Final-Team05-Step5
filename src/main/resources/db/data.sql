insert into product_tb(name, price, qty, created_at) values('바나나', 1000, 50, now());
insert into product_tb(name, price, qty, created_at) values('사과', 2000, 100, now());
insert into product_tb(name, price, qty, created_at) values('딸기', 3000, 150, now());

INSERT INTO user_tb(username, password, email, created_at) VALUES ('ssar', '1234', 'ssar@nate.com', NOW());
INSERT INTO user_tb(username, password, email, created_at) VALUES ('cos', '1234', 'cos@nate.com', NOW());

INSERT INTO orders_tb(orders_name, orders_price, orders_qty, product_id, user_id, created_at) VALUES ('바나나', 1000, 10, 1, 1, NOW());
INSERT INTO orders_tb(orders_name, orders_price, orders_qty, product_id, user_id, created_at)  VALUES ('사과', 2000, 100, 2, 2, NOW());
INSERT INTO orders_tb(orders_name, orders_price, orders_qty, product_id, user_id, created_at)  VALUES ('딸기', 3000, 150, 3, 2, NOW());

commit;