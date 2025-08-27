package br.com.sustentavel.gerenciadorprojetos.service;

import br.com.sustentavel.gerenciadorprojetos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Este método é chamado pelo Spring Security para carregar o usuário pelo username.
     * Ao criar este bean, estamos dizendo ao Spring exatamente como encontrar nossos usuários.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o nome: " + username));

        // Aqui, retornamos a implementação UserDetails do próprio Spring Security.
        // O AuthenticationManager usará esta informação para validar a senha.
        return new User(usuario.getUsername(), usuario.getSenha(), new ArrayList<>());
    }
}