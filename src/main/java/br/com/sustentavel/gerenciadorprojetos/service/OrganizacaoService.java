package br.com.sustentavel.gerenciadorprojetos.service;

import br.com.sustentavel.gerenciadorprojetos.domain.Organizacao;
import br.com.sustentavel.gerenciadorprojetos.dto.OrganizacaoRequestDTO;
import br.com.sustentavel.gerenciadorprojetos.dto.OrganizacaoResponseDTO;
import br.com.sustentavel.gerenciadorprojetos.repository.OrganizacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrganizacaoService {

    private final OrganizacaoRepository organizacaoRepository;

    @Autowired
    public OrganizacaoService(OrganizacaoRepository organizacaoRepository) {
        this.organizacaoRepository = organizacaoRepository;
    }

    @Transactional(readOnly = true)
    public List<OrganizacaoResponseDTO> listarTodas() {
        return organizacaoRepository.findAll()
                .stream()
                .map(OrganizacaoResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public OrganizacaoResponseDTO buscarPorId(Long id) {
        return organizacaoRepository.findById(id)
                .map(OrganizacaoResponseDTO::new)
                .orElseThrow(() -> new EntityNotFoundException("Organização não encontrada com o ID: " + id));
    }

    @Transactional
    public OrganizacaoResponseDTO cadastrar(OrganizacaoRequestDTO dto) {
        Organizacao novaOrganizacao = new Organizacao();
        novaOrganizacao.setNome(dto.nome());
        novaOrganizacao.setContato(dto.contato());

        Organizacao organizacaoSalva = organizacaoRepository.save(novaOrganizacao);
        return new OrganizacaoResponseDTO(organizacaoSalva);
    }

    @Transactional
    public OrganizacaoResponseDTO atualizar(Long id, OrganizacaoRequestDTO dto) {
        Organizacao organizacaoExistente = organizacaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Organização não encontrada com o ID: " + id));

        organizacaoExistente.setNome(dto.nome());
        organizacaoExistente.setContato(dto.contato());

        Organizacao organizacaoAtualizada = organizacaoRepository.save(organizacaoExistente);
        return new OrganizacaoResponseDTO(organizacaoAtualizada);
    }

    @Transactional
    public void excluir(Long id) {
        if (!organizacaoRepository.existsById(id)) {
            throw new EntityNotFoundException("Organização não encontrada com o ID: " + id);
        }
        organizacaoRepository.deleteById(id);
    }
}