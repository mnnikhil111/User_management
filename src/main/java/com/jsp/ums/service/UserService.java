package com.jsp.ums.service;

import org.springframework.http.ResponseEntity;

import com.jsp.ums.entity.User;
import com.jsp.ums.util.ResponseStructure;

public interface UserService {
	public ResponseEntity<ResponseStructure<User>> saveUser(User user);

	public ResponseEntity<ResponseStructure<User>> updateUser(int userId, User user);

	public ResponseEntity<ResponseStructure<User>> findUser(int userId);

	public ResponseEntity<ResponseStructure<User>> deleteUser(int userId);
}
