package com.ty.springboothospitalmgmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ty.springboothospitalmgmt.dto.Login;
import com.ty.springboothospitalmgmt.dto.ResponseStructure;
import com.ty.springboothospitalmgmt.dto.User;
import com.ty.springboothospitalmgmt.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService service;

	@PostMapping("/users")
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody User user) {
		return service.saveUser(user);
	}

	@GetMapping("/users")
	public ResponseEntity<ResponseStructure<List<User>>> getAllUser() {
		return service.getAllUser();
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<ResponseStructure<User>> getUserById(@PathVariable int id) {
		return service.getUserById(id);
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteUserById(@PathVariable int id) {
		return service.deleteUserById(id);
	}

	@PostMapping("users/login")
	public ResponseEntity<ResponseStructure<User>> validateUser(@RequestBody Login login) {
		return service.validateUser(login.getEmail(), login.getPassword());
	}

	@GetMapping("/users/phone/{phone}")
	public ResponseEntity<ResponseStructure<User>> getUserByPhone(@PathVariable long phone) {
		return service.getUserByPhone(phone);
	}

	@PutMapping("/users/{id}")
	public ResponseEntity<ResponseStructure<User>> updateUserById(@RequestBody User user, @PathVariable int id) {
		return service.updateUserById(user, id);
	}
}