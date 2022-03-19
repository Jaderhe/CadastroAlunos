package com.example.cadastroalunos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadastroalunos.R;
import com.example.cadastroalunos.model.Professor;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class ProfessorAdapter extends RecyclerView.Adapter<ProfessorAdapter.ProfessorViewHolder> {

    private List<Professor> listaProfessors;
    private Context context;

    public ProfessorAdapter(List<Professor> listaProfessors, Context context) {
        this.listaProfessors = listaProfessors;
        this.context = context;
    }

    public static class ProfessorViewHolder extends RecyclerView.ViewHolder {
        TextInputEditText edNomeProfessor;
        TextInputEditText edCpfProfessor;

        public ProfessorViewHolder(@NonNull View itemView) {
            super(itemView);
            edNomeProfessor = (TextInputEditText) itemView.findViewById(R.id.edNomeProfessor);
            edCpfProfessor = (TextInputEditText) itemView.findViewById(R.id.edCpfProfessor);
        }
    }

    @Override
    public ProfessorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_professor, parent, false);

        return new ProfessorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfessorViewHolder holder, int position) {
        Professor professor = listaProfessors.get(position);


        holder.edCpfProfessor.setText(professor.getCpf());
        holder.edNomeProfessor.setText(professor.getNome());

    }

    @Override
    public int getItemCount() {
        return listaProfessors.size();
    }


}
