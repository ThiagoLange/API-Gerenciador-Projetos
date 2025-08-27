package br.com.sustentavel.gerenciadorprojetos.repository;

import br.com.sustentavel.gerenciadorprojetos.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Método para buscar um usuário pelo seu nome de usuário (username)
    // Essencial para o Spring Security
    Optional<Usuario> findByUsername(String username);
}