package com.intergrated.jasoseo.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.util.ObjectUtils;

import java.security.Key;
import java.util.Date;

@Configuration
public class JWTFilter {
//    @Value("")
    private String jwtKey;

    private int jwtAccessTokenExpirationMinutes = 0;
    private int jwtRefreshTokenExpirationMinutes = 0;

    public String createToken(String subject, int userId, boolean mode){ // mode true : accessToken, false : refreshToken
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + (mode ? jwtAccessTokenExpirationMinutes : jwtRefreshTokenExpirationMinutes) * 60 * 1000);
        Key key = getSigningKey();
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer("son")
                .setSubject(subject)
                .claim("id", userId)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    public Claims parseToken(String token){
        try{
            Key key = getSigningKey();
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

        }
        catch (JwtException e){
            return null;
        }
    }

    public String extractTokenFromHeader(HttpServletRequest request){
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(header != null && header.startsWith("Bearer ")){
            return header.substring(7);
        }
        return null;
    }
    public Boolean checkValidateToken(String token){
        return !ObjectUtils.isEmpty(token) && !ObjectUtils.isEmpty(parseToken(token));
    }

    private Key getSigningKey(){
        byte[] keyBytes = Decoders.BASE64.decode(this.jwtKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
