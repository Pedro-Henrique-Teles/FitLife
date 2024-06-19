package com.FitLife.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class ConsoleController implements CommandLineRunner {

    private static final int OPCAO_CADASTRO_ALUNOS = 1;
    private static final int OPCAO_REGISTRO_EXERCICIOS = 2;
    private static final int OPCAO_SAIR = 6;

    private final AlunoController alunoController;
    private final ExercicioController exercicioController;

    @Autowired
    public ConsoleController(AlunoController alunoController, ExercicioController exercicioController) {
        this.alunoController = alunoController;
        this.exercicioController = exercicioController;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("### SERVIDOR INICIADO ###");
        Scanner le = new Scanner(System.in);
        int escolha;
        do {
            escolha = exibeMenu(le);
            switch (escolha) {
                case OPCAO_CADASTRO_ALUNOS:
                    alunoController.menuAlunos(le);
                    break;
                case OPCAO_REGISTRO_EXERCICIOS:
                    exercicioController.registrarExercicios(le);
                    break;
                case OPCAO_SAIR:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (escolha != OPCAO_SAIR);
    }

    private static int exibeMenu(Scanner le) {
        System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println("┃     FYTLIFE APP MENU      ┃");
        System.out.println("┣━━━━━━━━━━━━━━━━━━━━━━━━━━━┫");
        System.out.println("┃ 1) Cadastro De Alunos     ┃");
        System.out.println("┃ 2) Registro De Exercícios ┃");
        System.out.println("┃ 3) Consultas              ┃");
        System.out.println("┃ 4) Relat. e Estat         ┃");
        System.out.println("┃ 5) Feedback Treinador     ┃");
        System.out.println("┃ 6) Sair                   ┃");
        System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        System.out.print("Escolha Sua Opção:: ");
        while (!le.hasNextInt()){
            System.out.println("Por Favor, Insira Um Número");
            le.next(); //Limpa a Entrada incorreta
            System.out.println("Escolha Sua Opção:: ");
        }
        int escolha = le.nextInt();
        return escolha;
    }
}
