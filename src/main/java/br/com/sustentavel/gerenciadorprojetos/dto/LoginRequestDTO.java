package br.com.sustentavel.gerenciadorprojetos.dto;

// Usar um record é uma forma moderna e concisa de criar um DTO imutável.
public record LoginRequestDTO(String username, String password) {
}