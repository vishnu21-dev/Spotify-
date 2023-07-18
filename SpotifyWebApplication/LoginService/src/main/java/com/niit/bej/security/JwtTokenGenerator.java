package com.niit.bej.security;

import com.niit.bej.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
@Service
public class JwtTokenGenerator implements SecurityTokenGenerator {
    @Override
    public Map<String, String> generateToken(User user) {
        String generatedToken= Jwts.builder()
                .setSubject(user.getEmailId())
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,"vishnu")
                .compact();
        return Map.of("token",generatedToken,
                "message","token is generated");
    }
    }

