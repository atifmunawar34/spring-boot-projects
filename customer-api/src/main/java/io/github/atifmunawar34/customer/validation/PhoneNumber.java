package io.github.atifmunawar34.customer.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = PhoneNumberValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER, ElementType.RECORD_COMPONENT })
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneNumber {

	String message() default "must be a valid phone number (e.g. +14155550123)";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
