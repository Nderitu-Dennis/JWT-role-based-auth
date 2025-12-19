package tech.csm.JWT_role_based_auth.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JwtUtil {

    private final String SECRET = "mySecretKey123"; //secret to create & verify the signature-known by server only

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
                .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()))  //signature creation
                .compact();
    }

    public Claims extractClaims(String token) { //token validation
        return Jwts.parserBuilder()
                .setSigningKey(SECRET.getBytes()) //signature verification
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}