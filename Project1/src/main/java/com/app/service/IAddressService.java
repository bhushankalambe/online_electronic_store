package com.app.service;

import java.util.List;

import com.app.pojos.Address;

public interface IAddressService {

	List<Address> getAddress(int id);
	
	void addAddresss(Address a,int id);
	
	void removeAddress(int id);
	
	Address getAddressById(int id);
	
	void editAddress(Address a);
}
