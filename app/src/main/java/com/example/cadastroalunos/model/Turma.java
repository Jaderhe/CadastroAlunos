package com.example.cadastroalunos.model;

import static java.util.Objects.nonNull;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.cadastroalunos.dao.SugarDAO;
import com.orm.SugarRecord;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class Turma extends SugarRecord implements Serializable {

    String nome;
    RegimeTurma regimeTurma;
    @Builder.Default
    @ToString.Exclude
    List<DisciplinaTurma> disciplinas = new ArrayList<>();
    @Builder.Default
    @ToString.Exclude
    List<AlunoTurma> alunoTurmas = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void adicionaAluno(AlunoTurma aluno) {
        if (Objects.isNull(alunoTurmas)) {
            alunoTurmas = new ArrayList<>();
        }
        alunoTurmas.add(aluno);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void removeAluno(AlunoTurma aluno) {
        if (nonNull(alunoTurmas)) {
            alunoTurmas.remove(aluno);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void adicionaDisciplina(DisciplinaTurma disciplinaTurma) {
        if (Objects.isNull(disciplinas)) {
            disciplinas = new ArrayList<>();
        }
        disciplinas.add(disciplinaTurma);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void removeDisciplina(DisciplinaTurma disciplinaTurma) {
        if (nonNull(disciplinas)) {
            disciplinas.remove(disciplinaTurma);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean containsAluno(Aluno aluno) {
        List<AlunoTurma> obj = AlunoTurma.find(AlunoTurma.class, " turma = ? and aluno = ?", getId().toString(), aluno.getId().toString());
        return nonNull(obj) && !obj.isEmpty();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean containsDisciplina(Disciplina disciplina) {
        List<DisciplinaTurma> obj = DisciplinaTurma.find(DisciplinaTurma.class, " turma = ? and disciplina = ?", getId().toString(), disciplina.getId().toString());
        return nonNull(obj) && !obj.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Turma turma = (Turma) o;
        return Objects.equals(nome, turma.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}
