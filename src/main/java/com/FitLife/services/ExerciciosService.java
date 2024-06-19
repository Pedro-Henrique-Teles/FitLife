package com.FitLife.services;

import com.FitLife.models.Exercicios;
import com.FitLife.models.repository.AlunoRepository;
import com.FitLife.models.repository.ExerciciosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Date;

@Service
public class ExerciciosService {

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

        Exercicios exercicio = new Exercicios();
        exercicio.setNome(nome);
        exercicio.setIntensidade(intensidade);
        exercicio.setDuracao(duracao);
        exercicio.setData(data);
        exercicio.setIdAluno(idAluno);

        return exerciciosRepository.save(exercicio);
    }
}
