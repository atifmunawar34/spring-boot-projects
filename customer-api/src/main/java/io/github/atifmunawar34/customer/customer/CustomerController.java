package io.github.atifmunawar34.customer.customer;

import java.net.URI;
import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

	private final CustomerService service;

	public CustomerController(CustomerService service) {
		this.service = service;
	}

	@GetMapping
	public List<Customer> list() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public Customer get(@PathVariable Long id) {
		return service.findById(id);
	}

	@PostMapping
	public ResponseEntity<Customer> create(@Valid @RequestBody CreateCustomerRequest request) {
		Customer created = service.create(request);
		return ResponseEntity.created(URI.create("/api/v1/customers/" + created.getId()))
				.body(created);
	}

	@PutMapping("/{id}")
	public Customer update(@PathVariable Long id, @Valid @RequestBody UpdateCustomerRequest request) {
		return service.update(id, request);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
