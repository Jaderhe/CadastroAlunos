package com.example.cadastroalunos.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RegimeTurma {
    SEMESTRAL(2),
    ANUAL(4);
    int qtdBimestres;

}
