package br.com.datarey.test.entity;

import java.time.LocalDate;
import java.util.List;

import br.com.datarey.model.type.Dinheiro3;

public class Usuario {

    private String nome;
    private String senha;
    private Integer inteiro;
    private LocalDate data = LocalDate.now();
    private Dinheiro3 dinheiro3;

    private List<String> lista;

    public List<String> getLista() {
        return this.lista;
    }

    public void setLista(List<String> lista) {
        this.lista = lista;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getInteiro() {
        return this.inteiro;
    }

    public void setInteiro(Integer inteiro) {
        this.inteiro = inteiro;
    }

    public LocalDate getData() {
        return this.data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Long getThreadId() {
        return Thread.currentThread().getId();
    }

    public Dinheiro3 getDinheiro3() {
        return this.dinheiro3;
    }

    public void setDinheiro3(Dinheiro3 dinheiro3) {
        this.dinheiro3 = dinheiro3;
    }
    
    

}
