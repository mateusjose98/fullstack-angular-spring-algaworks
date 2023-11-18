package com.algamoney.api.repository;

import com.algamoney.api.model.Pessoa;
import com.algamoney.api.repository.pessoa.PessoaRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>, PessoaRepositoryQuery {
}
