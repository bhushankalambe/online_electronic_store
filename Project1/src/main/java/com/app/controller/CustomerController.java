package com.app.controller;

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

import com.app.pojos.Address;
import com.app.pojos.Cart;
import com.app.pojos.User;
import com.app.pojos.UserRole;
import com.app.service.AddressServiceImple;
import com.app.service.CartServiceImple;
import com.app.service.CategoryServiceImple;
import com.app.service.ProductServiceImple;
import com.app.service.UserServiceImple;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {

	@Autowired
	private UserServiceImple customerService;
	@Autowired
	private ProductServiceImple productService;
	@Autowired
	private CartServiceImple cartService;
	@Autowired
	private CategoryServiceImple categoryService;
	@Autowired
	private AddressServiceImple addressService;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getCustomerDetails(@PathVariable int id)
	{
		return new ResponseEntity<>(customerService.getUser(id), HttpStatus.OK);
	}
	
	@PutMapping("/updateProfile")
	public void updateCustomerDetails(@RequestBody User u)
	{
		u.setRole(UserRole.ROLE_CUSTOMER);
		customerService.addUser(u);
	}
	@PutMapping("/updatePassword")
	public void updatePassword(@RequestBody User u)
	{
		System.out.println(u);
		customerService.updatePassword(u);
	}
	
	@GetMapping("/showProductList")
	public ResponseEntity<?> showProductList()
	{
		return new ResponseEntity<>(productService.getAllProduct(), HttpStatus.OK);
	}
	@GetMapping("/getProductByCategory/{id}")
	public ResponseEntity<?> getProductByCategory(@PathVariable int id)
	{
		return new ResponseEntity<>(productService.getProductByCategory(id),HttpStatus.OK);
	}
	
	@PostMapping("/addToCart/{id}")
	public void addToCart(@RequestBody int pid,@PathVariable int id)
	{
		cartService.addProduct(pid,id);
	}
	
	@GetMapping("/categoryList")
	public ResponseEntity<?> showCategoryList()
	{
		return new ResponseEntity<>(categoryService.getAllCategory(), HttpStatus.OK);
	}
	
	@GetMapping("/addressList/{id}")
	public ResponseEntity<?> showAddress(@PathVariable int id)
	{
		return new ResponseEntity<>(addressService.getAddress(id), HttpStatus.OK);
	}
	
	@GetMapping("/getAddress/{id}")
	public ResponseEntity<?> getAddressById(@PathVariable int id)
	{
		return new ResponseEntity<>(addressService.getAddressById(id), HttpStatus.OK);
	}
	
	@PostMapping("/addAddress/{id}")
	public void addAddress(@RequestBody Address a,@PathVariable int id)
	{
		addressService.addAddresss(a,id);
	}
	
	@PutMapping("/editAddress/{id}")
	public void editAddress(@RequestBody Address a, @PathVariable int id)
	{
		addressService.addAddresss(a, id);
	}
	
	@DeleteMapping("/removeAddress/{id}")
	public void removeAddress(@PathVariable int id) {
		addressService.removeAddress(id);
	}
	
	@GetMapping("/getCart/{id}")
	public ResponseEntity<?> getCart(@PathVariable int id)
	{
		return new ResponseEntity<>(cartService.getCart(id), HttpStatus.OK);
	}
	
	@GetMapping("/getCartById/{id}")
	public ResponseEntity<?> getCartById(@PathVariable int id)
	{
		return new ResponseEntity<>(cartService.getCartById(id), HttpStatus.OK);
	}
	
	@PutMapping("/editCart")
	public void getCartById(@RequestBody Cart c)
	{
		cartService.editCart(c);
	}
	@DeleteMapping("/removeFromCart/{id}")
	public void removeFromCart(@PathVariable int id) {
		cartService.removeFromCart(id);
	}
	
}
