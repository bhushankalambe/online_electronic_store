package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.AddressRepository;
import com.app.dao.UserRepository;
import com.app.pojos.Address;
import com.app.pojos.User;

@Service
@Transactional
public class AddressServiceImple implements IAddressService {

	@Autowired
	private AddressRepository dao;
	@Autowired 
	private UserRepository userdao;
	
	@Override
	public List<Address> getAddress(int id) {
		User user = userdao.findById(id).orElseThrow(()-> new RuntimeException("Invalid Id"));
		User um = userdao.findByEmail(user.getEmail());
		um.getOrder().size();
		System.out.println(um);
		return dao.findByUserId(um);
	}

	@Override
	public void addAddresss(Address a,int id) {
		User user = userdao.findById(id).orElseThrow(()-> new RuntimeException("Invalid Id"));
		User um = userdao.findByEmail(user.getEmail());
		um.getOrder().size();
		a.setUserId(um);
		dao.save(a);
	}

	@Override
	public void removeAddress(int id) {
		Address a = dao.findById(id).orElseThrow(()->new RuntimeException("Id Not Found"));
		dao.delete(a);
	}

	@Override
	public Address getAddressById(int id) {
		return dao.findById(id).orElseThrow(()->new RuntimeException("Id Not Found"));
		
	}

	@Override
	public void editAddress(Address a) {
		dao.save(a);
	}

}
