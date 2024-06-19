package com.FitLife.controller;

import com.FitLife.models.Aluno;
import com.FitLife.services.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;



@Controller
public class AlunoController {

    @Autowired
    AlunoService alunoService;

    private static final int OPCAO_ADICIONAR_ALUNOS = 1;
    private static final int OPCAO_REMOVER_ALUNOS = 2;
    private static final int OPCAO_ATUALIZAR_ALUNOS = 3;
    private static final int OPCAO_LISTAR_ALUNOS = 4;
    private static final int OPCAO_VOLTAR = 5;

    public void menuAlunos(Scanner le) {
        int opcao;
        do {
            System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("┃   MENU DE ALUNOS        ┃");
            System.out.println("┣━━━━━━━━━━━━━━━━━━━━━━━━━┫");
            System.out.println("┃ 1) Adicionar Aluno      ┃");
            System.out.println("┃ 2) Remover Aluno        ┃");
            System.out.println("┃ 3) Atualizar Aluno      ┃");
            System.out.println("┃ 4) Listar Alunos        ┃");
            System.out.println("┃ 5) Voltar               ┃");
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━┛");
            System.out.print("Escolha uma opção: ");
            System.out.println();
            while (!le.hasNextInt()) {
                System.out.println("Por favor, insira um número válido.");
                le.next(); // Limpa a entrada incorreta
                System.out.println();
                System.out.print("Escolha uma opção: ");
            }
            opcao = le.nextInt();

            switch (opcao) {
                case OPCAO_ADICIONAR_ALUNOS:
                    cadastrarAlunos(le);
                    break;
                case OPCAO_REMOVER_ALUNOS:
                    removerAluno(le, alunoService);
                    break;
                case OPCAO_ATUALIZAR_ALUNOS:
                    atualizarAluno(le);
                    break;
                case OPCAO_LISTAR_ALUNOS:
                    listarAlunos(le);
                    break;
                case OPCAO_VOLTAR:
                    return; // Volta para o menu principal
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 5);
    }

    private void cadastrarAlunos(Scanner le) {
        System.out.println();
        System.out.println("Digite o nome do aluno:");
        String nome = le.next();

        System.out.println("Digite o sexo do aluno (F ou M):");
        String sexo = le.next();

        System.out.println("Digite a idade do aluno(a):");
        int idade = le.nextInt();

        System.out.println("Digite a data de inscrição do aluno (dd-MM-yyyy):");
        String dataInscricaoStr = le.next();

        // Chama o método de cadastro do serviço e passa os dados coletados
        Aluno novoAluno = alunoService.cadastrarAluno(nome, sexo, idade, dataInscricaoStr);

        if(novoAluno != null){
            System.out.println("Aluno cadastrado com sucesso!");
        }else {
            System.out.println("Não foi possível cadastrar o aluno. Verifique os dados e tente novamente.");
        }
    }


    private void removerAluno(Scanner le, AlunoService alunoService) {
        alunoService.removerAluno(le);
    }

    private void atualizarAluno(Scanner le) {
        alunoService.atualizarAluno(le);

    }
    private void listarAlunos(Scanner le) {
        alunoService.exibirAlunos();
    }


}