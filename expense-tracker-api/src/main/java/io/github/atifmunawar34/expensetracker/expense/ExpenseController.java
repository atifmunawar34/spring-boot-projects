package io.github.atifmunawar34.expensetracker.expense;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import jakarta.validation.Valid;

import org.springframework.format.annotation.DateTimeFormat;
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
@RequestMapping("/api/v1/expenses")
public class ExpenseController {

	private final ExpenseService service;

	public ExpenseController(ExpenseService service) {
		this.service = service;
	}

	@GetMapping
	public List<Expense> list(
			@RequestParam(required = false) ExpenseCategory category,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
		return service.findAll(category, from, to);
	}

	@GetMapping("/{id}")
	public Expense get(@PathVariable Long id) {
		return service.findById(id);
	}

	@PostMapping
	public ResponseEntity<Expense> create(@Valid @RequestBody CreateExpenseRequest request) {
		Expense created = service.create(request);
		return ResponseEntity.created(URI.create("/api/v1/expenses/" + created.getId())).body(created);
	}

	@PutMapping("/{id}")
	public Expense update(@PathVariable Long id, @Valid @RequestBody UpdateExpenseRequest request) {
		return service.update(id, request);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
