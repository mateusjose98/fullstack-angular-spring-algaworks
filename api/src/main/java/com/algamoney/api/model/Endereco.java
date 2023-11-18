package com.algamoney.api.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Embeddable
@Data
public class Endereco {

    @NotBlank
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    @NotBlank
    private String cep;
    private String cidade;
    private String estado;
}
