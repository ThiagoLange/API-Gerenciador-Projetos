package br.com.sustentavel.gerenciadorprojetos.controller;

import br.com.sustentavel.gerenciadorprojetos.dto.OrganizacaoRequestDTO;
import br.com.sustentavel.gerenciadorprojetos.dto.OrganizacaoResponseDTO;
import br.com.sustentavel.gerenciadorprojetos.service.OrganizacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/organizations")
public class OrganizacaoController {

    private final OrganizacaoService organizacaoService;

    @Autowired
    public OrganizacaoController(OrganizacaoService organizacaoService) {
        this.organizacaoService = organizacaoService;
    }

    @GetMapping
    public ResponseEntity<List<OrganizacaoResponseDTO>> listarTodas() {
        return ResponseEntity.ok(organizacaoService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrganizacaoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(organizacaoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<OrganizacaoResponseDTO> cadastrar(@RequestBody OrganizacaoRequestDTO dto) {
        OrganizacaoResponseDTO novaOrganizacao = organizacaoService.cadastrar(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(novaOrganizacao.id()).toUri();
        return ResponseEntity.created(uri).body(novaOrganizacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrganizacaoResponseDTO> atualizar(@PathVariable Long id, @RequestBody OrganizacaoRequestDTO dto) {
        return ResponseEntity.ok(organizacaoService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        organizacaoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}