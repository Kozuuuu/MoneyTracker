CREATE DATABASE MoneyTracker;
CREATE TABLE financial_records (
    payroll BIGINT AUTO_INCREMENT PRIMARY KEY,
    cash DECIMAL(10, 2),
    date DATE,
    expenses DECIMAL(10, 2),
    monthly_payments DECIMAL(10, 2),
    savings DECIMAL(10, 2),
    extra DECIMAL(10, 2),
    days_until_next_payroll INT,
    next_payroll_date DATE,
    period VARCHAR(255),
    INDEX (date)
);
INSERT INTO financial_records (cash, date, monthly_payments, expenses, savings, extra, period)
VALUES 
    (9228.10, '2024-07-31', 0, 9228.10, 0, 0, 'July 15-31, 2024'),
    (17130.95, '2024-08-15', 0, 17130.95, 0, 0, 'August 1-15, 2024'),
    (13407.44, '2024-08-30', 0, 13407.44, 0, 0, 'August 16-30, 2024'),
    (15000.00, '2024-09-13', 5000.00, 7500.00, 5000.00, 2500, 'September 1-15, 2024');



===================================== FOR expenses_savings TABLE

create table if not exists expenses_savings (
	no INT AUTO_INCREMENT PRIMARY KEY,
    expenses DECIMAL(10, 2),
    expenses_description VARCHAR(200),
    monthly_payments DECIMAL (10, 2),
    savings DECIMAL(10, 2),
    savings_description VARCHAR(200),
    total_savings DECIMAL(10, 2)
);