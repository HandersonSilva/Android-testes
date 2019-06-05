package com.example.frankenstein.Entidades;

public class Usuario {
    private int _id;
    private  String nome;
    private String funcao;
    private String senha;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getNome() {
        return nome;
    }

    public String getFuncao() {
        return funcao;
    }

    public String getSenha() {
        return senha;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


}
