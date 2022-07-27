package br.com.molina.algamoney.algamoneyapi.domain.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;


@Getter
@Setter
@Entity
@Table(name = "pessoa")
public class PessoaModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @NotNull
    @Size(min = 3, max = 100)
    private String nome;

    @NotNull
    private boolean ativo;

    @Embedded
    private EnderecoModel enderecoModel;


}
