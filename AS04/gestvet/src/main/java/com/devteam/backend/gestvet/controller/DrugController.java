package com.devteam.backend.gestvet.controller;

import com.devteam.backend.gestvet.model.Drug;
import com.devteam.backend.gestvet.security.services.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping({"/gestvet/drug"})
public class DrugController {
    @Autowired
    private DrugService drugService;

    @PostMapping
    public Drug create(@RequestBody Drug drug){
        return drugService.create(drug);
    }

    @PutMapping(path = {"/{id}"})
    public Drug upgrade(@PathVariable("id") Long id, @RequestBody Drug drug) {
        drug.setId(id);
        return drugService.update(drug);
    }

    @GetMapping(path = {"/{id}"})
    public Drug findById(@PathVariable("id") Long id) {
        return drugService.findById(id);
    }

    @DeleteMapping(path = {"/{id}"})
    public Drug delete(@PathVariable("id") Long id){
        return drugService.delete(id);
    }

}
