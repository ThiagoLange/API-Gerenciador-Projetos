package br.com.sustentavel.gerenciadorprojetos.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys; // Novo import
import jakarta.annotation.PostConstruct; // Novo import
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets; // Novo import
import java.security.Key; // Novo import
import java.util.Date;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpirationMs;

    // [ALTERAÇÃO] Vamos usar um objeto Key, que é mais seguro e correto
    private Key key;

    /**
     * Este método será executado uma vez após a inicialização do service.
     * Ele converte a nossa chave secreta (String) em um objeto Key seguro.
     */
    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(Authentication authentication) {
        User principal = (User) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationMs);

        return Jwts.builder()
                .setSubject(principal.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                // [ALTERAÇÃO] Assinando com o objeto Key seguro
                .signWith(key)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        // [ALTERAÇÃO] Usando o mesmo objeto Key para validar a assinatura
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}