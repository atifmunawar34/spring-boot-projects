package io.github.atifmunawar34.catalog.common;

import java.util.LinkedHashMap;
import java.util.Map;

import io.github.atifmunawar34.catalog.product.ProductNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ApiError> handleNotFound(ProductNotFoundException ex,
			HttpServletRequest request) {
		return build(HttpStatus.NOT_FOUND, ex.getMessage(), request);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex,
			HttpServletRequest request) {
		Map<String, String> fieldErrors = new LinkedHashMap<>();
		ex.getBindingResult().getFieldErrors()
				.forEach(e -> fieldErrors.put(e.getField(), e.getDefaultMessage()));
		ApiError body = ApiError.of(HttpStatus.BAD_REQUEST.value(), "Validation failed",
				fieldErrors, request.getRequestURI());
		return ResponseEntity.badRequest().body(body);
	}

	@ExceptionHandler({ IllegalArgumentException.class, HttpMessageNotReadableException.class,
			MethodArgumentTypeMismatchException.class })
	public ResponseEntity<ApiError> handleBadRequest(Exception ex, HttpServletRequest request) {
		return build(HttpStatus.BAD_REQUEST, ex.getMessage(), request);
	}

	private ResponseEntity<ApiError> build(HttpStatus status, String message,
			HttpServletRequest request) {
		return ResponseEntity.status(status)
				.body(ApiError.of(status.value(), message, request.getRequestURI()));
	}
}
