package com.rewards.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Handles application wide exceptions.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleCustomerNotFound(CustomerNotFoundException ex) {

		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(ErrorResponse.builder().status("ERROR").message(ex.getMessage()).build());
	}
}
