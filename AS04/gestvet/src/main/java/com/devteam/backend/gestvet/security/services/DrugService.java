package com.devteam.backend.gestvet.security.services;

import com.devteam.backend.gestvet.model.Drug;

import java.util.List;

public interface DrugService {

    Drug create(Drug drug);

    Drug delete(Long id);

    List<Drug> findAll();

    Drug findById(Long id);

    Drug findByName(String name);

    Drug update(Drug drug);

}
