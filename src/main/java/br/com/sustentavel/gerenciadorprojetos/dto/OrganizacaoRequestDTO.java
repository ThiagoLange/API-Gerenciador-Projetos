package br.com.sustentavel.gerenciadorprojetos.dto;

// DTO para receber dados de criação e atualização de uma Organização.
public record OrganizacaoRequestDTO(
        String nome,
        String contato
) {
}