package com.studies.api.infra.errorHandler.invalidAuthentication;

import com.studies.api.infra.errorHandler.ErrorTypes;
import java.time.Instant;

public record InvalidAuthenticationType(
        Instant timestamp,
        int HttpStatus,
        ErrorTypes errorType,
        String message
) {
}
