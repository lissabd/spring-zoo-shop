CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255),
    role VARCHAR(50)
);

INSERT INTO users (name, email, password, role)
VALUES ('admin', 'admin@mail.ru', '$2a$10$A23jN4ZCFMZGblpNsl3YceIWiA8TO6sSSgJUI.9ucSYKZ9fu8F4Ay', 'ADMIN');


CREATE TABLE IF NOT EXISTS products (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255),
    category VARCHAR(255),
    cost DECIMAL(10, 2),
    image VARCHAR(255)
);
CREATE TABLE IF NOT EXISTS cart_items (
    id SERIAL PRIMARY KEY,
    user_id INT,
    product_id INT,
    quantity INT,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);
CREATE TABLE IF NOT EXISTS orders (
    id SERIAL PRIMARY KEY,
    user_id INT,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    phone_number VARCHAR(20),
    address VARCHAR(255),
    product_ids TEXT,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

INSERT INTO products (title, category, cost) VALUES
('Корм для собак', 'dog', 500.0),
('Ошейник для собак', 'dog', 1500.0),
('Игрушка косточка для собак', 'dog', 1500.0),
('Веревка с мячиком для собак', 'dog', 1500.0),
('Игрушечный мячик для собак', 'dog', 1500.0),
('Корм для рыбок', 'fish', 500.0),
('Аквариум для рыбок', 'fish', 2000.0),
('Декорация для аквариума', 'fish', 1500.0),
('Корм для кошек', 'cat', 700.0),
('Мятные шарики для кошек', 'cat', 1300.0),
('Игрушка для кошек', 'cat', 880.0);

