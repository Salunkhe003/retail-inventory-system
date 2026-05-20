-- Create database if it doesn't exist
CREATE DATABASE IF NOT EXISTS inventory_db;
USE inventory_db;

-- Core inventory table structure
CREATE TABLE IF NOT EXISTS products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DOUBLE NOT NULL,
    stock_quantity INT NOT NULL
);