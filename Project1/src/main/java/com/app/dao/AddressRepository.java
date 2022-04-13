package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.pojos.Address;
import com.app.pojos.User;

public interface AddressRepository extends JpaRepository<Address, Integer> {

	List<Address> findByUserId(User u);
	
	@Query("select a from Address a where a.userId.id=:id")
	List<Address> FindByUser(@Param("id") Integer id);
}
