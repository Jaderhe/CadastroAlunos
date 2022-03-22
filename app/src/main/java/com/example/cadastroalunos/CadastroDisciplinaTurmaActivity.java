package com.example.cadastroalunos;

import static java.util.Objects.nonNull;

import androidx.annotation.RequiresApi;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TableRow;

import com.example.cadastroalunos.dao.SugarDAO;
import com.example.cadastroalunos.model.Disciplina;
import com.example.cadastroalunos.model.DisciplinaTurma;
import com.example.cadastroalunos.model.Turma;
import com.example.cadastroalunos.util.Util;

import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;

public class CadastroDisciplinaTurmaActivity extends BaseActivity {

    MaterialSpinner spTurma;
    Turma turmaSelecionada;
    List<DisciplinaTurma> disciplinaDaTurmaSelecionada;
    LinearLayout lnDisciplinaTurma;
    LinearLayout lnDisciplinas;
    private List<Disciplina> disciplinasDoBanco;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.DisciplinaTurmaTitle);
        setContentView(R.layout.activity_cadastro_disciplina_turma);
        loadComponents();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    void loadComponents() {
        spTurma = findViewById(R.id.spTurma);
        lnDisciplinaTurma = findViewById(R.id.lnDisciplinaTurma);
        lnDisciplinas = findViewById(R.id.lnDisciplinaCheckBox);
        List<Turma> turmas = SugarDAO.retornaObjetos(Turma.class, "nome asc");
        disciplinasDoBanco = SugarDAO.retornaObjetos(Disciplina.class);
        spTurma.setAdapter(new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, turmas));

        criaCheckbox(disciplinasDoBanco);
        spTurma.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spTurma.getSelectedItemPosition() != 0) {
                    turmaSelecionada = turmas.get(spTurma.getSelectedItemPosition() - 1);
                    preencheDisciplinaTurma();
                    criaCheckbox(disciplinasDoBanco);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                turmaSelecionada = null;
            }
        });
    }

    private void preencheDisciplinaTurma() {
        disciplinaDaTurmaSelecionada = DisciplinaTurma.find(DisciplinaTurma.class, " turma = ?", turmaSelecionada.getId().toString());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void criaCheckbox(List<Disciplina> disciplinas) {
        lnDisciplinas.removeAllViews();
        disciplinas.forEach(disciplina -> {
            TableRow row = new TableRow(this);
            row.setId(disciplina.getId().intValue());
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            CheckBox checkBox = new CheckBox(this);
            checkBox.setOnCheckedChangeListener((compoundButton, b) -> {
                if (nonNull(turmaSelecionada)) {
                    if (b) {
                        turmaSelecionada.adicionaDisciplina(bucaDisciplinaTurmaPorDisciplina(disciplina));
                    } else {
                        turmaSelecionada.removeDisciplina(bucaDisciplinaTurmaPorDisciplina(disciplina));
                    }
                }
            });
            if (nonNull(turmaSelecionada)) {
                checkBox.setChecked(turmaSelecionada.containsDisciplina(disciplina));
            }
            checkBox.setId(disciplina.getId().intValue());
            checkBox.setText(disciplina.getNome());
            checkBox.setEnabled(nonNull(turmaSelecionada));
            row.addView(checkBox);
            lnDisciplinas.addView(row);
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private DisciplinaTurma bucaDisciplinaTurmaPorDisciplina(Disciplina disciplina) {
        return disciplinaDaTurmaSelecionada.stream().filter(obj -> obj.getDisciplina().equals(disciplina)).findAny().orElse(criaDisciplinaTurma(disciplina, turmaSelecionada));
    }

    private DisciplinaTurma criaDisciplinaTurma(Disciplina disciplina, Turma turmaSelecionada) {
        return DisciplinaTurma.builder().turma(turmaSelecionada).disciplina(disciplina).build();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    void salvar() {
        if (nonNull(turmaSelecionada)) {
            turmaSelecionada.getDisciplinas().forEach(SugarDAO::salvar);
            disciplinaDaTurmaSelecionada.stream().filter(obj -> !turmaSelecionada.getDisciplinas().contains(obj)).forEach(SugarDAO::delete);
            preencheDisciplinaTurma();
            Util.customSnackBar(lnDisciplinaTurma, "Alterações salvas com sucesso!", 1);

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    void limparCampos() {
        super.limparCampos();
        turmaSelecionada = null;
        criaCheckbox(disciplinasDoBanco);
        spTurma.setSelection(0);
    }
}