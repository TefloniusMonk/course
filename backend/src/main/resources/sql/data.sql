--liquibase formatted sql

--changeset User:1
BEGIN TRANSACTION;
insert into org."user"(user_id, created, updated, email, login, password, customer_customer_id)
VALUES (1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'email@yandex.ru', 'admin',
        '7a37b85c8918eac19a9089c0fa5a2ab4dce3f90528dcdeec108b23ddf3607b99', null);

insert into course.customer(customer_id, created, updated, birth_date, email, full_name, basket_basket_id, user_user_id)
VALUES (1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'email@yandex.ru', 'Петров Алексей Федорович', null,
        null);

insert into course.basket(basket_id, created, updated, total_cost, customer_customer_id)
VALUES (1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0, null);
END TRANSACTION;

BEGIN TRANSACTION;
update course.customer
set basket_basket_id = 1,
    user_user_id     =1
where customer_id = 1;
update org."user"
set customer_customer_id = 1
where user_id = 1;
update course.basket
set customer_customer_id = 1
where basket_id = 1;

insert into course.product(product_id, created, updated, price, product_desc, product_name)
VALUES (1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 100,
        'Шоколад «Алёнка» — молочный шоколад, изготавливавшийся в СССР с 1965 года, а позднее производимый в России, в том числе на кондитерской фабрике «Красный Октябрь» (с 1966 года). Отличительной особенностью является кремовый, «жирный» вкус.',
        'Шоколад Аленка');

insert into course.product(product_id, created, updated, price, product_desc, product_name)
VALUES (2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 34,
        '«Беломоркана́л» (сокр. «Беломор» или «БК») — самые массовые папиросы эпохи СССР. Приобрели популярность главным образом из-за низкой цены. Названы в честь Беломорско-Балтийского канала. Простая картонная пачка размером 82 × 83 × 23 мм содержит 25 папирос 5-го класса. Папиросы имеют очень высокое содержание смол. Каждая папироса содержит 1,5—1,7 мг никотина и 27—32 мг смолы.',
        'Папиросы Беломорканал');

insert into course.product(product_id, created, updated, price, product_desc, product_name)
VALUES (3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 58,
        'Ирис Кис-кис 250г - это популярная конфета-тянучка, запущенная в производство еще в 20-х годах прошлого века и вошедшая в Золотой фонд легендарной московской кондитерской фабрики "Красный Октябрь". Производится на основе сгущенного с сахаром молока и патоки.',
        'Зубодробительные ирис "Кис-Кис"');

insert into course.product(product_id, created, updated, price, product_desc, product_name)
VALUES (4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 200,
        'Turbo (Турбо) — жевательная резинка, первые выпуски (предположительно до 1985 г.) с ароматом персика, последующие выпуски были с фруктовым вкусом и ароматом — производимая турецкой компанией «Kent Gida», содержащая вкладыши с фотографиями, как правило, автомобилей или мотоциклов',
        'Жвачка Турбо');

insert into course.product(product_id, created, updated, price, product_desc, product_name)
VALUES (5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2000,
        'Ва́ленки — тёплые войлочные сапоги из свалянной овечьей шерсти; чаще делаются твёрдыми, но бывают и мягкими, под другую обувь. Валенки (пимы) — традиционная обувь народов Евразии, которая используется для ходьбы по сухому снегу.',
        'Валенки(Спортивные)');

insert into course.product(product_id, created, updated, price, product_desc, product_name)
VALUES (6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1500,
        'Удобные и прочные треники, отлично подойдут как для посиделой с братишками, так и на вечерней дискотеке, а так же, окажутся незаменимыми в случае различных рамсов',
        'Треники Adidas(Парадные)');

insert into course.catalog(catalog_id, created, updated, catalog_name)
VALUES (1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Продукты');

insert into course.catalog(catalog_id, created, updated, catalog_name)
VALUES (2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Все для тела и души');
END TRANSACTION;

BEGIN TRANSACTION;
insert into course.catalog_products(catalog_id, product_id)
VALUES (1, 1);
insert into course.catalog_products(catalog_id, product_id)
VALUES (1, 2);
insert into course.catalog_products(catalog_id, product_id)
VALUES (1, 3);
insert into course.catalog_products(catalog_id, product_id)
VALUES (1, 4);
insert into course.catalog_products(catalog_id, product_id)
VALUES (2, 5);
insert into course.catalog_products(catalog_id, product_id)
VALUES (2, 6);

insert into course.basket_products(product_id, basket_id)
VALUES (1, 1);
insert into course.basket_products(product_id, basket_id)
VALUES (3, 1);
insert into course.basket_products(product_id, basket_id)
VALUES (6, 1);

insert into course.bill(bill_id, created, updated, sale_date_time, total_sum, customer_customer_id)
VALUES (1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3500, 1);

insert into course.customer_bills(customer_id, bill_id)
values (1, 1);

insert into course.bill_products(bill_bill_id, products_product_id)
VALUES (1, 5);
insert into course.bill_products(bill_bill_id, products_product_id)
VALUES (1, 6);
END TRANSACTION;

--changeset User:6
insert into org.user_roles(role_id, user_id)
VALUES (4, 1);
