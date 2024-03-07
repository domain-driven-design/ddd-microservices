package com.example.bff.utils;

import com.example.bff.domain.UserContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import utils.JacksonUtil;

import java.util.Date;

public class JwtUtil {
    private static final String SECRET_KEY = "your_secret_key_here";

    public static String generateToken(UserContext userContext) throws Exception {
        String userContextJson = JacksonUtil.toJson(userContext);

        long currentTimeMillis = System.currentTimeMillis();
        return Jwts.builder().claim("userContext", userContextJson) // Add serialized UserContext as a claim
                .setSubject(userContext.getUserName()).setIssuedAt(new Date(currentTimeMillis)).setExpiration(new Date(currentTimeMillis + 3600000)) // 1 hour expiration
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public static UserContext getUserContextFromToken(String token) throws Exception {
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();

        String userContextJson = claims.get("userContext", String.class);
        return JacksonUtil.fromJsonToObject(userContextJson, UserContext.class);
    }
}
