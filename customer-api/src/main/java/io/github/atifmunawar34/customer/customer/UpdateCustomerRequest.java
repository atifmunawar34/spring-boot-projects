package io.github.atifmunawar34.customer.customer;

import io.github.atifmunawar34.customer.validation.PhoneNumber;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateCustomerRequest(
		@NotBlank @Size(min = 2, max = 100) String name,
		@PhoneNumber String phone,
		@Valid @NotNull Address address) {
}
