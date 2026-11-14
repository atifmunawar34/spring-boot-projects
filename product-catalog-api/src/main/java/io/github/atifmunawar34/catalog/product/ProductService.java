package io.github.atifmunawar34.catalog.product;

import java.math.BigDecimal;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ProductService {

	private static final int MAX_PAGE_SIZE = 100;
	private static final Set<String> SORTABLE_FIELDS = Set.of("id", "name", "price", "stock",
			"category", "createdAt");

	private final ProductRepository repository;

	public ProductService(ProductRepository repository) {
		this.repository = repository;
	}

	public Page<Product> search(ProductCategory category, String query, BigDecimal minPrice,
			BigDecimal maxPrice, int page, int size, String sortParam) {
		Pageable pageable = PageRequest.of(Math.max(page, 0), clampSize(size), parseSort(sortParam));
		return repository
				.findAll(ProductSpecifications.withFilters(category, query, minPrice, maxPrice),
						pageable);
	}

	public Product findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
	}

	@Transactional
	public Product create(ProductRequest request) {
		return repository.save(new Product(request.name(), request.description(), request.price(),
				request.category(), request.stock()));
	}

	@Transactional
	public Product update(Long id, ProductRequest request) {
		Product product = findById(id);
		product.setName(request.name());
		product.setDescription(request.description());
		product.setPrice(request.price());
		product.setCategory(request.category());
		product.setStock(request.stock());
		return repository.save(product);
	}

	@Transactional
	public void delete(Long id) {
		repository.delete(findById(id));
	}

	private int clampSize(int size) {
		return (size <= 0 || size > MAX_PAGE_SIZE) ? 20 : size;
	}

	private Sort parseSort(String sortParam) {
		if (sortParam == null || sortParam.isBlank()) {
			return Sort.by("id").ascending();
		}
		String[] parts = sortParam.split(",");
		String field = parts[0].trim();
		if (!SORTABLE_FIELDS.contains(field)) {
			throw new IllegalArgumentException("Cannot sort by field: " + field);
		}
		Sort.Direction direction = parts.length > 1 && "desc".equalsIgnoreCase(parts[1].trim())
				? Sort.Direction.DESC
				: Sort.Direction.ASC;
		return Sort.by(direction, field);
	}
}
