package com.example.cadastroalunos;

import static java.util.Objects.nonNull;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.cadastroalunos.model.NotaAluno;
import com.example.cadastroalunos.util.CpfMask;
import com.example.cadastroalunos.util.Util;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class LancaNotaAlunoActivity extends BaseActivity {

    private NotaAluno notaAluno;
    TextInputEditText edRaAluno;
    TextInputEditText edNomeAluno;
    TextInputEditText edCpfAluno;
    TextInputEditText edNotaAluno;
    LinearLayout lnLancaNotaAluno;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lanca_nota_aluno);
        setTitle(R.string.LancarNota);
        notaAluno = (NotaAluno) getIntent().getSerializableExtra("notaAluno");
        notaAluno.getAluno().setId(getIntent().getLongExtra("idAluno", 0L));
        notaAluno.getTurma().setId(getIntent().getLongExtra("idTurma", 0L));
        notaAluno.getDisciplina().setId(getIntent().getLongExtra("idDisciplina", 0L));
        notaAluno.setId(getIntent().getLongExtra("idNotaAluno", 0L));
        loadComponents();
        edCpfAluno.addTextChangedListener(CpfMask.insert(edCpfAluno));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    void loadComponents() {
        edRaAluno = findViewById(R.id.edRaAluno);
        edNomeAluno = findViewById(R.id.edNomeAluno);
        edCpfAluno = findViewById(R.id.edCpfAluno);
        edNotaAluno = findViewById(R.id.edNotaAluno);
        lnLancaNotaAluno = findViewById(R.id.lnLancaNotaAluno);

        edRaAluno.setText(String.valueOf(notaAluno.getAluno().getRa()));
        edCpfAluno.setText(notaAluno.getAluno().getCpf());
        edNomeAluno.setText(notaAluno.getAluno().getNome());
        edNotaAluno.setText(Optional.ofNullable(notaAluno.getNota()).map(Object::toString).orElse(""));

        inputs = Arrays.asList(edRaAluno,
                edNomeAluno,
                edCpfAluno,
                edNotaAluno);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    void salvar() {

        notaAluno.setNota(Float.valueOf(edNotaAluno.getText().toString()));
        NotaAluno.save(notaAluno);
        Util.customSnackBar(lnLancaNotaAluno, "Nota salva com sucesso!", 1);

        setResult(RESULT_OK);
        finish();
    }


}