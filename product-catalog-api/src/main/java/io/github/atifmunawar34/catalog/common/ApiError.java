package io.github.atifmunawar34.catalog.common;

import java.time.Instant;
import java.util.Map;

public record ApiError(int status, String message, Map<String, String> errors, String path,
		Instant timestamp) {

	public static ApiError of(int status, String message, String path) {
		return new ApiError(status, message, Map.of(), path, Instant.now());
	}

	public static ApiError of(int status, String message, Map<String, String> errors, String path) {
		return new ApiError(status, message, errors, path, Instant.now());
	}
}
