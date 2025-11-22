package com.projeto.service;

import com.projeto.entity.Pessoa;
import com.projeto.repository.PessoaSpringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {
    @Autowired
    private PessoaSpringRepository pessoaRepository;

    // Criar ou atualizar pessoa
    public Pessoa salvar(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    // Buscar pessoa por ID
    public Optional<Pessoa> buscarPorId(Long id) {
        return pessoaRepository.findById(id);
    }

    // Listar todas as pessoas com paginação
    public Page<Pessoa> listarTodos(int pagina) {
        return pessoaRepository.findAll(PageRequest.of(pagina, 10));
    }

    // Listar apenas pessoas ativas
    public Page<Pessoa> listarAtivos(Pageable pageable) {
        return pessoaRepository.findByAtivoTrue(pageable);
    }

    // Deletar pessoa por ID
    public void deletarPorId(Long id) {
        pessoaRepository.deleteById(id);
    }
}
