package com.inventory.main;

import java.util.List;
import java.util.Scanner;
import com.inventory.dao.ProductDao;
import com.inventory.dao.ProductDaoImpl;
import com.inventory.model.Product;

public class MainApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProductDao productDao = new ProductDaoImpl();
        boolean running = true;

        while (running) {
            System.out.println("\n========= RETAIL INVENTORY MENU =========");
            System.out.println("1. ➕ Add New Product");
            System.out.println("2. 📋 View All Inventory");
            System.out.println("3. 🔄 Update Product Stock");
            System.out.println("4. 🗑️ Delete Product");
            System.out.println("5. ❌ Exit Application");
            System.out.print("Choose an option (1-5): ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear the buffer line shadow

            switch (choice) {
                case 1:
                    System.out.print("Enter product name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter product price: ");
                    double price = scanner.nextDouble();
                    System.out.print("Enter initial stock quantity: ");
                    int stock = scanner.nextInt();
                    
                    Product newProduct = new Product(name, price, stock);
                    productDao.addProduct(newProduct);
                    break;

                case 2:
                    System.out.println("\n--- Current Inventory Items ---");
                    List<Product> list = productDao.getAllProducts();
                    if (list.isEmpty()) {
                        System.out.println("No products found in the database.");
                    } else {
                        for (Product p : list) {
                            System.out.println(p);
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter Product ID to update: ");
                    int updateId = scanner.nextInt();
                    System.out.print("Enter new stock level: ");
                    int newStock = scanner.nextInt();
                    
                    productDao.updateProductStock(updateId, newStock);
                    break;

                case 4:
                    System.out.print("Enter Product ID to delete: ");
                    int deleteId = scanner.nextInt();
                    
                    productDao.deleteProduct(deleteId);
                    break;

                case 5:
                    running = false;
                    System.out.println("Closing connection. Goodbye, Harshal!");
                    break;

                default:
                    System.out.println("❌ Invalid selection! Please choose a number between 1 and 5.");
            }
        }
        
        scanner.close();
    }
}