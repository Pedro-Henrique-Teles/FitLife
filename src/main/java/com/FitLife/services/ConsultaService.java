package com.FitLife.services;

import com.FitLife.models.Aluno;
import com.FitLife.models.Exercicios;
import com.FitLife.models.repository.AlunoRepository;
import com.FitLife.models.repository.ExerciciosRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

@Service
public class ConsultaService {

    @Autowired
    private ExerciciosRepository exerciciosRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    public void exibirAlunos() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        List<Aluno> alunos = alunoRepository.findAll();
        for (int i = 0; i < alunos.size(); i++) {
            Aluno aluno = alunos.get(i);
            String dataInscricaoFormatada = aluno.getDataInscricao() != null ? sdf.format(aluno.getDataInscricao()) : "N/A";
            System.out.println((i + 1) + ") ID: " + aluno.getId() +
                    " - Nome: " + aluno.getNome() +
                    " - Sexo: " + aluno.getSexo() +
                    " - Idade: " + aluno.getIdade() +
                    " - Data Inscrição: " + dataInscricaoFormatada);
            System.out.println();
        }
    }

    @SneakyThrows
    public void buscarExerciciosPorAluno(Scanner le) {
        exibirAlunos();
        System.out.println("Escolha um aluno pelo número: ");
        int index = le.nextInt() - 1;
        le.nextLine(); // Consume o '\n' restante

        // Verifica se o índice é válido
        List<Aluno> alunos = alunoRepository.findAll();
        if (index < 0 || index >= alunos.size()) {
            System.out.println("Número inválido. Tente novamente.");
            return;
        }

        // Obtém o ID do aluno e realize a consulta
        String idAluno = alunos.get(index).getId();
        List<Exercicios> exercicios = exerciciosRepository.findByIdAluno(idAluno);

        //Exibe os Exercicios
        System.out.println("Exercícios realizados pelo aluno " + alunos.get(index).getNome() + ":");
        System.out.println();
        for (Exercicios exercicio : exercicios) {
            System.out.println(exercicio.getNome());
        }
        Thread.sleep(4000);
    }

    @SneakyThrows
    public void buscarExerciciosPorData(Scanner le) {
        //Insere a data
        System.out.println("Digite a data no formato dd/MM/yyyy: ");
        String dataStr = le.nextLine();

        // Converte a string para Date
        Date data = new SimpleDateFormat("dd/MM/yyyy").parse(dataStr);

        // Realiza a consulta
        List<Exercicios> exercicios = exerciciosRepository.findByData(data);

        // Exibe os exercícios
        System.out.println("Exercícios realizados na data " + dataStr + ":");
        Thread.sleep(1000);
        for (Exercicios exercicio : exercicios) {
            System.out.println(exercicio.getNome());
        }
        Thread.sleep(3000);

    }

    public void buscarExerciciosPorIntensidadeEDuracao(Scanner le) {
        String intensidade = "alta";
        Duration duracao = Duration.ofMinutes(10);

        // Realiza a consulta
        List<Exercicios> exercicios = exerciciosRepository.findByIntensidadeAndDuracaoGreaterThan(intensidade, duracao);

        // Verifica se encontrou algum exercício
        if (exercicios.isEmpty()) {
            System.out.println("Não foram encontrados exercícios de intensidade '" + intensidade + "' e duração maior que " + duracao.toMinutes() + " minutos.");
        } else {
            // Exibe os exercícios
            System.out.println("Exercícios de intensidade '" + intensidade + "' e duração maior que " + duracao.toMinutes() + " minutos:");
            for (Exercicios exercicio : exercicios) {
                System.out.println(exercicio.getNome());
            }
        }
    }

    public void calcularMediaDuracaoExercicios(Scanner le) {
        List<Exercicios> exercicios = exerciciosRepository.findAllDurations();

        long totalSeconds = 0;
        for (Exercicios exercicio : exercicios) {
            totalSeconds += exercicio.getDuracao().getSeconds();
        }

        long mediaSeconds = totalSeconds / exercicios.size();
        Duration mediaDuracao = Duration.ofSeconds(mediaSeconds);

        long horas = mediaDuracao.toHours();
        int minutos = mediaDuracao.toMinutesPart();
        int segundos = mediaDuracao.toSecondsPart();

        System.out.println("A média de duração dos exercícios é: " + horas + " horas, " + minutos + " minutos e " + segundos + " segundos.");
    }

    public void exibirAlunoComMaiorTempoExercicios(Scanner le) {
        List<Aluno> alunos = alunoRepository.findAll();

        Aluno alunoComMaiorTempo = null;
        Duration maiorTempo = Duration.ZERO;

        for (Aluno aluno : alunos) {
            List<Exercicios> exercicios = exerciciosRepository.findByIdAluno(aluno.getId());

            Duration tempoTotal = Duration.ZERO;
            for (Exercicios exercicio : exercicios) {
                tempoTotal = tempoTotal.plus(exercicio.getDuracao());
            }

            if (tempoTotal.compareTo(maiorTempo) > 0) {
                alunoComMaiorTempo = aluno;
                maiorTempo = tempoTotal;
            }
        }

        if (alunoComMaiorTempo != null) {
            System.out.println("O aluno com o maior tempo de exercícios realizados é: " + alunoComMaiorTempo.getNome() +
                    " com um tempo total de " + maiorTempo.toHours() + " horas, " + maiorTempo.toMinutesPart() + " minutos e " + maiorTempo.toSecondsPart() + " segundos.");
        } else {
            System.out.println("Não foram encontrados alunos ou exercícios.");
        }
    }

    @SneakyThrows
    public void listarAlunosPorIntervaloDeDatas(Scanner le) {
        try {
            System.out.println("Digite a data de início no formato dd/MM/yyyy: ");
            String dataInicioStr = le.nextLine();
            Date dataInicio = new SimpleDateFormat("dd/MM/yyyy").parse(dataInicioStr);

            System.out.println("Digite a data de fim no formato dd/MM/yyyy: ");
            String dataFimStr = le.nextLine();
            Date dataFim = new SimpleDateFormat("dd/MM/yyyy").parse(dataFimStr);
            System.out.println();

            List<Aluno> alunos = alunoRepository.findAll();
            for (Aluno aluno : alunos) {
                List<Exercicios> exercicios = exerciciosRepository.findByDataBetween(dataInicio, dataFim);
                if (!exercicios.isEmpty()) {
                    System.out.println(aluno.getNome());
                }
            }
        } catch (ParseException e) {
            System.out.println("Erro: O formato da data é inválido. Por favor, insira a data no formato dd/MM/yyyy.");
        }
    }
}

