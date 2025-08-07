package com.example.assess.Handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.assess.response.ResponseGenerator;

@ControllerAdvice
public class Exceptionhandle {
	@ExceptionHandler(Exception.class)
	 public ResponseEntity<?> handleException(Exception e) {
	     return ResponseGenerator.errorResponse("Exception caught: " + e.getMessage());
	 }
}
