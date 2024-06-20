package com.FitLife.models.repository;

import com.FitLife.models.Aluno;

import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface AlunoRepository extends MongoRepository<Aluno, String> {
    Aluno findByNome (String nome);
    Optional<Aluno> findById (String id);
    List<Aluno> findBySexo (String sexo);
    List<Aluno> findByIdadeGreaterThan (int idade);

    @Aggregation("{ '$group': { '_id': null, 'mediaIdade': { '$avg': '$idade' } } }")
    AggregationResults<Map> findIdadeMedia();

    @Aggregation("{ '$group': { '_id': '$sexo', 'quantidade': { '$sum': 1 } } }")
    AggregationResults<Map> countBySexo();

    @Aggregation(pipeline = { "{ '$sort': { 'idade': -1 } }", "{ '$limit': 1 }" })
    AggregationResults<Aluno> findAlunoMaisVelho();

    @Aggregation("{ '$bucket': { 'groupBy': '$idade', 'boundaries': [ 0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100 ], 'default': 'Outros', 'output': { 'quantidade': { '$sum': 1 } } } }")
    AggregationResults<Map> countStudentsByAgeRange();

}

