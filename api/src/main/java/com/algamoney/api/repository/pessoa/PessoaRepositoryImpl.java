package com.algamoney.api.repository.pessoa;

import com.algamoney.api.model.Pessoa;
import com.algamoney.api.repository.filter.PessoaFilter;
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

public class PessoaRepositoryImpl implements PessoaRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Pessoa> filtrar(PessoaFilter filter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Pessoa> criteriaQuery = builder.createQuery(Pessoa.class);
        Root<Pessoa> root = criteriaQuery.from(Pessoa.class);
        Predicate[] predicates = criarRestricoes(filter, builder, root);
        criteriaQuery.where(predicates);
        TypedQuery<Pessoa> query = manager.createQuery(criteriaQuery);
        adicionarRestricoesPaginacao(query, pageable);
        return new PageImpl<>(query.getResultList(), pageable, total(filter));
    }

    private Predicate[] criarRestricoes(PessoaFilter filter, CriteriaBuilder builder, Root<Pessoa> root) {
        List<Predicate> predicates = new ArrayList<>();
        if (StringUtils.hasText(filter.getNome())) {
            predicates.add(builder.like(
                    builder.lower(root.get("nome")),
                    "%" + filter.getNome().toLowerCase() + "%"
            ));
        }
        return predicates.toArray(new Predicate[predicates.size()]);
    }

    private void adicionarRestricoesPaginacao(TypedQuery<Pessoa> query, Pageable pageable) {
        int atual = pageable.getPageNumber();
        int tamanho = 5;
        int primeiroRegistroPagina = atual * tamanho;
        query.setFirstResult(primeiroRegistroPagina);
        query.setMaxResults(tamanho);
    }

    private long total(PessoaFilter filter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<Pessoa> root = criteriaQuery.from(Pessoa.class);
        Predicate[] predicates = criarRestricoes(filter, builder, root);
        criteriaQuery.where(predicates);
        criteriaQuery.select(builder.count(root));
        return manager.createQuery(criteriaQuery).getSingleResult();
    }
}
