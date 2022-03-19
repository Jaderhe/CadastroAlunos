package com.example.cadastroalunos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadastroalunos.R;
import com.example.cadastroalunos.model.Disciplina;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;

public class DisciplinaAdapter extends RecyclerView.Adapter<DisciplinaAdapter.DisciplinaViewHolder> {

    private List<Disciplina> listaDisciplinas;
    private Context context;

    public DisciplinaAdapter(List<Disciplina> listaDisciplinas, Context context) {
        this.listaDisciplinas = listaDisciplinas;
        this.context = context;
    }

    public static class DisciplinaViewHolder extends RecyclerView.ViewHolder {
        TextInputEditText edNomeDisciplina;
        TextInputEditText edCargaHorariaDisciplina;
        TextInputEditText edNomeProfessor;

        public DisciplinaViewHolder(@NonNull View itemView) {
            super(itemView);
            edNomeDisciplina = (TextInputEditText) itemView.findViewById(R.id.edNomeDisciplina);
            edCargaHorariaDisciplina = (TextInputEditText) itemView.findViewById(R.id.edCargaHorariaDisciplina);
            edNomeProfessor = (TextInputEditText) itemView.findViewById(R.id.edNomeProfessor);
        }
    }

    @Override
    public DisciplinaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_disciplina, parent, false);

        return new DisciplinaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DisciplinaViewHolder holder, int position) {
        Disciplina disciplina = listaDisciplinas.get(position);


        holder.edCargaHorariaDisciplina.setText(disciplina.getCargaHoraria() + "");
        holder.edNomeDisciplina.setText(disciplina.getNome());
        holder.edNomeProfessor.setText(disciplina.getProfessor().getNome());

    }

    @Override
    public int getItemCount() {
        return listaDisciplinas.size();
    }


}
