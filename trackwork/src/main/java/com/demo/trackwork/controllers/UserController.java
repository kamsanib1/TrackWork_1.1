package com.demo.trackwork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.trackwork.entities.Users;
import com.demo.trackwork.services.IUserService;

@RestController
@RequestMapping("api/users")
public class UserController {

	@Autowired
	IUserService userService;
	
	@GetMapping("/{username}")
	protected ResponseEntity<Users> getUser(@PathVariable("username") String username) {
		Users res = userService.read(username);
		return new ResponseEntity<Users>(res,HttpStatus.OK);
	}
	
	@PostMapping("")
	protected ResponseEntity<String> createUser(@RequestBody Users user){
		ResponseEntity<String> res = new ResponseEntity<String>("new user \""+user.getUsername()+"\" created.",HttpStatus.CREATED);
		System.out.println("controller:creating new user..."+user);
		if(userService.create(user)!=0)
			res = new ResponseEntity<String>("failed to create new user \""+user.getUsername()+"\".",HttpStatus.EXPECTATION_FAILED);
		return res;
	}
	
	@DeleteMapping("/{username}")
	protected ResponseEntity<String> deleteUser(@PathVariable("username") String username){
		userService.delete(username);
		return new ResponseEntity<String>("deleted user.", HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/{username}")
	protected ResponseEntity<String> updateUser(@RequestBody Users user,
												@PathVariable("username") String username){
		user.setUsername(username);
		if(userService.update(user)==0)
			return new ResponseEntity<String>("updated user.", HttpStatus.ACCEPTED);
		else 
			return new ResponseEntity<String>("failed to update user.", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
