package io.github.atifmunawar34.taskflow.common;

import java.time.Instant;

public record ApiError(int status, String message, Instant timestamp) {
}
