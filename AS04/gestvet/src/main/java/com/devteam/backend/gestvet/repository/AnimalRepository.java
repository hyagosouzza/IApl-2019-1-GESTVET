package com.devteam.backend.gestvet.repository;

import com.devteam.backend.gestvet.model.Animal;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface AnimalRepository extends Repository<Animal, Integer> {

    Animal save(Animal animal);

    List<Animal> findAll();

    Animal findById(Long id);

    void delete(Animal animal);
}
