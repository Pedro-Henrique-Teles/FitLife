package com.FitLife.models.repository;

import com.FitLife.models.FichaTreino;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FichaTreinoRepository extends MongoRepository<FichaTreino, String> {
    FichaTreino save(FichaTreino fichaTreino);
    Optional<FichaTreino> findById(String id);
    List<FichaTreino> findAll();
    void deleteById(String id);


}
