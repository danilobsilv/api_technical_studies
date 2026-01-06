package com.studies.api.infra.security;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataTokenJwtType(
        @NotNull
        @NotBlank
        String jwtToken
) {
}
