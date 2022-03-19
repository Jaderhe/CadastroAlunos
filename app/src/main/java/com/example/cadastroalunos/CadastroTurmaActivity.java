package com.example.cadastroalunos;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.cadastroalunos.dao.SugarDAO;
import com.example.cadastroalunos.model.Turma;
import com.example.cadastroalunos.util.Util;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Arrays;

public class CadastroTurmaActivity extends BaseActivity {


    private TextInputEditText edNomeTurma;
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
                .build();
        if (SugarDAO.salvar(turma) > 0) {
            setResult(RESULT_OK);
            finish();
        } else
            Util.customSnackBar(lnTurma, "Erro ao salvar o Turma (" + turma.getNome() + ") " +
                    "verifique o log", 0);

    }
}