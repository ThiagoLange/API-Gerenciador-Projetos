package br.com.sustentavel.gerenciadorprojetos.repository;

import br.com.sustentavel.gerenciadorprojetos.domain.Organizacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizacaoRepository extends JpaRepository<Organizacao, Long> {
    // Métodos de busca customizados podem ser adicionados aqui
}