package com.devteam.backend.gestvet.security.services;

import com.devteam.backend.gestvet.model.Animal;
import com.devteam.backend.gestvet.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalServiceImpl implements AnimalService{
    @Autowired
    private AnimalRepository repository;

    @Override
    public Animal create(Animal animal) {
        return repository.save(animal);
    }

    @Override
    public Animal delete(Long id) {
        Animal animal = findById(id);
        if(animal != null){
            repository.delete(animal);
        }
        return animal;
    }

    @Override
    public List<Animal> findAll() {
        return repository.findAll();
    }

    @Override
    public Animal findById(Long id){
        return repository.findById(id);
    }

    @Override
    public Animal upgrade(Animal animal) {
        return repository.save(animal);
    }
}
