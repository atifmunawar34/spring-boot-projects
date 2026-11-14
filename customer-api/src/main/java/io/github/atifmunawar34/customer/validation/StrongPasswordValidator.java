package io.github.atifmunawar34.customer.validation;

import java.util.regex.Pattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StrongPasswordValidator implements ConstraintValidator<StrongPassword, String> {

	private static final Pattern PATTERN = Pattern
			.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^\\w\\s]).{8,}$");

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value == null || PATTERN.matcher(value).matches();
	}
}
