package com.FitLife.controller;

import com.FitLife.models.Aluno;
import com.FitLife.services.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Scanner;



@Controller
public class AlunoController {

    @Autowired
    AlunoService alunoService;

    private static final int OPCAO_ADICIONAR_ALUNOS = 1;
    private static final int OPCAO_REMOVER_ALUNOS = 2;
    private static final int OPCAO_ATUALIZAR_ALUNOS = 3;
    private static final int OPCAO_VOLTAR = 4;

    public void menuAlunos(Scanner le) {
        int opcao;
        do {
            System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("┃   MENU DE ALUNOS        ┃");
            System.out.println("┣━━━━━━━━━━━━━━━━━━━━━━━━━┫");
            System.out.println("┃ 1) Adicionar Aluno      ┃");
            System.out.println("┃ 2) Remover Aluno        ┃");
            System.out.println("┃ 3) Atualizar Aluno      ┃");
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
                case OPCAO_ADICIONAR_ALUNOS:
                    cadastrarAlunos(le);
                    break;
                case OPCAO_REMOVER_ALUNOS:
                    removerAluno(le);
                    break;
                case OPCAO_ATUALIZAR_ALUNOS:
                    atualizarAluno(le);
                    break;
                case OPCAO_VOLTAR:
                    return; // Volta para o menu principal
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 4);
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

    private void removerAluno(Scanner le) {
        // Implementar Remover Aluno
    }

    private void atualizarAluno(Scanner le) {
        // Implementar Atualizar Aluno
    }

}