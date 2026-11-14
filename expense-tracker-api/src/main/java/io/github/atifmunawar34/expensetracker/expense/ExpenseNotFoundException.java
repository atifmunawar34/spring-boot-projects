package io.github.atifmunawar34.expensetracker.expense;

public class ExpenseNotFoundException extends RuntimeException {

	public ExpenseNotFoundException(Long id) {
		super("Expense not found: " + id);
	}
}
