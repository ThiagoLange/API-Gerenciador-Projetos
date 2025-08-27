package br.com.sustentavel.gerenciadorprojetos.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false, unique = true, length = 100) // username deve ser único
    private String username;

    @Column(nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING) // Armazena a String ("ADMIN", "USER") no banco, que é mais robusto
    @Column(nullable = false, length = 20)
    private Perfil perfil;
}