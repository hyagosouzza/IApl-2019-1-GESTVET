package com.devteam.backend.gestvet.controller;

import com.devteam.backend.gestvet.model.Drug;
import com.devteam.backend.gestvet.security.services.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping({"/gestvet/drugs"})
public class DrugsController {
    @Autowired
    private DrugService drugService;

    @GetMapping
    public List<Drug> findAll(){
        return drugService.findAll();
    }
}
