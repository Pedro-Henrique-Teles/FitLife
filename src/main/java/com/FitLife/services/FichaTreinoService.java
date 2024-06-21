package com.FitLife.services;

import com.FitLife.models.Aluno;
import com.FitLife.models.Exercicios;
import com.FitLife.models.FichaTreino;
import com.FitLife.models.repository.AlunoRepository;
import com.FitLife.models.repository.ExerciciosRepository;
import com.FitLife.models.repository.FichaTreinoRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Service
public class FichaTreinoService {

    private final FichaTreinoRepository fichaTreinoRepository;
    private final AlunoRepository alunoRepository;
    private final ExerciciosRepository exerciciosRepository;

    @Autowired
    public FichaTreinoService(FichaTreinoRepository fichaTreinoRepository, AlunoRepository alunoRepository, ExerciciosRepository exerciciosRepository) {
        this.fichaTreinoRepository = fichaTreinoRepository;
        this.alunoRepository = alunoRepository;
        this.exerciciosRepository = exerciciosRepository;
    }



    @SneakyThrows
    public void criarFichasParaAlunos(Scanner le) {
        Scanner scanner = new Scanner(System.in);

        // Listar Exercícios
        List<Exercicios> todosExercicios = exerciciosRepository.findAll();
        Map<Integer, Exercicios> exercicioMap = new HashMap<>();
        System.out.println("Lista de Exercícios:");
        for (int i = 0; i < todosExercicios.size(); i++) {
            exercicioMap.put(i + 1, todosExercicios.get(i));
            System.out.println((i + 1) + ": " + todosExercicios.get(i).getNome());
        }

        // Listar Alunos
        List<Aluno> todosAlunos = alunoRepository.findAll();
        Map<Integer, Aluno> alunoMap = new HashMap<>();
        System.out.println("Lista de Alunos:");
        for (int i = 0; i < todosAlunos.size(); i++) {
            alunoMap.put(i + 1, todosAlunos.get(i));
            System.out.println((i + 1) + ": " + todosAlunos.get(i).getNome());
        }

        Aluno aluno = null;
        while (aluno == null) {
            // Selecionar Aluno
            System.out.print("Digite o número do aluno: ");
            int alunoEscolha = scanner.nextInt();
            aluno = alunoMap.get(alunoEscolha);

            // Verificar se o aluno já possui 3 fichas
            List<FichaTreino> fichasExistentes = fichaTreinoRepository.findByAlunoId(aluno.getId());
            if (fichasExistentes.size() >= 3) {
                System.out.println("Este aluno já possui 3 fichas. Por favor, selecione outro aluno.");
                aluno = null;
            }
        }

        // Criar 3 Fichas
        List<FichaTreino> fichas = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            List<Exercicios> exerciciosFicha = new ArrayList<>();
            Set<Integer> exerciciosEscolhidos = new HashSet<>();
            System.out.println("Selecionando exercícios para a ficha " + (i + 1));
            while (exerciciosFicha.size() < 5) {
                System.out.print("Digite o número do exercício: ");
                int escolha = scanner.nextInt();
                if (exercicioMap.containsKey(escolha) && !exerciciosEscolhidos.contains(escolha)) {
                    exerciciosFicha.add(exercicioMap.get(escolha));
                    exerciciosEscolhidos.add(escolha);
                } else {
                    System.out.println("Exercício inválido ou já escolhido. Tente novamente.");
                }
            }
            FichaTreino fichaTreino = new FichaTreino(UUID.randomUUID().toString(), exerciciosFicha, new ArrayList<>(), 0, null, null);
            fichaTreino.setAluno(aluno);  // Atribuir o aluno à ficha
            fichas.add(fichaTreino);
        }

        // Configurar Repetições e Tempo de Descanso para cada ficha
        for (int i = 0; i < fichas.size(); i++) {
            FichaTreino ficha = fichas.get(i);
            System.out.println("Configurando a ficha " + (i + 1));
            System.out.print("Digite o número de repetições para a ficha " + (i + 1) + ": ");
            int repeticoes = scanner.nextInt();
            ficha.setRepeticoes(repeticoes);

            System.out.print("Digite o tempo de descanso (em segundos) para a ficha " + (i + 1) + ": ");
            int tempoDescansoSegundos = scanner.nextInt();
            ficha.setTempoDeDescanso(new Time(tempoDescansoSegundos * 1000L));

            fichaTreinoRepository.save(ficha);
        }

        // Atribuir Fichas aos Dias da Semana
        List<String> diasDaSemana = Arrays.asList("Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado", "Domingo");
        for (int i = 0; i < diasDaSemana.size(); i++) {
            FichaTreino ficha = fichas.get(i % 3);
            List<String> dias = ficha.getDiasDaSemana();
            if (dias == null) {
                dias = new ArrayList<>();
            }
            dias.add(diasDaSemana.get(i));
            ficha.setDiasDaSemana(dias);
        }

        // Salvar Fichas
        for (FichaTreino ficha : fichas) {
            fichaTreinoRepository.save(ficha);
        }

        System.out.println("Fichas criadas e atribuídas com sucesso!");
    }
}


