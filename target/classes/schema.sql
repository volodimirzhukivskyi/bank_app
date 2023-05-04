DROP TABLE IF EXISTS CUSTOMERS;

CREATE TABLE CUSTOMERS
(
    id              INT AUTO_INCREMENT PRIMARY KEY,
    name            VARCHAR(50) NOT NULL,
    email           VARCHAR(50) DEFAULT NULL,
    age             INT         NOT NULL,
    phone_number    INT         DEFAULT NULL,
    created_date    VARCHAR(50) DEFAULT NULL,
    last_modified_date  VARCHAR(50) DEFAULT NULL
);
DROP TABLE IF EXISTS ACCOUNTS;
CREATE TABLE ACCOUNTS
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    number      VARCHAR(50)     ,
    currency    VARCHAR(50)      NOT NULL,
    balance     double precision NOT NULL DEFAULT 0.0,
    customer_id INT,
    created_date    VARCHAR(50) DEFAULT NULL,
    last_modified_date  VARCHAR(50) DEFAULT NULL

);
DROP TABLE IF EXISTS TBL_EMPLOYERS;
CREATE TABLE TBL_EMPLOYERS
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(250) NOT NULL,
    address     VARCHAR(250) NOT NULL,
    created_date    VARCHAR(50) DEFAULT NULL,
    last_modified_date  VARCHAR(50) DEFAULT NULL
);
DROP TABLE IF EXISTS EMPLOYER_STAFF;
create table EMPLOYER_STAFF
(
    tbl_emloyers_id int,
    customer_id     int,
    foreign key (customer_id) references TBL_EMPLOYERS (id),
    foreign key (tbl_emloyers_id) references CUSTOMERS (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);
ALTER TABLE ACCOUNTS
    ADD FOREIGN KEY (customer_id) REFERENCES CUSTOMERS (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE;