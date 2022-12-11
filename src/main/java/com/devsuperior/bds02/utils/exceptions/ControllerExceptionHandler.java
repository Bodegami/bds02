package com.devsuperior.bds02.utils.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError errorReponse = new StandardError();
		errorReponse.setStatus(status.value());
		errorReponse.setTimestamp(Instant.now());
		errorReponse.setError("Resource not found...");
		errorReponse.setMessage(e.getMessage());
		errorReponse.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(errorReponse);
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError errorReponse = new StandardError();
		errorReponse.setTimestamp(Instant.now());
		errorReponse.setStatus(status.value());
		errorReponse.setError("Database exception...");
		errorReponse.setMessage(e.getMessage());
		errorReponse.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(errorReponse);
	}

}
