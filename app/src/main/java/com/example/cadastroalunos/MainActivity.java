package com.example.cadastroalunos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.cadastroalunos.dao.AlunoDAO;
import com.example.cadastroalunos.model.Aluno;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.MainTitle);
        setContentView(R.layout.activity_main);
    }


    public void moduloAluno(View view) {
        Intent intent = new Intent(this, ListaAlunoActivity.class);
        startActivity(intent);
    }

    public void moduloProfessor(View view) {
        Intent intent = new Intent(this, ListaProfessorActivity.class);
        startActivity(intent);
    }

    public void moduloDisciplina(View view) {
        Intent intent = new Intent(this, ListaDisciplinasActivity.class);
        startActivity(intent);
    }

    public void moduloTurma(View view) {
        Intent intent = new Intent(this, ListaTurmaActivity.class);
        startActivity(intent);
    }

    public void moduloAlunoTurma(View view) {
        Intent intent = new Intent(this, CadastroAlunoTurmaActivity.class);
        startActivity(intent);
    }

    public void moduloDisciplinaTurma(View view) {
        Intent intent = new Intent(this, CadastroDisciplinaTurmaActivity.class);
        startActivity(intent);
    }

    public void moduloNotaAluno(View view) {
        Intent intent = new Intent(this, CadastroNotaAlunoActivity.class);
        startActivity(intent);
    }

    public void moduloPresencaAluno(View view) {
        Intent intent = new Intent(this, PresencaAlunoActivity.class);
        startActivity(intent);
    }

    public void moduloResultado(View view) {
        Intent intent = new Intent(this, ListaResultadoActivity.class);
        startActivity(intent);
    }


}