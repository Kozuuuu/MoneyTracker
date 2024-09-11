CREATE TABLE financial_records (
    payroll BIGINT AUTO_INCREMENT PRIMARY KEY,
    cash DECIMAL(10, 2),
    date DATE DEFAULT CURRENT_DATE(),
    expenses DECIMAL(10, 2),
    savings DECIMAL(10, 2),
    extra DECIMAL(10, 2) DEFAULT 150,
    days_until_next_payroll INT,
    next_payroll_date DATE,
    period VARCHAR(255),
    INDEX (date)
);
