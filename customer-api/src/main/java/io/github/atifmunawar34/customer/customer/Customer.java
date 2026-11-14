package io.github.atifmunawar34.customer.customer;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Customer {

	private Long id;
	private String name;
	private String email;
	private String phone;
	private Address address;

	@JsonIgnore
	private String password;

	private Instant createdAt;

	public Customer() {
	}

	public Customer(Long id, String name, String email, String phone, String password,
			Address address) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.address = address;
		this.createdAt = Instant.now();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}
}
