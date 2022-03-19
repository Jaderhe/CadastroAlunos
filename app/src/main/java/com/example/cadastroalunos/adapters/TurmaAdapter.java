package com.example.cadastroalunos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadastroalunos.R;
import com.example.cadastroalunos.model.Turma;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class TurmaAdapter extends RecyclerView.Adapter<TurmaAdapter.TurmaViewHolder> {

    private List<Turma> listaTurmas;
    private Context context;

    public TurmaAdapter(List<Turma> listaTurmas, Context context) {
        this.listaTurmas = listaTurmas;
        this.context = context;
    }

    public static class TurmaViewHolder extends RecyclerView.ViewHolder {
        TextInputEditText edNomeTurma;

        public TurmaViewHolder(@NonNull View itemView) {
            super(itemView);
            edNomeTurma = (TextInputEditText) itemView.findViewById(R.id.edNomeTurma);
        }
    }

    @Override
    public TurmaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_turma, parent, false);

        return new TurmaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TurmaViewHolder holder, int position) {
        Turma turma = listaTurmas.get(position);
        holder.edNomeTurma.setText(turma.getNome());

    }

    @Override
    public int getItemCount() {
        return listaTurmas.size();
    }


}
