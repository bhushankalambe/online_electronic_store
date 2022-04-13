package com.app.service;

import java.util.List;

import com.app.pojos.Cart;
import com.app.pojos.Order;

public interface ICartService {

	void addProduct(int pid ,int id);
	
	List<Cart> getCart(int id);
	
	Cart getCartById(int id);
	
	void editCart(Cart c);
	
	void addToOrder(Order o, int id);
	
	List<Order> getOrderList(int id);
	
	List<Order> getCompleteOrderList();
	
	Order getOrder(int id);
	
	void updateOrder(Order o);
}
