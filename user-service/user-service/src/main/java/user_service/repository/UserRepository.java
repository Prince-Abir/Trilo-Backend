package user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import user_service.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{


//	User findUserById(long userId);


	//boolean findByUserEmailAndUserPassword(String userEmail,String userPassword);


	User findByUserEmail(String userEmail);



	@Query(value = "SELECT * FROM users WHERE user_name = :userName", nativeQuery = true)
	User findByUsername(@Param("userName") String userName);






}
