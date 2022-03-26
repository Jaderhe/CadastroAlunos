package com.example.cadastroalunos.adapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadastroalunos.R;
import com.example.cadastroalunos.model.RegimeTurma;
import com.example.cadastroalunos.model.Turma;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;
import java.util.Optional;

public class TurmaAdapter extends RecyclerView.Adapter<TurmaAdapter.TurmaViewHolder> {

    private List<Turma> listaTurmas;
    private Context context;

    public TurmaAdapter(List<Turma> listaTurmas, Context context) {
        this.listaTurmas = listaTurmas;
        this.context = context;
    }

    public static class TurmaViewHolder extends RecyclerView.ViewHolder {
        TextInputEditText edNomeTurma;
        TextInputEditText edRegimeTurma;

        public TurmaViewHolder(@NonNull View itemView) {
            super(itemView);
            edNomeTurma = (TextInputEditText) itemView.findViewById(R.id.edNomeTurma);
            edRegimeTurma = (TextInputEditText) itemView.findViewById(R.id.edRegimeTurma);
        }
    }

    @Override
    public TurmaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_turma, parent, false);

        return new TurmaViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull TurmaViewHolder holder, int position) {
        Turma turma = listaTurmas.get(position);
        holder.edNomeTurma.setText(turma.getNome());
        holder.edRegimeTurma.setText(Optional.ofNullable(turma.getRegimeTurma()).orElse(RegimeTurma.ANUAL).name());

    }

    @Override
    public int getItemCount() {
        return listaTurmas.size();
    }


}
