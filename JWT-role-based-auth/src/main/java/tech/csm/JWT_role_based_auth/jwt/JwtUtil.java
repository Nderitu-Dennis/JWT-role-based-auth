package tech.csm.JWT_role_based_auth.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JwtUtil {

    private final SecretKey SECRET = Keys.hmacShaKeyFor("mySuperSecretKey1234567890123456".getBytes());

    public String generateToken(String username, List<String> roles) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", roles);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(System.currentTimeMillis() + 1000 * 60 * 30)
                )
                .signWith(SECRET)  //signature creation
                .compact();
    }

    public Claims extractClaims(String token) { //token validation

                return Jwts.parserBuilder()
                    .setSigningKey(SECRET) //signature verification
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

        }
        }


