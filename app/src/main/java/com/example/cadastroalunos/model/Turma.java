package com.example.cadastroalunos.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.orm.SugarRecord;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    @Builder.Default
    List<Disciplina> disciplinas = new ArrayList<>();
    @Builder.Default
    List<Aluno> alunos = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void adicionaAluno(Aluno aluno) {
        if (Objects.isNull(alunos)){
            alunos = new ArrayList<>();
        }
        alunos.add(aluno);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void removeAluno(Aluno aluno) {
        if (Objects.nonNull(alunos)) {
            alunos.remove(aluno);
        }
    }
}
