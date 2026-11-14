package io.github.atifmunawar34.customer.customer;

public class CustomerNotFoundException extends RuntimeException {

	public CustomerNotFoundException(Long id) {
		super("Customer not found: " + id);
	}
}
