CREATE TABLE IF NOT EXISTS expenses (
	id SERIAL PRIMARY KEY,
	description VARCHAR(255),
	amount NUMERIC(10,2),
	category VARCHAR(100),
	date DATE
);

CREATE TABLE IF NOT EXISTS initial_budget (
    id INT AUTO_INCREMENT PRIMARY KEY,
    initial_budget DECIMAL(10, 2) NOT NULL
);
