package com.example.JWT;

import com.example.Entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {
        @Value("${jwt.secret}")
        private String secretKey;
        @Value("${jwt.expiration}")
        private Long jwtexpiration;

        public String extractUsername(String token) {
            return extractClaim(token, Claims::getSubject);
        }

        private <T> T extractClaim(String jwtToken , Function<Claims, T> claimsResolver) {
            final Claims claims = extractAllClaims(jwtToken);
            return claimsResolver.apply(claims);

        }

        private Claims extractAllClaims(String jwtToken) {
            return Jwts.parser()
                    .verifyWith(getSignInkey())
                    .build()
                    .parseSignedClaims(jwtToken)
                    .getPayload();
        }


        public SecretKey getSignInkey() {
            return Keys.hmacShaKeyFor(secretKey.getBytes());
        }

        public String generateToken(UserDetails userDetails) {
                
            return generateToken(new HashMap<>(), userDetails);
        }

       public String generateToken(Map<String,Object> extraClaim,UserDetails userDetails){
                return Jwts.builder().claims(extraClaim)
                        .subject(userDetails.getUsername())
                        .issuedAt(new Date(System.currentTimeMillis()))
                        .expiration(new Date(System.currentTimeMillis() + jwtexpiration))
                        .signWith(getSignInkey())
                        .compact();

       }


        public boolean isTokenValid(String jwtToken, User userDetails) {
                final  String username = extractUsername(jwtToken);
                return (username.equals(userDetails.getUsername()) && !isTokenExpired(jwtToken));
        }

        private boolean isTokenExpired(String jwtToken) {
            return extractExpiration(jwtToken).before(new Date());
        }

        private Date extractExpiration(String jwtToken) {
                return extractClaim(jwtToken,Claims::getExpiration);
        }
}
