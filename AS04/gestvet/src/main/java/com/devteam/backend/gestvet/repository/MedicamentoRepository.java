package com.devteam.backend.gestvet.repository;

import com.devteam.backend.gestvet.model.Drug;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface MedicamentoRepository  extends Repository<Drug, Integer> {

    Drug save(Drug drug);

    List<Drug> findAll();

    Drug findById(Long id);

    Drug findByNome(String nome);

    void delete(Drug drug);

}
