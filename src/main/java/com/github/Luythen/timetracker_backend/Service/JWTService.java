package com.github.Luythen.timetracker_backend.Service;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import com.github.Luythen.timetracker_backend.Model.UserModel;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {
    
    private final SecretKey key = Keys.hmacShaKeyFor("dasdadaaujshjsjhsjsjksjsh2wyhhyuwhugyauigvyyiugasiguyoasdioguasdiguasdiugiugasdiugiausgdiuyg2iugiu21g1iug2iu1g3iug1iu3g1iu3gi1ug3iu13giu1gi2g3iu1g3".getBytes());
    private static final long EXPIRATION_TIME = 86400000; // 24 hours

    public String generateToken (UserModel userModel) {
        return Jwts.builder()
            .subject(userModel.getUsername())
            .issuer("TimeTrackerAPP")
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .signWith(key)
            .compact();
    }

    public Claims getClaims (String token) {
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
    }

    public boolean isTokenValid (String token, UserModel userModel) {
        String username = getClaims(token).getSubject();

        return username.equals(userModel.getUsername()) && !isTokenExpired(token);
    }

    public boolean isTokenExpired (String token) {
        return getClaims(token).getIssuedAt().before(getClaims(token).getExpiration());
    }
}
