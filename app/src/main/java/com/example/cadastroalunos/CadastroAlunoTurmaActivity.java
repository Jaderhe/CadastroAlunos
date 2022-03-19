package com.example.cadastroalunos;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TableRow;

import com.example.cadastroalunos.dao.SugarDAO;
import com.example.cadastroalunos.model.Aluno;
import com.example.cadastroalunos.model.Disciplina;
import com.example.cadastroalunos.model.Professor;
import com.example.cadastroalunos.model.Turma;
import com.example.cadastroalunos.util.Util;

import java.util.List;
import java.util.Objects;

import fr.ganfra.materialspinner.MaterialSpinner;

public class CadastroAlunoTurmaActivity extends BaseActivity {

    MaterialSpinner spTurma;
    Turma turmaSelecionada;
    LinearLayout lnTurma;
    LinearLayout lnAlunos;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_aluno_turma);
        loadComponents();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    void loadComponents() {
        spTurma = findViewById(R.id.spTurma);
        lnTurma = findViewById(R.id.lnTurma);
        lnAlunos = findViewById(R.id.lnAlunos);
        List<Turma> turmas = SugarDAO.retornaObjetos(Turma.class, "nome asc");
        List<Aluno> alunos = SugarDAO.retornaObjetos(Aluno.class);
        spTurma.setAdapter(new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, turmas));

        alunos.forEach(aluno -> {
            TableRow row = new TableRow(this);
            row.setId(aluno.getId().intValue());
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            CheckBox checkBox = new CheckBox(this);
            checkBox.setOnCheckedChangeListener((compoundButton, b) -> {
                if (b) {
                    turmaSelecionada.adicionaAluno(aluno);
                } else {
                    turmaSelecionada.removeAluno(aluno);
                }
            });
            checkBox.setId(aluno.getId().intValue());
            checkBox.setText(aluno.getNome());
            row.addView(checkBox);
            lnAlunos.addView(row);
        });
        spTurma.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spTurma.getSelectedItemPosition() != 0) {
                    turmaSelecionada = turmas.get(spTurma.getSelectedItemPosition() - 1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                turmaSelecionada = null;
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    void salvar() {
        if (Objects.nonNull(turmaSelecionada)) {
            if (SugarDAO.salvar(turmaSelecionada) > 0) {
                setResult(RESULT_OK);
                finish();
            } else
                Util.customSnackBar(lnTurma, "Erro ao salvar o Disciplina (" + turmaSelecionada.getNome() + ") " +
                        "verifique o log", 0);

        }
    }
}