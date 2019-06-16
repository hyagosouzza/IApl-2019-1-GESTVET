package com.devteam.backend.gestvet.security.services;

import com.devteam.backend.gestvet.model.Drug;
import com.devteam.backend.gestvet.repository.DrugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrugServiceImpl implements DrugService {
    @Autowired
    private DrugRepository repository;

    @Override
    public Drug create(Drug drug) {
        return repository.save(drug);
    }

    @Override
    public Drug delete(Long id) {
        Drug drug = findById(id);
        if(drug != null){
            repository.delete(drug);
        }
        return drug;
    }

    @Override
    public List<Drug> findAll() {
        return repository.findAll();
    }

    @Override
    public Drug findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Drug findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Drug update(Drug drug) {
        return repository.save(drug);
    }
}
