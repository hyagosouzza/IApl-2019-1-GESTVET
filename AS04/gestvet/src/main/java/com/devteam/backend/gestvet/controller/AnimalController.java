package com.devteam.backend.gestvet.controller;

import com.devteam.backend.gestvet.model.Animal;
import com.devteam.backend.gestvet.security.services.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping({"/gestvet/animais"})
public class AnimalController {
    @Autowired
    private AnimalService animalService;

    @PostMapping
    public Animal create(@RequestBody Animal animal){
        return animalService.create(animal);
    }

    @PutMapping(path = {"/{id}"})
    public Animal upgrade(@PathVariable("id") Long id, @RequestBody Animal animal){
        animal.setId(id);
        return animalService.upgrade(animal);
    }

    @GetMapping(path = {"/{id}"})
    public Animal findById(@PathVariable("id") Long id){
        return animalService.findById(id);
    }

    @DeleteMapping(path = {"/{id}"})
    public Animal delete(@PathVariable("id") Long id){
        return animalService.delete(id);
    }

    @GetMapping
    public List<Animal> findAll(){
        return animalService.findAll();
    }
}
