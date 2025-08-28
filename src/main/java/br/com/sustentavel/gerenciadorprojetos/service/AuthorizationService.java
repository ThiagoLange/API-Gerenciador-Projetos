package br.com.sustentavel.gerenciadorprojetos.service;

import br.com.sustentavel.gerenciadorprojetos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority; // Novo import
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections; // Novo import

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o nome: " + username));

        // [CORREÇÃO CRÍTICA]
        // Estávamos retornando uma lista de permissões vazia.
        // Agora, estamos criando a permissão correta (ex: "ROLE_ADMIN") a partir do perfil do usuário.
        var authorities = Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_" + usuario.getPerfil().name())
        );

        return new User(usuario.getUsername(), usuario.getSenha(), authorities);
    }
}