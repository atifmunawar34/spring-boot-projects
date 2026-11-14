package io.github.atifmunawar34.customer.customer;

import io.github.atifmunawar34.customer.validation.PhoneNumber;
import io.github.atifmunawar34.customer.validation.StrongPassword;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateCustomerRequest(
		@NotBlank @Size(min = 2, max = 100) String name,
		@NotBlank @Email String email,
		@PhoneNumber String phone,
		@NotBlank @StrongPassword String password,
		@Valid @NotNull Address address) {
}
