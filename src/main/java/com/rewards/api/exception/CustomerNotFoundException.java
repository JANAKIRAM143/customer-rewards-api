package com.rewards.api.exception;

/**
 * Thrown when customer transactions are not found.
 */
public class CustomerNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomerNotFoundException(String message) {
		super(message);
	}
}
