DROP TABLE IF EXISTS CUSTOMERS cascade;

CREATE TABLE CUSTOMERS
(
    id              SERIAL PRIMARY KEY,
    name            VARCHAR(50) NOT NULL,
    email           VARCHAR(50) DEFAULT NULL,
    age             INT         NOT NULL,
    phone_number    INT         DEFAULT NULL,
    created_date    VARCHAR(50) DEFAULT NULL,
    last_modified_date  VARCHAR(50) DEFAULT NULL
);
DROP TABLE IF EXISTS ACCOUNTS cascade;
CREATE TABLE ACCOUNTS
(
    id          SERIAL PRIMARY KEY,
    number      VARCHAR(50)     ,
    currency    VARCHAR(50)      NOT NULL,
    balance     double precision NOT NULL DEFAULT 0.0,
    customer_id INT,
    created_date    VARCHAR(50) DEFAULT NULL,
    last_modified_date  VARCHAR(50) DEFAULT NULL

);
DROP TABLE IF EXISTS TBL_EMPLOYERS cascade;
CREATE TABLE TBL_EMPLOYERS
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(250) NOT NULL,
    address     VARCHAR(250) NOT NULL,
    created_date    VARCHAR(50) DEFAULT NULL,
    last_modified_date  VARCHAR(50) DEFAULT NULL
);
DROP TABLE IF EXISTS EMPLOYER_STAFF cascade;
create table EMPLOYER_STAFF
(
    tbl_emloyers_id int,
    customer_id     int

);
ALTER TABLE ACCOUNTS
    ADD FOREIGN KEY (customer_id) REFERENCES CUSTOMERS (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE;
ALTER TABLE EMPLOYER_STAFF
    ADD FOREIGN KEY (customer_id) REFERENCES CUSTOMERS (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE;