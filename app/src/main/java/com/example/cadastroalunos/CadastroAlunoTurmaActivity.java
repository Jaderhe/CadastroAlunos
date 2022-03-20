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
import com.example.cadastroalunos.model.Aluno;
import com.example.cadastroalunos.model.AlunoTurma;
import com.example.cadastroalunos.model.Turma;
import com.example.cadastroalunos.util.Util;

import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;

public class CadastroAlunoTurmaActivity extends BaseActivity {

    MaterialSpinner spTurma;
    Turma turmaSelecionada;
    List<AlunoTurma> alunosDaTurmaSelecionada;
    LinearLayout lnAlunoTurma;
    LinearLayout lnAlunos;
    private List<Aluno> alunosDoBanco;

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
        lnAlunoTurma = findViewById(R.id.lnAlunoTurma);
        lnAlunos = findViewById(R.id.lnAlunos);
        List<Turma> turmas = SugarDAO.retornaObjetos(Turma.class, "nome asc");
        alunosDoBanco = SugarDAO.retornaObjetos(Aluno.class);
        spTurma.setAdapter(new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, turmas));

        criaCheckbox(alunosDoBanco);
        spTurma.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spTurma.getSelectedItemPosition() != 0) {
                    turmaSelecionada = turmas.get(spTurma.getSelectedItemPosition() - 1);
                    alunosDaTurmaSelecionada = AlunoTurma.find(AlunoTurma.class, " turma = ?", turmaSelecionada.getId().toString());
                    criaCheckbox(alunosDoBanco);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                turmaSelecionada = null;
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void criaCheckbox(List<Aluno> alunos) {
        lnAlunos.removeAllViews();
        alunos.forEach(aluno -> {
            TableRow row = new TableRow(this);
            row.setId(aluno.getId().intValue());
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            CheckBox checkBox = new CheckBox(this);
            checkBox.setOnCheckedChangeListener((compoundButton, b) -> {
                if (nonNull(turmaSelecionada)) {
                    if (b) {
                        turmaSelecionada.adicionaAluno(bucaAlunoTurmaPorAluno(aluno));
                    } else {
                        turmaSelecionada.removeAluno(bucaAlunoTurmaPorAluno(aluno));
                    }
                }
            });
            if (nonNull(turmaSelecionada)) {
                checkBox.setChecked(turmaSelecionada.containsAluno(aluno));
            }
            checkBox.setId(aluno.getId().intValue());
            checkBox.setText(aluno.getNome());
            checkBox.setEnabled(nonNull(turmaSelecionada));
            row.addView(checkBox);
            lnAlunos.addView(row);
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private AlunoTurma bucaAlunoTurmaPorAluno(Aluno aluno) {
        return alunosDaTurmaSelecionada.stream().filter(obj -> obj.getAluno().equals(aluno)).findAny().orElse(criaAlunoTurma(aluno, turmaSelecionada));
    }

    private AlunoTurma criaAlunoTurma(Aluno aluno, Turma turmaSelecionada) {
        return AlunoTurma.builder().turma(turmaSelecionada).aluno(aluno).build();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    void salvar() {
        if (nonNull(turmaSelecionada)) {
            turmaSelecionada.getAlunoTurmas().forEach(SugarDAO::salvar);
            alunosDaTurmaSelecionada.stream().filter(obj -> !turmaSelecionada.getAlunoTurmas().contains(obj)).forEach(SugarDAO::delete);
            Util.customSnackBar(lnAlunoTurma, "Alterações salvas com sucesso!", 1);

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    void limparCampos() {
        super.limparCampos();
        turmaSelecionada = null;
        criaCheckbox(alunosDoBanco);
        spTurma.setSelection(0);
    }
}