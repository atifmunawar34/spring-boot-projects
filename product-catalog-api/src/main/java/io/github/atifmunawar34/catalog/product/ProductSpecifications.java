package io.github.atifmunawar34.catalog.product;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

public final class ProductSpecifications {

	private ProductSpecifications() {
	}

	public static Specification<Product> withFilters(ProductCategory category, String query,
			BigDecimal minPrice, BigDecimal maxPrice) {
		return Specification.where(hasCategory(category))
				.and(nameContains(query))
				.and(priceGte(minPrice))
				.and(priceLte(maxPrice));
	}

	private static Specification<Product> hasCategory(ProductCategory category) {
		return (root, q, cb) -> category == null ? null : cb.equal(root.get("category"), category);
	}

	private static Specification<Product> nameContains(String query) {
		return (root, q, cb) -> (query == null || query.isBlank()) ? null
				: cb.like(cb.lower(root.get("name")), "%" + query.toLowerCase() + "%");
	}

	private static Specification<Product> priceGte(BigDecimal minPrice) {
		return (root, q, cb) -> minPrice == null ? null
				: cb.greaterThanOrEqualTo(root.get("price"), minPrice);
	}

	private static Specification<Product> priceLte(BigDecimal maxPrice) {
		return (root, q, cb) -> maxPrice == null ? null
				: cb.lessThanOrEqualTo(root.get("price"), maxPrice);
	}
}
