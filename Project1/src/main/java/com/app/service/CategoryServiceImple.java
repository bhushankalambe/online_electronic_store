package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.CategoryRepository;
import com.app.pojos.Category;

@Service
public class CategoryServiceImple implements ICategoryService{

	@Autowired
	private CategoryRepository dao;
	
	@Override
	public List<Category> getAllCategory() {
		return dao.findAll();
	}
	@Override
	public void AddCategory(Category c) {
		c.setCatImage(c.getName());
		dao.save(c);
	}
	@Override
	public Category getCategory(int id) {
		return dao.findById(id).orElseThrow(() -> new RuntimeException("Invalid id"));
	}
	

}
