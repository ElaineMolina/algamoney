package br.com.molina.algamoney.algamoneyapi.domain.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
@Embeddable
public class EnderecoModel {

    @NotNull
    @NotBlank
    @Size(min = 3, max = 150)
    private String logradouro;

    @NotNull
    private String numero;

    @Size(min = 3, max = 40)
    private String complemento;

    @NotNull
    @Size(min = 3, max = 40)
    private String bairro;

    @NotNull
    private String cep;

    @NotNull
    @Size(min = 3, max = 25)
    private String cidade;

    @NotNull
    private String estado;



}
