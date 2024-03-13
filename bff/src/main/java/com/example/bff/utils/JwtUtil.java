package com.example.bff.utils;

import com.example.common.auth.UserContext;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import com.example.common.utils.JacksonUtil;

import java.util.Date;

public class JwtUtil {
    private static final String SECRET_KEY = "869ec1fbca8af2d765cb041f6f33556f";

    public static String generateToken(UserContext userContext) throws Exception {
        String userContextJson = JacksonUtil.toJson(userContext);

        long currentTimeMillis = System.currentTimeMillis();
        return Jwts.builder().claim("userContext", userContextJson).setSubject(userContext.getUserName()).setIssuedAt(new Date(currentTimeMillis)).setExpiration(new Date(currentTimeMillis + 3600000)) // 1 hour expiration
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public static UserContext getUserContextFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();

        String userContextJson = claims.get("userContext", String.class);
        return JacksonUtil.fromJsonToObject(userContextJson, UserContext.class);
    }
}
