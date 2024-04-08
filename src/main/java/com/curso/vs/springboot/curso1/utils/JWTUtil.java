package com.curso.vs.springboot.curso1.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;


@SuppressWarnings("deprecation")
@Component
public class JWTUtil {

    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final long expirationTime = 21600000; // 6 horas en milisegundos

    public String generateToken(String id, String subject) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationTime);
        return Jwts.builder()
                .setId(id)
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key)
                .compact();
    }

    public static String getSubjectFromToken(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(key).build().parseClaimsJws(token);
            return claimsJws.getBody().getSubject();
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getIdFromToken(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(key).build().parseClaimsJws(token);
            return claimsJws.getBody().getId();
        } catch (Exception e) {
            return null;
        }
    }
}
    
