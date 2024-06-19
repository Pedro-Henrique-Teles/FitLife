package com.FitLife;

import com.FitLife.models.Aluno;
import com.FitLife.models.repository.AlunoRepository;
import com.FitLife.models.repository.ExerciciosRepository;
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
        int choice = le.nextInt();
        return choice;
    }

    private void cadastrarAlunos(Scanner le) {
        String nome;
        do {
            System.out.println("Digite o nome do aluno (mais de 3 letras):");
            nome = le.next();
        } while (nome == null || nome.length() <= 3);

        String sexo;
        do {
            System.out.println("Digite o sexo do aluno (F ou M):");
            sexo = le.next().toUpperCase();
        } while (!sexo.equals("F") && !sexo.equals("M"));

        int idade;
        do {
            System.out.println("Digite a idade do aluno (maior que 10 anos):");
            while (!le.hasNextInt()) {
                System.out.println("Por favor, insira um número válido para a idade.");
                le.next(); // Limpa a entrada incorreta
            }
            idade = le.nextInt();
        } while (idade <= 10);


        Aluno novoAluno = new Aluno();
        novoAluno.setNome(nome);
        novoAluno.setSexo(sexo);
        novoAluno.setIdade(idade);
        //Propriedades do Aluno

        // Salva o aluno no banco de dados
        alunoRepository.save(novoAluno);

        System.out.println("Aluno cadastrado com sucesso!");
    }


}
