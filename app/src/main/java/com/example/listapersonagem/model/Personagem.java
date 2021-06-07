package com.example.listapersonagem.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Personagem implements Serializable {
    /*criação das variaveis locais e os parametros*/
    private String nome;
    private String altura;
    private String nascimento;
    private String telefone;
    private String RG;
    private String CEP;
    private String genero;
    private int id = 0;


    // salva as informações preenchidas pelo usuario.
    public Personagem(String nome, String altura, String nascimento, String telefone, String RG, String CEP, String genero) {

        this.nome = nome;
        this.altura = altura;
        this.nascimento = nascimento;
        this.telefone = telefone;
        this.RG = RG;
        this.CEP = CEP;
        this.genero = genero;
    }

    public Personagem() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getRG() {
        return RG;
    }

    public void setRG(String RG) {
        this.RG = RG;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public String getGenero() {
        return genero;
    }

    public void setgenero(String genero) {
        this.genero = genero;
    }

    // transformando as informações para string
    @NonNull
    @Override
    //informações a serem colocadas na lista
    public String toString() {
        return nome;
    }

    public void SetId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean idvalido() {
        return id > 0;
    }

    /*public String getNome() {
        return nome;
    }

    public String getAltura() {
        return altura;
    }

    public String getNascimento() {
        return nascimento;
    }*/

}

