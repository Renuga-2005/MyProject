package com.example.assess;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

	 User findByUserNameAndUserPassword(String userName, String userPassword);
	 Optional<User> findByUserName(String userName);
//	 Optional<User> findByNameAndPassword(String userName, String userPassword);
				
}
