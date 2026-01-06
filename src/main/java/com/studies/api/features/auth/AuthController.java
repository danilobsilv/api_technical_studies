package com.studies.api.features.auth;

import com.studies.api.features.user.dto.AuthDataDto;
import com.studies.api.infra.security.DataTokenJwtType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping
    public ResponseEntity<DataTokenJwtType> doLogin(@RequestBody @Valid AuthDataDto dto){
        return authService.doLogin(dto);
    }
}

