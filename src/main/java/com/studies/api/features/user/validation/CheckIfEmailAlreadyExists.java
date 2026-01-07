package com.studies.api.features.user.validation;

import com.studies.api.features.user.UserRepository;
import com.studies.api.features.user.dto.CreateUserType;
import com.studies.api.infra.errorHandler.invalidEmailException.InvalidEmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CheckIfEmailAlreadyExists implements UserValidator {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void validate(CreateUserType data) {
        boolean exists = userRepository.existsByUserEmail(data.userEmail());

        if (exists){
            throw new InvalidEmailException("Esse e-mail já está vinculado a uma conta existente.");
        }
    }
}
