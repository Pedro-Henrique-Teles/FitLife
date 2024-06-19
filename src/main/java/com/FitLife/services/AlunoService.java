package com.FitLife.services;

import com.FitLife.models.Aluno;
import com.FitLife.models.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class AlunoService {

    @Autowired
    AlunoRepository alunoRepository;

    public Aluno cadastrarAluno(String nome, String sexo, int idade) {
        if (nome == null || nome.length() <= 3) {
            throw new IllegalArgumentException("Nome deve ter mais de 3 letras.");
        }
        if (!sexo.equals("F") && !sexo.equals("M")) {
            throw new IllegalArgumentException("Sexo deve ser 'F' ou 'M'.");
        }
        if (idade <= 10) {
            throw new IllegalArgumentException("Idade deve ser maior que 10 anos.");
        }

        Aluno novoAluno = new Aluno();
        novoAluno.setNome(nome);
        novoAluno.setSexo(sexo);
        novoAluno.setIdade(idade);

        // Salva o aluno no banco de dados
        alunoRepository.save(novoAluno);

        return novoAluno;
    }

    public void exibirAlunos() {
        List<Aluno> alunos = alunoRepository.findAll();
        for (int i = 0; i < alunos.size(); i++) {
            Aluno aluno = alunos.get(i);
            System.out.println((i + 1) + ") ID: " + aluno.getId() + " - Nome: " + aluno.getNome());
        }
    }



    public void removerAluno(Scanner le) {
        exibirAlunos();
        System.out.println();
        System.out.print("Digite o número do aluno que deseja remover: ");
        int numeroAluno = le.nextInt();

        List<Aluno> alunos = alunoRepository.findAll();
        if (numeroAluno < 1 || numeroAluno > alunos.size()) {
            System.out.println("Número inválido.");
            return;
        }

        Aluno alunoParaRemover = alunos.get(numeroAluno - 1);
        alunoRepository.deleteById(alunoParaRemover.getId());
        System.out.println("Aluno removido com sucesso.");
    }



    public void atualizarAluno(Scanner le) {
        exibirAlunos(); // Lista os Alunos
        System.out.println();
        System.out.print("Digite o número do aluno que deseja atualizar: ");
        int numeroAluno = le.nextInt();

        List<Aluno> alunos = alunoRepository.findAll();
        if (numeroAluno < 1 || numeroAluno > alunos.size()) {
            System.out.println("Número inválido.");
            return;
        }

        Aluno alunoParaAtualizar = alunos.get(numeroAluno - 1);

        System.out.println("Digite o novo nome do aluno ou pressione enter para manter o mesmo:");
        le.nextLine();
        String nome = le.nextLine();
        if (!nome.isEmpty()) {
            alunoParaAtualizar.setNome(nome);
        }

        System.out.println("Digite o novo sexo do aluno (F ou M) ou pressione enter para manter o mesmo:");
        String sexo = le.nextLine();
        if (!sexo.isEmpty() && (sexo.equals("F") || sexo.equals("M"))) {
            alunoParaAtualizar.setSexo(sexo);

        }

        System.out.println("Digite a nova idade do aluno ou digite '0' para manter a mesma:");
        int idade = le.nextInt();
        if (idade > 0) {
            alunoParaAtualizar.setIdade(idade);
        }

        // Salva as alterações
        alunoRepository.save(alunoParaAtualizar);

        System.out.println("Aluno atualizado com sucesso!");
    }



}
