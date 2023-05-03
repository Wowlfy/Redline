package com.groupe2.redline.controllers;
import com.groupe2.redline.entities.Salle;
import com.groupe2.redline.services.SalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salle")

public class SalleController {
    @Autowired
    private SalleService salleService;

    public SalleController(SalleService salleService) {
        this.salleService = salleService;
    }
    @GetMapping("/get")
    public ResponseEntity<List<Salle>> getSalles() {
        List<Salle> salles = this.salleService.getAllSalles();
        return ResponseEntity.ok(salles);
    }
    @PostMapping("/add")
    public String addSalle(@RequestBody Salle salle) {
        return salleService.addSalle(salle);

    }
    @GetMapping("/get/{id}")
    public String getSalleById(@PathVariable Long id){return "le fichier a bien été obtenu";}
    @PutMapping("/update/{id}")
    public String updateSalleById(@PathVariable Long id) {
        return "le fichier a bien été modifié";
    }
    @DeleteMapping("/delete/{id}")
    public String deleteSalleById(@PathVariable Long id) {
        return "le fichier a bien été supprimé";
    }

}