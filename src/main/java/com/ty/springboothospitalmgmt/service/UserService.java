package com.ty.springboothospitalmgmt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.springboothospitalmgmt.dao.UserDao;
import com.ty.springboothospitalmgmt.dto.ResponseStructure;
import com.ty.springboothospitalmgmt.dto.User;
import com.ty.springboothospitalmgmt.exception.InvalidUserException;
import com.ty.springboothospitalmgmt.exception.NoIdFoundException;
import com.ty.springboothospitalmgmt.util.AES;

@Service
public class UserService {
	@Autowired
	private UserDao dao;

	public ResponseEntity<ResponseStructure<User>> saveUser(User user) {
		String encPwd = AES.encrypt(user.getPassword(), "hospital-app");
		user.setPassword(encPwd);

		ResponseStructure<User> responseStructure = new ResponseStructure<User>();
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("Success");
		responseStructure.setData(dao.saveUser(user));
		return new ResponseEntity<ResponseStructure<User>>(responseStructure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<List<User>>> getAllUser() {
		ResponseStructure<List<User>> responseStructure = new ResponseStructure<List<User>>();
		List<User> users = dao.getAllUser();

		if (users.size() > 0) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Success");
			responseStructure.setData(users);
		} else {
			throw new NoIdFoundException("No users found ");
		}
		return new ResponseEntity<ResponseStructure<List<User>>>(responseStructure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<User>> getUserById(int id) {
		ResponseStructure<User> responseStructure = new ResponseStructure<User>();
		User user = dao.getUserById(id);

		if (user != null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Success");
			responseStructure.setData(user);
		} else {
			throw new NoIdFoundException("User id " + id + " does not found");
		}
		return new ResponseEntity<ResponseStructure<User>>(responseStructure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<String>> deleteUserById(int id) {
		ResponseStructure<String> responseStructure = new ResponseStructure<String>();
		User user = dao.getUserById(id);

		if (user != null) {
			dao.deleteUserById(id);
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Success");
			responseStructure.setData("User Deleted");
		} else {
			throw new NoIdFoundException("User id " + id + " does not found");
		}
		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<User>> validateUser(String email, String password) {
		String encPwd = AES.encrypt(password, "hospital-app");

		ResponseStructure<User> responseStructure = new ResponseStructure<User>();
		User user = dao.validateUser(email, encPwd);
		if (user != null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Success");
			responseStructure.setData(user);
		} else {
			throw new InvalidUserException("Either email OR password is wrong");
		}
		return new ResponseEntity<ResponseStructure<User>>(responseStructure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<User>> getUserByPhone(long phone) {
		ResponseStructure<User> responseStructure = new ResponseStructure<User>();
		User user = dao.getUserByPhone(phone);

		if (user != null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Success");
			responseStructure.setData(user);
		} else {
			throw new NoIdFoundException("User phone " + phone + " does not found");
		}
		return new ResponseEntity<ResponseStructure<User>>(responseStructure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<User>> updateUserById(User user, int id) {
		ResponseStructure<User> responseStructure = new ResponseStructure<User>();

		User u = dao.getUserById(id);

		if (u != null) {
			u.setEmail(user.getEmail());
			u.setGender(user.getGender());
			u.setName(user.getName());
			u.setPassword(AES.encrypt(user.getPassword(), "hospital-app"));
			u.setPhone(user.getPhone());
			u.setRole(user.getRole());

			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Success");
			responseStructure.setData(dao.updateUserById(u));
		} else {
			throw new NoIdFoundException("User id " + id + " does not found");
		}
		return new ResponseEntity<ResponseStructure<User>>(responseStructure, HttpStatus.OK);
	}
}
