package com.example.cadastroalunos.model;

import com.orm.SugarRecord;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Disciplina extends SugarRecord implements Serializable {
    String nome;
    Integer cargaHoraria;
    Professor professor;

    @Override
    public String toString() {
        return "Disciplina{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
