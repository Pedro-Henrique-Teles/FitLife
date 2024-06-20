package com.FitLife.models.repository;

import com.FitLife.models.Exercicios;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface ExerciciosRepository extends MongoRepository<Exercicios, String> {
    List<Exercicios> findByIdAluno (String idAluno);
    List<Exercicios> findByData (Date data);
    List<Exercicios> findByIntensidadeAndDuracaoGreaterThan (String intensidade, Duration duracao);
    List<Exercicios> findByIntensidade (String intensidade);
    List<Exercicios> findByDataBetween (Date dataInicio, Date dataFim);
    List<Exercicios> findByDataAfter (Date data);
    @Query(value = "{}", fields = "{ 'duracao' : 1 }")
    List<Exercicios> findAllDurations();
        @Aggregation("{ '$group': { '_id': '$intensidade', 'quantidade': { '$sum': 1 } } }")
        AggregationResults<Map> countStudentsByExerciseIntensity();
    }


