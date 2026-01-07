package com.studies.api.infra.errorHandler.validationError;

import com.studies.api.infra.errorHandler.ErrorTypes;
import java.time.Instant;
import java.util.Map;

public record ValidationErrorType(
        Instant timestamp,
        int httpStatus,
        ErrorTypes errorType,
        String message,
        Map<String, String> fieldErrors
) {
}
