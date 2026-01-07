package com.studies.api.infra.errorHandler.validationError;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidationErrorException extends RuntimeException{

    public ValidationErrorException(String message){
        super(message);
    }
}
