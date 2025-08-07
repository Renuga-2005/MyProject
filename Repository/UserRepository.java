package com.example.assess.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.assess.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

	 User findByUserNameAndUserPassword(String userName, String userPassword);
	 Optional<User> findByUserName(String userName);
//	 Optional<User> findByNameAndPassword(String userName, String userPassword);
				
}
