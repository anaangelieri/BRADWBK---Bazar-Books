DROP DATABASE IF EXISTS bazarbooks;
CREATE DATABASE IF NOT EXISTS bazarbooks;
USE bazarbooks;

CREATE TABLE User (
    id_user INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
);

CREATE TABLE Address (
    id_address INT PRIMARY KEY AUTO_INCREMENT,
    street VARCHAR(255) NOT NULL,
    number INT NOT NULL,
    complement VARCHAR(255),
    district VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    state VARCHAR(100) NOT NULL,
    postal_code VARCHAR(20) NOT NULL,
    latitude DOUBLE,
    longitude DOUBLE,
    id_user INT,
    CONSTRAINT fk_address_user FOREIGN KEY (id_user) REFERENCES User(id_user)
);

CREATE TABLE Book (
    id_book INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    image_url VARCHAR(500),
    description TEXT,
    price VARCHAR(50),
    rating DOUBLE,
    review_count INT,
    store VARCHAR(255)
);

CREATE TABLE Cart (
    id_cart INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_user INT UNIQUE,
    CONSTRAINT fk_cart_user FOREIGN KEY (id_user) REFERENCES User(id_user)
);

CREATE TABLE CartItem (
    id_cart_item INT PRIMARY KEY AUTO_INCREMENT,
    quantity INT NOT NULL,
    uni_price DOUBLE NOT NULL,
    id_cart INT,
    id_book INT,
    CONSTRAINT fk_cartitem_cart FOREIGN KEY (id_cart) REFERENCES Cart(id_cart),
    CONSTRAINT fk_cartitem_book FOREIGN KEY (id_book) REFERENCES Book(id_book)
);

CREATE TABLE Notification (
    id_notification INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    message TEXT NOT NULL,
    `read` BOOLEAN DEFAULT FALSE,
    sent_date DATETIME,
    id_user INT,
    CONSTRAINT fk_notification_user FOREIGN KEY (id_user) REFERENCES User(id_user)
);

INSERT INTO User (name, email) VALUES ('João', 'joao@gmail.com');
INSERT INTO User (name, email) VALUES ('Maria', 'maria@gmail.com');
INSERT INTO User (name, email) VALUES ('Pedro', 'pedro@gmail.com');
INSERT INTO User (name, email) VALUES ('Ana', 'ana@gmail.com');
INSERT INTO User (name, email) VALUES ('Lucas', 'lucas@gmail.com');
