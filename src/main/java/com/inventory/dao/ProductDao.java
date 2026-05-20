package com.inventory.dao;

import java.util.List;

import com.inventory.model.Product;

public interface ProductDao {

	void addProduct(Product product);
	
	List<Product> getAllProducts();
	
	void updateProductStock(int id, int newStock);
    
	void deleteProduct(int id);
}
