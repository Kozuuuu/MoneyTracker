CREATE DATABASE MoneyTracker;
CREATE TABLE MoneyTracker.financial_records (
    payroll INT AUTO_INCREMENT PRIMARY KEY,
    cash DECIMAL(10, 2) NOT NULL,
    date DATE NOT NULL,
    expenses DECIMAL(10, 2) NOT NULL,
    savings DECIMAL(10, 2) NOT NULL,
    extra DECIMAL(10, 2),
    Period VARCHAR(255) NOT NULL,
    INDEX (date)
);

INSERT INTO financial_records (payroll, cash, date, expenses, savings, extra, period)
VALUES 
    (1, 9228.10, '2024-06-30', 9228.10, 0, 0, 'July 1-31, 2024'),
    (2, 17130.95, '2024-06-30', 17130.95, 0, 0, 'August 1-15, 2024'),
    (3, 13407.44, '2024-06-30', 13407.44, 0, 0, 'August 16-31, 2024');


ALTER TABLE financial_records ADD COLUMN next_payroll_date DATE;
ALTER TABLE financial_records ADD COLUMN days_until_next_payroll INT;
