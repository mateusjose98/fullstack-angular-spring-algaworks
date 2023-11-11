package com.algamoney.api.repository.lancamento;

import com.algamoney.api.model.Lancamento;
import com.algamoney.api.repository.filter.LancamentoFilter;
import com.algamoney.api.repository.projection.ResumoLancamento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class LancamentoRepositoryImpl implements LancamentoRepositoryQuery{

    @PersistenceContext
    private EntityManager manager;
    @Override
    public Page<Lancamento> filtrar(LancamentoFilter filter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Lancamento> criteriaQuery = builder.createQuery(Lancamento.class);
        Root<Lancamento> root = criteriaQuery.from(Lancamento.class);
        Predicate[] predicates = criarRestricoes(filter, builder, root);
        criteriaQuery.where(predicates);
        TypedQuery<Lancamento> query = manager.createQuery(criteriaQuery);
        adicionarRestricoesPaginacao(query, pageable);
        return new PageImpl<>(query.getResultList(), pageable, total(filter));
    }

    @Override
    public Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<ResumoLancamento> criteria = builder.createQuery(ResumoLancamento.class);
        Root<Lancamento> root = criteria.from(Lancamento.class);

        criteria.select(builder.construct(ResumoLancamento.class,
                root.get("id"),
                root.get("descricao"),
                root.get("dataVencimento"),
                root.get("dataPagamento"),
                root.get("valor"),
                root.get("tipo"),
                root.get("categoria").get("nome"),
                root.get("pessoa").get("nome")));

        Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, root);
        criteria.where(predicates);

        TypedQuery<ResumoLancamento> query = manager.createQuery(criteria);
        adicionarRestricoesPaginacao(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(lancamentoFilter));
    }

    private Long total(LancamentoFilter filter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<Lancamento> root = criteriaQuery.from(Lancamento.class);
        Predicate[] predicates = criarRestricoes(filter, builder, root);
        criteriaQuery.where(predicates);
        criteriaQuery.select(builder.count(root));
        return manager.createQuery(criteriaQuery).getSingleResult();
    }

    private void adicionarRestricoesPaginacao(TypedQuery<?> query, Pageable pageable) {
        int atual = pageable.getPageNumber();
        int tamanho = pageable.getPageSize();
        int primeiroRegistroPagina = atual * tamanho;
        query.setFirstResult(primeiroRegistroPagina);
        query.setMaxResults(tamanho);
        
    }

    private Predicate[] criarRestricoes(LancamentoFilter filter,
                                        CriteriaBuilder builder,
                                        Root<Lancamento> root) {
        List<Predicate> predicates = new ArrayList<>();
        if(StringUtils.hasText(filter.getDescricao())) {
            predicates.add(builder.like(
                    builder.lower(root.get("descricao")),
                    "%" + filter.getDescricao().toLowerCase() + "%"
            ));
        }
        if(filter.getDataVencimentoDe() != null) {
            predicates.add(builder.greaterThanOrEqualTo(
                    root.get("dataVencimento"), filter.getDataVencimentoDe()
            ));
        }
        if(filter.getDataVencimentoAte() != null) {
            predicates.add(builder.lessThanOrEqualTo(
                    root.get("dataVencimento"), filter.getDataVencimentoAte()
            ));
        }

        return predicates.toArray(new Predicate[predicates.size()]);
    }
}
