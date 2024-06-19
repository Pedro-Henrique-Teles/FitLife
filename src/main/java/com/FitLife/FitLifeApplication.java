package com.FitLife;

import com.FitLife.models.Aluno;
import com.FitLife.models.repository.AlunoRepository;
import com.FitLife.models.repository.ExerciciosRepository;
import com.FitLife.services.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class FitLifeApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(FitLifeApplication.class, args);
    }

    @Autowired
    AlunoRepository alunoRepository;

    @Autowired
    ExerciciosRepository exerciciosRepository;

    @Autowired
    AlunoService alunoService;

    @Override
    public void run (String... args) throws Exception {
        System.out.println("### SERVIDOR INICIADO ###");
        Scanner le = new Scanner(System.in);
        int escolha;
        do {
            escolha = exibeMenu(le);
            switch (escolha) {
                case 1:
                    cadastrarAlunos(le);
                    break;
                // Adicione os outros casos aqui
                case 6:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (escolha != 6);
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

    private void cadastrarAlunos(Scanner le) {
        System.out.println("Digite o nome do aluno:");
        String nome = le.next();

        System.out.println("Digite o sexo do aluno (F ou M):");
        String sexo = le.next();

        System.out.println("Digite a idade do aluno:");
        int idade = le.nextInt();

        try {
            Aluno aluno = alunoService.cadastrarAluno(nome, sexo, idade);
            System.out.println("Aluno cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao cadastrar aluno: " + e.getMessage());
        }
    }



}
