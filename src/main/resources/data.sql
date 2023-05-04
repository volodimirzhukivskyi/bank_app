INSERT INTO TBL_EMPLOYERS (name, address)
VALUES ('GOOGLE', 'Kyiv'),
       ('McDonalds', 'Kharkiv'),
       ('KFC', 'Kramatorsk');
INSERT INTO CUSTOMERS (name, email, age)
VALUES ('Vasya', 'vasya@gmail.com', 23),
       ('Vova', 'vova@gmail.com', 25),
       ('Valentin', 'valentin@gmail.com', 19);
INSERT INTO ACCOUNTS (number, currency, balance, customer_id)
VALUES ('2222-3333-4444', 'CHF', 5.0, 1),

       ('2223-3334-4455', 'EUR', 10.2, 2);
INSERT INTO EMPLOYER_STAFF (tbl_emloyers_id,customer_id)
VALUES (1,2),(1,3),(2,2);