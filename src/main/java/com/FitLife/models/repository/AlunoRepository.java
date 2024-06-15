package com.FitLife.models.repository;

import com.FitLife.models.Aluno;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlunoRepository extends MongoRepository<Aluno, Long> {
    Aluno findByNome (String nome);
    Optional<Aluno> findById (Long id);
    List<Aluno> findBySexo (String sexo);
    List<Aluno> findByIdadeGreaterThan (int idade);

}
