package com.juaracoding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.juaracoding.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	@Query(value = "Select COUNT(*) FROM user where username = ?1 AND password = ?2",nativeQuery = true)
	int validateUser(String username, String password);
}
