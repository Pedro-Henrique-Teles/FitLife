package com.FitLife.services;

import com.FitLife.models.Aluno;
import com.FitLife.models.Exercicios;
import com.FitLife.models.repository.AlunoRepository;
import com.FitLife.models.repository.ExerciciosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Exercicios criarExercicio(String nome, String intensidade, Duration duracao, Date data, String idAluno) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do exercício não pode ser vazio.");
        }
        if (!intensidade.matches("baixa|media|alta")) {
            throw new IllegalArgumentException("Intensidade inválida. Use 'baixa', 'media' ou 'alta'.");
        }
        if (duracao.isNegative() || duracao.toMinutes() > 240) { // Exemplo: duração máxima de 4 horas
            throw new IllegalArgumentException("Duração inválida.");
        }
        if (data == null) {
            throw new IllegalArgumentException("Data inválida.");
        }
        if (!alunoRepository.existsById(idAluno)) {
            throw new IllegalArgumentException("ID do aluno não encontrado.");
        }

        //BUSCA O ALUNO PELO ID E ASSIM OBTEM O NOME
        Aluno aluno = alunoRepository.findById(idAluno).orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado."));

        Exercicios exercicio = new Exercicios();
        exercicio.setNome(nome);
        exercicio.setIntensidade(intensidade);
        exercicio.setDuracao(duracao);
        exercicio.setData(data);
        exercicio.setIdAluno(idAluno);
        exercicio.setNomeAluno(aluno.getNome());

        return exerciciosRepository.save(exercicio);

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
