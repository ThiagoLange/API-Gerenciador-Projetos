package br.com.sustentavel.gerenciadorprojetos.config;

import br.com.sustentavel.gerenciadorprojetos.domain.Perfil;
import br.com.sustentavel.gerenciadorprojetos.domain.Usuario;
import br.com.sustentavel.gerenciadorprojetos.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (usuarioRepository.count() == 0) {
                Usuario admin = new Usuario();
                admin.setNome("Administrador");
                admin.setUsername("admin");
                admin.setSenha(passwordEncoder.encode("admin123"));
                admin.setPerfil(Perfil.ADMIN);
                usuarioRepository.save(admin);
            }
        };
    }
}