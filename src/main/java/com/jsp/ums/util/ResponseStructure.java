package com.jsp.ums.util;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ResponseStructure <T>{
	private int status;
	private String message;
	private T data;
	
}
