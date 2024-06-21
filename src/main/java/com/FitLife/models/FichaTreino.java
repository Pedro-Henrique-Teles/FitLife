package com.FitLife.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.sql.Time;
import java.util.List;

@Document
public class FichaTreino {
    @Id
    private String id;
    @DocumentReference
    private List<Exercicios> exercicios;
    private List<String> diasDaSemana;
    private int repeticoes;
    private Time tempoDeDescanso;

}
