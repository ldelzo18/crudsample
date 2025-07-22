package com.learning.crudsample.application.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.learning.crudsample.infrastructure.security.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {

    @Value("$jwt.secret")
    private String SECRET_KEY;

    public String generateToken(User user) {
        return JWT.create()
                .withSubject(user.getEmail())
                .withClaim("fullName", user.getFullName())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 86400000))
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    public String recoverTokenSubject(String token) {
        return JWT.require(Algorithm.HMAC256(SECRET_KEY))
                .build()
                .verify(token)
                .getSubject();
    }

    /*public DecodedJWT getDecodedToken(String token) {
        return JWT.require(Algorithm.HMAC256(SECRET_KEY))
                .build()
                .verify(token);
    }*/
}
