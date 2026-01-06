package com.studies.api.features.auth;

import com.studies.api.features.user.User;
import com.studies.api.features.user.dto.AuthDataDto;
import com.studies.api.infra.errorHandler.invalidAuthentication.InvalidAuthentication;
import com.studies.api.infra.security.DataTokenJwtType;
import com.studies.api.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public ResponseEntity<DataTokenJwtType> doLogin(@RequestBody @Valid AuthDataDto data) throws InvalidAuthentication {
            var authToken = new UsernamePasswordAuthenticationToken(data.email(), data.password());

            var auth = authenticationManager.authenticate(authToken);

            var jwtToken = tokenService.generateToken((User) auth.getPrincipal());

            return ResponseEntity.ok(new DataTokenJwtType(jwtToken));
    }
}
