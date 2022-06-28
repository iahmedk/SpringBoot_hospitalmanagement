package com.ty.springboothospitalmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ty.springboothospitalmgmt.dto.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query("SELECT u from User u where u.email = :myemail AND u.password = :mypassword")
	User validateUser(@Param("myemail") String email, @Param("mypassword") String password);
	
	User findByPhone(long phone);
}
