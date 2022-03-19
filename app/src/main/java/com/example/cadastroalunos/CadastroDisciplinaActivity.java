package com.example.cadastroalunos;

import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;

import com.example.cadastroalunos.dao.SugarDAO;
import com.example.cadastroalunos.model.Disciplina;
import com.example.cadastroalunos.model.Professor;
import com.example.cadastroalunos.util.CpfMask;
import com.example.cadastroalunos.util.Util;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

import fr.ganfra.materialspinner.MaterialSpinner;

public class CadastroDisciplinaActivity extends BaseActivity {

    private TextInputEditText edNomeDisciplina;
    private TextInputEditText edCargaHorariaDisciplina;
    private MaterialSpinner spProfessor;
    private LinearLayout lnDisciplina;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.CadDisciplinaTitle);
        setContentView(R.layout.activity_cadastro_disciplina);
        loadComponents();
        edCargaHorariaDisciplina.addTextChangedListener(CpfMask.insert(edCargaHorariaDisciplina));
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    void loadComponents() {
        edNomeDisciplina = findViewById(R.id.edNomeDisciplina);
        edCargaHorariaDisciplina = findViewById(R.id.edCargaHorariaDisciplina);
        spProfessor = findViewById(R.id.spProfessor);
        lnDisciplina = findViewById(R.id.lnDisciplina);
        inputs = Arrays.asList(edCargaHorariaDisciplina, edNomeDisciplina);
        ArrayAdapter adapterProfessores = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, SugarDAO.retornaObjetos(Professor.class, "nome asc"));
        spProfessor.setAdapter(adapterProfessores);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    void validaCamposExtras(AtomicBoolean valido) {
        if (Objects.isNull(spProfessor.getSelectedItem())) {
            valido.set(false);
            spProfessor.requestFocus();
            spProfessor.setError("Selecione um Professor");

        }
    }

    void salvar() {
        Disciplina disciplina = Disciplina
                .builder()
                .cargaHoraria(Integer.valueOf(edCargaHorariaDisciplina.getText().toString().trim().replace(".", "").replace(",", "")))
                .nome(edNomeDisciplina.getText().toString().trim())
                .professor((Professor) spProfessor.getSelectedItem())
                .build();
        if (SugarDAO.salvar(disciplina) > 0) {
            setResult(RESULT_OK);
            finish();
        } else
            Util.customSnackBar(lnDisciplina, "Erro ao salvar o Disciplina (" + disciplina.getNome() + ") " +
                    "verifique o log", 0);

    }
}