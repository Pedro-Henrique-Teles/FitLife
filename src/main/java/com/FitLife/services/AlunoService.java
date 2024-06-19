package com.FitLife.services;

import com.FitLife.models.Aluno;
import com.FitLife.models.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
