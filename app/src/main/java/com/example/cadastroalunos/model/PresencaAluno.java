package com.example.cadastroalunos.model;

import com.orm.SugarRecord;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PresencaAluno extends SugarRecord implements Serializable {
    Aluno aluno;
    Disciplina disciplina;
    Turma turma;
    String data;
    boolean presente;

    public static class PresencaAlunoBuilder {

        public PresencaAlunoBuilder with(AlunoTurma alunoTurma) {
            return this.aluno(alunoTurma.getAluno()).turma(alunoTurma.getTurma());
        }
    }
}
