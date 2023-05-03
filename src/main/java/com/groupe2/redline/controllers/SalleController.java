package com.groupe2.redline.controllers;

import com.groupe2.redline.entities.Salle;
import com.groupe2.redline.entities.Utilisateur;
import com.groupe2.redline.services.SalleService;
import com.groupe2.redline.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/salle")

public class SalleController {
    @Autowired
    private SalleService salleService;

    @Autowired
    private UtilisateurService utilisateurService;

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
    public String getSalleById(@PathVariable Long id) {
        return "le fichier a bien été obtenu";
    }

    @PutMapping("/update/{id}")
    public String updateSalleById(@PathVariable Long id) {
        return "le fichier a bien été modifié";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteSalleById(@PathVariable Long id) {
        return "le fichier a bien été supprimé";
    }

    @PostMapping("/get/{id}/reserver")
    public ResponseEntity<String> reserver(@PathVariable Long id, @RequestParam Date date, @RequestParam int creneau, @RequestParam Long idUtilisateur) {
        // TODO Associer à une demande (argument optionnel)
        // TODO Récupérer automatiquement l'utilisateur connecté (nécessite d'implémenter l'authentification)

        // Récupérer les entités mentionnées dans la requête
        Optional<Salle> salle = salleService.findById(id);
        Optional<Utilisateur> auteur = utilisateurService.findById(idUtilisateur);


        // Contrôler les informations fournies, et tenter d'enregistrer la réservation
        if (salle.isPresent()) {
            if (auteur.isPresent()) {
                // La requête est ok, tenter de réserver
                if (salleService.reserver(salle.get(), date, creneau, auteur.get())) {
                    return ResponseEntity.status(201).body("Réservation enregistrée.");
                }
                return ResponseEntity.status(400).body("Une réservation existe déjà sur ce créneau.");
            }
            return ResponseEntity.status(404).body("L'utilisateur spécifié n'existe pas.");
        }
        return ResponseEntity.status(404).body("La salle spécifiée n'existe pas.");
    }
}