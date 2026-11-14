package io.github.atifmunawar34.expensetracker.expense;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ExpenseService {

	private final ExpenseRepository repository;

	public ExpenseService(ExpenseRepository repository) {
		this.repository = repository;
	}

	public List<Expense> findAll(ExpenseCategory category, LocalDate from, LocalDate to) {
		if (category != null) {
			return repository.findByCategory(category);
		}
		if (from != null && to != null) {
			return repository.findByExpenseDateBetween(from, to);
		}
		return repository.findAll();
	}

	public Expense findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new ExpenseNotFoundException(id));
	}

	@Transactional
	public Expense create(CreateExpenseRequest request) {
		Expense expense = new Expense(request.title(), request.amount(), request.category(),
				request.expenseDate());
		return repository.save(expense);
	}

	@Transactional
	public Expense update(Long id, UpdateExpenseRequest request) {
		Expense expense = findById(id);
		expense.setTitle(request.title());
		expense.setAmount(request.amount());
		expense.setCategory(request.category());
		expense.setExpenseDate(request.expenseDate());
		return repository.save(expense);
	}

	@Transactional
	public void delete(Long id) {
		Expense expense = findById(id);
		repository.delete(expense);
	}
}
