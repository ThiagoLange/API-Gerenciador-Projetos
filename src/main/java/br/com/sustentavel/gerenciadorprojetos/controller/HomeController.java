package br.com.sustentavel.gerenciadorprojetos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String paginaInicial() {
        return "Bem-vindo à API de Projetos Sustentáveis!";
    }

    @GetMapping("/admin")
    public String paginaAdmin() {
        return "Esta é uma página restrita para administradores.";
    }

    @GetMapping("/user")
    public String paginaUser() {
        return "Esta é uma página para usuários logados.";
    }
}