package com.studies.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.studies.api.features.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String apiSecurityToken;

    public String generateToken(User user){
        try {
            var algorithm = Algorithm.HMAC256(apiSecurityToken);
            return JWT.create()
                    .withIssuer("auth0")
                    .withSubject(user.getUserEmail())
                    .withClaim("id", user.getUserId())
                    .withExpiresAt(tokenExpirationDate())
                    .sign(algorithm);
        }
        catch (JWTCreationException exception){
            throw new RuntimeException("Error while generating JWT Token: " + exception);
        }
    }

    public String getSubject(String jwtToken){
        try{
            Algorithm algorithm = Algorithm.HMAC256(apiSecurityToken);
            return JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build()
                    .verify(jwtToken)
                    .getSubject();
        }
        catch (JWTVerificationException exception){
            throw new RuntimeException("Invalid or Expired Token;");
        }
    }

    private Instant tokenExpirationDate(){
        return LocalDateTime.now().plusMonths(1).toInstant(ZoneOffset.of("-04:00"));
    }
}
