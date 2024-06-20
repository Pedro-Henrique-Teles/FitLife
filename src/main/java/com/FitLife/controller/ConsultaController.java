package com.FitLife.controller;

import com.FitLife.models.repository.AlunoRepository;
import com.FitLife.models.repository.ExerciciosRepository;
import com.FitLife.services.ConsultaService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.util.Scanner;

@Controller
public class ConsultaController {
    private static final int BUSCAR_EX_POR_ALUNO = 1;
    private static final int BUSCAR_EX_POR_DATA = 2;
    private static final int BUSCAR_EX_POR_INTENSIDADE= 3;
    private static final int OBTER_MEDIA_TODOS_ALUNOS = 4;
    private static final int EXIBIR_ALUNO_MAIOR_TMP_EX = 5;
    private static final int LISTAR_ALUNO_EX_INTEVALO_DATA = 6;
    private static final int BUSCAR_EX_SEXO = 7;
    private static final int CONTAR_NUMERO_EX_POR_ALUNO = 8;
    private static final int OBTER_MED_DUR_INTENSIDADE = 9;
    private static final int LISTAR_EX_POR_ALUNO_IDADE_SUPERIOR_30 = 10;
    private static final int BUSCAR_EX_REALIZADO_ALUNO_INSCRICAO_APOS_DATA = 11;
    private static final int LISTAR_ALUNO_POR_LETRA = 12;
    private static final int CONT_EX_NA_ULTIMA_SEMANA = 13;
    private static final int EXIBIR_EX_MAIS_COMUM = 14;
    private static final int OBTER_LISTA_ALUNOS_QUE_REALIZARAM_EX_TODAS_INTENSIDADES = 15;
    private static final int DISTRIBUICAO_INTENSIDADE_EXERCICIOS = 16;
    private static final int DURACAO_TOTAL_EXERCICIOS_POR_ALUNO = 17;
    private static final int EXERCICIO_MAIS_LONGO_POR_ALUNO = 18;
    private static final int MEDIA_EXERCICIOS_POR_DIA = 19;
    private static final int CONTAGEM_ALUNOS_COM_TODAS_INTENSIDADES = 20;
    private static final int VOLTAR = 21;

    private final ConsultaService consultaService;

    @Autowired
    private ExerciciosRepository exerciciosRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    public ConsultaController(ConsultaService consultaService){
        this.consultaService = consultaService;
    }


