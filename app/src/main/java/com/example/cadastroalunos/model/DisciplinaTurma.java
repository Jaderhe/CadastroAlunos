package com.example.cadastroalunos.model;

import com.orm.SugarRecord;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DisciplinaTurma extends SugarRecord {
    Disciplina disciplina;
    Turma turma;
}
