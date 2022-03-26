package com.example.cadastroalunos.adapters;

import static com.orm.query.Condition.prop;
import static java.util.Objects.nonNull;

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
import com.example.cadastroalunos.model.Disciplina;
import com.example.cadastroalunos.model.NotaAluno;
import com.example.cadastroalunos.model.PresencaAluno;
import com.example.cadastroalunos.model.RegimeTurma;
import com.example.cadastroalunos.model.Turma;
import com.google.android.material.textfield.TextInputEditText;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ResultadoAdapter extends RecyclerView.Adapter<ResultadoAdapter.AlunoViewHolder> {

    private final List<Aluno> listaAlunos;
    private final Turma turma;
    private final Disciplina disciplina;
    private final Context context;


    public static class AlunoViewHolder extends RecyclerView.ViewHolder {
        TextInputEditText edRaAluno;
        TextInputEditText edNomeAluno;
        TextInputEditText edCpfAluno;
        TextInputEditText edResultado;

        public AlunoViewHolder(@NonNull View itemView) {
            super(itemView);

            edRaAluno = itemView.findViewById(R.id.edRaAluno);
            edNomeAluno = itemView.findViewById(R.id.edNomeAluno);
            edCpfAluno = itemView.findViewById(R.id.edCpfAluno);
            edResultado = itemView.findViewById(R.id.edResultado);

        }
    }

    @Override
    public AlunoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_resultado_aluno, parent, false);

        ResultadoAdapter.AlunoViewHolder viewHolder = new AlunoViewHolder(view);

        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull AlunoViewHolder holder, int position) {
        Aluno aluno = listaAlunos.get(position);

        holder.edRaAluno.setText(String.valueOf(aluno.getRa()));
        holder.edCpfAluno.setText(aluno.getCpf());
        holder.edNomeAluno.setText(aluno.getNome());
        holder.edResultado.setText(isAprovado(aluno));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private String isAprovado(Aluno aluno) {
        if (nonNull(disciplina) && nonNull(turma)) {
            List<NotaAluno> lista = Select.from(NotaAluno.class)
                    .where(prop("disciplina").eq(disciplina.getId().toString()),
                            prop("turma").eq(turma.getId().toString()),
                            prop("aluno").eq(aluno.getId().toString())).list();

            if (lista.stream().map(notaAluno -> BigDecimal.valueOf(notaAluno.getNota().longValue()))
                    .reduce(BigDecimal.ZERO, BigDecimal::add)
                    .divide(BigDecimal.valueOf(turma.getRegimeTurma().getQtdBimestres()), RoundingMode.HALF_UP)
                    .compareTo(BigDecimal.valueOf(60L)) < 0) {
                return "Reprovado por Nota.";
            }

        }
        if (nonNull(disciplina) && nonNull(turma)) {
            List<PresencaAluno> lista = Select.from(PresencaAluno.class)
                    .where(prop("disciplina").eq(disciplina.getId().toString()),
                            prop("turma").eq(turma.getId().toString()),
                            prop("aluno").eq(aluno.getId().toString())).list();

            if (Math.floorDiv((long) lista.size(), lista.stream().filter(PresencaAluno::isPresente).count()) < 0.7){
                return "Reprovado por Frequência.";
            }
        }
        return "Aprovado";
    }

    @Override
    public int getItemCount() {
        return listaAlunos.size();
    }


}
