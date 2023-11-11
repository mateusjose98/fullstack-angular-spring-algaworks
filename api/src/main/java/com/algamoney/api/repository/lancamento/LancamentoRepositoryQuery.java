package com.algamoney.api.repository.lancamento;

import com.algamoney.api.model.Lancamento;
import com.algamoney.api.repository.filter.LancamentoFilter;
import com.algamoney.api.repository.projection.ResumoLancamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LancamentoRepositoryQuery {

    Page<Lancamento> filtrar(LancamentoFilter filter, Pageable pageable);
    Page<ResumoLancamento> resumir(LancamentoFilter filter, Pageable pageable);

}
