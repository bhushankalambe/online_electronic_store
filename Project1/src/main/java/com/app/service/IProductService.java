package com.app.service;

import java.util.List;

import com.app.pojos.Product;

public interface IProductService {

	List<Product> getAllProduct();
	
	void AddProduct(Product p);
	
	void deleteProduct(int id);
	
	Product getProduct(int id);
	
	Product getProductByName(String name);
	
	Product getProduct(String name);
	
	List<Product> getProductByCategory(int id);
}
