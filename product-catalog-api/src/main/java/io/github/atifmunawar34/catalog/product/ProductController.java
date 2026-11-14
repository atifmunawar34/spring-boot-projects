package io.github.atifmunawar34.catalog.product;

import java.math.BigDecimal;
import java.net.URI;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

	private final ProductService service;

	public ProductController(ProductService service) {
		this.service = service;
	}

	@GetMapping
	public PagedResponse<Product> search(
			@RequestParam(required = false) ProductCategory category,
			@RequestParam(required = false, name = "q") String query,
			@RequestParam(required = false) BigDecimal minPrice,
			@RequestParam(required = false) BigDecimal maxPrice,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size,
			@RequestParam(defaultValue = "id,asc") String sort) {
		return PagedResponse.from(service.search(category, query, minPrice, maxPrice, page, size, sort));
	}

	@GetMapping("/{id}")
	public Product get(@PathVariable Long id) {
		return service.findById(id);
	}

	@PostMapping
	public ResponseEntity<Product> create(@Valid @RequestBody ProductRequest request) {
		Product created = service.create(request);
		return ResponseEntity.created(URI.create("/api/v1/products/" + created.getId()))
				.body(created);
	}

	@PutMapping("/{id}")
	public Product update(@PathVariable Long id, @Valid @RequestBody ProductRequest request) {
		return service.update(id, request);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
