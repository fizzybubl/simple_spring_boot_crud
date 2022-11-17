DROP DATABASE IF EXISTS api_db;
CREATE DATABASE api_db;

USE api_db;

CREATE TABLE company (
    company_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    street VARCHAR(100) NOT NULL,
    postcode VARCHAR(50) NOT NULL,
    city VARCHAR(50) NOT NULL,
    country VARCHAR(50) NOT NULL
);

CREATE TABLE employee (
    employee_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    company_id INT,
    CONSTRAINT employee_company_fk FOREIGN KEY(company_id) REFERENCES company(company_id) ON DELETE CASCADE
);