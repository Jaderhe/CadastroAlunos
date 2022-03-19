package com.example.cadastroalunos;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadastroalunos.adapters.TurmaAdapter;
import com.example.cadastroalunos.dao.SugarDAO;
import com.example.cadastroalunos.model.Turma;

import java.util.List;

public class ListaTurmaActivity extends BaseListActivity {

    public ListaTurmaActivity() {
        super(R.layout.activity_lista_turma,
                R.string.TurmaTitle,
                R.id.rvListaTurmas,
                R.id.listaTurmas,
                "Turma",
                CadastroTurmaActivity.class);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    RecyclerView.Adapter createAdapter() {
        return new TurmaAdapter(SugarDAO.retornaObjetos(Turma.class, "nome asc"), this);
    }
}