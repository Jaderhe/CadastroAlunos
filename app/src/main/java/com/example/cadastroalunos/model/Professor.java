package com.example.cadastroalunos.model;

import com.orm.SugarRecord;

import java.util.Objects;

public class Professor extends SugarRecord {

    private int idProfessor;
    private String nome;
    private String cpf;

    public Professor() {
    }

    public Professor(int idProfessor, String nome, String cpf) {
        this.idProfessor = idProfessor;
        this.nome = nome;
        this.cpf = cpf;
    }

    public int getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(int idProfessor) {
        this.idProfessor = idProfessor;
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
        return getIdProfessor() == professor.getIdProfessor() && Objects.equals(getNome(), professor.getNome()) && Objects.equals(getCpf(), professor.getCpf());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdProfessor(), getNome(), getCpf());
    }
}
