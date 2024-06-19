package com.FitLife.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

@Document(collection = "exercicios")
public class Exercicios {

    @Id
    private String id;
    private String nome;
    private String intensidade;
    private Duration duracao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date data;
    @DocumentReference
    private Aluno aluno;
    private String idAluno; //Aluno que fez o exercicio

    @Override
    public String toString() {
        return "Exercício: " + this.nome +
                ", Intensidade: " + this.intensidade +
                ", Duração: " + this.duracao.toMinutes() + " minutos" +
                ", Data: " + new SimpleDateFormat("dd/MM/yyyy").format(this.data) +
                ", Aluno: " + this.nomeAluno;
    }


    private String nomeAluno;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIntensidade() {
        return intensidade;
    }

    public void setIntensidade(String intensidade) {
        this.intensidade = intensidade;
    }

    public Duration getDuracao() {
        return duracao;
    }

    public void setDuracao(Duration duracao) {
        this.duracao = duracao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public String getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(String idAluno) {
        this.idAluno = idAluno;
    }
}
