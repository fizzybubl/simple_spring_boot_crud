DROP TABLE employee IF EXISTS;
DROP TABLE company IF EXISTS;

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
    CONSTRAINT employee_company_fk FOREIGN KEY(company_id) REFERENCES company ON DELETE CASCADE
);