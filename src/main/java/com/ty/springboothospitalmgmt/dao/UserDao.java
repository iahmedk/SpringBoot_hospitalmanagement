package com.ty.springboothospitalmgmt.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.springboothospitalmgmt.dto.User;
import com.ty.springboothospitalmgmt.repository.UserRepository;

@Repository
public class UserDao {

	@Autowired
	private UserRepository userRepository;

	public User saveUser(User user) {
		return userRepository.save(user);
	}

	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	public User getUserById(int id) {
		Optional<User> opt = userRepository.findById(id);
		if (!opt.isEmpty()) {
			return opt.get();
		} else {
			return null;
		}
	}

	public void deleteUserById(int id) {
		userRepository.deleteById(id);
	}

	public User validateUser(String email, String password) {
		return userRepository.validateUser(email, password);
	}

	public User getUserByPhone(long phone) {
		return userRepository.findByPhone(phone);
	}

	public User updateUserById(User user) {
		return userRepository.save(user);
	}

}
