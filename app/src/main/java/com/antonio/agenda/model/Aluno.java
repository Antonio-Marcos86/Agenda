package com.antonio.agenda.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Aluno implements Serializable {
    private int id = 0;
    private  String nome;
    private  String telefone;
    private  String email;

    public Aluno(String nome, String telefone, String email) {

        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public Aluno() {

    }


    public String getNome() {
        return this.nome;
    }
    @NonNull
    @Override
    public String toString() {
        return nome ;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email ;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void getNome(String nome) {
        this.nome = nome;
    }

    public boolean temIdValido() {
        return id >0;
    }
}
