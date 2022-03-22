package com.example.cadastroalunos.model;

import com.orm.SugarRecord;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class NotaAluno extends SugarRecord {
    Aluno aluno;
    Disciplina disciplina;
    Turma turma;
    Float nota;
}
