package com.studies.api.features.user.validation;

import com.studies.api.features.user.UserRole;
import com.studies.api.features.user.dto.CreateUserType;
import com.studies.api.infra.errorHandler.invalidRoleException.InvalidRoleException;
import org.springframework.stereotype.Component;

@Component
public class CheckUserRole implements UserValidator {
    @Override
    public void validate(CreateUserType data){
        String roleString = String.valueOf(data.userRole());

        if (roleString == null || roleString.isBlank()){
            throw new InvalidRoleException("O campo ROLE é obrigatório.");
        }

        try{
            UserRole.valueOf(roleString.toUpperCase());
        }
        catch (IllegalArgumentException exception){
            throw new InvalidRoleException("O ROLE fornecido é invalido.");
        }
    }
}
