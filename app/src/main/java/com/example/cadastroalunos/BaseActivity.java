package com.example.cadastroalunos;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

import lombok.AllArgsConstructor;


public abstract class BaseActivity extends AppCompatActivity {
    List<TextInputEditText> inputs = new ArrayList<>();


    @RequiresApi(api = Build.VERSION_CODES.N)
    void limparCampos() {
        inputs.stream().map(TextInputEditText::getText).filter(Objects::nonNull).forEach(Editable::clear);
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    abstract void loadComponents();
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflaterMenu = getMenuInflater();
        inflaterMenu.inflate(R.menu.menu_cadastro, menu);
        return true;
    }

    abstract void salvar();

    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mn_limpar:
                limparCampos();
                return true;
            case R.id.mn_salvar:
                if (validaCampos())
                    salvar();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    boolean validaCampos() {
        AtomicBoolean valido = new AtomicBoolean(true);
        inputs.stream().filter(obj -> Objects.nonNull(obj.getText())).forEach(obj -> {
            if (obj.getText().toString().isEmpty()) {
                obj.setError("Campo inválido");
                obj.requestFocus();
                valido.set(false);
            }
        });
        validaCamposExtras(valido);
        return valido.get();
    }

    void validaCamposExtras(AtomicBoolean valido) {
    }
}
