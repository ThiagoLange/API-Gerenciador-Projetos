package br.com.sustentavel.gerenciadorprojetos.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data // Anotação do Lombok para gerar getters, setters, toString, etc.
@NoArgsConstructor // Construtor sem argumentos
@AllArgsConstructor // Construtor com todos os argumentos
@Entity // Indica que esta classe é uma entidade JPA
@Table(name = "organizacoes") // Define o nome da tabela no banco
public class Organizacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Estratégia de auto-incremento
    private Long id;

    @Column(nullable = false, length = 150) // Campo não nulo, com tamanho máximo
    private String nome;

    @Column(length = 100)
    private String contato;
}