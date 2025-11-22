package com.projeto.controller;

import com.projeto.entity.Pessoa;
import com.projeto.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    // Criar nova pessoa
    @PostMapping
    public ResponseEntity<Pessoa> criarPessoa(@RequestBody Pessoa pessoa) {
        Pessoa salva = pessoaService.salvar(pessoa);
        return ResponseEntity.ok(salva);
    }

    // Atualizar pessoa existente
    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> atualizarPessoa(@PathVariable Long id, @RequestBody Pessoa pessoa) {
        Optional<Pessoa> existente = pessoaService.buscarPorId(id);
        if (existente.isPresent()) {
            pessoa.setId(id);
            Pessoa atualizada = pessoaService.salvar(pessoa);
            return ResponseEntity.ok(atualizada);
        }
        return ResponseEntity.notFound().build();
    }

    // Listar todas as pessoas (página opcional, padrão 0)
    @GetMapping
    public ResponseEntity<Page<Pessoa>> listarPessoas(@RequestParam(defaultValue = "0") int pagina) {
        Page<Pessoa> lista = pessoaService.listarTodos(pagina);
        return ResponseEntity.ok(lista);
    }

    // Listar apenas pessoas ativas
    @GetMapping("/ativos")
    public ResponseEntity<Page<Pessoa>> listarAtivos(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho) {

        Pageable pageable = PageRequest.of(pagina, tamanho);
        Page<Pessoa> lista = pessoaService.listarAtivos(pageable);
        return ResponseEntity.ok(lista);
    }

    // Buscar pessoa por ID
    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> buscarPorId(@PathVariable Long id) {
        Optional<Pessoa> pessoa = pessoaService.buscarPorId(id);
        return pessoa.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Deletar pessoa por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id) {
        pessoaService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
