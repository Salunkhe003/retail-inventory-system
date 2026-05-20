package com.inventory.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.inventory.config.DatabaseConfig;
import com.inventory.model.Product;

public class ProductDaoImpl implements ProductDao{

	@Override
	public void addProduct(Product product) {
		// TODO Auto-generated method stub
		// Raw SQL query with placeholders (?) for security
		String sql = "INSERT INTO products (name, price, stock_quantity) VALUES (?, ?, ?)";
		
		// Using try-with-resources to automatically close the connection and statement
		try(Connection connection = DatabaseConfig.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql)){
			
			// Setting the values dynamically into the placeholders
			preparedStatement.setString(1, product.getName());
			preparedStatement.setDouble(2, product.getPrice());
			preparedStatement.setInt(3, product.getStockQuantity());
			
			int rowsInsert = preparedStatement.executeUpdate();
			if(rowsInsert > 0) {
				System.out.println("🎉 Product successfully added to the database!");
			}
			
		}
		catch (SQLException e) {
			// TODO: handle exception
			System.err.println("❌ Error while inserting product into database!");
			e.printStackTrace();
		}
	}

	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		List<Product> products = new ArrayList<Product>();
		String sql = "SELECT * FROM products";
		
		// try-with-resources handles closing Connection, Statement, and ResultSet automatically
		try(Connection connection = DatabaseConfig.getConnection();
				Statement statement = connection.createStatement();
	             ResultSet resultSet = statement.executeQuery(sql)){
			
			// resultSet.next() moves the pointer to the next row. Returns false when there are no more rows.
			while(resultSet.next()) {
				// 1. Extract data from the current row using column names
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				double price = resultSet.getDouble("price");
				int stockQuantity = resultSet.getInt("stock_quantity");
				
				// 2. Map the DB columns into a Java Product object
				Product product = new Product(id, name, price, stockQuantity);
				
				// 3. Add the product to our local list
				products.add(product);
			}
		}
		catch (SQLException e) {
            System.err.println("❌ Error while retrieving products from database!");
            e.printStackTrace();
        }
		return products;
	}
	
	@Override
	public void updateProductStock(int id, int newStock) {
		String sql = "UPDATE products SET stock_quantity = ? WHERE id = ?";
		try(Connection connection = DatabaseConfig.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql)){
			
			preparedStatement.setInt(1, newStock);
			preparedStatement.setInt(2, id);
			
			int rowsUpdated = preparedStatement.executeUpdate();
			if(rowsUpdated > 0) {
				System.out.println("🔄 Stock updated successfully for Product ID: " +  id);
			}
			else {
				System.out.println("⚠️ Product ID " + id + " not found.");
			}
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public void deleteProduct(int id) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM products WHERE id = ?";
		try(Connection connection = DatabaseConfig.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql)){
			
			preparedStatement.setInt(1, id);
			
			int rowsDeleted = preparedStatement.executeUpdate();
			if(rowsDeleted > 0) {
				System.out.println("🗑️ Product deleted successfully from inventory.");
			}
			else {
				System.out.println("⚠️ Product ID " + id + " not found.");
			}
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
