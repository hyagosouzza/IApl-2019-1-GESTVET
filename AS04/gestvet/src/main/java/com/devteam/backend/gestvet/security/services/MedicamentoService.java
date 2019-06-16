package com.devteam.backend.gestvet.security.services;

import com.devteam.backend.gestvet.model.Drug;

import java.util.List;

public interface MedicamentoService {

    Drug create(Drug drug);

    Drug delete(Long id);

    List<Drug> findAll();

    Drug findById(Long id);

    Drug findByNome(String nome);

    Drug update(Drug drug);

}
