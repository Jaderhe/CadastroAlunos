package com.example.cadastroalunos.model;

import com.orm.SugarRecord;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AlunoTurma extends SugarRecord {
    Aluno aluno;
    Turma turma;
}