    @SneakyThrows
    public void menuConsultas(Scanner le) {
        int opcao;
        do {
            System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("┃              MENU DE CONSULTAS                 ┃");
            System.out.println("┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫");
            System.out.println("┃ 1) Buscar exercícios por aluno                 ┃");
            System.out.println("┃ 2) Buscar exercícios por data                  ┃");
            System.out.println("┃ 3) Buscar exercícios por intensidade alta      ┃");
            System.out.println("┃    e duração maior que 10 min                  ┃");
            System.out.println("┃ 4) Obter média de duração dos exercícios       ┃");
            System.out.println("┃ 5) Exibir aluno com maior tempo de exercícios  ┃");
            System.out.println("┃ 6) Listar alunos que realizaram exercícios     ┃");
            System.out.println("┃    em um intervalo de datas                    ┃");
            System.out.println("┃ 7) Buscar exercícios realizados por sexo       ┃");
            System.out.println("┃ 8) Contar número total de exercícios por aluno ┃");
            System.out.println("┃ 9) Obter média de duração por intensidade      ┃");
            System.out.println("┃ 10) Listar exercícios por alunos > 30 anos     ┃");
            System.out.println("┃ 11) Buscar exercícios após data de inscrição   ┃");
            System.out.println("┃ 12) Listar exercícios por inicial do nome      ┃");
            System.out.println("┃ 13) Contar exercícios na última semana         ┃");
            System.out.println("┃ 14) Exibir exercício mais comum                ┃");
            System.out.println("┃ 15) Listar alunos que realizaram todas as      ┃");
            System.out.println("┃     intensidades                               ┃");
            System.out.println("┃ 16) Distribuição de intensidade dos exercícios ┃");
            System.out.println("┃ 17) Duração total de exercícios por aluno      ┃");
            System.out.println("┃ 18) Exercício mais longo por aluno             ┃");
            System.out.println("┃ 19) Média de exercícios por dia                ┃");
            System.out.println("┃ 20) Contagem de alunos com todas intensidades  ┃");
            System.out.println("┃ 21) Voltar                                     ┃");
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
                case BUSCAR_EX_POR_ALUNO:
                    System.out.println("Você escolheu 'Buscar todos os exercícios realizados por um determinado aluno'");
                    Thread.sleep(2000);
                    buscarExPorAluno(le);
                    System.out.println();
                    break;

                case BUSCAR_EX_POR_DATA:
                    System.out.println("Você escolheu 'Buscar todos os exercícios realizados em uma data específica'");
                    buscarExerciciosPorData(le);
                    System.out.println();
                    break;

                case BUSCAR_EX_POR_INTENSIDADE:
                    System.out.println("Você escolheu 'Buscar todos os exercícios de intensidade 'intenso' e duração maior que 10 minutos'");
                    buscarExerciciosPorIntensidadeEDuracao(le);
                    System.out.println();
                    le.nextLine();
                    break;

                case OBTER_MEDIA_TODOS_ALUNOS:
                    System.out.println("Você escolheu 'Obter a média de duração dos exercícios realizados por todos os alunos'");
                    System.out.println();
                    Thread.sleep(2000);
                    calcularMediaDuracaoExercicios(le);
                    System.out.println();

                    break;

                case EXIBIR_ALUNO_MAIOR_TMP_EX:
                    System.out.println("Você escolheu 'Exibir o aluno com o maior tempo de exercícios realizados'");
                    System.out.println();
                    exibirAlunoComMaiorTempoExercicios(le);
                    System.out.println();
                    Thread.sleep(3000);
                    break;

                case LISTAR_ALUNO_EX_INTEVALO_DATA:
                    System.out.println("Você escolheu 'Listar alunos que realizaram exercícios em um intervalo de datas'");
                    System.out.println();
                    listarAlunosPorIntervaloDeDatas(le);
                    System.out.println();
                    Thread.sleep(3000);
                    break;

                case BUSCAR_EX_SEXO:
                    System.out.println("Você escolheu 'Buscar exercícios realizados por sexo'");
                    System.out.println();
                    buscarExerciciosPorSexo(le);
                    System.out.println();
                    Thread.sleep(3000);
                    break;

                case CONTAR_NUMERO_EX_POR_ALUNO:
                    System.out.println("Você escolheu 'Contar número total de exercícios por aluno'");
                    System.out.println();
                    contarExerciciosPorAluno(le);
                    System.out.println();
                    Thread.sleep(3000);
                    break;

                case OBTER_MED_DUR_INTENSIDADE:
                    System.out.println("Você escolheu 'Obter média de duração por intensidade'");
                    System.out.println();
                    calcularMediaDuracaoPorIntensidade(le);
                    System.out.println();
                    Thread.sleep(3000);
                    break;

                case LISTAR_EX_POR_ALUNO_IDADE_SUPERIOR_30:
                    System.out.println("Você escolheu 'Listar exercícios por alunos > 30 anos'");
                    System.out.println();
                    listarExerciciosPorAlunosAcima30(le);
                    System.out.println();
                    Thread.sleep(3000);
                    break;

                case BUSCAR_EX_REALIZADO_ALUNO_INSCRICAO_APOS_DATA:
                    System.out.println("Você escolheu 'Buscar exercícios após data de inscrição'");
                    System.out.println();
                    buscarExerciciosPorDataInscricao(le);
                    System.out.println();
                    Thread.sleep(3000);
                    break;

                case LISTAR_ALUNO_POR_LETRA:
                    System.out.println("Você escolheu 'Buscar exercícios após data de inscrição'");
                    System.out.println();
                    listarExerciciosPorInicialNomeAluno(le);
                    System.out.println();
                    Thread.sleep(3000);
                    break;

                case VOLTAR:
                    System.out.println("Você escolheu 'Voltar'");
                    return; // Volta para o menu principal
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != VOLTAR);
    }


    private void buscarExPorAluno (Scanner le){
        consultaService.buscarExerciciosPorAluno(le);
    }

    private void buscarExerciciosPorData (Scanner le){
        consultaService.buscarExerciciosPorData(le);
    }

    private void buscarExerciciosPorIntensidadeEDuracao (Scanner le){
        consultaService.buscarExerciciosPorIntensidadeEDuracao(le);
    }

    private void calcularMediaDuracaoExercicios (Scanner le){
        consultaService.calcularMediaDuracaoExercicios(le);
    }

    private void exibirAlunoComMaiorTempoExercicios (Scanner le){
        consultaService.exibirAlunoComMaiorTempoExercicios(le);
    }

    private void listarAlunosPorIntervaloDeDatas (Scanner le) {
        consultaService.listarAlunosPorIntervaloDeDatas(le);
    }

    private void buscarExerciciosPorSexo (Scanner le) {
        consultaService.buscarExerciciosPorSexo(le);
    }

    private void contarExerciciosPorAluno (Scanner le) {
        consultaService.contarExerciciosPorAluno(le);
    }

    private void calcularMediaDuracaoPorIntensidade (Scanner le) {
        consultaService.calcularMediaDuracaoPorIntensidade(le);
    }

    private void listarExerciciosPorAlunosAcima30 (Scanner le) {
        consultaService.listarExerciciosPorAlunosAcima30(le);
    }

    private void buscarExerciciosPorDataInscricao (Scanner le) {
        consultaService.buscarExerciciosPorDataInscricao(le);
    }

    private void listarExerciciosPorInicialNomeAluno (Scanner le) {
        consultaService.listarExerciciosPorInicialNomeAluno(le);
    }


}
