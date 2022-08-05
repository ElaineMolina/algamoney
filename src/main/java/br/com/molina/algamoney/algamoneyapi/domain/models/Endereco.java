package br.com.molina.algamoney.algamoneyapi.domain.models;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Embeddable
public class Endereco {

    @NotNull
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

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
