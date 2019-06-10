package com.devteam.backend.gestvet.repository;

import com.devteam.backend.gestvet.model.Medicamento;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface MedicamentoRepository  extends Repository<Medicamento, Integer> {

    Medicamento save(Medicamento medicamento);

    List<Medicamento> findAll();

    Medicamento findById(Long id);

    Medicamento findByNome(String nome);

    void delete(Medicamento medicamento);

}
