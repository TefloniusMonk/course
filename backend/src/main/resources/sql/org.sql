--liquibase formatted sql

--changeset User:5
insert into org.roles(role_id, role, role_desc)
VALUES (1, 'ADMIN', 'Администратор сайта');
insert into org.roles(role_id, role, role_desc)
VALUES (2, 'CUSTOMER', 'Покупатель');
insert into org.roles(role_id, role, role_desc)
VALUES (3, 'ANONYMOUS', 'Незарегестрированный пользователь');
insert into org.roles(role_id, role, role_desc)
VALUES (4, 'ALLMIGHTY', 'ВСЕМОГУЩИЙ');

insert into org.authorities(authority_id, authority, authority_desc)
values (1, 'CATALOG_WRITE', null);
insert into org.authorities(authority_id, authority, authority_desc)
values (2, 'CATALOG_VIEW', null);
insert into org.authorities(authority_id, authority, authority_desc)
values (3, 'BILL_VIEW', null);
insert into org.authorities(authority_id, authority, authority_desc)
values (4, 'PROFILE_VIEW', null);
insert into org.authorities(authority_id, authority, authority_desc)
values (5, 'PROFILE_WRITE', null);
insert into org.authorities(authority_id, authority, authority_desc)
values (6, 'PRODUCT_WRITE', null);
insert into org.authorities(authority_id, authority, authority_desc)
values (7, 'PRODUCT_VIEW', null);
insert into org.authorities(authority_id, authority, authority_desc)
values (8, 'BASKET_VIEW', null);
insert into org.authorities(authority_id, authority, authority_desc)
values (9, 'BASKET_WRITE', null);
insert into org.authorities(authority_id, authority, authority_desc)
values (10, 'UNAUTHORIZED', null);

insert into org.role_authorities(role_id, authority_id)
VALUES (1, 1);
insert into org.role_authorities(role_id, authority_id)
VALUES (1, 2);
insert into org.role_authorities(role_id, authority_id)
VALUES (1, 6);
insert into org.role_authorities(role_id, authority_id)
VALUES (1, 7);

insert into org.role_authorities(role_id, authority_id)
VALUES (2, 2);
insert into org.role_authorities(role_id, authority_id)
VALUES (2, 3);
insert into org.role_authorities(role_id, authority_id)
VALUES (2, 4);
insert into org.role_authorities(role_id, authority_id)
VALUES (2, 5);
insert into org.role_authorities(role_id, authority_id)
VALUES (2, 7);
insert into org.role_authorities(role_id, authority_id)
VALUES (2, 8);
insert into org.role_authorities(role_id, authority_id)
VALUES (2, 9);

insert into org.role_authorities(role_id, authority_id)
VALUES (3, 10);

insert into org.role_authorities(role_id, authority_id)
VALUES (4, 1);
insert into org.role_authorities(role_id, authority_id)
VALUES (4, 2);
insert into org.role_authorities(role_id, authority_id)
VALUES (4, 3);
insert into org.role_authorities(role_id, authority_id)
VALUES (4, 4);
insert into org.role_authorities(role_id, authority_id)
VALUES (4, 5);
insert into org.role_authorities(role_id, authority_id)
VALUES (4, 6);
insert into org.role_authorities(role_id, authority_id)
VALUES (4, 7);
insert into org.role_authorities(role_id, authority_id)
VALUES (4, 8);
insert into org.role_authorities(role_id, authority_id)
VALUES (4, 9);