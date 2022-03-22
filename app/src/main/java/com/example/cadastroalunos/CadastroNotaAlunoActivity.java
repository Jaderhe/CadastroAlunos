package com.example.cadastroalunos;

import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadastroalunos.adapters.NotaAlunoAdapter;
import com.example.cadastroalunos.dao.SugarDAO;
import com.example.cadastroalunos.model.AlunoTurma;
import com.example.cadastroalunos.model.Disciplina;
import com.example.cadastroalunos.model.DisciplinaTurma;
import com.example.cadastroalunos.model.Turma;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import fr.ganfra.materialspinner.MaterialSpinner;

public class CadastroNotaAlunoActivity extends BaseActivity {
    private RecyclerView rvListaAlunos;
    private LinearLayout lnNotaAluno;
    MaterialSpinner spTurma;
    MaterialSpinner spDisciplina;
    Turma turmaSelecionada;
    Disciplina disciplinaSelecionada;
    List<Disciplina> disciplinaTurmas = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.NotaAlunoTitle);
        setContentView(R.layout.activity_cadastro_nota_aluno);
        loadComponents();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    void loadComponents() {
        lnNotaAluno = findViewById(R.id.lnNotaAluno);
        rvListaAlunos = findViewById(R.id.rvListaAlunos);
        spTurma = findViewById(R.id.spTurma);
        spDisciplina = findViewById(R.id.spDisciplina);
        List<Turma> turmas = SugarDAO.retornaObjetos(Turma.class, "nome asc");
        spTurma.setAdapter(new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, turmas));
        spDisciplina.setAdapter(new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, disciplinaTurmas));

        spTurma.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spTurma.getSelectedItemPosition() != 0) {
                    turmaSelecionada = turmas.get(spTurma.getSelectedItemPosition() - 1);
                    disciplinaTurmas.clear();
                    List<DisciplinaTurma> test = DisciplinaTurma.find(DisciplinaTurma.class, " turma = ? ", turmaSelecionada.getId().toString());
                    disciplinaTurmas.addAll(test.stream().map(DisciplinaTurma::getDisciplina).collect(Collectors.toList()));
                    spDisciplina.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            if (spDisciplina.getSelectedItemPosition() != 0) {
                                disciplinaSelecionada = disciplinaTurmas.get(spDisciplina.getSelectedItemPosition() - 1);
                                criaListaAlunos();
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                            disciplinaSelecionada = null;
                        }
                    });
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                turmaSelecionada = null;
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void criaListaAlunos() {
        NotaAlunoAdapter adapter = new NotaAlunoAdapter(AlunoTurma.find(AlunoTurma.class, " turma = ?", turmaSelecionada.getId().toString()).stream().map(AlunoTurma::getAluno).collect(Collectors.toList()), this);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvListaAlunos.setLayoutManager(llm);
        rvListaAlunos.setAdapter(adapter);
        rvListaAlunos.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        };
    }

    @Override
    void salvar() {

    }
}