package com.example.cadastroalunos;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadastroalunos.adapters.ProfessorAdapter;
import com.example.cadastroalunos.dao.SugarDAO;
import com.example.cadastroalunos.model.Professor;

public class ListaProfessorActivity extends BaseListActivity {

    public ListaProfessorActivity() {
        super(R.layout.activity_lista_professor,
                R.string.ProfTitle,
                R.id.rvListaProfessores,
                R.id.listaProfessores,
                "Professor",
                CadastroProfessorActivity.class);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    RecyclerView.Adapter createAdapter() {
        return new ProfessorAdapter(SugarDAO.retornaObjetos(Professor.class, "nome asc"), this);
    }
}