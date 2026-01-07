package com.studies.api.features.user.dto;

import com.studies.api.features.user.UserRole;

public record CreateUserType(
        String userName,
        String userEmail,
        String userPassword,
        UserRole userRole
) {
}
