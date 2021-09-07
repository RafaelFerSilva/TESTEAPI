package br.com.brasilprev.pojo;

import java.util.Collections;
import java.util.List;

public class Pessoa {

    private String nome;

    private String cpf;

    private List<Endereco> enderecos;

    private List<Telefone> telefones;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(Telefone telefones) {
        this.telefones = Collections.singletonList(telefones);
    }
}
