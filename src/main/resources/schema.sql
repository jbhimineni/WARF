CREATE SCHEMA IF NOT EXISTS ADDRESS_BOOK;
-- create table for block listed IP's
CREATE TABLE IF NOT EXISTS ADDRESS_BOOK.BLOCK_LIST (
    ID INT AUTO_INCREMENT,
    HOST_IP VARCHAR(15) NOT NULL,
    UPDATED_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);


CREATE SCHEMA IF NOT EXISTS EMPLOYEE;
-- create table for block listed IP's
CREATE TABLE IF NOT EXISTS EMPLOYEE.EMP_DATA (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    EMP_NAME_FIRST CHAR(25),
    EMP_NAME_LAST CHAR(25),
    EMP_USER_NAME CHAR(15),
    EMP_BASE_LOCATION CHAR(20)
    );