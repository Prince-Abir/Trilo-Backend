package com.tshirtmart.trilo.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tshirtmart.trilo.Entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	

//	User findUserById(long userId);
	
	
	//boolean findByUserEmailAndUserPassword(String userEmail,String userPassword);
	
	

	
	
}
