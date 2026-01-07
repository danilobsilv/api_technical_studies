package com.studies.api.features.user.validation;


import com.studies.api.features.user.dto.CreateUserType;

public interface UserValidator {
    void validate(CreateUserType data);
}
