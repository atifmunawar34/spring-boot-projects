package io.github.atifmunawar34.expensetracker.expense;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record UpdateExpenseRequest(
		@NotBlank @Size(max = 120) String title,
		@NotNull @Positive BigDecimal amount,
		@NotNull ExpenseCategory category,
		@NotNull LocalDate expenseDate) {
}
