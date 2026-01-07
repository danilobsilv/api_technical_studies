package com.studies.api.features.user.dto;

public record UpdateUserModType(
        String userName,
        String userEmail,
        String userPassword
) {
}
