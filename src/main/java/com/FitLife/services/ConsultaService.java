package com.FitLife.services;

import com.FitLife.models.Aluno;
import com.FitLife.models.Exercicios;
import com.FitLife.models.repository.AlunoRepository;
import com.FitLife.models.repository.ExerciciosRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
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





}
