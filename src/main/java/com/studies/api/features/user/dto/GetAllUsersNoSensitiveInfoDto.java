package com.studies.api.features.user.dto;

import com.studies.api.features.user.User;
import com.studies.api.features.user.UserRole;

public record GetAllUsersNoSensitiveInfoDto(
        String userName,
        String userEmail,
        UserRole userRole,
        Boolean isActive
) {
    public GetAllUsersNoSensitiveInfoDto(User user){
        this(
                user.getUsername(),
                user.getUserEmail(),
                user.getUserRole(),
                user.isActive()
        );
    }
}
