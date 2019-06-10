package com.devteam.backend.gestvet.controller;

import com.devteam.backend.gestvet.model.Medicamento;
import com.devteam.backend.gestvet.security.services.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping({"/gestvet/medicamentos"})
public class MedicamentoController {
    @Autowired
    private MedicamentoService medicamentoService;

    @PostMapping
    public Medicamento create(@RequestBody Medicamento medicamento){
        return medicamentoService.create(medicamento);
    }

    @GetMapping(path = {"/{id}"})
    public Medicamento update(@PathVariable("id") Long id, @RequestBody Medicamento medicamento){
        return medicamentoService.update(medicamento);
    }

    @DeleteMapping(path = {"/{id}"})
    public Medicamento delete(@PathVariable("id") Long id){
        return medicamentoService.delete(id);
    }

    @GetMapping
    public List<Medicamento> findAll(){
        return medicamentoService.findAll();
    }
}
