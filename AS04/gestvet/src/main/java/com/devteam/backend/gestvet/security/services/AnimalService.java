package com.devteam.backend.gestvet.security.services;

import com.devteam.backend.gestvet.model.Animal;

import java.util.List;

public interface AnimalService {
    Animal create(Animal animal);

    Animal delete(Long id);

    List<Animal> findAll();

    Animal findById(Long id);

    Animal upgrade(Animal animal);
}
