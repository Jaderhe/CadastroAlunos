package com.example.cadastroalunos;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadastroalunos.adapters.DisciplinaAdapter;
import com.example.cadastroalunos.dao.SugarDAO;
import com.example.cadastroalunos.model.Disciplina;

public class ListaDisciplinasActivity extends BaseListActivity {


    public ListaDisciplinasActivity() {
        super(R.layout.activity_lista_disciplinas,
                R.string.DisciplinaTitle,
                R.id.rvListaDisciplinas,
                R.id.listaDisciplinas,
                "Disciplina",
                CadastroDisciplinaActivity.class);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    RecyclerView.Adapter createAdapter() {
        return new DisciplinaAdapter(SugarDAO.retornaObjetos(Disciplina.class, "nome asc"), this);
    }
}