package com.example.cadastroalunos;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import androidx.annotation.RequiresApi;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TableRow;

import com.example.cadastroalunos.dao.SugarDAO;
import com.example.cadastroalunos.model.AlunoTurma;
import com.example.cadastroalunos.model.Disciplina;
import com.example.cadastroalunos.model.DisciplinaTurma;
import com.example.cadastroalunos.model.NotaAluno;
import com.example.cadastroalunos.model.PresencaAluno;
import com.example.cadastroalunos.model.Turma;
import com.example.cadastroalunos.util.Util;
import com.google.android.material.textfield.TextInputEditText;
import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import fr.ganfra.materialspinner.MaterialSpinner;

public class PresencaAlunoActivity extends BaseActivity {
    MaterialSpinner spTurma;
    MaterialSpinner spDisciplina;
    Turma turmaSelecionada;
    Disciplina disciplinaSelecionada;
    LinearLayout lnAlunos;
    LinearLayout lnPresencas;
    List<Disciplina> disciplinaTurmas = new ArrayList<>();
    private TextInputEditText edDataPresenca;
    private List<PresencaAluno> listaAlunos;
    private int vAno;
    private int vMes;
    private int vDia;
    private View dataSelecionada;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.PresencaAluno);
        setContentView(R.layout.activity_presenca_aluno);
        loadComponents();
        setDataAtual();
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    void loadComponents() {
        spTurma = findViewById(R.id.spTurma);
        spDisciplina = findViewById(R.id.spDisciplina);
        lnAlunos = findViewById(R.id.lnAlunos);
        lnPresencas = findViewById(R.id.lnPresencas);
        edDataPresenca = findViewById(R.id.edDataPresenca);
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
                    List<DisciplinaTurma> test = com.example.cadastroalunos.model.DisciplinaTurma.find(DisciplinaTurma.class, " turma = ? ", turmaSelecionada.getId().toString());
                    disciplinaTurmas.addAll(test.stream().map(DisciplinaTurma::getDisciplina).collect(Collectors.toList()));
                    spDisciplina.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            if (spDisciplina.getSelectedItemPosition() != 0) {
                                disciplinaSelecionada = disciplinaTurmas.get(spDisciplina.getSelectedItemPosition() - 1);
                                criarCheckbox();
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                            disciplinaSelecionada = null;
                        }
                    });
                    criarCheckbox();
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                turmaSelecionada = null;
            }
        });
        edDataPresenca.setFocusable(false);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void criarCheckbox() {
        listaAlunos = criaPresencaAluno();
        if (nonNull(listaAlunos) && nonNull(edDataPresenca.getText().toString()) && !edDataPresenca.getText().toString().isEmpty()) {
            lnAlunos.removeAllViews();
            listaAlunos.forEach(aluno -> {
                TableRow row = new TableRow(this);
                row.setId(View.generateViewId());
                row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                CheckBox checkBox = new CheckBox(this);
                checkBox.setOnCheckedChangeListener((compoundButton, b) -> {
                    aluno.setPresente(b);
                });
                checkBox.setChecked(aluno.isPresente());
                checkBox.setId(View.generateViewId());
                checkBox.setText(aluno.getAluno().getNome());
                row.addView(checkBox);
                lnAlunos.addView(row);
            });
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private List<PresencaAluno> criaPresencaAluno() {
        if (nonNull(turmaSelecionada) && nonNull(disciplinaSelecionada) && !edDataPresenca.getText().toString().isEmpty())
            return AlunoTurma.find(AlunoTurma.class, " turma = ?", turmaSelecionada.getId().toString()).stream()
                    .map(alunoTurma -> {
                        List<PresencaAluno> alunos = PresencaAluno.find(
                                PresencaAluno.class, " aluno = ? and turma = ? and disciplina = ? and data = ?",
                                alunoTurma.getAluno().getId().toString(),
                                alunoTurma.getTurma().getId().toString(),
                                disciplinaSelecionada.getId().toString(),
                                edDataPresenca.getText().toString()
                        );
                        if (!alunos.isEmpty()) {
                            return alunos.get(0);
                        }
                        return PresencaAluno.builder()
                                .with(alunoTurma)
                                .disciplina(disciplinaSelecionada)
                                .data(edDataPresenca.getText().toString())
                                .build();
                    }).collect(Collectors.toList());
        return Collections.emptyList();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    void salvar() {
        listaAlunos.forEach(presencaAluno -> {
            presencaAluno.setData(edDataPresenca.getText().toString());
            SugarDAO.salvar(presencaAluno);
        });
        Util.customSnackBar(lnPresencas, "Alterações salvas com sucesso!", 1);
        criarCheckbox();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    void limparCampos() {
        spTurma.setSelection(0);
        spDisciplina.setSelection(0);
        turmaSelecionada = null;
        disciplinaSelecionada = null;
        listaAlunos = new ArrayList<>();
        lnAlunos.removeAllViews();
        edDataPresenca.setText("");
    }

    public void selecionarData(View view) {
        dataSelecionada = view;
        showDialog(0);
    }


    private DatePickerDialog.OnDateSetListener setDatePicker =
            new DatePickerDialog.OnDateSetListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                    vAno = year;
                    vMes = month;
                    vDia = day;

                    atualizaData();
                }
            };

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void atualizaData() {
        TextInputEditText edit = (TextInputEditText) dataSelecionada;
        edit.setText(new StringBuilder().append(vDia).append("/")
                .append(vMes + 1).append("/")
                .append(vAno));

        criarCheckbox();
    }

    private void setDataAtual() {
        Calendar calendar = Calendar.getInstance();
        vDia = calendar.get(Calendar.DAY_OF_MONTH);
        vMes = calendar.get(Calendar.MONTH);
        vAno = calendar.get(Calendar.YEAR);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        setDataAtual();
        return new DatePickerDialog(this, setDatePicker,
                vAno, vMes, vDia);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    void validaCamposExtras(AtomicBoolean valido) {
        if (isNull(spDisciplina.getSelectedItem())) {
            spDisciplina.setError("Selecione uma Disciplina");
            spDisciplina.requestFocus();
            valido.set(false);
        }
        if (isNull(spTurma.getSelectedItem())) {
            spTurma.setError("Selecione uma Turma");
            spTurma.requestFocus();
            valido.set(false);
        }
        if (isNull(edDataPresenca.getText().toString()) || edDataPresenca.getText().toString().isEmpty()) {
            edDataPresenca.setError("Selecione uma Data");
            edDataPresenca.requestFocus();
            valido.set(false);
        }
    }
}