package io.github.atifmunawar34.customer.customer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record Address(
		@NotBlank @Size(max = 200) String street,
		@NotBlank @Size(max = 100) String city,
		@Pattern(regexp = "^\\d{5}(-\\d{4})?$", message = "must be a valid ZIP code") String zipCode,
		@NotBlank @Size(max = 100) String country) {
}
