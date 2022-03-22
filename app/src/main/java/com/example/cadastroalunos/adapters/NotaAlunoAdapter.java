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
import com.example.cadastroalunos.model.Aluno;
import com.example.cadastroalunos.model.NotaAluno;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;
import java.util.Optional;

public class NotaAlunoAdapter extends RecyclerView.Adapter<NotaAlunoAdapter.AlunoViewHolder> {

    private List<NotaAluno> listaAlunos;
    private Context context;

    public NotaAlunoAdapter(List<NotaAluno> listaAlunos, Context context) {
        this.listaAlunos = listaAlunos;
        this.context = context;
    }

    public static class AlunoViewHolder extends RecyclerView.ViewHolder {
        TextInputEditText edRaAluno;
        TextInputEditText edNomeAluno;
        TextInputEditText edCpfAluno;
        TextInputEditText edNotaAluno;
        MaterialButton btnSalvar;
        NotaAluno notaAlunoSelecionado;

        public AlunoViewHolder(@NonNull View itemView) {
            super(itemView);

            edRaAluno = itemView.findViewById(R.id.edRaAluno);
            edNomeAluno = itemView.findViewById(R.id.edNomeAluno);
            edCpfAluno = itemView.findViewById(R.id.edCpfAluno);
            edNotaAluno = itemView.findViewById(R.id.edNotaAluno);
            btnSalvar = itemView.findViewById(R.id.btnSalvar);
            btnSalvar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }

    @Override
    public AlunoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_nota_aluno, parent, false);

        NotaAlunoAdapter.AlunoViewHolder viewHolder = new AlunoViewHolder(view);

        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull AlunoViewHolder holder, int position) {
        NotaAluno notaAluno = listaAlunos.get(position);
        Aluno aluno = notaAluno.getAluno();

        holder.edRaAluno.setText(String.valueOf(aluno.getRa()));
        holder.edCpfAluno.setText(aluno.getCpf());
        holder.edNomeAluno.setText(aluno.getNome());
        holder.edNotaAluno.setText(Optional.ofNullable(notaAluno.getNota()).map(Object::toString).orElse(""));

    }

    @Override
    public int getItemCount() {
        return listaAlunos.size();
    }

    public void salvarNotaAluno() {
    }
}
