--liquibase formatted sql

--changeset User:1
drop schema if exists org cascade;
drop schema if exists course cascade;
truncate table public.databasechangelog;
drop sequence if exists hibernate_sequence;
create schema if not exists org;
create schema if not exists course;


--changeset User:2
create table course.basket
(
    basket_id            int8 not null,
    created              timestamp,
    updated              timestamp,
    total_cost           int8,
    customer_customer_id int8,
    primary key (basket_id)
);
create table course.basket_products
(
    basket_id  int8 not null,
    product_id int8 not null
);
create table course.bill
(
    bill_id              int8 not null,
    created              timestamp,
    updated              timestamp,
    sale_date_time       timestamp,
    total_sum            int8,
    customer_customer_id int8,
    primary key (bill_id)
);
create table course.bill_products
(
    bill_bill_id        int8 not null,
    products_product_id int8 not null
);
create table course.catalog
(
    catalog_id   int8 not null,
    created      timestamp,
    updated      timestamp,
    catalog_name varchar(255),
    primary key (catalog_id)
);
create table course.catalog_products
(
    catalog_id int8 not null,
    product_id int8 not null
);
create table course.customer
(
    customer_id      int8 not null,
    created          timestamp,
    updated          timestamp,
    birth_date       date,
    email            varchar(255),
    full_name        varchar(255),
    basket_basket_id int8,
    user_user_id     int8,
    primary key (customer_id)
);
create table course.product
(
    product_id   int8 not null,
    created      timestamp,
    updated      timestamp,
    price        int8,
    product_desc varchar(2000),
    product_name varchar(255),
    primary key (product_id)
);
create table org.user
(
    user_id              int8         not null,
    created              timestamp,
    updated              timestamp,
    email                varchar(255) not null unique,
    login                varchar(255) not null unique,
    password             varchar(255),
    customer_customer_id int8,
    primary key (user_id)
);
create sequence hibernate_sequence start 1 increment 1;
alter table if exists course.basket
    add foreign key (customer_customer_id) references course.customer;
alter table if exists course.basket_products
    add foreign key (product_id) references course.product;
alter table if exists course.basket_products
    add foreign key (basket_id) references course.basket;
alter table if exists course.bill
    add foreign key (customer_customer_id) references course.customer;
alter table if exists course.bill_products
    add foreign key (products_product_id) references course.product;
alter table if exists course.bill_products
    add foreign key (bill_bill_id) references course.bill;
alter table if exists course.catalog_products
    add foreign key (product_id) references course.product;
alter table if exists course.catalog_products
    add foreign key (catalog_id) references course.catalog;
alter table if exists course.customer
    add foreign key (basket_basket_id) references course.basket;
alter table if exists course.customer
    add foreign key (user_user_id) references org.user;
alter table if exists org.user
    add foreign key (customer_customer_id) references course.customer;

--changeset User:4
create table org.roles
(
    role_id   int8 not null primary key,
    created   timestamp,
    updated   timestamp,
    role      varchar(255),
    role_desc varchar(255)
);
create table org.authorities
(
    authority_id   int8 not null primary key,
    created        timestamp,
    updated        timestamp,
    authority      varchar(255),
    authority_desc varchar(255)
);
create table org.role_authorities
(
    role_id      int8 not null,
    authority_id int8 not null
);
create table org.user_roles
(
    role_id int8 not null,
    user_id int8 not null
);

alter table if exists org.role_authorities
    add foreign key (role_id) references org.roles;
alter table if exists org.role_authorities
    add foreign key (authority_id) references org.authorities;
alter table if exists org.user_roles
    add foreign key (role_id) references org.roles;
alter table if exists org.user_roles
    add foreign key (user_id) references org.user;