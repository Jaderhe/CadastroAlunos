package com.example.cadastroalunos;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.cadastroalunos.dao.SugarDAO;
import com.example.cadastroalunos.model.Professor;
import com.example.cadastroalunos.util.CpfMask;
import com.example.cadastroalunos.util.Util;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Arrays;

public class CadastroProfessorActivity extends BaseActivity {

    private TextInputEditText edNomeProfessor;
    private TextInputEditText edCpfProfessor;
    private LinearLayout lnProfessor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.CadProfTitle);
        setContentView(R.layout.activity_cadastro_professor);
        loadComponents();
        edCpfProfessor.addTextChangedListener(CpfMask.insert(edCpfProfessor));
    }

    void loadComponents() {
        edNomeProfessor = findViewById(R.id.edNomeProfessor);
        edCpfProfessor = findViewById(R.id.edCpfProfessor);
        lnProfessor = findViewById(R.id.lnProfessor);
        inputs = Arrays.asList(edCpfProfessor, edNomeProfessor);
    }


    void salvar() {
        Professor professor = Professor
                .builder()
                .cpf(edCpfProfessor.getText().toString().trim())
                .nome(edNomeProfessor.getText().toString().trim())
                .build();
        if (SugarDAO.salvar(professor) > 0) {
            setResult(RESULT_OK);
            finish();
        } else
            Util.customSnackBar(lnProfessor, "Erro ao salvar o Professor (" + professor.getNome() + ") " +
                    "verifique o log", 0);

    }
}