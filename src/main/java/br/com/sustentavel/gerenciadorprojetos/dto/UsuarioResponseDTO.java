package br.com.sustentavel.gerenciadorprojetos.dto;

import br.com.sustentavel.gerenciadorprojetos.domain.Perfil;
import br.com.sustentavel.gerenciadorprojetos.domain.Usuario;

// Este DTO representa os dados que a API retorna ao cliente.
// Inclui o ID, mas NUNCA a senha.
public record UsuarioResponseDTO(
        Long id,
        String nome,
        String username,
        Perfil perfil
) {
    // Construtor auxiliar para facilitar a conversão da Entidade para o DTO.
    public UsuarioResponseDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getUsername(), usuario.getPerfil());
    }
}