package org.example.recipefinder.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "superSecretKeyThatShouldBeLongAndSecure"; // Brug en rigtig hemmelig nøgle
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 10; // 10 timer

    // Brug Keys.hmacShaKeyFor() for at oprette en sikker nøgle
    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    // Generer JWT-token for brugerens username
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)  // Signerer med HS256 algoritme
                .compact();
    }

    // Ekstraherer username fra JWT-token
    public String extractUsername(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)  // Parser JWT og henter claims
                    .getBody()
                    .getSubject(); // Henter subject (brugernavn)
        } catch (Exception e) {
            // Håndter fejlen, hvis token parsing fejler
            return null;
        }
    }

    // Validerer om tokenet er korrekt og ikke udløbet
    public boolean validateToken(String token, String username) {
        return username.equals(extractUsername(token)) && !isTokenExpired(token);
    }

    // Tjekker om tokenet er udløbet
    private boolean isTokenExpired(String token) {
        try {
            Date expirationDate = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration(); // Henter udløbsdatoen
            return expirationDate.before(new Date()); // Tjek om udløbsdatoen er før nu
        } catch (Exception e) {
            return true;  // Hvis der opstår en fejl, anses tokenet som udløbet
        }
    }
}
