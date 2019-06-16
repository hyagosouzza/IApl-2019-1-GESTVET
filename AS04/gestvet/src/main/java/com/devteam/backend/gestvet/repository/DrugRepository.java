package com.devteam.backend.gestvet.repository;

import com.devteam.backend.gestvet.model.Drug;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface DrugRepository extends Repository<Drug, Integer> {

    Drug save(Drug drug);

    List<Drug> findAll();

    Drug findById(Long id);

    Drug findByName(String name);

    void delete(Drug drug);

}
