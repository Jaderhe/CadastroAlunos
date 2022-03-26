package com.example.cadastroalunos.model;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Professor extends SugarRecord implements Serializable {

    private String nome;
    private String cpf;

    @Override
    public String toString() {
        return nome;
    }
}
