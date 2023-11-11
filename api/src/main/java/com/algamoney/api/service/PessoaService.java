package com.algamoney.api.service;

import com.algamoney.api.model.Pessoa;
import com.algamoney.api.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PessoaService {

    final PessoaRepository pessoaRepository;

    public void atualizarAtivoPessoa(Long id, Boolean ativo) {
        var pessoaSalva = buscarPessoa(id);
        pessoaSalva.setAtivo(ativo);
        pessoaRepository.save(pessoaSalva);
    }

    public Pessoa atualizarPessoa(Long id, Pessoa pessoa) {
        var pessoaSalva = buscarPessoa(id);
        BeanUtils.copyProperties(pessoa, pessoaSalva, "id");
        Pessoa saved = pessoaRepository.save(pessoaSalva);
        return saved;
    }

    public Pessoa buscarPessoa(Long id) {
        var pessoaSalva = pessoaRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
        return pessoaSalva;
    }
}
