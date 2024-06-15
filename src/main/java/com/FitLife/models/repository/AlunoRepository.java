package com.FitLife.models.repository;

import com.FitLife.models.Aluno;
import com.FitLife.models.Exercicios;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AlunoRepository extends MongoRepository<Aluno, Long> {
    Aluno findByNome (String nome);
    Aluno findByIdAluno (Long id);
    List<Aluno> findBySexo (String sexo);
    List<Aluno> findByIdadeGreaterThan (int idade);

}
