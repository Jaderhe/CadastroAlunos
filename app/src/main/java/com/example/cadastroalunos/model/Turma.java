package com.example.cadastroalunos.model;

import com.orm.SugarRecord;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Turma extends SugarRecord {

    String nome;
    List<Disciplina> disciplinas;
    List<Aluno> alunos;
}
