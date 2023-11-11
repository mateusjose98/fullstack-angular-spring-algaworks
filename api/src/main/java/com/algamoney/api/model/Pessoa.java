package com.algamoney.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
    private Endereco endereco;

    private Boolean ativo;

    void inativar() {
        this.setAtivo(false);
    }

    @JsonIgnore
    @Transient
    public boolean isAtivo() {
        return this.ativo;
    }
}
