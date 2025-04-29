package com.tshirtmart.trilo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tshirtmart.trilo.DTO.UserDTO;
import com.tshirtmart.trilo.Entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	

//	User findUserById(long userId);
	
	
	//boolean findByUserEmailAndUserPassword(String userEmail,String userPassword);
	
	boolean existsByUserEmailAndUserPassword(String userEmail, String userPassword);
	
	@Query(value = "SELECT * FROM users WHERE user_name = :userName", nativeQuery = true)
	User findByUsername(@Param("userName") String userName);
	
	
	

	
	
}
