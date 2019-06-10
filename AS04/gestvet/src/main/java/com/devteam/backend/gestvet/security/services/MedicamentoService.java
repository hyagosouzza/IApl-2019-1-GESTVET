package com.devteam.backend.gestvet.security.services;

import com.devteam.backend.gestvet.model.Medicamento;

import java.util.List;

public interface MedicamentoService {

    Medicamento create(Medicamento medicamento);

    Medicamento delete(Long id);

    List<Medicamento> findAll();

    Medicamento findById(Long id);

    Medicamento findByNome(String nome);

    Medicamento update(Medicamento medicamento);

}
