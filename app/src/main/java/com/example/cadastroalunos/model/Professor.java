package com.example.cadastroalunos.model;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.util.Objects;

public class Professor extends SugarRecord {

    private String nome;
    private String cpf;

    public Professor() {
    }

    public Professor(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Professor)) return false;
        Professor professor = (Professor) o;
        return Objects.equals(getNome(), professor.getNome()) && Objects.equals(getCpf(), professor.getCpf());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNome(), getCpf());
    }
}
