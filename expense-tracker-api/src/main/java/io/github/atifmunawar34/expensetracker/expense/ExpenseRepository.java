package io.github.atifmunawar34.expensetracker.expense;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

	List<Expense> findByCategory(ExpenseCategory category);

	List<Expense> findByExpenseDateBetween(LocalDate from, LocalDate to);
}
