package com.FitLife.controller;

import com.FitLife.models.Exercicios;
import com.FitLife.services.ExercicioService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Scanner;

@Controller
public class ExercicioController {

    private final ExercicioService exercicioService;

    @Autowired
    public ExercicioController(ExercicioService exercicioService) {
        this.exercicioService = exercicioService;
    }

    @SneakyThrows
    public void registrarExercicios(Scanner le) {
        System.out.println("Digite o nome do exercício:");
        String nome = le.next();

        System.out.println("Digite a intensidade do exercício (baixa, media, alta):");
        String intensidade = le.next();

        System.out.println("Digite a duração do exercício em minutos:");
        long duracaoMinutos = le.nextLong();
        Duration duracao = Duration.ofMinutes(duracaoMinutos);

        System.out.println("Digite a data do exercício (formato DD/MM/AAAA):");
        String dataStr = le.next();
        Date data = new SimpleDateFormat("dd/MM/yyyy").parse(dataStr);

        System.out.println("Digite o ID do aluno:");
        String idAluno = le.next();

        try {
            Exercicios exercicio = exercicioService.criarExercicio(nome, intensidade, duracao, data, idAluno);
            System.out.println("Exercício registrado com sucesso!");
        } catch (IllegalArgumentException | ParseException e) {
            System.out.println("Erro ao registrar exercício: " + e.getMessage());
        }
    }
}
