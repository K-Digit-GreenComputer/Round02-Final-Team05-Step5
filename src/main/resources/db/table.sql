create table product_tb(
    id int auto_increment primary key,
    name varchar not null unique,
    price int not null,
    qty int not null,
    created_at timestamp
);

create table user_tb(
    id int auto_increment primary key,
    username varchar(100) not null unique,
    password varchar not null,
    email varchar(100) not null,
    created_at timestamp
);

create table orders_tb(
    id int primary KEY auto_increment,
    orders_name varchar(20) NOT null,
    orders_price int NOT null,
    orders_qty int NOT null,
    product_id int NOT null,
    user_id int NOT null,
    created_at timestamp
);