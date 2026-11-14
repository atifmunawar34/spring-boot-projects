package io.github.atifmunawar34.customer.customer;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

@Service
public class CustomerService {

	private final Map<Long, Customer> store = new ConcurrentHashMap<>();
	private final AtomicLong sequence = new AtomicLong();

	public List<Customer> findAll() {
		return store.values().stream()
				.sorted(Comparator.comparing(Customer::getId))
				.toList();
	}

	public Customer findById(Long id) {
		Customer customer = store.get(id);
		if (customer == null) {
			throw new CustomerNotFoundException(id);
		}
		return customer;
	}

	public Customer create(CreateCustomerRequest request) {
		requireEmailAvailable(request.email());
		Customer customer = new Customer(sequence.incrementAndGet(), request.name(),
				request.email(), request.phone(), request.password(), request.address());
		store.put(customer.getId(), customer);
		return customer;
	}

	public Customer update(Long id, UpdateCustomerRequest request) {
		Customer customer = findById(id);
		customer.setName(request.name());
		customer.setPhone(request.phone());
		customer.setAddress(request.address());
		return customer;
	}

	public void delete(Long id) {
		if (store.remove(id) == null) {
			throw new CustomerNotFoundException(id);
		}
	}

	private void requireEmailAvailable(String email) {
		boolean taken = store.values().stream()
				.anyMatch(c -> c.getEmail().equalsIgnoreCase(email));
		if (taken) {
			throw new DuplicateEmailException(email);
		}
	}
}
