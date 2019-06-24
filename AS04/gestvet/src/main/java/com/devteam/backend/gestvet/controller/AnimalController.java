package com.devteam.backend.gestvet.controller;

import com.devteam.backend.gestvet.model.Animal;
import com.devteam.backend.gestvet.model.ApiError;
import com.devteam.backend.gestvet.security.services.AnimalService;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Calendar;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping({"/gestvet/animals"})
public class AnimalController {
    @Autowired
    private AnimalService animalService;

    @PostMapping
    public Animal create(@RequestBody Animal animal) {
        return animalService.create(animal);
    }

    @PutMapping(path = {"/{id}"})
    public Animal upgrade(@PathVariable("id") Long id, @RequestBody Animal animal) {
        animal.setId(id);
        return animalService.upgrade(animal);
    }

    @GetMapping(path = {"/{id}"})
    public Animal findById(@PathVariable("id") Long id) {
        return animalService.findById(id);
    }

    @DeleteMapping(path = {"/{id}"})
    public Animal delete(@PathVariable("id") Long id) {
        return animalService.delete(id);
    }

    @GetMapping
    public List<Animal> findAll() {
        return animalService.findAll();
    }

    @ExceptionHandler({TypeMismatchException.class})
    public ResponseEntity<Object> handleTypeMismatch(
            TypeMismatchException ex, WebRequest request) {
        String error =
                ex.getPropertyName() + " deve ser do tipo " + ex.getRequiredType().getName();

        ApiError apiError =
                new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
        return new ResponseEntity<Object>(
                apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleGeneric(Exception ex, WebRequest request) {
        String error = "Erro inesperado no servidor.";

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }
}
