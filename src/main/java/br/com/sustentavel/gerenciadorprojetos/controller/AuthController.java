package br.com.sustentavel.gerenciadorprojetos.controller;

import br.com.sustentavel.gerenciadorprojetos.domain.Usuario;
import br.com.sustentavel.gerenciadorprojetos.dto.LoginRequestDTO;
import br.com.sustentavel.gerenciadorprojetos.dto.LoginResponseDTO;
import br.com.sustentavel.gerenciadorprojetos.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login") // ou "/auth" se preferir
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequest) {
        // Cria um objeto de autenticação com os dados recebidos
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password());

        // O Spring Security usa o AuthenticationManager para validar as credenciais
        Authentication auth = authenticationManager.authenticate(usernamePassword);

        // Se a autenticação for bem-sucedida, gera o token
        String token = tokenService.generateToken(auth);

        // Retorna o token no corpo da resposta
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
}