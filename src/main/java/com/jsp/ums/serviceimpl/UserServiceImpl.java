package com.jsp.ums.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import com.jsp.ums.entity.User;
import com.jsp.ums.repository.UserRepo;
import com.jsp.ums.requestdto.UserRequest;
import com.jsp.ums.responsedto.UserResponse;
import com.jsp.ums.service.UserService;
import com.jsp.ums.util.ResponseStructure;
import com.jsp.ums.exception.*;

@Service
@Component
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepo userRepo;
	

	
	@Autowired
	private ResponseStructure<User> structure;
	
	@Override
	public ResponseEntity<ResponseStructure<User>> saveUser(User user) {
			User user2 =userRepo.save(user);
			
			ResponseStructure<User> responseStructure = new ResponseStructure<>();
			responseStructure.setStatus(HttpStatus.CREATED.value());
			responseStructure.setMessage("User Object Created Successfully");
			responseStructure.setData(user2);
			
		return new ResponseEntity<ResponseStructure<User>>(responseStructure,HttpStatus.CREATED);
	}
	


	@Override
	public ResponseEntity<ResponseStructure<User>> updateUser(int userId, User user) {
		User user2=userRepo.findById(userId).orElseThrow(()-> new RuntimeException());
		user.setUserId(userId);
		user2=userRepo.save(user);
		
		structure.setStatus(HttpStatus.OK.value());
		structure.setMessage("user updated successfully!");
		structure.setData(user2);
		
		return new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.OK);
	}



	@Override
	public ResponseEntity<ResponseStructure<User>> findUser(int userId) {
		Optional<User> optional=userRepo.findById(userId);
		if(optional.isPresent()) {
			User user=optional.get();
			ResponseStructure<User> responseStructure=new ResponseStructure<>();
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("User Object found Successfully");
			responseStructure.setData(user);
			return new ResponseEntity<ResponseStructure<User>>(responseStructure,HttpStatus.FOUND);
			
		}
		else
		{
			throw new UserNotFoundByIdException("user Not Found");
		}
	}



	@Override
	public ResponseEntity<ResponseStructure<User>> deleteUser(int userId) {
		Optional<User> optional = userRepo.findById(userId);
		if(optional.isPresent()) {
			User user=optional.get();
			userRepo.delete(user);
			
			ResponseStructure<User> responseStructure=new ResponseStructure<>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("User Object deleted Successfully");
			responseStructure.setData(user);
			
			return new ResponseEntity<ResponseStructure<User>>(responseStructure,HttpStatus.OK);
		}
		{
			return null;
		}
		
	}
	

//private User mapToUser(UserRequest request)
//{
//	return User.builder()
//			.userName(request.getUserName())
//			.emailId(request.getEmailId())
//			.password(request.getPassword())
//			.build();
//	
//}
//
//private UserResponse mapToUserResponse(User user)
//{
//	return UserResponse.builder()
//			.userId(user.getUserId())
//			.userName(user.getUserName())
//			.emailId(user.getEmailId())
//			.build();
//}	
			
	

}
