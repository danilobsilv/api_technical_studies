package com.studies.api.infra.errorHandler.resourceNotFoundException;

import com.studies.api.infra.errorHandler.ErrorTypes;
import java.time.Instant;

public record ResourceNotFoundType(
        Instant timestamp,
        int HttpStatus,
        ErrorTypes errorType,
        String message
) {
}
