package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Cart;
import com.app.pojos.Product;
import com.app.pojos.User;

public interface CartRepository extends JpaRepository<Cart, Integer> {

	List<Cart> findByUserId(User u);
	
	Cart findByUserIdAndProducts(User u,Product p);
}
