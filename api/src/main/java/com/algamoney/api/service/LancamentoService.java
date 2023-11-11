package com.algamoney.api.service;

import com.algamoney.api.model.Lancamento;
import com.algamoney.api.model.Pessoa;
import com.algamoney.api.repository.LancamentoRepository;
import com.algamoney.api.repository.PessoaRepository;
import com.algamoney.api.service.exception.PessoaInexistenteOuInativaException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LancamentoService {

    final LancamentoRepository lancamentoRepository;
    final PessoaRepository pessoaRepository;

    public Lancamento salvar(Lancamento lancamento) {
        var pessoa = pessoaRepository.findById(lancamento.getPessoa().getId());
        if(pessoa.isEmpty() || !pessoa.get().isAtivo()) {
            throw new PessoaInexistenteOuInativaException("Pessoa inativa ou não existe");
        }

        return lancamentoRepository.save(lancamento);

    }

    public Lancamento atualizar(Long id, Lancamento lancamento) {
        Lancamento lancamentoSalvo = buscarLancamentoExistente(id);
        if (!lancamento.getPessoa().equals(lancamentoSalvo.getPessoa())) {
            validarPessoa(lancamento);
        }
        BeanUtils.copyProperties(lancamento, lancamentoSalvo, "id");
        return lancamentoRepository.save(lancamentoSalvo);
    }

    private Lancamento buscarLancamentoExistente(Long id) {
        return lancamentoRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    private void validarPessoa(Lancamento lancamento) {
        Pessoa pessoa = null;
        if (lancamento.getPessoa().getId() != null) {
            pessoa = pessoaRepository.findById(lancamento.getPessoa().getId()).get();
        }

        if (pessoa == null || !pessoa.isAtivo()) {
            throw new PessoaInexistenteOuInativaException("");
        }
    }
}
