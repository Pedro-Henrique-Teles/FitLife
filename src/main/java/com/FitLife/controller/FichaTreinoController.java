package com.FitLife.controller;

import com.FitLife.models.repository.AlunoRepository;
import com.FitLife.models.repository.ExerciciosRepository;
import com.FitLife.services.ConsultaService;
import com.FitLife.services.FichaTreinoService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class FichaTreinoController {
    private static final int CRIAR_FICHA = 1;
    private static final int DELETAR_FICHAS = 2;
    private static final int LISTAR_FICHAS = 3;
    private static final int OPCAO_SAIR = 4;

    private final FichaTreinoService fichaTreinoService;

    @Autowired
    private ExerciciosRepository exerciciosRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    public FichaTreinoController(FichaTreinoService fichaTreinoService) {
        this.fichaTreinoService = fichaTreinoService;
    }


    @SneakyThrows
    public void menuFichas(Scanner le) {
        int opcao;
        do {
            System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("┃              MENU DE FICHAS                    ┃");
            System.out.println("┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫");
            System.out.println("┃ 1) Cadastrar Ficha                             ┃");
            System.out.println("┃ 2) Deletar Ficha                               ┃");
            System.out.println("┃ 3) Listar Fichas                               ┃");
            System.out.println("┃ 4) Exibir aluno com maior tempo de exercícios  ┃");
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
            System.out.print("Escolha uma opção: ");
            System.out.println();

            while (!le.hasNextInt()) {
                System.out.println("Por favor, insira um número válido.");
                le.next(); // Limpa a entrada incorreta
                System.out.print("Escolha uma opção: ");
            }
            opcao = le.nextInt();
            le.nextLine(); // Consome o '\n' restante

            switch (opcao) {
                case CRIAR_FICHA:
                    System.out.println("Você escolheu 'Ciar uma ficha'");
                    Thread.sleep(2000);
                    criarFichasParaAlunos(le);
                    System.out.println();
                    break;

                case DELETAR_FICHAS:
                        System.out.println("Você escolheu 'Deletar Fichas'");
                        Thread.sleep(2000);
                    listarEExcluirFichasPorAluno(le);
                        System.out.println();
                        break;


                case OPCAO_SAIR:
                    System.out.println("Você escolheu 'Voltar'");
                    return; // Volta para o menu principal
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != OPCAO_SAIR);
    }

    private void criarFichasParaAlunos(Scanner le) {
        fichaTreinoService.criarFichasParaAlunos(le);
    }

    private void listarEExcluirFichasPorAluno(Scanner le) {
        fichaTreinoService.listarEExcluirFichasPorAluno(le);
    }

}
