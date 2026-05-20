package com.inventory.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    
    // Change "inventory_db" to your local database name if it's different
    private static final String URL = "jdbc:mysql://localhost:3306/inventory_db";
    private static final String USER = "root";       // Your MySQL username
    private static final String PASSWORD = "root";   // Your MySQL password

    public static Connection getConnection() throws SQLException {
        try {
            // Explicitly loading the MySQL Driver class
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found! Make sure it's in pom.xml.");
            e.printStackTrace();
        }
        
        // Returns the connection object
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}