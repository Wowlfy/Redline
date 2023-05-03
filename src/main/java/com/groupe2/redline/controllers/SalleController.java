package com.groupe2.redline.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/salles")

public class SalleController {
    @Autowired
    private SalleService salleService;

    @GetMapping("/get")
    public List<Salle> getSalles() {
        return null ;
    }
    @PostMapping("/add")
    public String addSalle(@RequestBody Salle salle) {
        return salleService.addSalle(salle);
        //TODO améliorer avec un response entity
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