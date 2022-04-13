package com.app.service;

import java.util.List;

import com.app.pojos.Category;

public interface ICategoryService {

	List<Category> getAllCategory();
	
	void AddCategory(Category c);
	
	Category getCategory(int id);
	
}
