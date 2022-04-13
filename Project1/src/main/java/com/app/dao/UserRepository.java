package com.app.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.pojos.User;
import com.app.pojos.UserRole;

public interface UserRepository extends JpaRepository<User,Integer>{

	@EntityGraph(type = EntityGraphType.FETCH,value="address-graph")
	User findByEmail(@Param("email")String email);
	
	List<User> findByRole(UserRole role);
	
	@Modifying
	@Transactional
	@Query("update User u set u.password=:password where id=:id")
	void updatePassword(@Param("password") String password,@Param("id") int id);
	
	User findByFirstName(String name);
	
}
