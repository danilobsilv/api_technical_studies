package com.studies.api.features.user.validation;

import com.studies.api.features.user.dto.CreateUserType;
import com.studies.api.infra.errorHandler.invalidPasswordException.InvalidPasswordException;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class ValidatePassword implements UserValidator {

    private static final Pattern PASSWORD_PATTERN = Pattern.compile(
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$"
    );

    @Override
    public void validate(CreateUserType data) {
        String userPassword = data.userPassword();

        if (userPassword == null || userPassword.isBlank()) {
            throw new InvalidPasswordException("A senha não pode ser vazia.");
        }

        if (!PASSWORD_PATTERN.matcher(userPassword).matches()) {
            throw new InvalidPasswordException(
                    "Senha fraca: precisa ter no mínimo 8 caracteres, incluindo letra maiúscula, " +
                            "letra minúscula, número e caractere especial."
            );
        }
    }

    public static void validatePassword(String password){
        if (password == null || password.isBlank()) {
            throw new InvalidPasswordException("A senha não pode ser vazia.");
        }

        if (!PASSWORD_PATTERN.matcher(password).matches()) {
            throw new InvalidPasswordException(
                    "Senha fraca: precisa ter no mínimo 8 caracteres, incluindo letra maiúscula, " +
                            "letra minúscula, número e caractere especial."
            );
        }
    }
}
