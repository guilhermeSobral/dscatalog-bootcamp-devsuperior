package com.devsuperior.dscatalog.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devsuperior.dscatalog.services.exceptions.DatabaseException;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFoundHandler(ResourceNotFoundException e, HttpServletRequest request) {
		var error = new StandardError(Instant.now(), HttpStatus.NOT_FOUND.value(), "Resource not found", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> databaseHandler(DatabaseException e, HttpServletRequest request) {
		var error = new StandardError(Instant.now(), HttpStatus.BAD_REQUEST.value(), "Database exception", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> validationHandler(MethodArgumentNotValidException e, HttpServletRequest request) {
		var error = new ValidationError(Instant.now(), HttpStatus.UNPROCESSABLE_ENTITY.value(), "Validation exception", e.getMessage(), request.getRequestURI());
		e.getBindingResult().getFieldErrors().forEach(err -> error.addError(err.getField(), err.getDefaultMessage()));
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
	}

}
