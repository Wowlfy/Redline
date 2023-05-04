package com.groupe2.redline.controllers;

import com.groupe2.redline.entities.Salle;
import com.groupe2.redline.entities.Site;
import com.groupe2.redline.entities.Utilisateur;
import com.groupe2.redline.services.SalleService;
import com.groupe2.redline.services.UtilisateurService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @Operation(summary = "Modifier un site")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Site modifié"),
            @ApiResponse(responseCode = "500", description = "Erreur interne")
    })
    @PutMapping(value = "/{id}/edit", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <Salle> editSalle(@PathVariable Long id, @RequestBody Salle salle) throws EntityNotFoundException {
        return new ResponseEntity<>(salleService.editSalle(id, salle), HttpStatus.OK);
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