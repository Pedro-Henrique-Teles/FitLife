package com.FitLife;

import com.FitLife.models.Aluno;
import com.FitLife.models.Exercicios;
import com.FitLife.models.repository.AlunoRepository;
import com.FitLife.models.repository.ExerciciosRepository;
import com.FitLife.services.AlunoService;
import com.FitLife.services.ExerciciosService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.expression.ParseException;


import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Scanner;
import java.util.Date;


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

    @Autowired
    ExerciciosService exerciciosService;

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
                case 2:
                    registrarExercicios(le);
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

    @SneakyThrows
    private void registrarExercicios(Scanner le) {
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
            Exercicios exercicio = exerciciosService.criarExercicio(nome, intensidade, duracao, data, idAluno);
            System.out.println("Exercício registrado com sucesso!");
        } catch (IllegalArgumentException | ParseException e) {
            System.out.println("Erro ao registrar exercício: " + e.getMessage());
        }
    }



}
