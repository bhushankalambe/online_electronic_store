package com.app.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.Order;
import com.app.service.CartServiceImple;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:3000")
public class CartController {

	@Autowired
	private CartServiceImple cartService;

	
	@PostMapping("/addToOrder/{id}")
	public void addToCart(@RequestBody Order o, @PathVariable int id)
	{
		o.setDeliveryDate(LocalDate.now());
		System.out.println(1);
		cartService.addToOrder(o,id);
	}
	
	@GetMapping("/getOrderList/{id}")
	public ResponseEntity<?> getOrderList(@PathVariable int id)
	{
		return new ResponseEntity<>(cartService.getOrderList(id), HttpStatus.OK);
	}
	
	
}
