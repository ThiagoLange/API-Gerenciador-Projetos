package br.com.sustentavel.gerenciadorprojetos.dto;

import br.com.sustentavel.gerenciadorprojetos.domain.Organizacao;

// DTO para enviar dados de uma Organização de volta ao cliente.
public record OrganizacaoResponseDTO(
        Long id,
        String nome,
        String contato
) {
    // Construtor que facilita a conversão da Entidade para DTO.
    public OrganizacaoResponseDTO(Organizacao organizacao) {
        this(organizacao.getId(), organizacao.getNome(), organizacao.getContato());
    }
}