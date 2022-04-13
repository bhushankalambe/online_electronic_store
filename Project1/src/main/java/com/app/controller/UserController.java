package com.app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.User;
import com.app.service.UserServiceImple;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins ="http://localhost:3000")
public class UserController {
	
	@Autowired
	private UserServiceImple userService;
	
	public UserController()
	{
		System.out.println("In ctor" + getClass());
	}
	@PostMapping("/login")
	public ResponseEntity<?> validateLogin(@RequestBody User u)
	{
		User user = userService.validateUser(u);
		if(user != null)
			return new ResponseEntity<>(user, HttpStatus.OK);
		else
			return new ResponseEntity<>("Invalid Login", HttpStatus.OK);
	}
	@PostMapping("/add")
	public ResponseEntity<?> addUser(@RequestBody User u)
	{
		try {
		userService.addUser(u);
		return new ResponseEntity<>("Valid", HttpStatus.OK);
		}catch(RuntimeException e)
		{
			e.printStackTrace();
			return new ResponseEntity<>("Invalid Login", HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/{id}")
	public User deleteUser(@PathVariable int id)
	{
		User user  = userService.deleteUser(id);
		System.out.println(user);
		return user;
	}
	
	@PutMapping()
	public void updateUser(@RequestBody User u)
	{
		userService.addUser(u);
	}
}
