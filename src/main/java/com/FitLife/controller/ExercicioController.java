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

    private static final int OPCAO_ADICIONAR_EXERCICIO = 1;
    private static final int OPCAO_REMOVER_EXERCICIO = 2;
    private static final int OPCAO_ATUALIZAR_EXERCICIO = 3;
    private static final int OPCAO_VOLTAR = 4;

    public void menuAlunos(Scanner le) {
        int opcao;
        do {
            System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("┃   MENU DE EXERCICIOS    ┃");
            System.out.println("┣━━━━━━━━━━━━━━━━━━━━━━━━━┫");
            System.out.println("┃ 1) Adicionar Exercício  ┃");
            System.out.println("┃ 2) Remover Exercício    ┃");
            System.out.println("┃ 3) Atualizar Exercício  ┃");
            System.out.println("┃ 4) Voltar               ┃");
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━┛");
            System.out.print("Escolha uma opção: ");
            while (!le.hasNextInt()) {
                System.out.println("Por favor, insira um número válido.");
                le.next(); // Limpa a entrada incorreta
                System.out.print("Escolha uma opção: ");
            }
            opcao = le.nextInt();

            switch (opcao) {
                case OPCAO_ADICIONAR_EXERCICIO:
                    registrarExercicios(le);
                    break;
                case OPCAO_REMOVER_EXERCICIO:
                    removerExercicio(le);
                    break;
                case OPCAO_ATUALIZAR_EXERCICIO:
                    atualizarExercicio(le);
                    break;
                case OPCAO_VOLTAR:
                    return; // Volta para o menu principal
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 4);
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

    private void removerExercicio(Scanner le) {
        // Implementar Remover Aluno
    }

    private void atualizarExercicio(Scanner le) {
        // Implementar Atualizar Aluno
    }
}
