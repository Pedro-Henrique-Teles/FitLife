package com.FitLife.models.repository;

import com.FitLife.models.Exercicios;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.Date;
import java.util.List;

@Repository
public interface ExerciciosRepository extends MongoRepository<Exercicios, String> {
    List<Exercicios> findByIdAluno (String idAluno);
    List<Exercicios> findByData (Date data);
    List<Exercicios> findByIntensidadeAndDuracaoGreaterThan (String intensidade, Duration duracao);
    List<Exercicios> findByIntensidade (String intensidade);
    List<Exercicios> findByDataBetween (Date dataInicio, Date dataFim);
    List<Exercicios> findByDataAfter (Date data);

}
