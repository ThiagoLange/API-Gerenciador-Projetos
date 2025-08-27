package br.com.sustentavel.gerenciadorprojetos.dto;

import br.com.sustentavel.gerenciadorprojetos.domain.Perfil;

// Este DTO representa os dados que a API recebe para criar ou atualizar um usuário.
// Não inclui o ID e recebe a senha em texto plano.
public record UsuarioRequestDTO(
        String nome,
        String username,
        String senha,
        Perfil perfil
) {
}