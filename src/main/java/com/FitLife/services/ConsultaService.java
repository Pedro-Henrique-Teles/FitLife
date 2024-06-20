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
import java.util.*;

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

    public void buscarExerciciosPorSexo(Scanner le) {
        try {
            System.out.println("Digite o sexo dos alunos (M para masculino, F para feminino): ");
            String sexo = le.nextLine();
            System.out.println();

            if (!sexo.equalsIgnoreCase("M") && !sexo.equalsIgnoreCase("F")) {
                System.out.println("Erro: Sexo inválido. Por favor, insira M para masculino ou F para feminino.");
                System.out.println();
                return;
            }

            List<Aluno> alunos = alunoRepository.findBySexo(sexo);
            if (alunos.isEmpty()) {
                System.out.println("Erro: Não foram encontrados alunos do sexo " + sexo + ".");
                return;
            }

            boolean encontrouExercicios = false;
            for (Aluno aluno : alunos) {
                List<Exercicios> exercicios = exerciciosRepository.findByIdAluno(aluno.getId());
                if (!exercicios.isEmpty()) {
                    encontrouExercicios = true;
                    System.out.println("Aluno: " + aluno.getNome());
                    for (Exercicios exercicio : exercicios) {
                        System.out.println("  Exercício: " + exercicio.getNome());
                    }
                }
            }

            if (!encontrouExercicios) {
                System.out.println("Erro: Não foram encontrados exercícios realizados por alunos do sexo " + sexo + ".");
            }
        } catch (Exception e) {
            System.out.println("Erro: Ocorreu um erro ao buscar os exercícios. Por favor, tente novamente.");
        }
    }

    public void contarExerciciosPorAluno(Scanner le) {
        List<Aluno> alunos = alunoRepository.findAll();
        for (Aluno aluno : alunos) {
            List<Exercicios> exercicios = exerciciosRepository.findByIdAluno(aluno.getId());
            System.out.println("Aluno: " + aluno.getNome() + " - Número de exercícios realizados: " + exercicios.size());
        }
    }

    public void calcularMediaDuracaoPorIntensidade(Scanner le) {
        List<Exercicios> exercicios = exerciciosRepository.findAll();

        Map<String, Long> totalDuracaoPorIntensidade = new HashMap<>();
        Map<String, Integer> contagemPorIntensidade = new HashMap<>();

        for (Exercicios exercicio : exercicios) {
            String intensidade = exercicio.getIntensidade();
            long duracao = exercicio.getDuracao().getSeconds();

            totalDuracaoPorIntensidade.put(intensidade, totalDuracaoPorIntensidade.getOrDefault(intensidade, 0L) + duracao);
            contagemPorIntensidade.put(intensidade, contagemPorIntensidade.getOrDefault(intensidade, 0) + 1);
        }

        for (Map.Entry<String, Long> entry : totalDuracaoPorIntensidade.entrySet()) {
            String intensidade = entry.getKey();
            long totalDuracao = entry.getValue();
            int contagem = contagemPorIntensidade.get(intensidade);

            long mediaDuracao = totalDuracao / contagem;
            Duration mediaDuracaoDuration = Duration.ofSeconds(mediaDuracao);

            System.out.println("Intensidade: " + intensidade + " - Média de duração: " + mediaDuracaoDuration.toHours() + " horas, " + mediaDuracaoDuration.toMinutesPart() + " minutos e " + mediaDuracaoDuration.toSecondsPart() + " segundos.");
        }
    }

    public void listarExerciciosPorAlunosAcima30(Scanner le) {
        List<Aluno> alunos = alunoRepository.findByIdadeGreaterThan(30);
        if (alunos.isEmpty()) {
            System.out.println("Não há alunos com idade superior a 30 anos.");
            return;
        }

        boolean encontrouExercicios = false;
        for (Aluno aluno : alunos) {
            List<Exercicios> exercicios = exerciciosRepository.findByIdAluno(aluno.getId());
            if (!exercicios.isEmpty()) {
                encontrouExercicios = true;
                System.out.println("Aluno: " + aluno.getNome());
                for (Exercicios exercicio : exercicios) {
                    System.out.println("  Exercício: " + exercicio.getNome());
                }
            }
        }

        if (!encontrouExercicios) {
            System.out.println("Não foram encontrados exercícios realizados por alunos com idade superior a 30 anos.");
        }
    }

    @SneakyThrows
    public void buscarExerciciosPorDataInscricao(Scanner le) {
        try {
            System.out.println("Digite a data no formato dd/MM/yyyy: ");
            String dataStr = le.nextLine();
            Date data = new SimpleDateFormat("dd/MM/yyyy").parse(dataStr);
            System.out.println();

            List<Aluno> alunos = alunoRepository.findAll();
            boolean encontrouAlunos = false;
            for (Aluno aluno : alunos) {
                if (aluno.getDataInscricao() != null && aluno.getDataInscricao().after(data)) {
                    List<Exercicios> exercicios = exerciciosRepository.findByIdAluno(aluno.getId());
                    if (!exercicios.isEmpty()) {
                        encontrouAlunos = true;
                        System.out.println("Aluno: " + aluno.getNome());
                        for (Exercicios exercicio : exercicios) {
                            System.out.println("  Exercício: " + exercicio.getNome());
                        }
                    }
                }
            }

            if (!encontrouAlunos) {
                System.out.println("Não há alunos que se inscreveram após a data especificada.");
            }
        } catch (ParseException e) {
            System.out.println("Erro: O formato da data é inválido. Por favor, insira a data no formato dd/MM/yyyy.");
        }
    }

    public void listarExerciciosPorInicialNomeAluno(Scanner le) {
        System.out.println("Digite a letra inicial do nome dos alunos: ");
        String letraInicial = le.nextLine().toUpperCase();

        List<Aluno> alunos = alunoRepository.findAll();
        boolean encontrouAlunos = false;
        for (Aluno aluno : alunos) {
            if (aluno.getNome().toUpperCase().startsWith(letraInicial)) {
                encontrouAlunos = true;
                List<Exercicios> exercicios = exerciciosRepository.findByIdAluno(aluno.getId());
                if (!exercicios.isEmpty()) {
                    System.out.println("Aluno: " + aluno.getNome());
                    for (Exercicios exercicio : exercicios) {
                        System.out.println("  Exercício: " + exercicio.getNome());
                    }
                }
            }
        }

        if (!encontrouAlunos) {
            System.out.println("Não foram encontrados alunos cujo nome começa com a letra '" + letraInicial + "'.");
        }
    }

    public void contarExerciciosPorDiaNaUltimaSemana(Scanner le) {
        Calendar calendar = Calendar.getInstance();
        Date hoje = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, -7);
        Date umaSemanaAtras = calendar.getTime();

        List<Exercicios> exercicios = exerciciosRepository.findByDataBetween(umaSemanaAtras, hoje);

        if (exercicios.isEmpty()) {
            System.out.println("Não foram realizados exercícios na última semana.");
            return;
        }

        Map<Date, Integer> contagemPorDia = new HashMap<>();
        for (Exercicios exercicio : exercicios) {
            Date data = exercicio.getData();
            contagemPorDia.put(data, contagemPorDia.getOrDefault(data, 0) + 1);
        }

        for (Map.Entry<Date, Integer> entry : contagemPorDia.entrySet()) {
            Date data = entry.getKey();
            int contagem = entry.getValue();
            System.out.println("Data: " + data + " - Número de exercícios realizados: " + contagem);
        }
    }

    public void exibirExercicioMaisComum(Scanner le) {
        List<Exercicios> exercicios = exerciciosRepository.findAll();

        if (exercicios.isEmpty()) {
            System.out.println("Não foram realizados exercícios.");
            return;
        }

        Map<String, Integer> contagemPorExercicio = new HashMap<>();
        for (Exercicios exercicio : exercicios) {
            String nomeExercicio = exercicio.getNome();
            contagemPorExercicio.put(nomeExercicio, contagemPorExercicio.getOrDefault(nomeExercicio, 0) + 1);
        }

        String exercicioMaisComum = null;
        int maxContagem = 0;
        for (Map.Entry<String, Integer> entry : contagemPorExercicio.entrySet()) {
            String nomeExercicio = entry.getKey();
            int contagem = entry.getValue();
            if (contagem > maxContagem) {
                exercicioMaisComum = nomeExercicio;
                maxContagem = contagem;
            }
        }

        System.out.println("O exercício mais comum realizado pelos alunos é: " + exercicioMaisComum);
    }
}


