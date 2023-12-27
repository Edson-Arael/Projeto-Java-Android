package com.example.testeprojeto;

public class Multas {
    public int id;
    public String nome, placa, gravidade, valor;

    public Multas() {
    }

    public Multas(String nome, String placa, String gravidade, String valor) {
        this.nome = nome;
        this.placa = placa;
        this.gravidade = gravidade;
        this.valor = valor;
    }

    @Override
    public String toString(){
        return nome + " | " + placa+ " | " + valor + " | " + gravidade;
    }



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getGravidade() {
        return gravidade;
    }

    public void setGravidade(String gravidade) {
        this.gravidade = gravidade;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
