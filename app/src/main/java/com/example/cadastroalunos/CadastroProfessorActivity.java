package com.example.cadastroalunos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class CadastroProfessorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.ProfTitle);
        setContentView(R.layout.activity_cadastro_professor);
    }
}