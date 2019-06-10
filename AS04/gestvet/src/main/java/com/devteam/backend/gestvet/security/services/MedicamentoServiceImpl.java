package com.devteam.backend.gestvet.security.services;

import com.devteam.backend.gestvet.model.Medicamento;
import com.devteam.backend.gestvet.repository.MedicamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicamentoServiceImpl implements MedicamentoService {
    @Autowired
    private MedicamentoRepository repository;

    @Override
    public Medicamento create(Medicamento medicamento) {
        return repository.save(medicamento);
    }

    @Override
    public Medicamento delete(Long id) {
        Medicamento medicamento = findById(id);
        if(medicamento != null){
            repository.delete(medicamento);
        }
        return medicamento;
    }

    @Override
    public List<Medicamento> findAll() {
        return repository.findAll();
    }

    @Override
    public Medicamento findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Medicamento findByNome(String nome) {
        return repository.findByNome(nome);
    }

    @Override
    public Medicamento update(Medicamento medicamento) {
        return repository.save(medicamento);
    }
}
