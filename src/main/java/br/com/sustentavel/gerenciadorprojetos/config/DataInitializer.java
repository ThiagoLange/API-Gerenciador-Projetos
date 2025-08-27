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
            // Só adiciona se não houver nenhum usuário no banco
            if (usuarioRepository.count() == 0) {
                System.out.println("Criando usuários iniciais...");

                Usuario admin = new Usuario();
                admin.setNome("Administrador");
                admin.setUsername("admin");
                admin.setSenha(passwordEncoder.encode("admin123")); // Senha criptografada!
                admin.setPerfil(Perfil.ADMIN);
                usuarioRepository.save(admin);

                Usuario user = new Usuario();
                user.setNome("Usuário Comum");
                user.setUsername("user");
                user.setSenha(passwordEncoder.encode("user123")); // Senha criptografada!
                user.setPerfil(Perfil.USER);
                usuarioRepository.save(user);

                System.out.println("Usuários criados com sucesso!");
            }
        };
    }
}