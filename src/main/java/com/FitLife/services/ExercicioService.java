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
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

@Service
public class ExercicioService {

    @Autowired
    private ExerciciosRepository exerciciosRepository;

    @Autowired
    private AlunoRepository alunoRepository;


    @SneakyThrows
    public Exercicios criarExercicio(Scanner le) {
        System.out.println("Digite o nome do exercício:");
        String nome = le.nextLine();
        if (nome.trim().isEmpty()) {
            System.out.println("Nome do exercício não pode ser vazio.");
            return null;
        }

        System.out.println("Digite a intensidade do exercício (baixa, media, alta):");
        String intensidade = le.nextLine();
        if (!intensidade.matches("baixa|media|alta")) {
            System.out.println("Intensidade inválida. Use 'baixa', 'media' ou 'alta'.");
            return null;
        }

        System.out.println("Digite a duração do exercício em minutos:");
        while (!le.hasNextLong()) {
            System.out.println("Por favor, insira um número válido para a duração.");
            le.next(); // Limpa a entrada incorreta
        }
        long duracaoMinutos = le.nextLong();
        Duration duracao = Duration.ofMinutes(duracaoMinutos);

        System.out.println("Digite a data do exercício (formato DD/MM/AAAA):");
        String dataStr = le.next();
        Date data;
        try {
            data = new SimpleDateFormat("dd/MM/yyyy").parse(dataStr);
        } catch (ParseException e) {
            System.out.println("Formato de data inválido. Por favor, insira a data no formato dd/MM/yyyy.");
            return null;
        }

        // Listar alunos
        List<Aluno> alunos = alunoRepository.findAll();
        System.out.println();
        for (int i = 0; i < alunos.size(); i++) {
            Aluno aluno = alunos.get(i);
            System.out.println((i + 1) + ") ID: " + aluno.getId() + " - Nome: " + aluno.getNome());
        }
        System.out.println();
        System.out.println("Digite o número do aluno:");
        while (!le.hasNextInt()) {
            System.out.println("Por favor, insira um número válido para o aluno.");
            le.next(); // Limpa a entrada incorreta
        }
        int numeroAluno = le.nextInt();
        if (numeroAluno < 1 || numeroAluno > alunos.size()) {
            System.out.println("Número de aluno inválido.");
            return null;
        }
        String idAluno = alunos.get(numeroAluno - 1).getId();

        Exercicios exercicio = new Exercicios();
        exercicio.setNome(nome);
        exercicio.setIntensidade(intensidade);
        exercicio.setDuracao(duracao);
        exercicio.setData(data);
        exercicio.setIdAluno(idAluno);
        exercicio.setNomeAluno(alunos.get(numeroAluno - 1).getNome());

        exerciciosRepository.save(exercicio);
        System.out.println("Exercício registrado com sucesso!");
        System.out.println();

        return exercicio;
    }






    public void exibirExercicios() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        List<Exercicios> exercicios = exerciciosRepository.findAll();
        for (int i = 0; i < exercicios.size(); i++) {
            Exercicios exercicio = exercicios.get(i);
            String dataFormatada = exercicio.getData() != null ? formatter.format(exercicio.getData().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()) : "N/A";
            System.out.println((i + 1) + ") ID Exercício: " + exercicio.getId() +
                    "\n Nome: " + exercicio.getNome() +
                    "\n Intensidade: " + exercicio.getIntensidade() +
                    "\n Duração: " + exercicio.getDuracao().toMinutes() +
                    "\n Data: " + dataFormatada +
                    "\n ID Aluno: " + exercicio.getIdAluno());
            System.out.println();
        }

    }

    @SneakyThrows
    public void atualizarExercicio(Scanner le) {
        exibirExercicios(); // Lista os Exercícios
        System.out.println();
        System.out.print("Digite o número do exercício que deseja atualizar: ");
        int numeroExercicio = le.nextInt();

        List<Exercicios> exercicios = exerciciosRepository.findAll();
        if (numeroExercicio < 1 || numeroExercicio > exercicios.size()) {
            System.out.println("Número inválido.");
            return;
        }

        Exercicios exercicioParaAtualizar = exercicios.get(numeroExercicio - 1);

        System.out.println("Digite o novo nome do exercício ou pressione enter para manter o mesmo:");
        le.nextLine();
        String nome = le.nextLine();
        if (!nome.isEmpty()) {
            exercicioParaAtualizar.setNome(nome);
        }

        System.out.println("Digite a nova intensidade do exercício (baixa, media, alta) ou pressione enter para manter o mesmo:");
        String intensidade = le.nextLine();
        if (!intensidade.isEmpty() && intensidade.matches("baixa|media|alta")) {
            exercicioParaAtualizar.setIntensidade(intensidade);
        }

        System.out.println("Digite a nova duração do exercício em minutos ou digite '0' para manter a mesma:");
        long duracaoMinutos = le.nextLong();
        if (duracaoMinutos > 0) {
            exercicioParaAtualizar.setDuracao(Duration.ofMinutes(duracaoMinutos));
        }

        System.out.println("Digite a nova data do exercício no formato dd/MM/yyyy ou pressione enter para manter a mesma:");
        le.nextLine();
        String dataStr = le.nextLine();
        if (!dataStr.isEmpty()) {
            Date data = new SimpleDateFormat("dd/MM/yyyy").parse(dataStr);
            exercicioParaAtualizar.setData(data);
        }

        System.out.println("Digite o novo ID do aluno ou pressione enter para manter o mesmo:");
        String idAluno = le.nextLine();
        if (!idAluno.isEmpty() && alunoRepository.existsById(idAluno)) {
            Aluno aluno = alunoRepository.findById(idAluno).orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado."));
            exercicioParaAtualizar.setIdAluno(idAluno);
            exercicioParaAtualizar.setNomeAluno(aluno.getNome());
        }

        // Salva as alterações
        exerciciosRepository.save(exercicioParaAtualizar);

        System.out.println("Exercício atualizado com sucesso!");
    }




    public void removerExercicio(Scanner le) {
        exibirExercicios();
        System.out.println();
        System.out.print("Digite o número do exercício que deseja remover: ");
        int numeroExercicio = le.nextInt();

        List<Exercicios> exercicios = exerciciosRepository.findAll();
        if (numeroExercicio < 1 || numeroExercicio > exercicios.size()) {
            System.out.println("Número inválido.");
            return;
        }

        Exercicios exercicioParaRemover = exercicios.get(numeroExercicio - 1);
        exerciciosRepository.deleteById(exercicioParaRemover.getId());
        System.out.println("Exercício removido com sucesso.");
    }


}
