package com.algamoney.api.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min = 2, max = 100)
    private String nome;

    @Embedded
    @Valid
    private Endereco endereco;

    private Boolean ativo;

    void inativar() {
        this.setAtivo(false);
    }

}
