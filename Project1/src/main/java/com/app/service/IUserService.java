package com.app.service;

import java.util.List;

import com.app.pojos.User;

public interface IUserService {

	void addUser(User u);
	
	User deleteUser(int id);
	
	User validateUser(User u);
	
	List<User> getAllCustomer();
	
	User getUser(int id);
	
	void updatePassword(User u);
	
}
