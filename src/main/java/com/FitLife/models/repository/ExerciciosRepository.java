package com.FitLife.models.repository;

import com.FitLife.models.Exercicios;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.Duration;
import java.util.Date;
import java.util.List;

public interface ExerciciosRepository extends MongoRepository<Exercicios, Long> {
    List<Exercicios> findByIdAluno (Long idAluno);
    List<Exercicios> findByData (Date data);
    List<Exercicios> findByIntensidadeAnAndAndDuracaoGreaterThan (String intensidade, Duration duracao);
    List<Exercicios> findByIntensidade (String intensidade);
    List<Exercicios> findByDataBetween (Date dataInicio, Date dataFim);
    List<Exercicios> findByDataAfter (Date data);

}
