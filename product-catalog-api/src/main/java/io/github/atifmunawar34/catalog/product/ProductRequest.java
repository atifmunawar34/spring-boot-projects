package io.github.atifmunawar34.catalog.product;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ProductRequest(
		@NotBlank @Size(max = 120) String name,
		@Size(max = 2000) String description,
		@NotNull @Positive BigDecimal price,
		@NotNull ProductCategory category,
		@NotNull @Min(0) Integer stock) {
}
