package com.algamoney.api.repository.projection;

import com.algamoney.api.enums.TipoLancamento;
import com.algamoney.api.model.Categoria;
import com.algamoney.api.model.Pessoa;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
@Data
public class ResumoLancamento {

    private Long id;
    private String descricao;
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;
    private BigDecimal valor;
    private String tipo;
    private String categoria;
    private String pessoa;
}
