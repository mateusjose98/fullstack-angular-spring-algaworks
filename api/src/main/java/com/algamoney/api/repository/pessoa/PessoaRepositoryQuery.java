package com.algamoney.api.repository.pessoa;


import com.algamoney.api.model.Pessoa;

import com.algamoney.api.repository.filter.PessoaFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PessoaRepositoryQuery {
    Page<Pessoa> filtrar(PessoaFilter filter, Pageable pageable);
}
