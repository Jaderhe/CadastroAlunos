package com.example.cadastroalunos;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.example.cadastroalunos.dao.SugarDAO;
import com.example.cadastroalunos.model.RegimeTurma;
import com.example.cadastroalunos.model.Turma;
import com.example.cadastroalunos.util.Util;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Arrays;

public class CadastroTurmaActivity extends BaseActivity {


    private TextInputEditText edNomeTurma;
    private RegimeTurma regimeTurmaSelecionado;
    private LinearLayout lnTurma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.CadTurmaTitle);
        setContentView(R.layout.activity_cadastro_turma);
        loadComponents();
    }


    @Override
    protected void loadComponents() {
        edNomeTurma = findViewById(R.id.edNomeTurma);
        lnTurma = findViewById(R.id.lnTurma);
        inputs = Arrays.asList(edNomeTurma);
    }

    @Override
    void salvar() {
        Turma turma = Turma
                .builder()
                .nome(edNomeTurma.getText().toString().trim())
                .regimeTurma(regimeTurmaSelecionado)
                .build();
        if (SugarDAO.salvar(turma) > 0) {
            setResult(RESULT_OK);
            finish();
        } else
            Util.customSnackBar(lnTurma, "Erro ao salvar o Turma (" + turma.getNome() + ") " +
                    "verifique o log", 0);

    }

    @SuppressLint("NonConstantResourceId")
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radioAnual:
                if (checked)
                    regimeTurmaSelecionado = RegimeTurma.ANUAL;
                break;
            case R.id.radioSemestral:
                if (checked)
                    regimeTurmaSelecionado = RegimeTurma.SEMESTRAL;
                break;
        }
    }
}