package com.example.cadastroalunos;

import static java.util.Objects.nonNull;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.cadastroalunos.adapters.ProfessorAdapter;
import com.example.cadastroalunos.adapters.ResultadoAdapter;
import com.example.cadastroalunos.dao.SugarDAO;
import com.example.cadastroalunos.model.Aluno;
import com.example.cadastroalunos.model.Disciplina;
import com.example.cadastroalunos.model.DisciplinaTurma;
import com.example.cadastroalunos.model.Professor;
import com.example.cadastroalunos.model.Turma;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import fr.ganfra.materialspinner.MaterialSpinner;

public class ListaResultadoActivity extends BaseListActivity {

    MaterialSpinner spTurma;
    MaterialSpinner spDisciplina;
    List<Disciplina> disciplinaTurmas = new ArrayList<>();
    Turma turmaSelecionada;
    Disciplina disciplinaSelecionada;

    public ListaResultadoActivity() {
        super(R.layout.activity_lista_resultado,
                R.string.ResultadoTitle,
                R.id.rvListaResultado,
                R.id.listaResultados,
                "",
                null);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    void doSomeOnCreate() {
        recyclerView.setVisibility(View.INVISIBLE);
        List<Turma> turmas = SugarDAO.retornaObjetos(Turma.class, "nome asc");
        spTurma = findViewById(R.id.spTurma);
        spDisciplina = findViewById(R.id.spDisciplina);
        spTurma.setAdapter(new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, turmas));
        spDisciplina.setAdapter(new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, disciplinaTurmas));
        spTurma.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spTurma.getSelectedItemPosition() != 0) {
                    turmaSelecionada = turmas.get(spTurma.getSelectedItemPosition() - 1);
                    disciplinaTurmas.clear();
                    List<DisciplinaTurma> test = com.example.cadastroalunos.model.DisciplinaTurma.find(DisciplinaTurma.class, " turma = ? ", turmaSelecionada.getId().toString());
                    disciplinaTurmas.addAll(test.stream().map(DisciplinaTurma::getDisciplina).collect(Collectors.toList()));
                    spDisciplina.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            if (spDisciplina.getSelectedItemPosition() != 0) {
                                disciplinaSelecionada = disciplinaTurmas.get(spDisciplina.getSelectedItemPosition() - 1);
                                carregaAdapter();
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                            disciplinaSelecionada = null;
                        }
                    });
                    carregaAdapter();
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                turmaSelecionada = null;
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void carregaAdapter() {
        if (nonNull(turmaSelecionada) && nonNull(disciplinaSelecionada)) {
            recyclerView.setVisibility(View.VISIBLE);
            fillList();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    RecyclerView.Adapter createAdapter() {
        return new ResultadoAdapter(SugarDAO.retornaObjetos(Aluno.class, "nome asc"), turmaSelecionada, disciplinaSelecionada, this);
    }
}