package com.hyeonson.yajalal.utils;

import com.hyeonson.yajalal.dto.UserNickAndIdx;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil implements Serializable {
    public static final long JWT_TOKEN_VALIDITY = 48 * 60 * 60;
    private static final long serialVersionUID = -3312529429390939534L;


    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.secret}")
    private String secret;



    public UserNickAndIdx getUserNickAndIdxFromToken(final String token) {
        return new UserNickAndIdx(getClaimFromToken(token, Claims::getSubject), (Integer) getAllClaimsFromToken(token).get("userIdx"));
    }

    /*public String getUserNicknameFromToken(final String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }*/


    public <T> T getClaimFromToken(final String token, final Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(final String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }


    public Date getExpirationDateFromToken(final String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    private Boolean isTokenExpired(final String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(final String nickname, final Long id) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", id);
        return doGenerateToken(claims, nickname);
    }

    private String doGenerateToken(final Map<String, Object> claims, final String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                //.setIssuedAt(new Date(System.currentTimeMillis()))
                .setIssuer(issuer)
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public Boolean validateToken(final String token) {
        //final String username = getUsernameFromToken(token);
        return !isTokenExpired(token);
    }
}
