package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.pojos.Category;
import com.app.pojos.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	Product findByName(String name);
	
	@Query("select p from Product p JOIN FETCH p.category c where p.name=:name")
	Product findCategoryByName(@Param("name")String name);
	
	List<Product> findByCategory(Category c);
	
}
