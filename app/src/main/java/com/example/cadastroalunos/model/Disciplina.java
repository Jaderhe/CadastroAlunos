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
public class Disciplina extends SugarRecord {
    String nome;
    Integer cargaHoraria;
    Professor professor;
    Turma turma;
}
