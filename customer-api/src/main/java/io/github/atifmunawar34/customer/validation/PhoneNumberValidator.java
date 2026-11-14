package io.github.atifmunawar34.customer.validation;

import java.util.regex.Pattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

	private static final Pattern E164 = Pattern.compile("^\\+?[1-9]\\d{6,14}$");

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value == null || E164.matcher(value).matches();
	}
}
