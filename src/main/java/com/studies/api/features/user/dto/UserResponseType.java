package com.studies.api.features.user.dto;

import com.studies.api.features.user.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserResponseType(
        @NotBlank
        @NotNull
        String userId,

        @NotNull
        @NotBlank
        String userName,

        @NotNull
        @NotBlank
        String userRole
) {
    public UserResponseType(User data){
        this(
            data.getUserId(),
            data.getUsername(),
            String.valueOf(data.getUserRole())
        );
    }
}
