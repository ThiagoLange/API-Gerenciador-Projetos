package br.com.sustentavel.gerenciadorprojetos.config;

import br.com.sustentavel.gerenciadorprojetos.domain.Usuario;
import br.com.sustentavel.gerenciadorprojetos.repository.UsuarioRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 1. Define um codificador de senhas. Senhas NUNCA devem ser salvas em texto plano.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 2. Define como o Spring Security buscará os usuários no banco de dados.
    @Bean
    public UserDetailsService userDetailsService(UsuarioRepository usuarioRepository) {
        return username -> {
            Usuario usuario = usuarioRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));

            return new User(
                    usuario.getUsername(),
                    usuario.getSenha(),
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + usuario.getPerfil().name()))
            );
        };
    }

    // 3. Configura as regras de segurança HTTP.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // Libera o acesso ao console do H2
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
                        // Define que rotas que começam com /admin só podem ser acessadas por ADMINS
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        // Qualquer outra requisição precisa de autenticação
                        .anyRequest().authenticated()
                )
                // Habilita o formulário de login padrão do Spring Security
                .formLogin(form -> form.defaultSuccessUrl("/", true))
                .logout(logout -> logout.logoutSuccessUrl("/login"));

        // Configurações necessárias para o H2 console funcionar com Spring Security
        http.csrf(csrf -> csrf.ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")));
        http.headers(headers -> headers.frameOptions().sameOrigin());

        return http.build();
    }
}