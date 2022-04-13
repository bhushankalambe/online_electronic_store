package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.Category;
import com.app.pojos.Order;
import com.app.pojos.Product;
import com.app.service.CartServiceImple;
import com.app.service.CategoryServiceImple;
import com.app.service.ProductServiceImple;
import com.app.service.UserServiceImple;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {

	@Autowired
	private UserServiceImple userService;
	@Autowired
	private ProductServiceImple productService;
	@Autowired
	private CategoryServiceImple categoryService;
	@Autowired
	private CartServiceImple cartService;
	
	@GetMapping("/customers")
	public ResponseEntity<?> getCustomerList()
	{
		return new ResponseEntity<>(userService.getAllCustomer(), HttpStatus.OK);
	}
	
	@GetMapping("/products")
	public ResponseEntity<?> getProducts()
	{
		return new ResponseEntity<>(productService.getAllProduct(), HttpStatus.OK);
	}
	
	@PostMapping("/addProduct/{id}")
	public void addProduct(@RequestBody Product p,@PathVariable int id)
	{
		p.setCategory(categoryService.getCategory(id));
		productService.AddProduct(p);
	}
	@GetMapping("/product/{id}")
	public ResponseEntity<?> getProductById(@PathVariable int id)
	{
		System.out.println(12);
		return new ResponseEntity<>(productService.getProduct(id),HttpStatus.OK);
	}
	
	@PutMapping("/updateProduct")
	public void updateProduct( @RequestBody Product p)
	{
		productService.AddProduct(p);
	}
	
	@DeleteMapping("/deleteProduct/{id}")
	public void deleteProduct(@PathVariable int id)
	{
		productService.deleteProduct(id);
	}
	
	@GetMapping("/category")
	public ResponseEntity<?> getAllCategories()
	{
		return new ResponseEntity<>(categoryService.getAllCategory(), HttpStatus.OK);
	}
	
	
	@PostMapping("/categoryAdd")
	public void addCategory(@RequestBody Category c)
	{
		try {
		categoryService.AddCategory(c);
		}catch(RuntimeException e)
		{
			e.getMessage();
		}
	}
	
	@PostMapping("/linkProduct/{id}")
	public void linkProduct(@RequestBody Product p,@PathVariable int id)
	{
		Product product = productService.getProductByName(p.getName());
		Category c = categoryService.getCategory(id);
		List<Product> list = c.getProduct();
		list.add(product);
		c.setProduct(list);
		categoryService.AddCategory(c);
	}
	
	@GetMapping("/orderList")
	public ResponseEntity<?> getOrderList()
	{
		return new ResponseEntity<>(cartService.getCompleteOrderList(),HttpStatus.OK);
	}
	
	@GetMapping("/orderList/{id}")
	public ResponseEntity<?> getOrder(@PathVariable int id)
	{
		return new ResponseEntity<>(cartService.getOrder(id),HttpStatus.OK);
	}
	
	@PutMapping("/updateOrder")
	public void updateOrder(@RequestBody Order o)
	{
		cartService.updateOrder(o);
	}
}
