package com.jsp.ums.util;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.handler.ResponseStatusExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jsp.ums.exception.UserNotFoundByIdException;
@RestControllerAdvice
public class ApplicationHandler  extends ResponseEntityExceptionHandler{
	
	@Override
	
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		List<ObjectError> allErrors=ex.getAllErrors();
		Map<String, String> errors=new HashMap<String,String>();
		allErrors.forEach(error ->{
			FieldError fieldError=(FieldError) error;
			errors.put(fieldError.getField(), fieldError.getDefaultMessage());
		});
		
		 	return structure(HttpStatus.BAD_REQUEST,"Failed to save the data",errors);
	}

	private ResponseEntity<Object> structure(HttpStatus status, String message,Object rootCause)
	{
	
		return new ResponseEntity<Object> (Map.of(
				"status", status.value(),
				"message", message,
				"rootmessage", rootCause
				), status);

	}

	@ExceptionHandler(UserNotFoundByIdException.class)
	public ResponseEntity<Object> handleUserNotFoundById(UserNotFoundByIdException ex)
	{

		return structure(HttpStatus.NOT_FOUND,ex.getMessage(),"User not found with the given data");

	}

	public ResponseEntity<Object> handleRuntime(RuntimeException ex){

		return structure(HttpStatus.BAD_REQUEST,ex.getMessage(),"");

	}



}

//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import com.jsp.ums.exception.UserNotFoundByIdException;
//
//@RestControllerAdvice
//public class ApplicationHandler {
//
//	@ExceptionHandler
//	public ResponseEntity<ResponseStructure<String>> userNotFoundById(UserNotFoundByIdException e){
//		ResponseStructure<String> responseStructure = new ResponseStructure<>();
//		responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
//		responseStructure.setMessage(e.getMessage());
//		responseStructure.setData("user Object with the given Id doesn't exists!!");
//		
//		return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NOT_FOUND);
//	}
//	
//	//private ResponseEntity<Object> structure
//}
