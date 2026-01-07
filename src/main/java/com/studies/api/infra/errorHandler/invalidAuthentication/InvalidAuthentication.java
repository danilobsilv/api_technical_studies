package com.studies.api.infra.errorHandler.invalidAuthentication;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class InvalidAuthentication extends RuntimeException{
    public InvalidAuthentication(String message){
        super(message);
    }
}
