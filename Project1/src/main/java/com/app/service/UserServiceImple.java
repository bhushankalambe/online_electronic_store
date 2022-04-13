package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.app.dao.UserRepository;
import com.app.pojos.User;
import com.app.pojos.UserRole;

@Service
@Transactional
public class UserServiceImple implements IUserService {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public void addUser(User u) {
		if(u.getId()!= null)
		{
			System.out.println("In 1");
			User user =  userRepo.findById(u.getId()).orElseThrow(() -> new RuntimeException("User Not Found"));
			u.setRole(UserRole.ROLE_CUSTOMER);
			u.setPassword(user.getPassword());
			userRepo.save(u);
		}
		else {
		u.setRole(UserRole.ROLE_CUSTOMER);
		u.setPassword(encoder.encode(u.getPassword()));
		System.out.println(u);
		userRepo.save(u);
		System.out.println("User Added");
		}
	}

	@Override
	public User deleteUser(int id) {
		User user = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User Not Found"));
		userRepo.delete(user);
		return user;
	}

	@Override
	public User validateUser(User u) {
		User user = userRepo.findByEmail(u.getEmail());
		if (user != null) {
			if (encoder.matches(u.getPassword(), user.getPassword())) {
				user.getOrder().size();
				return user;
			}
		}
		return null;
	}

	@Override
	public List<User> getAllCustomer() {
		List<User> u = userRepo.findByRole(UserRole.ROLE_CUSTOMER);
		return u;
	}

	@Override
	public User getUser(int id) {
		return userRepo.findById(id).orElseThrow(() -> new RuntimeException("Invalid id"));
	}

	@Override
	public void updatePassword(User u) {
		userRepo.updatePassword(encoder.encode(u.getPassword()), u.getId());
	}

}
