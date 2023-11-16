package com.algamoney.api.model;

import com.algamoney.api.enums.TipoLancamento;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "lancamento")
public class Lancamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String descricao;

    @Column(name = "data_vencimento")
    @NotNull
    private LocalDate dataVencimento;

    @Column(name = "data_pagamento")
    @NotNull
    private LocalDate dataPagamento;

    private BigDecimal valor;

    private String obs;

    @Enumerated(EnumType.STRING)
    @NotNull
    private TipoLancamento tipo;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;
}
