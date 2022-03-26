package com.example.cadastroalunos.model;

import com.orm.SugarRecord;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Aluno extends SugarRecord implements Serializable {

    int ra;
    String nome;
    String cpf;
    String dtNasc;
    String dtMatricula;
    String curso;
    String periodo;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aluno aluno = (Aluno) o;
        return ra == aluno.ra && Objects.equals(nome, aluno.nome) && Objects.equals(cpf, aluno.cpf) && Objects.equals(dtNasc, aluno.dtNasc) && Objects.equals(dtMatricula, aluno.dtMatricula) && Objects.equals(curso, aluno.curso) && Objects.equals(periodo, aluno.periodo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ra, nome, cpf, dtNasc, dtMatricula, curso, periodo);
    }
}
