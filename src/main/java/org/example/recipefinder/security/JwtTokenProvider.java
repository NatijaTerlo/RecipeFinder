package org.example.recipefinder.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    private final String JWT_SECRET = "supersecretkey";
    private final long JWT_EXPIRATION = 86400000; // 1 dag

    // Genererer JWT-token
    public String generateToken(Authentication authentication) {
        return Jwts.builder()
                .setSubject(authentication.getName()) // Set brugernavn som subject
                .setIssuedAt(new Date()) // Sætter udstedelsestidspunkt
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION)) // Sætter udløbstidspunkt
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET) // Signering af tokenet med HS512 og hemmelig nøgle
                .compact();
    }

    // Henter brugernavn fra JWT-token
    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token) // Parse JWT
                .getBody()
                .getSubject(); // Hent subject (brugernavn)
    }

    // Validerer tokenet
    public boolean validateToken(String token) {
        try {
            // Parsing og validering af token
            Jwts.parser()
                    .setSigningKey(JWT_SECRET)
                    .parseClaimsJws(token);
            return true; // Hvis ingen undtagelse opstår, er tokenet validt
        } catch (Exception e) {
            // Håndter fejlhåndtering ved ugyldig token
            return false;
        }
    }

    // Validerer om token er udløbet
    public boolean isTokenExpired(String token) {
        Date expirationDate = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return expirationDate.before(new Date()); // Tjek om token er udløbet
    }
}
