package com.example.assess;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;





@Service
public class UserService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	
	
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public Optional<User> getByUserId(String userName) {
		return userRepository.findById(userName);
	}

	public User saveUser(User user) {
		return userRepository.save(user);
	}

	public User updateUser(User updatedUser) {
		Optional<User> existingUser = userRepository.findById(updatedUser.getUserName());

		if (existingUser.isPresent()) {
			existingUser.get().setUserName(updatedUser.getUserName());
			existingUser.get().setUserPassword(updatedUser.getUserPassword());
			existingUser.get().setStudentId(updatedUser.getStudentId());

			return userRepository.saveAndFlush(existingUser.get());
		}
		return null;
	}

	public boolean deleteUserById(String userName) {
		if (userRepository.existsById(userName)) {
			userRepository.deleteById(userName);
			return true;
		}
		return false;
	}

//	public UserDTO login(String userName,String userPassword) {
//	        Optional<User> users = userRepository. findByNameAndPassword(userName,userPassword);
//	        if (users.isPresent()) {
//	        	User user = users.get();
//	            UserDTO dto = new UserDTO();
//	                dto.setUserName(user.getUserName());
//	                dto.setUserPassword(user.getUserPassword());
//	                return dto;
//	            }
//	        
//	        return null;
//	    }
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<User> userOptional = userRepository.findByUserName(userName);
		if (!userOptional.isPresent()) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		User user = userOptional.get();
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getUserPassword(),
				getAuthority());
	}

 private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}
 
 public User getUserDetails(String userName, String userPassword) {
		return userRepository.findByUserNameAndUserPassword(userName, userPassword);
	}
	
	

}



	