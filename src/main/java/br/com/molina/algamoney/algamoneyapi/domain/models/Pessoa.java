package br.com.molina.algamoney.algamoneyapi.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "pessoa")
public class Pessoa implements Serializable {
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
    private Endereco enderecoModel;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Endereco getEnderecoModel() {
        return enderecoModel;
    }

    public void setEnderecoModel(Endereco enderecoModel) {
        this.enderecoModel = enderecoModel;
    }

    @JsonIgnore
    @Transient
    public boolean isInativo(){
        return !this.ativo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return codigo.equals(pessoa.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}
