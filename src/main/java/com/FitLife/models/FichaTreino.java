package com.FitLife.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.Date;
import java.util.List;

@Document(collection = "fichas")
public class FichaTreino {
    @Id
    private String id;
    @DocumentReference
    private List<Exercicios> exercicios;
    private List<String> diasDaSemana;
    private int repeticoes;
    private Date tempoDeDescanso;  // Alterado para java.util.Date
    @DocumentReference
    private Aluno aluno;  // Referência ao aluno

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Exercicios> getExercicios() {
        return exercicios;
    }

    public void setExercicios(List<Exercicios> exercicios) {
        this.exercicios = exercicios;
    }

    public int getRepeticoes() {
        return repeticoes;
    }

    public void setRepeticoes(int repeticoes) {
        this.repeticoes = repeticoes;
    }

    public Date getTempoDeDescanso() {
        return tempoDeDescanso;
    }

    public void setTempoDeDescanso(Date tempoDeDescanso) {
        this.tempoDeDescanso = tempoDeDescanso;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public FichaTreino(String id, List<Exercicios> exercicios, List<String> diasDaSemana, int repeticoes, Date tempoDeDescanso, Aluno aluno) {
        this.id = id;
        this.exercicios = exercicios;
        this.diasDaSemana = diasDaSemana;
        this.repeticoes = repeticoes;
        this.tempoDeDescanso = tempoDeDescanso;
        this.aluno = aluno;
    }

    public List<String> getDiasDaSemana() {
        return diasDaSemana;
    }

    public void setDiasDaSemana(List<String> diasDaSemana) {
        this.diasDaSemana = diasDaSemana;
    }
}
