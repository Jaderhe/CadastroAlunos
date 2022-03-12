package com.example.cadastroalunos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.cadastroalunos.util.CpfMask;
import com.google.android.material.textfield.TextInputEditText;

public class CadastroProfessorActivity extends AppCompatActivity {

    private TextInputEditText edNomeProfessor;
    private TextInputEditText edCpfProfessor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.ProfTitle);
        setContentView(R.layout.activity_cadastro_professor);

        loadComponents();

        edCpfProfessor.addTextChangedListener(CpfMask.insert(edCpfProfessor));
    }

    private void loadComponents() {
        edNomeProfessor = findViewById(R.id.edNomeProfessor);
        edCpfProfessor = findViewById(R.id.edCpfProfessor);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflaterMenu = getMenuInflater();
        inflaterMenu.inflate(R.menu.menu_cadastro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mn_limpar:
                //TODO: adicionar método  de limpar dados
                //limparCampos();
                return true;
            case R.id.mn_salvar:
                //TODO: adicionar método  de salvar dados
                //validaCampos();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}