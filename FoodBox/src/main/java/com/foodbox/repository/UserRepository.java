package com.foodbox.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.foodbox.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("SELECT user FROM User user WHERE CONCAT(user.firstName, ' ', user.lastName, ' ', user.emailAddress, ' ') LIKE %?1%")
	public List<User> search(String keyword);
	
	@Query("Select user from User user where user.emailAddress = :emailAddress")
	public User findUserByEmail(@Param("emailAddress") String email );
	
	@Query("Select user from User user where user.emailAddress = ?1")
	public List<User> getUserByEmail(String emailAddress);

	
}
