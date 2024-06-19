package com.FitLife.controller;

import com.FitLife.models.Exercicios;
import com.FitLife.services.ExercicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    private static final int OPCAO_LISTAR_EXERCICIO = 4;
    private static final int OPCAO_VOLTAR = 5;

    public void menuExercicio(Scanner le) {
        int opcao;
        do {
            System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("┃   MENU DE EXERCICIOS    ┃");
            System.out.println("┣━━━━━━━━━━━━━━━━━━━━━━━━━┫");
            System.out.println("┃ 1) Adicionar Exercício  ┃");
            System.out.println("┃ 2) Remover Exercício    ┃");
            System.out.println("┃ 3) Atualizar Exercício  ┃");
            System.out.println("┃ 4) Listar Exercício     ┃");
            System.out.println("┃ 5) Voltar               ┃");
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━┛");
            System.out.println();
            System.out.print("Escolha uma opção: ");
            while (!le.hasNextInt()) {
                System.out.println("Por favor, insira um número válido.");
                le.next(); // Limpa a entrada incorreta
                System.out.print("Escolha uma opção: ");
            }
            opcao = le.nextInt();
            le.nextLine();

            switch (opcao) {
                case OPCAO_ADICIONAR_EXERCICIO:
                    registrarExercicios(le);
                    break;
                case OPCAO_REMOVER_EXERCICIO:
                    removerExercicio(le, exercicioService);
                    break;
                case OPCAO_ATUALIZAR_EXERCICIO:
                    atualizarExercicio(le);
                    break;
                case OPCAO_LISTAR_EXERCICIO:
                    listarExercicio(le);
                    break;
                case OPCAO_VOLTAR:
                    return; // Volta para o menu principal
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 5);
    }

    public void registrarExercicios(Scanner le) {
        Exercicios exercicio = exercicioService.criarExercicio(le);
        if (exercicio == null) {
            System.out.println("Falha ao registrar exercício. Tente novamente.");
        } else {
            System.out.println("Exercício registrado com sucesso!");
        }
    }

    private void removerExercicio(Scanner le, ExercicioService exercicioService) {
        exercicioService.removerExercicio(le);
    }

    private void atualizarExercicio(Scanner le) {
        exercicioService.atualizarExercicio(le);
    }

    private void listarExercicio(Scanner le) {
        exercicioService.exibirExercicios();
    }
}
