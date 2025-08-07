package com.example.assess.response;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseGenerator {
	
	public static  ResponseEntity<Response> successResponse(String message, Object data) {
	    Response response = new Response("Success", message, data);
	    return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	public static  ResponseEntity<Response> errorResponse(String message) {
	    Response response = new Response();
	    response.setMessage(message);
	    response.setStatus("Error");
	    
	
	    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

}