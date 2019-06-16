package com.devteam.backend.gestvet.controller;

import com.devteam.backend.gestvet.model.Drug;
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
    public Drug create(@RequestBody Drug drug){
        return medicamentoService.create(drug);
    }

    @GetMapping(path = {"/{id}"})
    public Drug update(@PathVariable("id") Long id, @RequestBody Drug drug){
        return medicamentoService.update(drug);
    }

    @DeleteMapping(path = {"/{id}"})
    public Drug delete(@PathVariable("id") Long id){
        return medicamentoService.delete(id);
    }

    @GetMapping
    public List<Drug> findAll(){
        return medicamentoService.findAll();
    }
}
