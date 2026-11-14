package io.github.atifmunawar34.customer.customer;

public class DuplicateEmailException extends RuntimeException {

	public DuplicateEmailException(String email) {
		super("Email already registered: " + email);
	}
}
