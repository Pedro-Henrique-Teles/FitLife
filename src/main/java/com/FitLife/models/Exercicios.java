package com.FitLife.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Duration;
import java.util.Date;

@Document(collection = "exercicios")
public class Exercicios {

    @Id
    private Long id;
    private String nome;
    private String intencidade;
    private Duration duracao;
    private Date data;
    private Long idAluno; //Aluno que fez o exercicio

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIntencidade() {
        return intencidade;
    }

    public void setIntencidade(String intencidade) {
        this.intencidade = intencidade;
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

    public Long getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(Long idAluno) {
        this.idAluno = idAluno;
    }
}
