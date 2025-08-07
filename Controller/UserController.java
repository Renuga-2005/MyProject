package com.example.assess.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.assess.Entity.Jwtutil;
import com.example.assess.Entity.User;
import com.example.assess.Service.UserService;
import com.example.assess.response.ResponseGenerator;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private Jwtutil jwtutil;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllUsers() {
        try {
            List<User> userList = userService.getAllUsers();
            if (!userList.isEmpty()) {
                return ResponseGenerator.successResponse("Users fetched successfully", userList);
            } else {
                return ResponseGenerator.errorResponse("No users found");
            }
        } catch (Exception e) {
            return ResponseGenerator.errorResponse("An error occurred: " + e.getMessage());
        }
    }

    @GetMapping("/getById")
    public ResponseEntity<?> getUserById(@RequestParam String userName) {
        try {
            Optional<User> user = userService.getByUserId(userName);
            if (user.isPresent()) {
                return ResponseGenerator.successResponse("User found successfully", user.get());
            } else {
                return ResponseGenerator.errorResponse("User not found with ID: " + userName);
            }
        } catch (Exception e) {
            return ResponseGenerator.errorResponse("An error occurred: " + e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
        try {
            User createdUser = userService.saveUser(user);
            if (createdUser != null) {
                return ResponseGenerator.successResponse("User created successfully", createdUser);
            } else {
                return ResponseGenerator.errorResponse("User creation failed");
            }
        } catch (Exception e) {
            return ResponseGenerator.errorResponse("User creation failed: " + e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User request) {
        try {
            User updatedUser = userService.updateUser(request);
            if (updatedUser != null) {
                return ResponseGenerator.successResponse("User updated successfully", updatedUser);
            } else {
                return ResponseGenerator.errorResponse("User update failed");
            }
        } catch (Exception e) {
            return ResponseGenerator.errorResponse("An error occurred: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestParam String userName) {
        try {
            boolean isDeleted = userService.deleteUserById(userName);
            if (isDeleted) {
                return ResponseGenerator.successResponse("User deleted successfully", "Deleted ID: " + userName);
            } else {
                return ResponseGenerator.errorResponse("User with ID " + userName + " not found");
            }
        } catch (Exception e) {
            return ResponseGenerator.errorResponse("An error occurred: " + e.getMessage());
        }
    }
    @PostMapping("/login")
	public ResponseEntity<?> loginDetails(@RequestBody User request){
		try {
			User response=userService.getUserDetails(request.getUserName(),request.getUserPassword());
			if(response!=null) {
				String token=jwtutil.generateToken(request.getUserName());
				Map<String,Object> result=new HashMap<String, Object>();
				result.put("token", token);
				result.put("user",response);
			return ResponseGenerator.successResponse("login successfull", result);
			}else {
				return ResponseGenerator.errorResponse("login failed");
			}
		}catch(Exception e)
		{
			
			return ResponseGenerator.errorResponse(e.getMessage());
		}
 }
    
}
